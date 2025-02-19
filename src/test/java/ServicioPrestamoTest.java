
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.model.EstadoPrestamo;
import co.com.andresrojas.model.Libro;
import co.com.andresrojas.model.Usuario;
import co.com.andresrojas.service.ServicioLibros;
import co.com.andresrojas.service.ServicioPrestamo;
import co.com.andresrojas.service.ServicioUsuario;

public class ServicioPrestamoTest {
    private ServicioUsuario servicioUsuario;
    private ServicioLibros servicioLibro;
    private ServicioPrestamo servicio;

    @BeforeEach
    void setUp() {
        servicioLibro = Mockito.mock(ServicioLibros.class);
        servicioUsuario = Mockito.mock(ServicioUsuario.class);
        servicio = new ServicioPrestamo(servicioLibro, servicioUsuario);
    }

    @Test
    void testAgregarPrestamoDisponoble() throws NotFoundException {
        var id = "1";
        var isbn = "978-0307474728";

        var mockLibro = new Libro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", isbn,
                LocalDate.of(1967, 5, 30));

        var mockUser = new Usuario(id, "Andres Rojas", "andres@email", "3209134394");

        Mockito.when(servicioUsuario.obtenerUsuario(id)).thenReturn(mockUser);
        Mockito.when(servicioLibro.obtenerLibroIsbn(isbn)).thenReturn(mockLibro);

        servicio.agregarPrestamo(id, isbn);

        var prestamos = servicio.getPrestamo();
        assertEquals(1, prestamos.size());

        var prestamo = prestamos.get(0);
        assertNotNull(prestamo.getLibro());
        assertNotNull(prestamo.getUsuario());
        assertEquals(EstadoPrestamo.PRESTADO, prestamo.getEstado());

    }

    @Test
    void testAgregarPrestamoNoDisponible() throws NotFoundException {
        var id = "1";
        var isbn = "978-0307474728";

        var mockLibro = new Libro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", isbn,
                LocalDate.of(1967, 5, 30));

        var mockUser = new Usuario(id, "Andres Rojas", "andres@email", "3209134394");

        Mockito.when(servicioUsuario.obtenerUsuario(id)).thenReturn(mockUser);
        Mockito.when(servicioLibro.obtenerLibroIsbn(isbn)).thenReturn(mockLibro);

        servicio.agregarPrestamo(id, isbn);

        var prestamos = servicio.getPrestamo();
        assertEquals(1, prestamos.size());

        var prestamo = prestamos.get(0);
        assertNotNull(prestamo.getLibro());
        assertNotNull(prestamo.getUsuario());
        assertEquals(EstadoPrestamo.PRESTADO, prestamo.getEstado());

        // Agregamos un segundo prestamo
        assertThrows(NotFoundException.class, () -> {
            servicio.agregarPrestamo(id, isbn);
        });
    }

    @Test
    void testDevolverPrestamoExistente() throws NotFoundException {
        var id = "1";
        var isbn = "978-0307474728";

        var mockLibro = new Libro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", isbn,
                LocalDate.of(1967, 5, 30));

        var mockUser = new Usuario(id, "Andres Rojas", "andres@email", "3209134394");

        Mockito.when(servicioUsuario.obtenerUsuario(id)).thenReturn(mockUser);
        Mockito.when(servicioLibro.obtenerLibroIsbn(isbn)).thenReturn(mockLibro);

        servicio.agregarPrestamo(id, isbn);

        servicio.devolverPrestamo(id, isbn);

        var prestamos = servicio.getPrestamo();
        assertEquals(1, prestamos.size());

        var prestamo = prestamos.getFirst();
        assertEquals(id, prestamo.getUsuario().getId());
        assertEquals(isbn, prestamo.getLibro().getIsbn());
        assertEquals(EstadoPrestamo.DISPONIBLE, prestamo.getEstado());
    }

    @Test
    void testDevolverPrestamoNoExistente() throws NotFoundException {
        var id = "1";
        var isbn = "978-0307474728";

        assertThrows(NotFoundException.class, () -> servicio.devolverPrestamo(id, isbn));
    }
}
