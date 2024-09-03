package com.ejercicio15.controller;

import com.ejercicio15.entity.Usuario;
import com.ejercicio15.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarUsuarioPorId() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Perez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioService.findByLogin(1L)).thenReturn(usuario);
        ResponseEntity<?> response = usuarioController.buscarUsuarioPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void buscarUsuarioPorId_NotFound() {
        when(usuarioService.findByLogin(1L)).thenThrow(new EntityNotFoundException("Usuario no encontrado con id: 1"));
        ResponseEntity<?> response = usuarioController.buscarUsuarioPorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado con id: 1", response.getBody());
    }

    @Test
    void obtenerTodosLosUsuarios() {
        Usuario usuario1 = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        Usuario usuario2 = new Usuario(2L, "Ana Gómez", "Gómez", "Gómez", LocalDate.now(), 'F', "CURP", "RFC", null);
        when(usuarioService.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));
        ResponseEntity<?> response = usuarioController.obtenerTodosLosUsuarios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((List<?>) response.getBody()).contains(usuario1));
        assertTrue(((List<?>) response.getBody()).contains(usuario2));
    }

    @Test
    void guardarUsuario() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioService.save(usuario)).thenReturn(usuario);
        ResponseEntity<?> response = usuarioController.guardarUsuario(usuario);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void actualizarUsuario() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioService.update(usuario)).thenReturn(usuario);
        ResponseEntity<?> response = usuarioController.actualizarUsuario(1L, usuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void actualizarUsuario_NotFound() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioService.update(usuario)).thenThrow(new EntityNotFoundException("Usuario no encontrado con id: 1"));
        ResponseEntity<?> response = usuarioController.actualizarUsuario(1L, usuario);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado con id: 1", response.getBody());
    }

    @Test
    void eliminarUsuario() {
        when(usuarioService.delete(1L)).thenReturn("Usuario con id: 1 eliminado con exito");
        ResponseEntity<?> response = usuarioController.eliminarUsuario(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario con id: 1 eliminado con exito", response.getBody());
    }

    @Test
    void eliminarUsuario_NotFound() {
        when(usuarioService.delete(1L)).thenThrow(new EntityNotFoundException("Usuario con id: 1 no encontrado"));
        ResponseEntity<?> response = usuarioController.eliminarUsuario(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario con id: 1 no encontrado", response.getBody());
    }

}