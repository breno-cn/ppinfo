package com.backend.ppinfo.config.security.jwt;

import com.backend.ppinfo.dto.data.JwtRequest;
import com.backend.ppinfo.exception.error.GeneralException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.backend.ppinfo.exception.error.ErrorCode.GENERAL_ERROR;

@AllArgsConstructor
public class JwtRequestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final ObjectMapper mapper = new ObjectMapper();

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            var jwtRequest = mapper.readValue(request.getInputStream(), JwtRequest.class);
            var username = jwtRequest.getUsername();
            var password = jwtRequest.getPassword();
            var token = new UsernamePasswordAuthenticationToken(username, password);

            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new GeneralException(GENERAL_ERROR);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain filterChain, Authentication authentication)
            throws IOException, ServletException {
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

}
