package com.ejercicio15.service;

import com.ejercicio15.entity.Usuario;
import com.ejercicio15.exception.DataBaseException;
import com.ejercicio15.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByLogin(Long id) throws DataBaseException {
        try {
            return usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al acceder a los datos del usuario", e);
        }
    }

    public List<Usuario> findAll() throws DataBaseException {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron usuarios en la base de datos");
            }
            return usuarios;
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al acceder a la lista de usuarios", e);
        }
    }

    public Usuario save(Usuario usuario) throws DataBaseException {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al guardar el usuario en la base de datos", e);
        }
    }

    public Usuario update(Usuario usuario) throws DataBaseException {
        try {
            if(usuarioRepository.existsById(usuario.getId()))
                return usuarioRepository.save(usuario);
            else
                throw new EntityNotFoundException("Usuario no encontrado con id: " + usuario.getId());
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al actualizar el usuario en la base de datos", e);
        }
    }

    public String delete(Long id) throws DataBaseException {
        try {
            if(usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return "Usuario con id: " + id + " eliminado con exito";
            } else
                throw new EntityNotFoundException("Usuario con id: " + id + " no encontrado");
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al eliminar el usuario en la base de datos", e);
        }
    }

}