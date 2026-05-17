package com.tiendafriki.carrito.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import com.tiendafriki.carrito.repository.*;
import com.tiendafriki.carrito.model.*;
import com.tiendafriki.carrito.dto.*;
import java.util.*;

@Service
public class DetalleServ {

    @Autowired
    private RestTemplate rt;
    private DetalleRepo dr;
    private CarritoRepo cr;

    private Integer ObtenerPrecio(Integer productoID) {
        
        String url = "http://localhost:8080/catalogo/buscarxid/{id}";
        Map response = rt.getForObject(url, Map.class);

        if (response == null || response.containsKey("Precio")) {
           throw new RuntimeException("[+] El Producto No Existe En El Catalogo [>_<] ... ");
        }
        return (Integer) response.get("Precio");
    }

    public List <Detalle> listar() {
        return dr.findAll();
    }

    public Optional <Detalle> buscarxID(Integer id) {
        return dr.findById(id);
    }

    public List <Detalle> buscarxCarrito(Integer carritoID) {
        return dr.findByCarritoID(carritoID);
    }

    public List <Detalle> buscarxProducto(Integer productoID) {
        return dr.findByProductoID(productoID);
    }

    public String Guardar(DetalleDTO dto) {
        Optional <Carrito> ct = cr.findByID(dto.getCarritoID());
        if (ct.isEmpty()) {
            return "[+] El Carrito Con El ID : " + dto.getCarritoID() + " No Existe [>_<] ... ";
        }

        Integer PrecioxUnidad = ObtenerPrecio(dto.getProductoID());
        Integer Subtotal      = dto.getCantidad() * PrecioxUnidad;

        Detalle dt = new Detalle();
        dt.setCarrito(ct.get());
        dt.setProductoID(dto.getProductoID());
        dt.setCantidad(dto.getCantidad());
        dt.setSubtotal(Subtotal);
        dr.save(dt);

        return "[+] Detalle Agregado Exitosamente [>_<] ... " + 
               "[+] Cantidad : " + dto.getCantidad() + " | " +
               "[+] PrecioxUnidad : " + PrecioxUnidad + " | " +
               "[+] Subtotal : $" + Subtotal + " [>_<] ... ";
    }

    public String Actualizar(Integer id, DetalleDTO dto) {
       Optional <Detalle> dt = dr.findById(id);
       if (dt.isEmpty()) {
            return "[+] Detalle Con El ID : " + id + " No Encontrado [>_<] ... ";
       }
       Integer PrecioxUnidad = ObtenerPrecio(dto.getProductoID());
       Integer Subtotal = dto.getCantidad() * PrecioxUnidad;
       
       Detalle detalle = dt.get();
       detalle.setProductoID(dto.getProductoID());
       detalle.setCantidad(dto.getCantidad());
       detalle.setPrecioxUnidad(PrecioxUnidad);
       detalle.setSubtotal(Subtotal);
       dr.save(detalle);

        return "[+] Detalle Actualizado Correctamente. " +
               "[+] Cantidad : " + dto.getCantidad() + " | " +
               "[+] PrecioxUnidad : $" + PrecioxUnidad + " | " +
               "[+] Subtotal : $" + Subtotal + " [>_<] ... ";
    }

    public String Eliminar(Integer id) {
        Optional <Detalle> dt = dr.findById(id);
        if (dt.isPresent()) {
            dr.deleteById(id);
            return "[+] El Detalle a Sido Eliminado Con Exito [>_<] ... ";
        }
        return "[+] Detalle Con El ID : " + id + " No a Sido Encontrado [>_<] ... ";
    }

}