package com.backend.ppinfo.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUserResponse {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;
    private Boolean isBanned;

}