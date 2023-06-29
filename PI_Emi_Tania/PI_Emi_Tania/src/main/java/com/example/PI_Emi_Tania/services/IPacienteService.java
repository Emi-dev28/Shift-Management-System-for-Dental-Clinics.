package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService  {
    List<PacienteDto> listarPacientes();

    PacienteDto guardar(Paciente paciente) throws BadRequestException;

    PacienteDto actualizar(Paciente paciente) throws ResourceNotFoundException;

    PacienteDto buscarPorId(Long id) throws ResourceNotFoundException;

    void eliminarPorId(Long id) throws ResourceNotFoundException;

}
