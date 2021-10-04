package com.backend.ppinfo.dto.data;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;

}
