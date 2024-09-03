package com.ejercicio15.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String paterno;
    @Column(nullable = false)
    private String materno;
    @Column(nullable = false)
    private LocalDate fachaNacimiento;
    @Column(nullable = false)
    private Character genero;
    @Column(nullable = false)
    private String curp;
    @Column(nullable = false)
    private String rfc;
    @Embedded
    @Column(nullable = false)
    private Direccion direccion;

}