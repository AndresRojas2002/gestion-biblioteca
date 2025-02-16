package co.com.andresrojas.model;

import java.time.LocalDate;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;

   
   

    
    public Usuario(String id, String nombre, String email, String telefono) {
       this(id, nombre, email, telefono, LocalDate.now());
    }


    public Usuario(String id, String nombre, String email, String telefono, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }


    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFecha() {
        return fechaRegistro;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


   

    

    


}
