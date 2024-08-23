package com.haratres.SpringSecurity.core.security.jwt;

import com.haratres.SpringSecurity.entities.concretes.CustomUserDetail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtHelper {

    String secretKey = "myprivatesecretkeyforspringsecurity";
    private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
    private final long EXPIRATION = 900000;




    public String generateToken(CustomUserDetail userDetails) {

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();


    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("JWT Token Validation Error: " + e.getMessage());
        }
        return false;
    }

}
