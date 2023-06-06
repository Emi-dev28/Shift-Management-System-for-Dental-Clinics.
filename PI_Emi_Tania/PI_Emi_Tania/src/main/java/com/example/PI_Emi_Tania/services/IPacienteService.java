package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente guardar(Paciente paciente);
    Paciente actualizar (Paciente paciente);
    Paciente buscarPorId(int id);
    void eliminarPorId(int id);
}
