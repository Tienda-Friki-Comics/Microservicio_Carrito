package com.tiendafriki.carrito;

import com.tiendafriki.carrito.model.Carrito;
import com.tiendafriki.carrito.model.Detalle;
import com.tiendafriki.carrito.repository.CarritoRepo;
import com.tiendafriki.carrito.repository.DetalleRepo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(
            CarritoRepo carritoRepo,
            DetalleRepo detalleRepo
    ) {

        return args -> {

            if (carritoRepo.count() == 0) {

                // === CARRITO 1 === //

                Carrito c1 = new Carrito(
                        null,
                        "11.111.111-1",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                carritoRepo.save(c1);

                // Harry Potter x1 = 23000

                detalleRepo.save(new Detalle(
                        null,
                        c1,
                        1,
                        1,
                        23000,
                        23000
                ));

                // Naruto x2 = 20000

                detalleRepo.save(new Detalle(
                        null,
                        c1,
                        3,
                        2,
                        10000,
                        20000
                ));

                // === CARRITO 2 === //

                Carrito c2 = new Carrito(
                        null,
                        "22.222.222-2",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                carritoRepo.save(c2);

                // One Piece x3 = 30000

                detalleRepo.save(new Detalle(
                        null,
                        c2,
                        4,
                        3,
                        10000,
                        30000
                ));

                // === CARRITO 3 === //

                Carrito c3 = new Carrito(
                        null,
                        "33.333.333-3",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                carritoRepo.save(c3);

                // Hobbit x1 = 43000

                detalleRepo.save(new Detalle(
                        null,
                        c3,
                        2,
                        1,
                        43000,
                        43000
                ));

                // Naruto x1 = 10000

                detalleRepo.save(new Detalle(
                        null,
                        c3,
                        3,
                        1,
                        10000,
                        10000
                ));

                // === CARRITO 4 === //
                // carrito vacío de ejemplo

                Carrito c4 = new Carrito(
                        null,
                        "44.444.444-4",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                carritoRepo.save(c4);

                System.out.println(
                        "[+] DataLoader De Carrito Ejecutado Correctamente [>_<] ..."
                );
            }
        };
    }

}
