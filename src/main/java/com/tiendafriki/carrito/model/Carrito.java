package com.tiendafriki.carrito.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import lombok.*;

@Table(name = "Carrito")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    // Usuario propietario del carrito
    // Se utiliza rut por ser un identificador único

    @NotBlank(message = "[+] El Rut No Puede Estar Vacio [>_<] ... ")
    @Column(nullable = false, length = 14, unique = true)
    private String RutUsuario;

    // Fecha de creación o última actualización

    @Column(nullable = false)
    private LocalDateTime Fecha;

    @OneToMany(mappedBy = "Carrito", cascade = CascadeType.ALL)
    private List <Detalle> Detalles;

}