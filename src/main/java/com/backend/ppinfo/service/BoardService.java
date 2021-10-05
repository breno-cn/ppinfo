package com.backend.ppinfo.service;

import com.backend.ppinfo.exception.error.GeneralException;
import com.backend.ppinfo.relational.entity.Board;
import com.backend.ppinfo.relational.entity.BoardUser;
import com.backend.ppinfo.relational.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.backend.ppinfo.exception.error.ErrorCode.BOARD_NAME_ALREADY_EXISTS;
import static com.backend.ppinfo.exception.error.ErrorCode.BOARD_NOT_FOUND;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardUserService boardUserService;

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new GeneralException(BOARD_NOT_FOUND));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board createBoard(Board board, String username) {
        BoardUser user = boardUserService.findByUsername(username);
        board.setBoardUser(user);

        if (boardRepository.checkIfNameExists(board.getName())) {
            throw new GeneralException(BOARD_NAME_ALREADY_EXISTS);
        }

        return boardRepository.save(board);
    }

}
