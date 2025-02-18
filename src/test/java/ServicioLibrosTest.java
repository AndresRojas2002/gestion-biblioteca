import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.service.ServicioLibros;

public class ServicioLibrosTest {
    private ServicioLibros servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioLibros();
    }

    @Test
    void testAgregarLibro() throws NotFoundException {
        // GIVEN
        String titulo = "Cien años de Soledad";
        String autor = "Gabriel Garcia Marquez";
        String editorial = "Sudamericana";
        String isbn = "978-0307474728";
        LocalDate fechaPublicacion = LocalDate.of(1967, 5, 30);

        // WHEN
        servicio.agregarLibro(titulo, autor, editorial, isbn, fechaPublicacion);

        // THEN
        var libro = servicio.obtenerLibroIsbn(isbn);
        assertNotNull(libro);
        assertEquals(titulo, libro.getTitulo());
        assertEquals(autor, libro.getAutor());
        assertEquals(editorial, libro.getEditorial());
        assertEquals(fechaPublicacion, libro.getFechaPublicacion());

    }

    @Test
    void testEliminarLibro() throws NotFoundException {
        // GIVEN
        String titulo = "Cien años de Soledad";
        String autor = "Gabriel Garcia Marquez";
        String editorial = "Sudamericana";
        String isbn = "978-0307474728";
        LocalDate fechaPublicacion = LocalDate.of(1967, 5, 30);
        servicio.agregarLibro(titulo, autor, editorial, isbn, fechaPublicacion);

        // WHEN
        servicio.eliminarLibro(isbn);

        // THEN
        try {
            servicio.obtenerLibroIsbn(isbn);
            fail();

        } catch (NotFoundException e) {
            assertTrue(true);

        }

    }

    @Test
    void testOdtenerLibroNoExistente() {

        // GIVEN
        servicio.agregarLibro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", "978-0307474728",
                LocalDate.of(1967, 5, 30));
                
        var libro = "978-0307474729";

        // WHEN & THEN

        assertThrows(NotFoundException.class, () -> servicio.obtenerLibroIsbn(libro));
    }

    @Test
    void testEliminarLibroNoExistente () {
        // GIVEN
        servicio.agregarLibro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", "978-0307474728",
                LocalDate.of(1967, 5, 30));
        var libro = "978-0307474729";

        //WHEN & THEN
        assertThrows (NotFoundException.class, ()-> servicio.eliminarLibro(libro));
    }

    @Test
    void testRecorrerListaLibros(){
        //GIVEN
        servicio.agregarLibro("Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", "978-0307474728",
                LocalDate.of(1967, 5, 30));
        servicio.agregarLibro("El Quijote", "Miguel de Cervantes", "Santillana", "978-8468087814",
                LocalDate.of(1605, 1, 10));

        //WHEN & THEN
        assertNotNull(servicio);
        assertEquals(2, servicio.getLibros().size());
    }

}
