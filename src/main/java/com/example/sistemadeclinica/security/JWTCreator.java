package com.example.sistemadeclinica.security;

import com.example.sistemadeclinica.exception.InvalidJwtException;
import com.example.sistemadeclinica.model.Usuario;
import com.example.sistemadeclinica.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Clock;

import java.util.Date;

@Component
public class JWTCreator {

    public static final String ROLES_AUTHORITIES = "role";
    private final Clock clock;
    private final JWTObject jwtObject;
    private Key secretKey;
    private final UsuarioRepository userRepository;

    @Autowired
    public JWTCreator(JWTObject jwtObject, UsuarioRepository userRepository) {
        this(jwtObject, userRepository, Clock.systemUTC());
    }

    public JWTCreator(JWTObject jwtObject, UsuarioRepository userRepository, Clock clock) {
        this.jwtObject = jwtObject;
        this.userRepository = userRepository;
        this.clock = clock;
    }

    @PostConstruct
    protected void init() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            byte[] decodedKeyBytes = new byte[32];
            secureRandom.nextBytes(decodedKeyBytes);
            this.secretKey = Keys.hmacShaKeyFor(decodedKeyBytes);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to decode JWT secret", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String genarateAccessToken(UsuarioLogado usuario) {

        Date now = Date.from(clock.instant());
        Date expiry = Date.from(clock.instant().plusMillis(jwtObject.getExpiration()));

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim(ROLES_AUTHORITIES, usuario.getAuthorities().stream()
                        .map(auth -> auth.getAuthority().replace("ROLE_", "")) // remove prefixo
                        .findFirst()
                        .orElse("USER"))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Usuario getCurrentUser() {
        UserDetails userDetails = getAuthentication();
        if (userDetails != null) {
            String username = userDetails.getUsername();
            return userRepository.findByLogin(username)
                    .orElseThrow(() -> new InvalidJwtException("User not found with email: " + username));
        }
        throw new InvalidJwtException("No authenticated user found");
    }

    public UserDetails getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        boolean isRoleAnonymous = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS"));

        if (!isRoleAnonymous) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
