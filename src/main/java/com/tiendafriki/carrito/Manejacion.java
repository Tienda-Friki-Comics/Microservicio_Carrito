package com.tiendafriki.carrito;

import com.tiendafriki.carrito.dto.ErrorDTO;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import java.time.*;
import java.util.*;

@RestControllerAdvice

public class Manejacion {

    // === ERROR VALIDACIÓN JAKARTA === //

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ErrorDTO> ErrorValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> Errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
        Errores.put(error.getField(),error.getDefaultMessage()
        ));

        ErrorDTO errorDTO = new ErrorDTO(

                LocalDateTime.now(),
                400,
                "[ERROR] : 400 Error En La Validacion [>_<] ... ",
                Errores,
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    // === ERRORES DE VALIDACIONES DE NEGOCIO === //

    // Nota: Este metodo NO es para validaciones jakarta, sino para validaciones
    // de logica de negocio interna devuelto por el service, como cuando un objeto ya existe.
    // Este metodo nos servirá para centralizar los mensjaes de errores de negocio
    // con su correspondiente HTTTP Status

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<ErrorDTO> ErrorSolicitud(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                400,

                // MOSTRAMOS EL MENSAJE REAL
                // enviado desde el service

                ex.getMessage(),

                null,

                request.getRequestURI());

        return ResponseEntity.badRequest().body(error);
    }

    // === ERROR NO ENCONTRADO === //

    @ExceptionHandler(NoSuchElementException.class)

    public ResponseEntity<ErrorDTO> ErrorNoEncontrado(
            NoSuchElementException ex,
            HttpServletRequest request
    ) {

        ErrorDTO error =
                new ErrorDTO(

                        LocalDateTime.now(),

                        404,

                        "[ERROR] : 404 Recurso No Encontrado [>_<] ... ",

                        null,

                        request.getRequestURI()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

        // === ERROR 500: ERROR INTERNO DEL SERVIDOR === //

        // Esta excepción se utiliza para errores inesperados
        // ocurridos durante la ejecución del sistema.
        //
        // Generalmente ocurre cuando existe un problema
        // al comunicarse con otros microservicios.
        //
        // Ejemplos:
        //
        // - El microservicio carrito está apagado
        // - Falló la conexión HTTP
        // - Timeout de comunicación
        // - URL inexistente
        // - Error inesperado del servidor
        //
        // Normalmente estas excepciones ocurren dentro
        // de bloques try-catch del service.
        //
        // Cuando ocurre uno de estos errores,
        // se lanza un RuntimeException y este manejador
        // lo transforma automáticamente en un
        // HTTP 500 (Internal Server Error).

        @ExceptionHandler(RuntimeException.class)

        public ResponseEntity<ErrorDTO> manejarErrorInterno(
                RuntimeException ex,
                HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        errores.put("error", ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO(

                LocalDateTime.now(),

                500,

                "[ERROR] Error Interno del Servidor [X_X]",

                errores,

                request.getRequestURI());

        return ResponseEntity.status(500).body(errorDTO);
        }

}
