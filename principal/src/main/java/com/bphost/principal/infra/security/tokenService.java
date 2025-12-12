package com.bphost.principal.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bphost.principal.model.user_account;

@Service
public class tokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generatedToken(user_account user){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                            .withIssuer("auth-api")
                            .withSubject(user.getUsername())
                            .withExpiresAt(generatedExpirateDate())
                            .sign(algoritimo);
            
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generatedToken ", e);
        }        
    }

    public String getExtractedUsernameFromToken(String token){
        String username = JWT.decode(token).getSubject();
        return username;
    }

    public String validateToken(String token){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generatedExpirateDate(){
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}

