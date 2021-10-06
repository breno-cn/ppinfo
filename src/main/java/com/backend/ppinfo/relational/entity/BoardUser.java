package com.backend.ppinfo.relational.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUser {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private LocalDateTime createdAt;

    private Boolean isBanned;

    @OneToMany(mappedBy = "boardUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Board> boards;

    @OneToMany(mappedBy = "boardUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "boardUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Boolean isAccountNonLocked() {
        return !this.isBanned;
    }

}
