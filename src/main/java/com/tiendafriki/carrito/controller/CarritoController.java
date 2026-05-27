package com.tiendafriki.carrito.controller;

import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.carrito.service.CarritoServ;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.tiendafriki.carrito.dto.CarritoDTO;
import com.tiendafriki.carrito.model.Carrito;
import jakarta.validation.*;
import java.util.*;

@RestController
@RequestMapping("/carrito")

// REVISADO OK
// Nada que corregir

public class CarritoController {

    @Autowired
    private CarritoServ s;

    @GetMapping("/listar")
    public List<Carrito> listar() {

        return s.listar();
    }

    // IMPORTANTE:
    // Ahora usamos orElseThrow para lanzar una excepción
    // si el carrito NO existe.
    // Esto permitirá activar automáticamente
    // el manejador global 404.

    @GetMapping("/buscarxid/{id}")
    public Carrito buscarxID(@PathVariable Integer id) {

        return s.buscarxID(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Carrito No Encontrado [>_<] ... "
                        )
                );
    }

    // NUEVO:
    // Este endpoint permite obtener el total de un carrito por su id
    // es mejor separarlo del contenido del carrito,
    // ya que el total siempre puede cambiar

    @GetMapping("/total/{id}")
    public ResponseEntity<?> total(@PathVariable Integer id) {

        return ResponseEntity.ok(
                s.calcularTotal(id)
        );
    }

    // IMPORTANTE:
    // También aplicamos la misma lógica para búsqueda por rut

    @GetMapping("/buscarxrut/{rut}")
    public Carrito buscarxRut(@PathVariable String rut) {

        return s.buscarxRut(rut)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Carrito No Encontrado [>_<] ... "
                        )
                );
    }

    @PostMapping("/crear")
    public ResponseEntity<?> Crear(
            @Valid @RequestBody CarritoDTO dto
    ) {

        return ResponseEntity
                .status(201)
                .body(s.Guardar(dto));
    }


    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity<?> Eliminar(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                s.Eliminar(id)
        );
    }

}