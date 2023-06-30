package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.dto.DomicilioDto;
import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
     @Autowired
     private PacienteService pacienteService;
     @Autowired
     private OdontologoService odontologoService;
     @Autowired
     private ObjectMapper objectMapper;
     private static Odontologo odontologo;
     private static Paciente paciente;

     @BeforeAll
     public static void init(){
         odontologo = new Odontologo("Ad-123423423", "German", "Gonzalez");
         paciente = new Paciente(1L, "Emiliano", "Cellilli", "427239778888", LocalDate.of(2023, 10,4), new Domicilio(1L, "Roca", 1423, "Moron", "Buenos Aires"));
     }
    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() throws BadRequestException, ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardar(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        TurnoDto turnoDto = turnoService.guardar(new Turno(paciente,odontologo,LocalDateTime.of(LocalDate.of(2024,10,15), LocalTime.of(15,30))));
        assertNotNull(turnoDto.getId());
        assertNotNull(turnoDto);
    }
    @Test
    @Order(2)
    void deberiaListarTurnos() throws BadRequestException, ResourceNotFoundException {
         PacienteDto pacienteDto = pacienteService.guardar(paciente);
         OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
         TurnoDto turnoDto = turnoService.guardar(new Turno(paciente,odontologo,LocalDateTime.of(LocalDate.of(2024,10,15), LocalTime.of(15,30))));
        List<TurnoDto> turnos = turnoService.listar();
        Assertions.assertFalse(turnos.isEmpty());
    }
    @Test
    @Order(3)
    void deberiaEliminarUnTurno() throws BadRequestException, ResourceNotFoundException {
         PacienteDto pacienteDto = pacienteService.guardar(paciente);
         OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
         TurnoDto turnoDto = turnoService.guardar(new Turno(paciente,odontologo,LocalDateTime.of(LocalDate.of(2024,10,15), LocalTime.of(15,30))));
         turnoService.eliminar(turnoDto.getId());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            turnoService.eliminar(turnoDto.getId());
        });
    }

}