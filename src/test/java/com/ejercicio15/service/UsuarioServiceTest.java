package com.ejercicio15.service;

import com.ejercicio15.entity.Usuario;
import com.ejercicio15.exception.DataBaseException;
import com.ejercicio15.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByLogin() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.findByLogin(1L);
        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getNombre(), result.getNombre());
    }

    @Test
    void findByLogin_NotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.findByLogin(1L);
        });
        assertEquals("Usuario no encontrado con id: 1", exception.getMessage());
    }

    @Test
    void findByLogin_DataBaseException() {
        when(usuarioRepository.findById(1L)).thenThrow(new DataAccessException("Database error") {});
        Exception exception = assertThrows(DataBaseException.class, () -> {
            usuarioService.findByLogin(1L);
        });
        assertEquals("Error al acceder a los datos del usuario", exception.getMessage());
    }

    @Test
    void findAll() {
        Usuario usuario1 = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        Usuario usuario2 = new Usuario(2L, "Ana Gómez", "Gómez", "Gómez", LocalDate.now(), 'F', "CURP", "RFC", null);
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));
        List<Usuario> result = usuarioService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(usuario1));
        assertTrue(result.contains(usuario2));
    }

    @Test
    void findAll_Empty() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.findAll();
        });
        assertEquals("No se encontraron usuarios en la base de datos", exception.getMessage());
    }

    @Test
    void findAll_DataBaseException() {
        when(usuarioRepository.findAll()).thenThrow(new DataAccessException("Database error") {});
        Exception exception = assertThrows(DataBaseException.class, () -> {
            usuarioService.findAll();
        });
        assertEquals("Error al acceder a la lista de usuarios", exception.getMessage());
    }

    @Test
    void save() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioService.save(usuario);
        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
    }

    @Test
    void save_DataBaseException() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.save(usuario)).thenThrow(new DataAccessException("Database error") {});
        Exception exception = assertThrows(DataBaseException.class, () -> {
            usuarioService.save(usuario);
        });
        assertEquals("Error al guardar el usuario en la base de datos", exception.getMessage());
    }

    @Test
    void update() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioService.update(usuario);
        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
    }

    @Test
    void update_NotFound() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.update(usuario);
        });
        assertEquals("Usuario no encontrado con id: 1", exception.getMessage());
    }

    @Test
    void update_DataBaseException() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "Pérez", "Pérez", LocalDate.now(), 'M', "CURP", "RFC", null);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenThrow(new DataAccessException("Database error") {});
        Exception exception = assertThrows(DataBaseException.class, () -> {
            usuarioService.update(usuario);
        });
        assertEquals("Error al actualizar el usuario en la base de datos", exception.getMessage());
    }

    @Test
    void delete() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        String result = usuarioService.delete(1L);
        assertEquals("Usuario con id: 1 eliminado con exito", result);
    }

    @Test
    void delete_NotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.delete(1L);
        });
        assertEquals("Usuario con id: 1 no encontrado", exception.getMessage());
    }

    @Test
    void delete_DataBaseException() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doThrow(new DataAccessException("Database error") {}).when(usuarioRepository).deleteById(1L);
        Exception exception = assertThrows(DataBaseException.class, () -> {
            usuarioService.delete(1L);
        });
        assertEquals("Error al eliminar el usuario en la base de datos", exception.getMessage());
    }

}