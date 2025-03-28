package com.icbt.hospitalsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET_KEY = "d04be208e3a54a8fbf9761ce4943e8eadc881ebcc724fe59ebe4392cc77edd48848b37d9f4cf602879319ba10b3c7673bbe94ee7d19aee14a3a51faa69da80067c8174a120b89d4ece1c1198c67940e5d863b8d1c6a7a626c0e0b81a3fa74b78d3ba013d7fa600367c571aeb9bf5b92a8e0757b0044984826042e9435256ce711d4139eaf0209480131ebda68d0d0f9ea87877df1367cf375b1d9c4248c5cdf73c3ec78eb53f37918831d5117fe0bc7811f127ea112b5a4ae0b035f227c8e78008de8e58a715da90cda4cec317d85d1bea4037d23597a885e27b642f961cacb730ff15aa5dabcf0bc590f1fde162795f53738d52584430a7bbfe7008cc97b5c4";

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setClaims(Map.of("roles", roles))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractClaims(token);
        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
