package com.tiendafriki.carrito.service;

import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.carrito.repository.CarritoRepo;
import org.springframework.stereotype.Service;
import com.tiendafriki.carrito.dto.CarritoDTO;
import com.tiendafriki.carrito.model.Carrito;
import java.time.*;
import java.util.*;

@Service
public class CarritoServ {

    @Autowired
    private CarritoRepo cr;

    public List <Carrito> listar() {
        return cr.findAll();
    }

    public Optional <Carrito> buscarxID(Integer id) {
        return cr.findByID(id);
    }

    public List <Carrito> buscarxNombre(String nombre) {
        return cr.findByNombreIgnoreCase(nombre);
    }

    public String Guardar(CarritoDTO dto) {
        List <Carrito> Existente = cr.findByNombreIgnoreCase(dto.getNombre());
        if (Existente.isEmpty()) {
            return "[+] Ya Existe Un Carrito Para El Cliente " + dto.getNombre() + " [>_<] ... ";
        }
        Carrito c = new Carrito();
        c.setNombre(dto.getNombre());
        c.setFecha(LocalDateTime.now());
        cr.save(c);
        return "[+] Carrito Creado Correctamente Para " + dto.getNombre() + " [>_<] ... ";
    }

    public String Actualizar(Integer id, CarritoDTO dto) {
        Optional <Carrito> ct = cr.findByID(id);
        if (ct.isPresent()) {
            Carrito c = ct.get();
            c.setNombre(dto.getNombre());
            cr.save(c);
            return "[+] Carrito Actualizado Correctamente [>_<] ... ";
        }
        return "[+] Carrito Con El ID : " + id + " No Encontrado [>_<] ... ";
    }

    public String Eliminar(Integer id) {
        Optional <Carrito> ct = cr.findByID(id);
        if (ct.isPresent()) {
            cr.deleteById(id);
            return "[+] Carrito Eliminado Correctamente [>_<] ... ";
        }
        return "[+] Carrito Con El ID : " + id + " No a Sido Encontrado [>_<] ... ";
    }

}