package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.LoginDto;
import com.example.sistemadeclinica.dto.UserResponseDto;
import com.example.sistemadeclinica.exception.UsuarioJaExisteException;
import com.example.sistemadeclinica.mapper.UsuarioMapper;
import com.example.sistemadeclinica.model.Usuario;
import com.example.sistemadeclinica.repository.UsuarioRepository;
import com.example.sistemadeclinica.security.JWTCreator;
import com.example.sistemadeclinica.security.UsuarioLogado;
import com.example.sistemadeclinica.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTCreator tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String checkLogin(String login, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.genarateAccessToken(((UsuarioLogado) authentication.getPrincipal()));
    }

    @Transactional
    @Override
    public UserResponseDto create(LoginDto loginDto) {
        if (usuarioRepository.existsByLogin(loginDto.login())) {
            throw new UsuarioJaExisteException(loginDto.login());
        }
        Usuario user = UsuarioMapper.toEntity(loginDto);
        user.setSenha(passwordEncoder.encode(loginDto.senha()));
        user = usuarioRepository.save(user);
        return UsuarioMapper.toDto(user);
    }

}