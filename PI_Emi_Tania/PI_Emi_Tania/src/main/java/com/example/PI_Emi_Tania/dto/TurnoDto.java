package com.example.PI_Emi_Tania.dto;

import com.example.PI_Emi_Tania.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {
    private String paciente;
    private String odontologo;
    private LocalDateTime fechaYHora;

    public TurnoDto(String paciente, String odontologo, LocalDateTime fechaYHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public TurnoDto() {
    }
    public static TurnoDto fromTurno(Turno turno){
        String paciente = turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido();
        String odontologo = turno.getOdontologo().getNombre() + "" + turno.getOdontologo().getApellido();
        return new TurnoDto(paciente, odontologo, turno.getFechaYHora());
    }
}
