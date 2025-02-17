package co.com.andresrojas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.model.Usuario;

public class ServicioUsuario {
    private static final Logger LOG = LoggerFactory.getLogger(ServicioUsuario.class);

    private List<Usuario> usuarios;

    public ServicioUsuario() {
        usuarios = new ArrayList<>();

    }

    public void agregarUsuario(String id, String nombre, String email, String telefono) {
        usuarios.add(new Usuario(id, nombre, email, telefono));
        LOG.info("USUARIO AGREGADO : " + nombre);
    }

    public void agregarUsuario(String id, String nombre, String email, String telefono, LocalDate fechaRegistro) {
        usuarios.add(new Usuario(id, nombre, email, telefono, fechaRegistro));
        LOG.info("USUARIO AGREGADO : " + nombre);
    }

    public List<Usuario> getUsuario() {
        LOG.debug("OBTENIENDO USUARIOS");
        return usuarios;
    }

    public Usuario obtenerUsuario(String id) throws NotFoundException {
        LOG.debug("BUSCANDO USUARIO");
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                LOG.info("USUARIO ENCONTRADO: " + usuario);
                return usuario;
            }

        }
        LOG.error("NO SE ENCONTRO EL USUARIO BUSCADO CON EL NOMBRE: " + id);
        throw new NotFoundException("NO SE ENCONTRO EL USUARIO : " + id + "POR FAVOR VERIFICA EL NOMBRE");

    }

    public void eliminarUsuario(String nombre) throws NotFoundException {
        LOG.debug("BUSCANDO USUARIO PARA ELIMINAR");
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuarios.remove(usuario);
                LOG.info("USUARIO ELIMINADO: " + usuario);
                return;
            }

        }
        LOG.error("NO SE ENCONTRO EL USUARIO PARA ELIMINAR CON NOMBRE: " + nombre);

        throw new NotFoundException(
                "NO SE ENCONTRO EL USUARIO  BUSCADO CON NOMBRE: " + nombre + "POR FAVOR VERIFICA EL NOMBRE");

    }

    public void cambiarGmailUsuario(String id, String email) throws NotFoundException {
        var usuarios = obtenerUsuario(id);
        usuarios.setEmail(email);
    }

    public void cambiarTelefonoUsuario(String id, String telefono) throws NotFoundException {
        var usuarios = obtenerUsuario(id);
        usuarios.setTelefono(telefono);

    }
}