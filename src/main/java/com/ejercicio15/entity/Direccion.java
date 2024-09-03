package com.ejercicio15.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    @Column(nullable = false)
    private String calle;
    @Column(nullable = false)
    private String numInt;
    @Column(nullable = false)
    private String numExt;
    @Column(nullable = false)
    private String colonia;
    @Column(nullable = false)
    private String cp;
    @Column(nullable = false)
    private String municipio;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;

}