package com.tiendafriki.carrito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tiendafriki.carrito.repository.*;
import com.tiendafriki.carrito.model.*;
import com.tiendafriki.carrito.dto.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DetalleServ {

    //@Autowired
    //private RestTemplate rt;

    @Autowired
    private DetalleRepo dr;

    @Autowired
    private CarritoRepo cr;

    // === OBTENER PRECIO DESDE CATÁLOGO === //

    private Integer ObtenerPrecio(Integer productoId) {

        // Creamos un nuevo Rest Template para permitir la comunicacion con catalogo

        RestTemplate rt = new RestTemplate();

        String url =
                "http://localhost:8081/catalogo/buscarxid/"
                        + productoId;

        try {

            // Obtenemos respuesta desde catálogo

            Map response =
                    rt.getForObject(
                            url,
                            Map.class
                    );

            // Validamos existencia del producto y que contenga el precio

            if (response == null
                    || !response.containsKey("precio")) {

                throw new RuntimeException(
                        "[+] El Producto No Existe En El Catalogo [>_<] ... "
                );
            }

            // Retornamos precio del producto

            return (Integer) response.get("precio");

        } catch (Exception e) {

            throw new RuntimeException(
                    "[+] Error Al Consultar Catalogo [>_<] ... "
            );
        }
    }

    // === LISTAR === //

    public List<Detalle> listar() {

        return dr.findAll();
    }

    // === BUSCAR POR ID === //

    public Optional<Detalle> buscarxID(Integer id) {

        return dr.findById(id);
    }

    // === BUSCAR POR CARRITO === //

    public List<Detalle> buscarxCarrito(Integer carritoId) {

        return dr.findByCarrito_Id(carritoId);
    }

    // === BUSCAR POR PRODUCTO === //

    public List<Detalle> buscarxProducto(Integer productoId) {

        return dr.findByProductoId(productoId);
    }

    // === CREAR DETALLE === //

    public String Guardar(DetalleDTO dto) {

        // Validamos carrito existente

        Optional<Carrito> ct =
                cr.findById(dto.getCarritoId());

        if (ct.isEmpty()) {

            throw new NoSuchElementException(
                    "[+] El Carrito Con El ID : "
                            + dto.getCarritoId()
                            + " No Existe [>_<] ... "
            );
        }

        // Obtener precio desde catálogo

        Integer precioxUnidad =
                ObtenerPrecio(dto.getProductoId());

        // Calcular subtotal

        Integer subtotal =
                dto.getCantidad() * precioxUnidad;

        // Crear detalle

        Detalle dt = new Detalle();

        dt.setCarrito(ct.get());

        dt.setProductoId(dto.getProductoId());

        dt.setCantidad(dto.getCantidad());

        dt.setPrecioxUnidad(precioxUnidad);

        dt.setSubtotal(subtotal);

        dr.save(dt);

        // Actualizamos fecha del carrito

        Carrito carrito = ct.get();

        carrito.setFecha(LocalDateTime.now());

        cr.save(carrito);

        return "[+] Detalle Agregado Exitosamente [>_<] ... "
                + "[+] Cantidad : "
                + dto.getCantidad()
                + " | "
                + "[+] PrecioxUnidad : $"
                + precioxUnidad
                + " | "
                + "[+] Subtotal : $"
                + subtotal
                + " [>_<] ... ";
    }

    // === ACTUALIZAR DETALLE === //

    public String Actualizar(
            Integer id,
            DetalleDTO dto
    ) {

        Optional<Detalle> dt =
                dr.findById(id);

        if (dt.isEmpty()) {

            throw new NoSuchElementException(
                    "[] Detalle Con El ID : "
                            + id
                            + " No Encontrado [>_<] ... "
            );
        }

        // Validar carrito

        Optional<Carrito> ct =
                cr.findById(dto.getCarritoId());

        if (ct.isEmpty()) {

            throw new NoSuchElementException(
                    "[ERROR] El Carrito No Existe [>_<] ... "
            );
        }

        // Obtener precio actualizado

        Integer precioxUnidad =
                ObtenerPrecio(dto.getProductoId());

        // Recalcular subtotal

        Integer subtotal =
                dto.getCantidad() * precioxUnidad;

        // Actualizar detalle

        Detalle detalle = dt.get();

        detalle.setCarrito(ct.get());

        detalle.setProductoId(dto.getProductoId());

        detalle.setCantidad(dto.getCantidad());

        detalle.setPrecioxUnidad(precioxUnidad);

        detalle.setSubtotal(subtotal);

        dr.save(detalle);

        // Actualizar fecha carrito

        Carrito carrito = ct.get();

        carrito.setFecha(LocalDateTime.now());

        cr.save(carrito);

        return "[+] Detalle Actualizado Correctamente "
                + "[+] Cantidad : "
                + dto.getCantidad()
                + " | "
                + "[+] PrecioxUnidad : $"
                + precioxUnidad
                + " | "
                + "[+] Subtotal : $"
                + subtotal
                + " [>_<] ... ";
    }

    // === ELIMINAR === //

    public String Eliminar(Integer id) {

        Optional<Detalle> dt = dr.findById(id);

        if (dt.isEmpty()) {

            throw new NoSuchElementException(
                    "[ERROR] Detalle Con El ID : "
                            + id
                            + " No A Sido Encontrado [>_<] ... "
            );
        }

        dr.deleteById(id);

        return "[+] El Detalle A Sido Eliminado Con Exito [>_<] ... ";
    }

    

}