package com.blackcode.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRes {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long userId;
    private String username;

    public JwtRes(String token, String refreshToken, Long userId, String username) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
    }
}
