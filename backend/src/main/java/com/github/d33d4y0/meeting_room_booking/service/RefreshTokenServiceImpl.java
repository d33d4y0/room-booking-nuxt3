package com.github.d33d4y0.meeting_room_booking.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.meeting_room_booking.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class RefreshTokenServiceImpl implements JwtService {

    // @Value("${token.signing.key}")
    private final String JWT_KEY = "U2jLp56vDqr9fK3RmB7aXeRnYQzg9WsP4SjG6xD5eVhM2TkFqHjCvN8yZuR1LwP0";
    private final Key SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_KEY));

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, (claims) -> claims.get("email", String.class));
    }
    
    @Override
    public Long extractUserId(String token) {
        return extractClaim(token, (claims) -> claims.get("id", Long.class));
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getUsername());
        claims.put("roles", user.getAuthorities().stream().map(authority -> authority.getAuthority()).toArray());
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256).compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token)
                .getBody();
    }
}
