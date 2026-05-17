package com.tiendafriki.carrito.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDTO {

    @NotNull(message = "[+] El ID Del Carrito No Puede Ser Nulo [>_<] ... ")
    private Integer CarritoID;

    @NotNull(message = "[+] La ID Del Producto No Puede Estar Nulo [>_<] ... ")
    private Integer ProductoID;

    @Min(value = 1, message = "[+] La Cantidad Debe Ser Mayor o Igual a 1 [>_<] ... ")
    @NotNull(message = "[+] La Cantidad No Puede Ser Nula [>_<] ...                 ")
    private Integer Cantidad;

}