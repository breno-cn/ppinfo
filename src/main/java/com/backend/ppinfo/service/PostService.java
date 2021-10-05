package com.backend.ppinfo.service;

import com.backend.ppinfo.relational.entity.Board;
import com.backend.ppinfo.relational.entity.BoardUser;
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

    private final BoardService boardService;

    private final BoardUserService boardUserService;

    public List<Post> findPostsByBoardId(Long boardId) {
        return postRepository.findAllByBoardId(boardId);
    }

    public Post createPost(Post post, Long boardId, String username) {
        Board board = boardService.findById(boardId);
        BoardUser user = boardUserService.findByUsername(username);

        post.setBoard(board);
        post.setBoardUser(user);

        return postRepository.save(post);
    }

}
