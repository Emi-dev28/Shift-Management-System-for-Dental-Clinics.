package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto buscarOdontologoPorId(Long id);
    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo);
    OdontologoDto actualizarOdontologo (Odontologo odontologo);
    void eliminarOdontologo(Long id);

}
