package com.backend.ppinfo.config.security.jwt;

import com.backend.ppinfo.exception.error.GeneralException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.backend.ppinfo.exception.error.ErrorCode.GENERAL_ERROR;
import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    JwtUtils jwtUtils;

    JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (isNull(requestTokenHeader)) {
            log.info("Unauthenticated request");
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = null;
        if (!requestTokenHeader.startsWith("Bearer ")) {
            log.warn("JWT does not start with 'Bearer'.");
            throw new GeneralException(GENERAL_ERROR);
        } else {
            jwtToken = requestTokenHeader.substring(7);
        }

        String username = null;
        try {
            username = jwtUtils.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            log.warn("Could not generate JWT token");
            chain.doFilter(request, response);
            return;
        } catch (ExpiredJwtException e) {
            log.warn("JWT has expired.");
            chain.doFilter(request, response);
            return;
        }

//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (isNull(username) || isNull(authentication)) {
////            throw new GeneralException(GENERAL_ERROR);
//        }

        var userDetails = jwtUserDetailsService.loadUserByUsername(username);

        if (!jwtUtils.validateToken(jwtToken, userDetails)) {
            throw new GeneralException(GENERAL_ERROR);
        }

        var usernameAndPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        usernameAndPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernameAndPasswordAuthenticationToken);

        chain.doFilter(request, response);
    }

}
