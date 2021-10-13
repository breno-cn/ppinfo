package com.backend.ppinfo.relational.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostLike {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BoardUser boardUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

}
