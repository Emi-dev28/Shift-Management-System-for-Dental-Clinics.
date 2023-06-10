package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto buscarOdontologoPorId(int id);
    List<Odontologo> listaDeOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo);
    OdontologoDto actualizarOdontologo (Odontologo odontologo);
    void eliminarOdontologo(int id);

}
