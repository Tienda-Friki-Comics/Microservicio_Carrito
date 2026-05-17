package com.tiendafriki.carrito.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    
    @NotBlank(message = "[+] El Nombre No Puede Quedar Vacio [>_<] ... ")
    private String Nombre;

}