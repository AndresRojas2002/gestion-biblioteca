package co.com.andresrojas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.model.Libro;

public class ServicioLibros {

    private static final Logger LOG = LoggerFactory.getLogger(ServicioLibros.class);

    private List<Libro> libros;

    public ServicioLibros() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(String titulo, String autor, String editorial, String isbn, LocalDate fechaPublicacion) {
        LOG.info("AGREGANDO LIBRO: " + titulo);
        libros.add(new Libro(titulo, autor, editorial, isbn, fechaPublicacion));
    }

    public List<Libro> obtenerLibros() {
        LOG.info("OBTENIENDO LIBROS");
        return libros;
    }

    public Libro obtenerLibro(String isbn) throws NotFoundException {
        LOG.info("BUSCANDO LIBRO CON ISBN: " + isbn);
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                LOG.info("LIBRO ENCONTRADO: " + libro);
                return libro;

            }

        }
        LOG.error("NO SE ENCONTRO EL LIBRO BUSCADO CON ISBN: " + isbn);
        throw new NotFoundException("NO SE ENCONTRO EL LIBRO BUSCADO CON ISBN: " + isbn);

    }

    public void eliminarLibro(String isbn) throws NotFoundException {
        LOG.info("BUSCANDO LIBRO PARA ELIMINAR CON ISBN: " + isbn);
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                libros.remove(libro);
                LOG.info("LIBRO ELIMINADO: " + libro);
                return;
            }
        }
        LOG.error("NO SE ENCONTRO EL LIBRO BUSCADO CON ISBN: " + isbn);
        throw new NotFoundException("NO SE ENCONTRO EL LIBRO BUSCADO CON ISBN: " + isbn);

    }

}
