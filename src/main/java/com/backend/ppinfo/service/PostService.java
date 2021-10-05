package com.backend.ppinfo.service;

import com.backend.ppinfo.relational.entity.Post;
import com.backend.ppinfo.relational.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findPostsByBoardId(Long boardId) {
        return postRepository.findAllByBoardId(boardId);
    }

}
