package com.backend.ppinfo.relational.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BoardFollow {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BoardUser boardUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

}
