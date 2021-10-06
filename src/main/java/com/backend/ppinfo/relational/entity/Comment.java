package com.backend.ppinfo.relational.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BoardUser boardUser;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
