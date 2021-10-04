package com.backend.ppinfo.dto.mapper;

import com.backend.ppinfo.dto.data.BoardUserRequest;
import com.backend.ppinfo.dto.data.BoardUserResponse;
import com.backend.ppinfo.relational.entity.BoardUser;
import org.springframework.stereotype.Component;

@Component
public class BoardUserMapper {

    public BoardUser requestToEntity(BoardUserRequest boardUserRequest) {
        return BoardUser.builder()
                .id(null)
                .username(boardUserRequest.getUsername())
                .email(boardUserRequest.getEmail())
                .password(boardUserRequest.getPassword())
                .firstname(boardUserRequest.getFirstname())
                .lastname(boardUserRequest.getLastname())
                .createdAt(null)
                .isBanned(false)
                .build();
    }

    public BoardUserResponse entityToResponse(BoardUser boardUser) {
        return BoardUserResponse.builder()
                .id(boardUser.getId())
                .username(boardUser.getUsername())
                .firstname(boardUser.getFirstname())
                .lastname(boardUser.getLastname())
                .createdAt(boardUser.getCreatedAt())
                .isBanned(boardUser.getIsBanned())
                .build();
    }

}
