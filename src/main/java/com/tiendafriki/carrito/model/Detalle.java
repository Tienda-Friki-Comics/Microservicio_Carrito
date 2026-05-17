package com.tiendafriki.carrito.model;

import jakarta.validation.constraints.*;
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
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito Carrito;

    @NotBlank(message = "[+] El ID Del Producto No Puede Quedar Vacio [>_<] ... ")
    @Column(nullable = false)
    private Integer ProductoID;

    @Min(value = 1, message = "[+] El Digito Ingresado Debe Ser Mayor A Uno [>_<] ... ")
    @Positive(message = "[+] El Digito Ingresado Debe Ser Positivo [>_<] ... ")
    @Column(nullable = false)
    private Integer Cantidad;

    @Min(value = 5000, message = "[+] El Precio Debe Ser Mayor a $5.000 [>_<] ... ")
    @NotBlank(message = "[+] El Precio No Puede Quedar Vacio [>_<] ... ")
    @Column(nullable = false)
    private Integer PrecioxUnidad;

    @Column(nullable = false)
    private Integer Subtotal;

}