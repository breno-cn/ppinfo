package com.backend.ppinfo.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUserRequest {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

}