package com.backend.ppinfo.dto.mapper;

import com.backend.ppinfo.dto.data.BoardRequest;
import com.backend.ppinfo.dto.data.BoardResponse;
import com.backend.ppinfo.relational.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardResponse entityToResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public Board requestToEntity(BoardRequest request) {
        return Board.builder()
                .id(null)
                .name(request.getName())
                .description(request.getDescription())
                .createdAt(null)
                .build();
    }

}
