package com.backend.ppinfo.dto.mapper;

import com.backend.ppinfo.dto.data.PostRequest;
import com.backend.ppinfo.dto.data.PostResponse;
import com.backend.ppinfo.relational.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostMapper {

    private final BoardUserMapper boardUserMapper;

    public PostResponse entityToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .creator(boardUserMapper.entityToResponse(post.getBoardUser()))
                .build();
    }

    public Post requestToEntity(PostRequest postRequest) {
        return Post.builder()
                .id(null)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .createdAt(null)
                .build();
    }

}
