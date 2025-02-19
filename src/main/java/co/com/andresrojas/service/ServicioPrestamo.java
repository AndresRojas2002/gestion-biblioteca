package co.com.andresrojas.service;

import java.util.ArrayList;
import java.util.List;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.model.EstadoPrestamo;
import co.com.andresrojas.model.Prestamo;

public class ServicioPrestamo {

    private ServicioLibros servicioLibros;
    private ServicioUsuario servicioUsuario;
    private List<Prestamo> prestamos;

    public ServicioPrestamo(ServicioLibros libros, ServicioUsuario usuario) {
        this.servicioLibros = libros;
        this.servicioUsuario = usuario;
        this.prestamos = new ArrayList<>();
    }

    public List<Prestamo> getPrestamo() {
        return prestamos;
    }

    public void agregarPrestamo(String id, String isbn) throws NotFoundException {

        var libro = servicioLibros.obtenerLibroIsbn(isbn);
        var usuario = servicioUsuario.obtenerUsuario(id);

        for (var prestamo : prestamos) {
            if (prestamo.getLibro().getIsbn().equals(isbn) && prestamo.getEstado().equals(EstadoPrestamo.PRESTADO)) {
                var libroPrestado = servicioLibros.obtenerLibroIsbn(isbn);
                throw new NotFoundException(
                        "EL LIBRO " + libroPrestado.getTitulo() + " CON ISBN: " + isbn + " YA ESTA PRESTADO");

            }

            prestamos.add(new Prestamo(libro, usuario));

        }

    }

    public void devolverPrestamo(String id, String isbn) throws NotFoundException {

        for (var prestamo : prestamos) {
            if (prestamo.getUsuario().getId().equals(id) && prestamo.getLibro().getIsbn().equals(isbn)
                    && prestamo.getEstado().equals(EstadoPrestamo.PRESTADO)) {
                prestamo.setEstado(EstadoPrestamo.DISPONIBLE);
                return;
            }
            var libroPrestado = servicioLibros.obtenerLibroIsbn(isbn);
            throw new NotFoundException("EL LIBRO " + libroPrestado.getTitulo() + " CON ISBN: " + isbn + " YA ESTA PRESTADO");

        }

    }

}
