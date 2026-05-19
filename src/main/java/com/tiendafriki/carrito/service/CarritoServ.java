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

        return cr.findByID(id);
    }

    // === BUSCAR POR RUT === //

    public Optional<Carrito> buscarxRut(String rut) {

        return cr.findByRutUsuarioIgnoreCase(rut);
    }

    // === CALCULAR TOTAL DEL CARRITO === //

    public Double calcularTotal(Integer carritoID) {

        List<Detalle> detalles =
                dr.findByCarrito_ID(carritoID);

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

        Optional<Carrito> Existente =
                cr.findByRutUsuarioIgnoreCase(
                        dto.getRutUsuario()
                );

        // ERROR CORREGIDO:
        // antes estaba invertido

        if (Existente.isPresent()) {

            return "[+] Ya Existe Un Carrito Para El Usuario "
                    + dto.getRutUsuario()
                    + " [>_<] ... ";
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

    // === ACTUALIZAR === //

    public String Actualizar(Integer id, CarritoDTO dto) {

        Optional<Carrito> ct = cr.findByID(id);

        if (ct.isPresent()) {

            Carrito c = ct.get();

            c.setRutUsuario(dto.getRutUsuario());

            // Actualizamos fecha automáticamente

            c.setFecha(LocalDateTime.now());

            cr.save(c);

            return "[+] Carrito Actualizado Correctamente [>_<] ... ";
        }

        return "[+] Carrito Con El ID : "
                + id
                + " No Encontrado [>_<] ... ";
    }

    // === ELIMINAR === //

    public String Eliminar(Integer id) {

        Optional<Carrito> ct = cr.findByID(id);

        if (ct.isPresent()) {

            cr.deleteById(id);

            return "[+] Carrito Eliminado Correctamente [>_<] ... ";
        }

        return "[+] Carrito Con El ID : "
                + id
                + " No Ha Sido Encontrado [>_<] ... ";
    }

}

/*

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


*/