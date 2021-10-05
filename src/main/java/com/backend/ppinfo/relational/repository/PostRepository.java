package com.backend.ppinfo.relational.repository;

import com.backend.ppinfo.relational.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByBoardId(Long boardId);

}
