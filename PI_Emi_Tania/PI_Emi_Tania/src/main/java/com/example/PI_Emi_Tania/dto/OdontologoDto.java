package com.example.PI_Emi_Tania.dto;

import com.example.PI_Emi_Tania.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDto {
    private String matricula;
    private String nombre;
    private String apellido;

    public OdontologoDto(Long id, String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public OdontologoDto() {
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


    public static OdontologoDto objectMapper(Odontologo odontologo){
        ObjectMapper objectMapper = new ObjectMapper();
        OdontologoDto odontologoDto = objectMapper.convertValue(odontologo, OdontologoDto.class);
        return odontologoDto;
    }








}
