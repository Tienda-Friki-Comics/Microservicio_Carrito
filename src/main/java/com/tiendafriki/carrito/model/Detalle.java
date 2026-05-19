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

    // los numeros usan NotNull, no NotBlank
    // Corregimos los NotBlanks por NotNull
    // Agregamos Positive para asegurarnos que no se ingrese un numero negativo
    // al solicitar el id

    @NotNull(message = "[+] El ID Del Producto No Puede Quedar Nulo [>_<] ... ")
    @Positive(message = "[+] El ID Del Producto Debe Ser Positivo [>_<] ... ")
    @Column(nullable = false)
    private Integer ProductoID;

    @Min(value = 1, message = "[+] La Cantidad Debe Ser Mayor A 0 [>_<] ... ")
    @Positive(message = "[+] La Cantidad Debe Ser Positiva [>_<] ... ")
    @Column(nullable = false)
    private Integer Cantidad;

    @NotNull(message = "[+] El Precio No Puede Quedar Nulo [>_<] ... ")
    @Positive(message = "[+] El Precio Debe Ser Positivo [>_<] ... ")
    @Column(nullable = false)
    private Integer PrecioxUnidad;

    @Column(nullable = false)
    private Integer Subtotal;

}

/*

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

*/