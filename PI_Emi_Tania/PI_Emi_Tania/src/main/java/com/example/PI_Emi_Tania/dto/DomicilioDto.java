package com.example.PI_Emi_Tania.dto;

import com.example.PI_Emi_Tania.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioDto {
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public DomicilioDto() {
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public DomicilioDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static DomicilioDto objectMapper(Domicilio domicilio){
        ObjectMapper objectMapper = new ObjectMapper();
        DomicilioDto domicilioDto = objectMapper.convertValue(domicilio, DomicilioDto.class);
        return domicilioDto;
    }
}




