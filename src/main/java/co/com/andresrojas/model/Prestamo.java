package co.com.andresrojas.model;

import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private EstadoPrestamo estado;

    public Prestamo(Libro libro, Usuario usuario, LocalDate fechaPrestamo, EstadoPrestamo estado) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.estado = estado;
    }

    public Prestamo(Libro libro, Usuario usuario) {
        this(libro, usuario, LocalDate.now(), EstadoPrestamo.DISPONIBLE);
    }

    public Prestamo(Libro libro, Usuario usuario, LocalDate fechaPrestamo) {
        this(libro, usuario, fechaPrestamo, EstadoPrestamo.DISPONIBLE);
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    

}
