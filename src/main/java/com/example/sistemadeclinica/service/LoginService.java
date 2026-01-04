package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.LoginDto;
import com.example.sistemadeclinica.dto.UserResponseDto;

public interface LoginService {

    String checkLogin(String login, String password);
    UserResponseDto create(LoginDto loginDto);
}
