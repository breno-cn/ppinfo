package com.backend.ppinfo.relational.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @Formula("(select count(*) from post_like pl where pl.post_id = id)")
    private Long likes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private BoardUser boardUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    List<PostLike> postLikes;

}
