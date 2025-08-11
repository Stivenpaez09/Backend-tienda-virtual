package com.shop.tienda_virtual.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Datos inválidos");
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Recurso no encontrado");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntidadInvalidaException.class)
    public ResponseEntity<Map<String, String>> handleEntidadInvalidaException(EntidadInvalidaException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Entidad inválida");
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFormatException(InvalidFormatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Formato inválido");
        response.put("message", "Error en el formato de fecha. Debe ser 'yyyy-MM-dd'.");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Error inesperado");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleAuthErrors(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario o contraseña incorrectos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}