package com.mycompany.gestioncitas.Servicio.Validator;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
