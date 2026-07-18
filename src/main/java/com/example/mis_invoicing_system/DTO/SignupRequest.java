package com.example.mis_invoicing_system.DTO;

import com.example.mis_invoicing_system.Enum.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String fullName;
}
