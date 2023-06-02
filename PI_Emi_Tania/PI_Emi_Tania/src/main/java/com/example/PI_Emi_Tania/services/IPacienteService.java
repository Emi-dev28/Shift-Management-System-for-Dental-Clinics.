package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarPacientePorDni(String dni);
}
