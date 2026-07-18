package com.example.mis_invoicing_system.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mySuperSecretKeyforMISinvoicingSystem123456789";

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                )
                .signWith(getSigningKey())
                .compact();
    }

    //to find out who does this token belong to
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //extract claims: JWT contains multiple pieces of information called claims
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //extract expiration time
    public Date extractExpiration(String token){
        return extractAllClaims(token)
                .getExpiration();
    }

    //check if expired(token expiry < current time --> token is dead)
    public boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }

    //validate token
    public boolean isTokenValid(String token, String email){
        String username = extractUsername(token);
        return username.equals(email)
                && !isTokenExpired(token);
    }

}
