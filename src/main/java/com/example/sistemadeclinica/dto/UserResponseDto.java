package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Usuario;

public record UserResponseDto(

        Long id,
        String name,
        Usuario.Role role

) {
}