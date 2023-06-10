package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Paciente;

import java.util.List;

public interface IPacienteService  {
    List<PacienteDto> listarPacientes();

    PacienteDto guardar(Paciente paciente);

    PacienteDto actualizar(Paciente paciente);

    PacienteDto buscarPorId(int id);

    void eliminarPorId(int id);
}
