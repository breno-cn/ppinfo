package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.CommentRequest;
import com.backend.ppinfo.dto.data.CommentResponse;
import com.backend.ppinfo.dto.mapper.CommentMapper;
import com.backend.ppinfo.service.CommentService;
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
@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        var response = commentService.findByPostId(postId)
                .stream()
                .map(commentMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(response);
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable(name = "postId") Long postId,
                                                         @Valid @RequestBody CommentRequest commentRequest,
                                                         Authentication authentication) {
        var username = authentication.getName();
        var comment = commentMapper.requestToEntity(commentRequest);
        var created = commentService.createComment(comment, postId, username);
        var response = commentMapper.entityToResponse(created);

        return ResponseEntity
                .status(CREATED)
                .body(response);
    }

}
