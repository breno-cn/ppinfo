package com.backend.ppinfo.service;

import com.backend.ppinfo.relational.entity.BoardUser;
import com.backend.ppinfo.relational.entity.Comment;
import com.backend.ppinfo.relational.entity.Post;
import com.backend.ppinfo.relational.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final CommentRepository commentRepository;

    private final BoardUserService boardUserService;

    private final PostService postService;

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment createComment(Comment comment, Long postId, String username) {
        var user = boardUserService.findByUsername(username);
        var post = postService.findById(postId);

        comment.setBoardUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

}
