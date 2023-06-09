package com.example.PI_Emi_Tania.services;

import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    TurnoDto guardar (Turno turno);
    List<TurnoDto> listar();
    TurnoDto actualizar(Turno turno);
    TurnoDto buscarPorId(int id);
    void eliminar(int id);
}
