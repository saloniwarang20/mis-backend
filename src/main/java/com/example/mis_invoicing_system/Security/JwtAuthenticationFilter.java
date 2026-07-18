package com.example.mis_invoicing_system.Security;

import com.example.mis_invoicing_system.Entity.User;
import com.example.mis_invoicing_system.Repository.UserRepository;
import java.util.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //read the authorization header
        String authHeader = request.getHeader("Authorization");

        //check if jwt exists
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //extract token
        String token = authHeader.substring(7);

        //extract email
        String email = jwtService.extractUsername(token);

        //check security context -> authenticate only if user is not already authenticated
        if(email != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null){
            Optional<User> userOptional = userRepository.findByEmail(email);

            if(userOptional.isPresent()){
                User user = userOptional.get();
                if(jwtService.isTokenValid(token, user.getEmail())){

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            Collections.emptyList()
                    );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
