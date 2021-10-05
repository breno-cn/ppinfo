package com.backend.ppinfo.controller;

import com.backend.ppinfo.dto.data.PostResponse;
import com.backend.ppinfo.dto.mapper.PostMapper;
import com.backend.ppinfo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    private final PostMapper postMapper;

//    TODO: paginação e sorting
    @GetMapping("/board/{boardId}")
    ResponseEntity<List<PostResponse>> getPostsFromBoard(@PathVariable(name = "boardId") Long boardId) {
        var response = postService.findPostsByBoardId(boardId)
                .stream()
                .map(postMapper::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(response);
    }

}
