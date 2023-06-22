package com.example.PI_Emi_Tania.services.implementaciones;


import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.services.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TurnoService implements ITurnoService {
    private IDao<Turno> turnoIDao;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
        this.turnoIDao = turnoIDao;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TurnoDto guardar(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.guardar(turno));
    }

    @Override
    public List<TurnoDto> listar() {
        return turnoIDao.listarTodos().stream().map(TurnoDto::fromTurno).toList();
    }

    @Override
    public TurnoDto actualizar(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.actualizar(turno));
    }

    @Override
    public TurnoDto buscarPorId(int id) {
        return TurnoDto.fromTurno(turnoIDao.buscarPorId(id));
    }

    @Override
    public void eliminar(int id) {
        turnoIDao.eliminar(id);
    }

}
