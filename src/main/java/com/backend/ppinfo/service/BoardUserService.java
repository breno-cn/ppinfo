package com.backend.ppinfo.service;

import com.backend.ppinfo.exception.error.GeneralException;
import com.backend.ppinfo.relational.entity.BoardUser;
import com.backend.ppinfo.relational.repository.BoardUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.backend.ppinfo.exception.error.ErrorCode.EMAIL_ALREADY_EXISTS;
import static com.backend.ppinfo.exception.error.ErrorCode.USERNAME_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;

    private final PasswordEncoder passwordEncoder;

    public BoardUser createUser(BoardUser boardUser) {
        if (boardUserRepository.checkIfUsernameExists(boardUser.getUsername())) {
            throw new GeneralException(USERNAME_ALREADY_EXISTS);
        }

        if (boardUserRepository.checkIfEmailExists(boardUser.getEmail())) {
            throw new GeneralException(EMAIL_ALREADY_EXISTS);
        }

        boardUser.setPassword(passwordEncoder.encode(boardUser.getPassword()));

        return boardUserRepository.save(boardUser);
    }

}
