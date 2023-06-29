package com.example.PI_Emi_Tania.services.implementaciones;


import com.example.PI_Emi_Tania.Repository.PacienteRepository;
import com.example.PI_Emi_Tania.Repository.TurnoRepository;
import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.services.ITurnoService;
import com.example.PI_Emi_Tania.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TurnoService implements ITurnoService {
    private TurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);




    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Override
    public TurnoDto guardar(Turno turno) throws BadRequestException, ResourceNotFoundException {
        TurnoDto turnoDto = null;
        PacienteDto pacienteDto = pacienteService.buscarPorId(turno.getPaciente().getId());
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if(odontologoDto == null || pacienteDto == null){
        if(pacienteDto == null && odontologoDto == null ){
            LOGGER.error("El odontologo y el paciente no se encuentran en la Base de datos");
            throw new BadRequestException("El odontologo y el paciente no se encuentran en la base de datos");
        }
        else if (pacienteDto == null) {
            LOGGER.error("El paciente no se ha encontrado en la base de datos");
            throw new BadRequestException("El paciente no se ha encontrado en la base de datos");
        }
        else {
            LOGGER.error("El odontologo no se encuentra en la base de datos");
            throw new BadRequestException("El odontologo no se encuentra en la base de datos");
        }

        }
        else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", JsonPrinter.toString(turnoDto));
        }
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listar() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnos.stream().map(TurnoDto::fromTurno).toList();
        LOGGER.info("La lista de turnos es la siguiente: {}", JsonPrinter.toString(turnoDtos));
        return turnoDtos;
    }

    @Override
    public TurnoDto actualizar(Turno turno) {
        Turno turnoAActualizar  = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoDto = null;
        if(turnoAActualizar != null){
            turnoAActualizar = turno;
            turnoRepository.save(turnoAActualizar);
            turnoDto = TurnoDto.fromTurno(turnoAActualizar);
            LOGGER.warn("Turno actualizado con exito {}", JsonPrinter.toString(turnoDto));
        }
        else LOGGER.error("No se pudo actualizar el turno");

        return turnoDto;
    }

    @Override
    public TurnoDto buscarPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if(turnoBuscado != null){
            turnoDto= TurnoDto.fromTurno(turnoBuscado);
            LOGGER.info("Turno encontrado con exito {}", JsonPrinter.toString(turnoDto));
        }
        else LOGGER.error("El id no se encuentra registrado en la base de datos");
        return turnoDto;
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        if (buscarPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Ha sido eliminado el turno con id {}", id);
        }
        else {
            LOGGER.warn("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con el id " + id);
        }
    }

}
