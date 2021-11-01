package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.BoardRequest;
import com.backend.ppinfo.dto.data.BoardResponse;
import com.backend.ppinfo.dto.data.FeedResponse;
import com.backend.ppinfo.dto.mapper.BoardMapper;
import com.backend.ppinfo.relational.entity.BoardFollow;
import com.backend.ppinfo.service.BoardFollowService;
import com.backend.ppinfo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardController {

    private final BoardService boardService;

    private final BoardFollowService boardFollowService;

    private final BoardMapper boardMapper;

//    TODO: paginação e sorting
    @GetMapping
    public ResponseEntity<List<BoardResponse>> findAll() {
        var boards = boardService.findAll()
                .stream()
                .map(boardMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(boards);
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@Valid @RequestBody BoardRequest boardRequest,
                                                     Authentication authentication) {
        var username = authentication.getName();
        var board = boardMapper.requestToEntity(boardRequest);
        var createdBoard = boardService.createBoard(board, username);
        var response = boardMapper.entityToResponse(createdBoard);

        return ResponseEntity
                .status(CREATED)
                .body(response);
    }

    @PatchMapping(path = "/{boardId}")
    public ResponseEntity<Void> followBoard(Authentication authentication,
                                            @PathVariable(name = "boardId") Long boardId) {
        var username = authentication.getName();

        boardFollowService.followBoard(username, boardId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
