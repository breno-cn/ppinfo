package com.backend.ppinfo.controller;

import com.backend.ppinfo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/like")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikeController {

    private final LikeService likeService;

    @PatchMapping("/post/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable(name = "postId") Long postId, Authentication authentication) {
        var username = authentication.getName();

        likeService.likePost(postId, username);

        return ResponseEntity
                .noContent()
                .build();
    }

}
