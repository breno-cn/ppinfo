package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.PostRequest;
import com.backend.ppinfo.dto.data.PostResponse;
import com.backend.ppinfo.dto.mapper.PostMapper;
import com.backend.ppinfo.service.PostService;
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
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    private final PostMapper postMapper;

//    TODO: paginação e sorting
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<PostResponse>> getPostsFromBoard(@PathVariable(name = "boardId") Long boardId) {
        var response = postService.findPostsByBoardId(boardId)
                .stream()
                .map(postMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(response);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<PostResponse> createBoard(@Valid @RequestBody PostRequest postRequest,
                                                    @PathVariable(name = "boardId") Long boardId,
                                                    Authentication authentication) {
        var username = authentication.getName();
        var post = postMapper.requestToEntity(postRequest);
        var created = postService.createPost(post, boardId, username);
        var response = postMapper.entityToResponse(created);

        return ResponseEntity
                .status(CREATED)
                .body(response);
    }

}
