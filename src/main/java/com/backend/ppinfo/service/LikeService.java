package com.backend.ppinfo.service;

import com.backend.ppinfo.relational.entity.PostLike;
import com.backend.ppinfo.relational.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikeService {

    private final PostService postService;

    private final BoardUserService boardUserService;

    private final PostLikeRepository postLikeRepository;

    public void likePost(Long postId, String username) {
        var likeOptional = postLikeRepository.findByPostId(postId);

        if (likeOptional.isPresent()) {
            postLikeRepository.deleteById(likeOptional.get().getId());
            return;
        }

        var user = boardUserService.findByUsername(username);
        var post = postService.findById(postId);
        var like = new PostLike();

        like.setPost(post);
        like.setBoardUser(user);
        postLikeRepository.save(like);
    }

}
