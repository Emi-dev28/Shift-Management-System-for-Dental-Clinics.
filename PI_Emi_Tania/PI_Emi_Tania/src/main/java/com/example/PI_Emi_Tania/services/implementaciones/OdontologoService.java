package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.Repository.OdontologoRepository;
import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.services.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PI_Emi_Tania.utils.JsonPrinter;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public OdontologoService(ObjectMapper objectMapper, OdontologoRepository odontologoRepository) {
    this.odontologoRepository = odontologoRepository;
    this.objectMapper = new ObjectMapper();
    }

    @Override
    public OdontologoDto buscarOdontologoPorId (Long id){
        Odontologo odontologoABuscar = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoABuscar != null){
            odontologoDto = objectMapper.convertValue(odontologoABuscar,OdontologoDto.class);
            LOGGER.info ("Se encontró al odontologo: {} ", JsonPrinter.toString(odontologoDto));
        } else {
            LOGGER.warn("El id que introdujo no se encuentra en la base de datos");
        }
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        // Obtener la lista de odontólogos desde el repositorio y guardarlo en odontologos de tipo List de Odontologo
        List<Odontologo> odontologos = odontologoRepository.findAll();

        // Mapear la lista de objetos Odontologo a objetos OdontologoDto utilizando una expresión lambda
        List<OdontologoDto> odontologosDtos = odontologos.stream()
                .map(odontologo-> {
                    // Crear un nuevo objeto OdontologoDto con los datos del odontólogo actual
                    return new OdontologoDto(odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
                }).toList();

        LOGGER.info("Lista de odontologos: {}", JsonPrinter.toString(odontologosDtos));

        return odontologosDtos;
    }



    @Override
        public OdontologoDto registrarOdontologo (Odontologo odontologo){
            Odontologo odontologoNuevo = odontologoRepository.save(odontologo);
            OdontologoDto odontologoDtoNuevo =objectMapper.convertValue(odontologoNuevo,OdontologoDto.class);
            LOGGER.info ("Se ha agregado un nuevo odontologo: {}", JsonPrinter.toString(odontologoDtoNuevo));
            return odontologoDtoNuevo;
        }

        @Override
        public OdontologoDto actualizarOdontologo (Odontologo odontologo){
            Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
            OdontologoDto odontologoActualizadoDto = null;
            if (odontologoAActualizar != null){
                odontologoAActualizar = odontologo; //paso el odontologo que me dan por parametro con el odontologoAActualizar que me traje con el ID del odontologo que me pasan por parametro
                odontologoRepository.save(odontologoAActualizar);
                odontologoActualizadoDto = objectMapper.convertValue(odontologoAActualizar, OdontologoDto.class);
                LOGGER.info("Se ha actualizado satisfactoriamente el odontologo: {}", JsonPrinter.toString(odontologoActualizadoDto));
            }else LOGGER.error("No se pudo actualizar el odontologo por estar registrado en la base de datos");

            return odontologoActualizadoDto;
        }

        @Override
        public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
            if (buscarOdontologoPorId(id) != null) {
                odontologoRepository.deleteById(id);
                LOGGER.warn("Se eliminó el odontologo con id: {} ", id);
            } else {
                LOGGER.error("No se encontró el odontologo con id " + id);
                throw new ResourceNotFoundException("No se encontró el odontologo con id " + id);
            }
        }



    }
