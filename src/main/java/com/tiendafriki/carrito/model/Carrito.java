package com.tiendafriki.carrito.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Integer id;

    // Usuario propietario del carrito
    // Se utiliza rut por ser un identificador único
    @Column(nullable = false, length = 12, unique = true)
    private String rutUsuario;

    // Fecha de creación o última actualización

    @Column(nullable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    @JsonManagedReference // "Esta es la relacion padre", sirve para evitar ciclos repetitivos al consultar detalles
    private List <Detalle> detalles;

}