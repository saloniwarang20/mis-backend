package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
