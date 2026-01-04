package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.security.JWTCreator;
import com.example.sistemadeclinica.security.UsuarioLogado;
import com.example.sistemadeclinica.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTCreator tokenService;

    @Override
    public String checkLogin(String login, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.genarateAccessToken(((UsuarioLogado) authentication.getPrincipal()));
    }

}