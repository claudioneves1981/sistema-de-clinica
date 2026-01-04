package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.LoginDto;
import com.example.sistemadeclinica.dto.TokenDto;
import com.example.sistemadeclinica.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDto> checkLogin(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(new TokenDto(loginService.checkLogin(login.login(), login.senha())));
    }
}