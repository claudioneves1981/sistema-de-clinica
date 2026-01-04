package com.example.sistemadeclinica.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTObject {

    private Long expiration;
    private Long refreshExpiration;
    private String secret;
    private String refreshSecret;
    private String prefix;
    private String header;
    private List<String> roles;

}