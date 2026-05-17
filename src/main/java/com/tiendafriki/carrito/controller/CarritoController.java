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
public class CarritoController {

    @Autowired
    private CarritoServ s;

    @GetMapping("/listar")
    public List <Carrito> listar() {
        return s.listar();
    }

    @GetMapping("/buscarxid/{id}")
    public Optional <Carrito> buscarxID(@PathVariable Integer id) {
        return s.buscarxID(id);
    }

    @GetMapping("/buscarxnombre/{nombre}")
    public List <Carrito> buscarxNombre(@PathVariable String nombre) {
        return s.buscarxNombre(nombre);
    }

    @PostMapping("/crear")
    public ResponseEntity <?> Crear(@Valid @RequestBody CarritoDTO dto) {
        return ResponseEntity.status(201).body(s.Guardar(dto));
    }

    @PostMapping("/actualizar/{id}")
    public ResponseEntity <?> Actualizar(@PathVariable Integer id, @Valid @RequestBody CarritoDTO dto) {
        return ResponseEntity.ok(s.Actualizar(id, dto));
    }

    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity <?> Eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(s.Eliminar(id));
    }

}