package com.example.sistemadeclinica.validators;

import com.example.sistemadeclinica.exception.ValidationException;

public interface Validator<T> {

    void validate(T t) throws ValidationException;

}