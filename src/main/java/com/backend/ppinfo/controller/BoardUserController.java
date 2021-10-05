package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.BoardUserRequest;
import com.backend.ppinfo.dto.data.BoardUserResponse;
import com.backend.ppinfo.dto.data.JwtRequest;
import com.backend.ppinfo.dto.data.JwtResponse;
import com.backend.ppinfo.dto.mapper.BoardUserMapper;
import com.backend.ppinfo.service.BoardUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class BoardUserController {

    private final BoardUserService boardUserService;

    private final BoardUserMapper boardUserMapper;

    @PostMapping("/register")
    public ResponseEntity<BoardUserResponse> registerBoardUser(@RequestBody BoardUserRequest boardUserRequest) {
        var boardUser = boardUserMapper.requestToEntity(boardUserRequest);
        var createdUser = boardUserService
                .createUser(boardUser);
        var response = boardUserMapper.entityToResponse(createdUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



}
