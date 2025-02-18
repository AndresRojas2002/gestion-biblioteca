
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import co.com.andresrojas.exception.NotFoundException;
import co.com.andresrojas.service.ServicioUsuario;

public class ServiocioUsuarioTest {
    private ServicioUsuario usuario;

    @BeforeEach
    void sepUp() {
        usuario = new ServicioUsuario();
    }

    @Test
    void testAgregarUsuario() throws NotFoundException {
        // GIVEN
        String id = "1";
        String nombre = "Andres Rojas";
        String email = "andres@gmail.com";
        String telefono = "3209134394";
        LocalDate fechaRegistro = LocalDate.of(2025, 1, 13);

        // WHEN
        usuario.agregarUsuario(id, nombre, email, telefono, fechaRegistro);

        // THEN
        var usuario1 = usuario.obtenerUsuario(id);
        assertNotNull(usuario1);
        assertEquals(nombre, usuario1.getNombre());
        assertEquals(email, usuario1.getEmail());
        assertEquals(telefono, usuario1.getTelefono());
        assertEquals(fechaRegistro, usuario1.getFecha());

    }

    @Test
    void testAgregarUsuarioSinFecha() throws NotFoundException {
        String id = "1";
        String nombre = "Andres Rojas";
        String email = "andres@gmail.com";
        String telefono = "3209134394";

        usuario.agregarUsuario(id, nombre, email, telefono);

        var usuario1 = usuario.obtenerUsuario(id);
        assertNotNull(usuario1);
        assertEquals(nombre, usuario1.getNombre());
        assertEquals(email, usuario1.getEmail());
        assertEquals(telefono, usuario1.getTelefono());

    }

    @Test

    void odtenerUsuarioNoExistente() {

        usuario.agregarUsuario("1", "Andres Rojas", "andres@gmail.com", "3209134394");

        var usuario1 = "2";

        assertThrows(NotFoundException.class, () -> usuario.obtenerUsuario(usuario1));

    }

    @Test
    void testEliminarUsuario() throws NotFoundException {

        String id = "1";
        String nombre = "Andres Rojas";
        String email = "andres@gmail.com";
        String telefono = "3209134394";

        usuario.agregarUsuario(id, nombre, email, telefono);

        usuario.eliminarUsuario(id);

        try {
            usuario.obtenerUsuario(id);
            fail();
        } catch (NotFoundException e) {
            assertTrue(true);

        }
    }

    @Test
    void testEliminarUsuarioNoExistente() {

        usuario.agregarUsuario("1", "Andres Rojas", "andres@gmail.com", "3209134394");

        var usuario1 = "2";

        assertThrows(NotFoundException.class, () -> usuario.eliminarUsuario(usuario1));

    }

    @Test
    void testRecorrerListaUsuarios() {
        usuario.agregarUsuario("1", "Andres Rojas", "andres@gmail.com", "3209134394");
        usuario.agregarUsuario("2", "Astrid Acevedo", "astrid@gmail.com", "3293847665");
        usuario.agregarUsuario("3", "Luis Rojas", "luis@gmail.com", "310234965");

        assertEquals(3, usuario.getUsuario().size());

    }

    @Test
    void testCambiarEmail() throws NotFoundException {
        String id = "1";
        String nombre = "Andres Rojas";
        String email = "andres@gmail.com";
        String telefono = "3209134394";

        usuario.agregarUsuario(id, nombre, email, telefono);

        var emailNuevo = "andresrojas@gmail.com";

        usuario.cambiarGmailUsuario("1", emailNuevo);

      var usuario1 = usuario.obtenerUsuario(id);
        assertEquals(emailNuevo, usuario1.getEmail());
        
    }

    @Test
    void testCambiarTelefono() throws NotFoundException {
    String id = "1";
        String nombre = "Andres Rojas";
        String email = "andres@gmail.com";
        String telefono = "320917546";

        usuario.agregarUsuario(id, nombre, email, telefono);

        var telefonoNuevo = "3209134394";

        usuario.cambiarTelefonoUsuario("1", telefonoNuevo);

      var usuario1 = usuario.obtenerUsuario(id);
        assertEquals(telefonoNuevo, usuario1.getTelefono());

}
}
