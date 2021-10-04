package com.backend.ppinfo.relational.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

    public Boolean isAccountNonLocked() {
        return !this.isBanned;
    }

}
