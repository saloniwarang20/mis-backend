package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.LoginRequest;
import com.example.mis_invoicing_system.DTO.SignupRequest;
import com.example.mis_invoicing_system.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest){
        try{
            String response = authService.signup(signupRequest);
            return ResponseEntity.ok(response);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        try{
            String response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
