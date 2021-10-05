package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.JwtRequest;
import com.backend.ppinfo.dto.data.JwtResponse;
import com.backend.ppinfo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtResponse> getAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        var username = jwtRequest.getUsername();
        var password = jwtRequest.getPassword();

        authenticationService.authenticate(username, password);

        var token = authenticationService.createAuthenticationToken(jwtRequest);
        var response = new JwtResponse(token);

        return ResponseEntity
                .ok(response);
    }

}
