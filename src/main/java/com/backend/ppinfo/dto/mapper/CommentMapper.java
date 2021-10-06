package com.backend.ppinfo.dto.mapper;

import com.backend.ppinfo.dto.data.CommentRequest;
import com.backend.ppinfo.dto.data.CommentResponse;
import com.backend.ppinfo.relational.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentMapper {

    private final BoardUserMapper boardUserMapper;

    public CommentResponse entityToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .creator(boardUserMapper.entityToResponse(comment.getBoardUser()))
                .build();
    }

    public Comment requestToEntity(CommentRequest commentRequest) {
        return Comment.builder()
                .content(commentRequest.getContent())
                .build();
    }

}
