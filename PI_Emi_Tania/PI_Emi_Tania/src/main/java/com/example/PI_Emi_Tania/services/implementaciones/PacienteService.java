package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.Repository.IDao;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.services.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;
    private final ObjectMapper objectMapper;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ObjectMapper objectMapper) {
        this.pacienteIDao = pacienteIDao;
        this.objectMapper = objectMapper;
    }


    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }

    @Override
    public List<PacienteDto> listarPacientes() {
        return pacienteIDao.listarTodos().stream().map(PacienteDto::objectMapper).toList();
    }

    @Override
    public PacienteDto guardar(Paciente paciente) {
        PacienteDto pacienteDto;
        pacienteDto = objectMapper.convertValue(pacienteIDao.guardar(paciente), PacienteDto.class);
        return pacienteDto;


    }

    @Override
    public PacienteDto actualizar(Paciente paciente) {
        PacienteDto pacienteDto;
        pacienteDto = objectMapper.convertValue(pacienteIDao.actualizar(paciente),PacienteDto.class);
        return pacienteDto;
    }

    @Override
    public PacienteDto buscarPorId(int id) {
        PacienteDto pacienteDto;
        pacienteDto = objectMapper.convertValue(pacienteIDao.buscarPorId(id),PacienteDto.class);
        return pacienteDto;
    }

    @Override
    public void eliminarPorId(int id) {
        pacienteIDao.eliminar(id);
    }


}
