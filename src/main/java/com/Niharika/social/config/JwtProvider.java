package com.Niharika.social.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        String jwt = Jwts.builder().setIssuer("social")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();


        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        // we want to return in format Bearer Token ,but we just want the token
        // ,so we took 7 from Bearer
       jwt = jwt.substring(7);

       Claims claims = Jwts.parser().setSigningKey(key)
               .build().parseClaimsJws(jwt).getBody();

       String email = String.valueOf(claims.get("email"));

       return email;
    }
}
