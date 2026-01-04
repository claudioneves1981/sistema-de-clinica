package com.example.sistemadeclinica.mapper;

import com.example.sistemadeclinica.dto.LoginDto;
import com.example.sistemadeclinica.dto.UserResponseDto;
import com.example.sistemadeclinica.model.Usuario;

public class UsuarioMapper {

    public static UserResponseDto toDto(Usuario user) {
        return new UserResponseDto(
                user.getId(),
                user.getLogin(),
                user.getRole()
        );
    }

    public static Usuario toEntity(LoginDto userDTO) {
        return new Usuario(
                userDTO.login(),
                userDTO.senha()
        );
    }
}
