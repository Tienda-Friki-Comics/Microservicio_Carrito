package com.tiendafriki.carrito.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.tiendafriki.carrito.repository.*;
import org.springframework.stereotype.Service;
import com.tiendafriki.carrito.dto.CarritoDTO;
import com.tiendafriki.carrito.model.*;
import java.time.*;
import java.util.*;

@Service

public class CarritoServ {

    @Autowired
    private CarritoRepo cr;

    @Autowired
    private DetalleRepo dr;

    // === LISTAR === //

    public List<Carrito> listar() {

        return cr.findAll();
    }

    // === BUSCAR POR ID === //

    public Optional<Carrito> buscarxID(Integer id) {

        return cr.findById(id);
    }

    // === BUSCAR POR RUT === //

    public Optional<Carrito> buscarxRut(String rut) {

        return cr.findByRutUsuarioIgnoreCase(rut);
    }

    // === CALCULAR TOTAL DEL CARRITO === //

    public Double calcularTotal(Integer carritoId) {

        // VALIDAMOS QUE EL CARRITO EXISTA
        // antes de calcular su total

        if (!cr.existsById(carritoId)) {

            throw new NoSuchElementException(
                    "[+] Carrito No Encontrado [>_<] ... "
            );
        }

        List<Detalle> detalles =
                dr.findByCarrito_Id(carritoId);

        double subtotal =
                detalles.stream()
                        .mapToDouble(Detalle::getSubtotal)
                        .sum();

        // IVA 19%

        double iva = subtotal * 0.19;

        return subtotal + iva;
    }

    // === CREAR CARRITO === //

    public String Guardar(CarritoDTO dto) {

        // Validamos que NO exista otro carrito
        // para el mismo usuario

        Optional<Carrito> existente =
                cr.findByRutUsuarioIgnoreCase(
                        dto.getRutUsuario()
                );

        // ERROR CORREGIDO:
        // antes estaba invertido

        if (existente.isPresent()) {

            throw new IllegalArgumentException(
                    "[+] Ya Existe Un Carrito Para El Usuario "
                            + dto.getRutUsuario()
                            + " [>_<] ... "
            );
        }

        /*
        // FUTURA CONEXIÓN CON USUARIOS

        RestTemplate rt = new RestTemplate();

        String url =
                "http://localhost:808X/usuarios/buscarxrut/"
                + dto.getRutUsuario();

        */

        Carrito c = new Carrito();

        c.setRutUsuario(dto.getRutUsuario());

        c.setFecha(LocalDateTime.now());

        cr.save(c);

        return "[+] Carrito Creado Correctamente Para "
                + dto.getRutUsuario()
                + " [>_<] ... ";
    }

    // === ELIMINAR === //

    public String Eliminar(Integer id) {

        Optional<Carrito> ct =
                cr.findById(id);

        // IMPORTANTE:
        // lanzamos excepción si no existe

        if (ct.isEmpty()) {

            throw new NoSuchElementException(
                    "[+] Carrito Con El ID : "
                            + id
                            + " No Ha Sido Encontrado [>_<] ... "
            );
        }

        cr.deleteById(id);

        return "[+] Carrito Eliminado Correctamente [>_<] ... ";
    }

}