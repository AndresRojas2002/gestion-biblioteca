package co.com.andresrojas.model;

import java.time.LocalDate;

public class Libro {

private String titulo;
private String autor;
private String editorial;
private String isbn;
private LocalDate fechaPublicacion;






public Libro(String titulo, String autor, String editorial, String isbn, LocalDate fechaPublicacion) {
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.isbn = isbn;
    this.fechaPublicacion = fechaPublicacion;
}


public String getTitulo() {
    return titulo;
}


public String getAutor() {
    return autor;
}


public String getEditorial() {
    return editorial;
}


public String getIsbn() {
    return isbn;
}


public LocalDate getFechaPublicacion() {
    return fechaPublicacion;
}








}
