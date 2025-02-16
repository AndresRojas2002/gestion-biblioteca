import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Not;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.service.ServicioLibros;

public class ServicioLibrosTest {
    private ServicioLibros servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioLibros();
    }

    @Test
    void agregarLibro() throws NotFoundException {
        //GIVEN
        String titulo = "Cien años de Soledad"; 
        String autor = "Gabriel Garcia Marquez";
        String editorial = "Sudamericana";
        String isbn = "978-0307474728";
        LocalDate fechaPublicacion = LocalDate.of(1967, 5, 30);

        //WHEN
         servicio.agregarLibro(titulo, autor, editorial, isbn, fechaPublicacion);

        //THEN
        var libro = servicio.obtenerLibro(isbn);
        assertNotNull(libro);
        assertEquals(titulo, libro.getTitulo());
        assertEquals(autor, libro.getAutor());
        assertEquals(editorial, libro.getEditorial());
        
        assertEquals(fechaPublicacion, libro.getFechaPublicacion());
       
}}
