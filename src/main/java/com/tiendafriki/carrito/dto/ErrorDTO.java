package com.tiendafriki.carrito.dto;

import java.util.*;
import java.time.*;
import lombok.*;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private LocalDateTime TimeStamp;
    private Integer Status;
    private String Mensaje;
    private Map <String, String> Errores;
    private String Path;

    public ErrorDTO(LocalDateTime TimeStamp, Integer Status, 
    String Mensaje, Map <String, String> Errores, String Path) {    
        this.TimeStamp = TimeStamp;
        this.Status = Status;
        this.Mensaje = Mensaje;
        this.Errores = Errores;
        this.Path = Path;
    }
}