package com.tiendafriki.carrito.dto;

import jakarta.validation.constraints.*;
import lombok.*;

// Este DTO se usa para crear y actualizar el carrito (request)
// Controla el ingresa de datos del usuario al crear o actualizar su carrito

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {

    // En vez de nombre, se usa rut del cliente al que le pertence el carrito
    // ya que el rut es un dato unico e irrepetible
    
    @NotBlank(message = "[+] El Rut Del Usuario No Puede Quedar Vacio [>_<] ... ")
    // Esto validará que rut tenga el formato correcto:
    @Pattern(
    regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$|^\\d{7,8}-[\\dkK]$",
    message = "[+] El Formato Del Rut Es Invalido [>_<] ... "
    )
    private String rutUsuario;

}