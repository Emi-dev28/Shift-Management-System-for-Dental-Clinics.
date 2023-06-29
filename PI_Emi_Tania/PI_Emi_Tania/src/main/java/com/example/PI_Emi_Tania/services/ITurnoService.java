package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    TurnoDto guardar (Turno turno) throws BadRequestException, ResourceNotFoundException;
    List<TurnoDto> listar();
    TurnoDto actualizar(Turno turno);
    TurnoDto buscarPorId(Long id);
    void eliminar(Long id) throws ResourceNotFoundException;
}
