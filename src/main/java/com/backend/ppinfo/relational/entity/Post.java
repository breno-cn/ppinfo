package com.backend.ppinfo.relational.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private BoardUser boardUser;

}
