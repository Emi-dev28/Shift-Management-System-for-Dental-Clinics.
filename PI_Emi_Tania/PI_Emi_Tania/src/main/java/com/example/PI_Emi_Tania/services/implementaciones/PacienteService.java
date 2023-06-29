package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.Repository.PacienteRepository;
import com.example.PI_Emi_Tania.dto.DomicilioDto;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.services.IPacienteService;
import com.example.PI_Emi_Tania.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public PacienteService(ObjectMapper objectMapper, PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }



    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacientesDtos = pacientes.stream()
                .map(paciente-> {
                    Domicilio dom = paciente.getDomicilio();
                    DomicilioDto domicilioDto = objectMapper.convertValue(dom,DomicilioDto.class);
                    return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(),paciente.getDni(),paciente.getFechaIngreso(),domicilioDto);
        }).toList();
        LOGGER.info("Lista de pacientes: {}", JsonPrinter.toString(pacientesDtos));
        return pacientesDtos;
     }

    @Override
    public PacienteDto guardar(Paciente paciente) throws BadRequestException {

            Paciente pacienteIngresado = pacienteRepository.save(paciente);
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteIngresado.getDomicilio(), DomicilioDto.class);
            PacienteDto pacienteDto = objectMapper.convertValue(pacienteIngresado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Se ha agregado un nuevo paciente: {}", JsonPrinter.toString(pacienteDto));


        return pacienteDto;
    }

    @Override
    public PacienteDto actualizar(Paciente paciente) throws ResourceNotFoundException {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;
        if (pacienteAActualizar != null){
            pacienteAActualizar = paciente; //paso el paciente que me dan por parametro con el pacienteAActualizar que me traje con el ID del paciente que me pasan por parametro
            pacienteRepository.save(pacienteAActualizar);
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = objectMapper.convertValue(pacienteAActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Se ha actualizado satisfactoriamente el paciente: {}", JsonPrinter.toString(pacienteActualizadoDto));
        }else {
            LOGGER.error("No se pudo actualizar el paciente por estar registrado en la base de datos");
            throw new ResourceNotFoundException("El paciente no se encuentra registrado en la base de datos");
        }
        return pacienteActualizadoDto;
    }



    @Override
    public void eliminarPorId(Long id) throws ResourceNotFoundException {
        if(buscarPorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id, {}", id);
        }
        else {
            LOGGER.error("No se ha encontrado el paciente con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id" + id);
        }
    }


    @Override
    public PacienteDto buscarPorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteABuscar = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteABuscar != null){
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteABuscar.getDomicilio(), DomicilioDto.class);
            pacienteDto = objectMapper.convertValue(pacienteABuscar,PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info ("Se encontró al paciente: {} ", JsonPrinter.toString(pacienteDto));
        } else {
            LOGGER.warn("El id que introdujo no se encuentra en la base de datos");
            throw new ResourceNotFoundException("El id no se encuentra registrado en la base de datos");
        }

        return pacienteDto;
    }


}
