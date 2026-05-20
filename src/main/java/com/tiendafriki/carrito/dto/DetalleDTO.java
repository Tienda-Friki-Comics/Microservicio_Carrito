package com.tiendafriki.carrito.dto;

import jakarta.validation.constraints.*;
import lombok.*;

// Este DTO sirve para la creación y actualización del detalle de carrito
// Controla lo que ingresa el usuario en la solicitud

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDTO {

    @NotNull(message = "[+] El ID Del Carrito No Puede Ser Nulo [>_<] ... ")
    private Integer carritoId;

    @NotNull(message = "[+] La ID Del Producto No Puede Estar Nulo [>_<] ... ")
    private Integer productoId;

    @Min(value = 1, message = "[+] La Cantidad Debe Ser Mayor o Igual a 1 [>_<] ... ")
    @NotNull(message = "[+] La Cantidad No Puede Ser Nula [>_<] ...                 ")
    private Integer cantidad;

}