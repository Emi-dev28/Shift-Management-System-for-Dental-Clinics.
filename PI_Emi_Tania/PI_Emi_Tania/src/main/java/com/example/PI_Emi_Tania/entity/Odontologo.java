package com.example.PI_Emi_Tania.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La matricula no puede ser nulo")
    @NotBlank(message = "Debe especificarse la matricula del odontologo")
    @Pattern(regexp = "^[A-Z]{2}-\\d{1,3}\\d*$")
    @Size(min=10, message ="La matricula debe 10 o mas caracteres")
    private String matricula;
    @Size(min=3, max=25, message ="El nombre debe tener entre 3 y 25 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @Pattern(regexp = "^[A-Za-záéíóúñÁÉÍÓÚÑ\\\\s]+$\"")
    private String nombre;
    @Size(min=3, max=25, message ="El apellido debe tener entre 3 y 25 caracteres")
    @NotNull(message = "El apellido no puede ser nulo")
    private String apellido;


<<<<<<< HEAD
    public Odontologo(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo() {
    }

=======
>>>>>>> 22f1f9b6bfcd36944d1aaf111dc025e8fbac9cd5
    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Odontologo() {
        // Constructor por defecto
    }


    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }
}
