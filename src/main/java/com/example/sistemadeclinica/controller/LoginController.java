package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.LoginDto;
import com.example.sistemadeclinica.dto.TokenDto;
import com.example.sistemadeclinica.dto.UserResponseDto;
import com.example.sistemadeclinica.mapper.UsuarioMapper;
import com.example.sistemadeclinica.model.Usuario;
import com.example.sistemadeclinica.security.JWTCreator;
import com.example.sistemadeclinica.service.LoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JWTCreator tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> checkLogin(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(new TokenDto(loginService.checkLogin(login.login(), login.senha())));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody LoginDto userRequestDTO) {
        UserResponseDto userResponseDTO = loginService.create(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> findByUsuario() {
        Usuario usuario = tokenService.getCurrentUser();
        return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
    }
}