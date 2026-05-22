package com.tiendafriki.carrito.model;

import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // los numeros usan NotNull, no NotBlank
    // Corregimos los NotBlanks por NotNull
    // Agregamos Positive para asegurarnos que no se ingrese un numero negativo
    // al solicitar el id

    @NotNull(message = "[+] El ID Del Producto No Puede Quedar Nulo [>_<] ... ")
    @Positive(message = "[+] El ID Del Producto Debe Ser Positivo [>_<] ... ")
    @Column(nullable = false)
    private Integer productoId;

    @Min(value = 1, message = "[+] La Cantidad Debe Ser Mayor A 0 [>_<] ... ")
    @Positive(message = "[+] La Cantidad Debe Ser Positiva [>_<] ... ")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "[+] El Precio No Puede Quedar Nulo [>_<] ... ")
    @Positive(message = "[+] El Precio Debe Ser Positivo [>_<] ... ")
    @Column(nullable = false)
    private Integer precioxUnidad;

    @Column(nullable = false)
    private Integer subtotal;

}
