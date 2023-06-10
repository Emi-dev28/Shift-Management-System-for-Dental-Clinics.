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
public class PacienteService implements IPacienteService<PacienteDto>{
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
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);
        pacienteIDao.guardar(pacienteDto);
        return paciente;


    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    @Override
    public Paciente buscarPorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public void eliminarPorId(int id) {
        pacienteIDao.eliminar(id);
    }


}
