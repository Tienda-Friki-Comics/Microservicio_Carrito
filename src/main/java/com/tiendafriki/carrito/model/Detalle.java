package com.tiendafriki.carrito.model;

import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Detalle")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    @JsonBackReference // "Esta es la relacion hija", sirve para evitar ciclos infinitos cuando carrito consulte detalles
    private Carrito carrito;

    @Column(nullable = false)
    private Integer productoId;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer precioxUnidad;

    @Column(nullable = false)
    private Integer subtotal;

}
