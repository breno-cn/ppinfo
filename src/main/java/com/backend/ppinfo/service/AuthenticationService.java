package com.backend.ppinfo.service;

import com.backend.ppinfo.config.security.jwt.JwtUserDetailsService;
import com.backend.ppinfo.config.security.jwt.JwtUtils;
import com.backend.ppinfo.dto.data.JwtRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final JwtUtils jwtUtils;

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }
    }

    public String createAuthenticationToken(JwtRequest jwtRequest) {
        var username = jwtRequest.getUsername();
        var userDetails = jwtUserDetailsService.loadUserByUsername(username);

        return jwtUtils.generateToken(userDetails);
    }

}
