package com.sairapuentes.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExcepcionGlobal {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> runtime(RuntimeException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacion(MethodArgumentNotValidException ex){
        String mensaje = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "mensaje", mensaje
                ));
    }
}
