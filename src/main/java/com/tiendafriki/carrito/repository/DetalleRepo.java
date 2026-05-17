package com.tiendafriki.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.carrito.model.Detalle;
import java.util.List;

@Repository
public interface DetalleRepo extends JpaRepository <Detalle, Integer> {
    List <Detalle> findByCarritoID(Integer carritoId);
    List <Detalle> findByProductoID(Integer productoId);
}