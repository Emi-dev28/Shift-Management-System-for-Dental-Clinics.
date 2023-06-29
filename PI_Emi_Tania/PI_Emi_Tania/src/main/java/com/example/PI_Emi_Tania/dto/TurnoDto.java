package com.example.PI_Emi_Tania.dto;

import com.example.PI_Emi_Tania.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {
    private Long id;
    private String paciente;
    private String odontologo;
    private LocalDateTime fechaYHora;

    public TurnoDto(Long id, String paciente, String odontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public TurnoDto() {
    }

    public static TurnoDto fromTurno(Turno turno) {
        String paciente = turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido();
        String odontologo = turno.getOdontologo().getNombre() + "" + turno.getOdontologo().getApellido();
        return new TurnoDto(turno.getId(),paciente, odontologo, turno.getFechaYHora());
    }

    public Long getId() {
        return id;
    }



    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(String odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
