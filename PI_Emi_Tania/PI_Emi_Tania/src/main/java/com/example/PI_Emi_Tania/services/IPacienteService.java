package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.entity.Paciente;

import java.util.List;

public interface IPacienteService <T> {
    List<Paciente> listarPacientes();

    T guardar(Paciente paciente);

    T actualizar(Paciente paciente);

    T buscarPorId(int id);

    void eliminarPorId(int id);
}
