package com.ejercicio15.initializer;

import com.ejercicio15.entity.Direccion;
import com.ejercicio15.entity.Usuario;
import com.ejercicio15.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class UsuarioInitializer {

    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void init() {
        // Agregamos tres usuarios diferentes con datos de ejemplo
        usuarioService.save(new Usuario(
                null,  // ID generado automáticamente
                "Juan",
                "Pérez",
                "Gómez",
                LocalDate.of(1990, 5, 15),
                'M',
                "JUAP900515HDFGMS07",
                "JUAG900515HDFGMS07",
                new Direccion("Calle 123", "Int 1", "Ext 2", "Colonia Centro", "12345", "Municipio Central", "Estado Central", "País Central")
        ));

        usuarioService.save(new Usuario(
                null,  // ID generado automáticamente
                "Ana",
                "Gómez",
                "Reyes",
                LocalDate.of(1985, 8, 22),
                'F',
                "ANAG850822MDFRNS09",
                "ANAG850822MDFRNS09",
                new Direccion("Avenida 456", "Int 2", "Ext 3", "Colonia Norte", "67890", "Municipio Norte", "Estado Norte", "País Norte")
        ));

        usuarioService.save(new Usuario(
                null,  // ID generado automáticamente
                "Carlos",
                "Márquez",
                "Vásquez",
                LocalDate.of(1992, 12, 30),
                'M',
                "CARL921230HDFVCS08",
                "CARL921230HDFVCS08",
                new Direccion("Boulevard 789", "Int 3", "Ext 4", "Colonia Sur", "54321", "Municipio Sur", "Estado Sur", "País Sur")
        ));
    }

}