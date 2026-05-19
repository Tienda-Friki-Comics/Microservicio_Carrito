package com.tiendafriki.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.carrito.model.Carrito;
import java.util.*;

@Repository
public interface CarritoRepo extends JpaRepository <Carrito, Integer> {
    Optional <Carrito> findByRutUsuarioIgnoreCase(String rutUsuario);
    Optional <Carrito> findByID(Integer id);
}