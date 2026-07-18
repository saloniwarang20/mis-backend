package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.LoginRequest;
import com.example.mis_invoicing_system.DTO.SignupRequest;
import com.example.mis_invoicing_system.Entity.User;
import com.example.mis_invoicing_system.Repository.UserRepository;
import com.example.mis_invoicing_system.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //sign up
    public String signup(SignupRequest signupRequest){
        //check if email exists
        if(userRepo.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("Error: Email already registered");
        }

        //create new user entity and encode password
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        //save to database
        userRepo.save(user);
        return "User registered successfully";
    }

    //login
    public String login(LoginRequest loginRequest){
        //find user by email
        Optional<User> userOptional = userRepo.findByEmail(loginRequest.getEmail());
        if(userOptional.isEmpty()){
            throw new RuntimeException("Error: User not found");
        }

        User user = userOptional.get();

        //verify matching password
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Error: Invalid credentials!");
        }

        //generate jwt token
        String token = jwtService.generateToken(
                user.getEmail()
        );

        return token;
    }
}
