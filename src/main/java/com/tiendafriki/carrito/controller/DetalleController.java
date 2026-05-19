package com.tiendafriki.carrito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tiendafriki.carrito.service.DetalleServ;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.tiendafriki.carrito.dto.DetalleDTO;
import com.tiendafriki.carrito.model.Detalle;
import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/detalleCarrito")
public class DetalleController {

    @Autowired
    private DetalleServ s;

    @GetMapping("/listar")
    public List <Detalle> listar() {
        return s.listar();
    }

    @GetMapping("/buscarxid/{id}")
    public Optional <Detalle> buscarxId(@PathVariable Integer id) {
        return s.buscarxID(id);
    }

    @GetMapping("/carrito/{carritoId}")
    public List <Detalle> buscarxCarrito(@PathVariable Integer carritoId) {
        return s.buscarxCarrito(carritoId);
    }

    @GetMapping("/producto/{productoId}")
    public List <Detalle> buscarxProducto(@PathVariable Integer productoId) {
        return s.buscarxProducto(productoId);
    }

    @PostMapping("/agregar")
    public ResponseEntity <?> Agregar(@Valid @RequestBody DetalleDTO dto) {
        return ResponseEntity.status(201).body(s.Guardar(dto));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity <?> Actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleDTO dto) {
        return ResponseEntity.ok(s.Actualizar(id, dto));
    }

    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity <?> Eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(s.Eliminar(id));
    }

}