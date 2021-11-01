package com.backend.ppinfo.relational.repository;

import com.backend.ppinfo.relational.entity.BoardFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardFollowRepository extends JpaRepository<BoardFollow, Long> {

    Optional<BoardFollow> findByBoardIdAndBoardUserId(Long boardId, Long boardUserId);

}
