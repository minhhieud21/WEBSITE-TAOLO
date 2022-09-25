package com.example.JavaSpring.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("{hector.app.jwtSecret}")
    private String jwtSecret;

    @Value("{hector.app.jwtExpirationMs}")
    private String jwtExpirationMs; //24h

    public String extractUsername(String token){
        return  extractClaims(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    // Get token and provide info
    private Claims extractAllClaims(String token) {
        return  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    // Check expiration
    public Boolean isExpiration(String token){
        return extractExpiration(token).before(new Date());
    }
    /**
    *
    * subject: who want to auth
    * signWith: encode with secret key
    *
    * */
    public String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) //start
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)) //end - 10h
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    // Check token if username is correct and not expiration
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token); // get username
        return (username.equals(userDetails.getUsername()) && !isExpiration(token));
    }
}
