package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.Repository.IDao;
import com.example.PI_Emi_Tania.Repository.PacienteRepository;
import com.example.PI_Emi_Tania.dto.DomicilioDto;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.services.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ObjectMapper objectMapper, PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = new ObjectMapper();
    }



    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacientesDtos = pacientes.stream()
                .map(paciente-> {
                    Domicilio dom = paciente.getDomicilio();
                    DomicilioDto domicilioDto = objectMapper.convertValue(dom,DomicilioDto.class);
                    return new PacienteDto(paciente.getId(),paciente.getNombre(), paciente.getApellido(),paciente.getDni(),paciente.getFechaIngreso(),domicilioDto);
        }).toList();
        LOGGER.info("Lista de pacientes: {}", pacientesDtos);
        return pacientesDtos;
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
    public PacienteDto buscarPorId(Long id) {
        PacienteDto pacienteDto;
        pacienteDto = objectMapper.convertValue(pacienteIDao.buscarPorId(id),PacienteDto.class);
        return pacienteDto;
    }

    @Override
    public void eliminarPorId(Long id) {
        pacienteRepository.deleteById(id);
    }


}
