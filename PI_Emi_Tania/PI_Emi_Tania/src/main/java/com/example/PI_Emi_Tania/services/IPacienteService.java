package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService  {
    List<PacienteDto> listarPacientes();

    PacienteDto guardar(Paciente paciente);

    PacienteDto actualizar(Paciente paciente);

    PacienteDto buscarPorId(Long id);

    void eliminarPorId(Long id) throws ResourceNotFoundException;

    PacienteDto buscarPacientePorId(Long id);
}
