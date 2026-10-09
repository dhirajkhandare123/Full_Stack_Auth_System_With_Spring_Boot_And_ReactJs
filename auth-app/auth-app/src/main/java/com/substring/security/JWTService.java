//package com.substring.security;
//
//import com.substring.entity.Roles;
//import com.substring.entity.User;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.time.Instant;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class JWTService {
//
//    private final SecretKey key;
//    private final long accessttlSeconds;
//    private final long refreshttlSeconds;
//    private final String issuer;
//
//    public JWTService(
//                      @Value("${security.jwt.secrete}") String secrete,
//                      @Value("${security.jwt.access-ttl-seconds}") long accessttlSeconds,
//                      @Value("${security.jwt.refresh-ttl-seconds}") long refreshttlSeconds,
//                      @Value("${security.jwt.issuer}") String issuer) {
//
//
//        if(secrete==null || secrete.length()<64){
//            throw new IllegalArgumentException("Invalid secrete");
//        }
//
//
//
//        this.key = Keys.hmacShaKeyFor(secrete.getBytes(StandardCharsets.UTF_8));
//        this.accessttlSeconds = accessttlSeconds;
//        this.refreshttlSeconds = refreshttlSeconds;
//        this.issuer = issuer;
//    }
//
//    // generate Token
//
//    public String generateToken(User user){
//        Instant now = Instant.now();
//        List<String>roles = user.getRoles() == null ? List.of() :
//                user.getRoles().stream().map(Roles::getName).toList();
//
//        return Jwts.builder()
//                .id(UUID.randomUUID().toString())
//                .subject(user.getId().toString())
//                .issuer(issuer)
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(now.plusSeconds(accessttlSeconds)))
//                .claims(Map.of(
//                        "email", user.getEmail(),
//                        "roles", roles,
//                        "typ", "access"
//                ))
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    // generate refresh token
//
//    public String generateRefreshToken(User user, String jwi){
//        Instant now = Instant.now();
//        return Jwts.builder()
//                .id(jwi)
//                .subject(user.getId().toString())
//                .issuer(issuer)
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(now.plusSeconds(refreshttlSeconds)))
//                .claim("typ", "refresh")
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    // parse the token
//
//    public Jws<Claims> parse(String token){
//        try{
//
//            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
//
//        } catch (JwtException e){
//
//            throw e;
//
//        }
//    }
//
//    public boolean isAccessToken(String token) {
//        Claims c = parse(token).getPayload();
//        return "access".equals(c.get("typ"));
//    }
//
//    public boolean isRefreshToken(String token) {
//        Claims c = parse(token).getPayload();
//        return "refresh".equals(c.get("typ"));
//    }
//
//    public UUID getUserId(String token) {
//        Claims c = parse(token).getPayload();
//        return UUID.fromString(c.getSubject());
//    }
//
//    public String getJti(String token) {
//
//        return parse(token).getPayload().getId();
//    }
//
//
//
//
//
//
//}
