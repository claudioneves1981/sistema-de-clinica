package com.example.sistemadeclinica.security;

import com.example.sistemadeclinica.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*public String getSubject(@NotNull String token) {
        var algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token).getSubject();
    }*/
}
