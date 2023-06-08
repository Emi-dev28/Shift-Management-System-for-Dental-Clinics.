package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo buscarOdontologoPorId(int id);
    List<Odontologo> listaDeOdontologos();
    Odontologo registrarOdontologo(Odontologo odontologo);
    Odontologo actualizarOdontologo (Odontologo odontologo);
    void eliminarOdontologo(int id);

}
