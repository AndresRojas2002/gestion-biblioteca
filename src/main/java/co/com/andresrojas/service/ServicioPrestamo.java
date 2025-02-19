package co.com.andresrojas.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.model.EstadoPrestamo;
import co.com.andresrojas.model.Prestamo;

public class ServicioPrestamo {

    private static final Logger LOG = LoggerFactory.getLogger(ServicioPrestamo.class);

    private ServicioLibros servicioLibros;
    private ServicioUsuario servicioUsuario;
    private List<Prestamo> prestamos;

    public ServicioPrestamo(ServicioLibros libros, ServicioUsuario usuario) {
        this.servicioLibros = libros;
        this.servicioUsuario = usuario;
        this.prestamos = new ArrayList<>();
    }

    public List<Prestamo> getPrestamo() {
        LOG.debug("OBTENIENDO LISTA DE PRESTAMOS");
        return prestamos;
    }

    public void agregarPrestamo(String id, String isbn) throws NotFoundException {
        LOG.debug("AGREGANDO PRESTAMO: " + id + ", " + isbn);
        var usuario = servicioUsuario.obtenerUsuario(id);
        var libro = servicioLibros.obtenerLibroIsbn(isbn);

        for (var prestamo : prestamos) {
            LOG.debug("VERIFICANDO PRESTAMO: " + prestamo);

            if (prestamo.getLibro().getIsbn().equals(isbn) && prestamo.getEstado().equals(EstadoPrestamo.PRESTADO)) {
                var libroPrestado = servicioLibros.obtenerLibroIsbn(isbn);
                LOG.error("EL LIBRO " + libroPrestado.getTitulo() + " CON ISBN: " + isbn + " YA ESTA PRESTADO");
                throw new NotFoundException(
                        "EL LIBRO " + libroPrestado.getTitulo() + " CON ISBN: " + isbn + " YA ESTA PRESTADO");

            }

        }

        prestamos.add(new Prestamo(libro, usuario));
        LOG.info("PRESTAMO AGREGADO CON EXITO: " + new Prestamo(libro, usuario));
    }

    public void devolverPrestamo(String id, String isbn) throws NotFoundException {

        for (var prestamo : prestamos) {
            LOG.debug("VERIFICANDO PRESTAMO: " + prestamo);

            if (prestamo.getUsuario().getId().equals(id) && prestamo.getLibro().getIsbn().equals(isbn)
                    && prestamo.getEstado().equals(EstadoPrestamo.PRESTADO)) {
                prestamo.setEstado(EstadoPrestamo.DISPONIBLE);
                LOG.info("DEVOLVIENDO PRESTAMO: " + prestamo);
                return;
            }

        }
        
        LOG.error("NO SE ENCONTRO UN PRESTAMO DEL LIBRO: " + isbn + " PARA EL USUARIO: " + id);
        throw new NotFoundException("NO SE ENCONTRO UN PRESTAMO DEL LIBRO: " + isbn + " PARA EL USUARIO: " + id);
                

    }

}
