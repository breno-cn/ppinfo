package com.backend.ppinfo.dto.mapper;

import com.backend.ppinfo.dto.data.BoardRequest;
import com.backend.ppinfo.dto.data.BoardResponse;
import com.backend.ppinfo.relational.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardMapper {

    private final BoardUserMapper boardUserMapper;

    public BoardResponse entityToResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .creator(boardUserMapper.entityToResponse(board.getBoardUser()))
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
