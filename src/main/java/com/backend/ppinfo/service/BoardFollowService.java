package com.backend.ppinfo.service;

import com.backend.ppinfo.relational.entity.BoardFollow;
import com.backend.ppinfo.relational.repository.BoardFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardFollowService {

    private final BoardFollowRepository boardFollowRepository;

    private final BoardUserService boardUserService;

    private final BoardService boardService;

    public void followBoard(String username, Long boardId) {
        var user = boardUserService.findByUsername(username);
        var followOptional = boardFollowRepository
                .findByBoardIdAndBoardUserId(boardId, user.getId());

        if (followOptional.isPresent()) {
            boardFollowRepository.deleteById(followOptional.get().getId());
            return;
        }

        var board = boardService.findById(boardId);
        var boardFollow = new BoardFollow();

        boardFollow.setBoard(board);
        boardFollow.setBoardUser(user);
        boardFollowRepository.save(boardFollow);
    }

}
