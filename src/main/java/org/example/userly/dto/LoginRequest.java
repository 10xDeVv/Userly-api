package org.example.userly.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userInfo;
    private String password;
}
