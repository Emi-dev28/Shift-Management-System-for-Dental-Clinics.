package com.example.PI_Emi_Tania.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Entity
@Table(name="PACIENTES")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3, max=25, message ="El nombre debe tener entre 3 y 25 caracteres")
    @NotNull
    // ^ y $ aseguran que la cadena coincida completamente con el patrón.
    //[A-Za-záéíóúñÁÉÍÓÚÑ] representa un rango de letras que incluye tanto mayúsculas como minúsculas, así como letras acentuadas en español (á, é, í, ó, ú, ñ, Á, É, Í, Ó, Ú, Ñ).
    //\\s permite espacios en blanco.
    //+ indica que el patrón anterior puede aparecer una o más veces.
    @Pattern(regexp = "^[A-Za-záéíóúñÁÉÍÓÚÑ\\\\s]+$\"")

    private String nombre;
    @Size(min=3, max=25, message = "El apellido debe contener entre 3 y 25 caracteres")
    @NotNull
    private String apellido;
    @Size(max = 12)
    @NotNull
    @Pattern(regexp = "[\\d{8}]")
    private String dni;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="domicilio_id")
    private Domicilio domicilio;




    public Paciente(Long id, String nombre, String apellido, String dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.id = id;
    }

    public Paciente() {
    }

    public Long getId() {
        return id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de ingreso: " + fechaIngreso + " - Domicilio: " + domicilio;
    }
}
