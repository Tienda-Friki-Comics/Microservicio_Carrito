package com.tiendafriki.carrito;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import com.tiendafriki.carrito.dto.ErrorDTO;
import java.time.*;
import java.util.*;

@RestControllerAdvice
public class Manejacion {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> ErrorValidacion(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> Errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            Errores.put(error.getField(), error.getDefaultMessage())
        );
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            400,
            "[+] Error : " + 400 + " Error En La Validacion [>_<] ... ",
            Errores,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> ErrorSolicitud(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            400,
            "[+] Error : " + 400 + " Error En La Solicitud [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> ErrorNoAutorizado(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            401,
            "[+] Error : " + 401 + " Error No Autorizado [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> ErrorNoEncontrado(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            404,
            "[+] Error : " + 404 + " Error Recurso No Encontrado [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> ErrorGeneral(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            500,
            "[+] Error : " + 500 + " Error Interno Del Servidor [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}