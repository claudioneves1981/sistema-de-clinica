package com.example.sistemadeclinica.exception;

public class PacienteNaoEncontradoException extends RuntimeException {
    public PacienteNaoEncontradoException(String message) {
        super(message);
    }
}