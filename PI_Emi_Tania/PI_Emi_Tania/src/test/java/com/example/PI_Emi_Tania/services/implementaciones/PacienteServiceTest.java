package com.example.PI_Emi_Tania.services.implementaciones;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.entity.Paciente;


import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;
    private static Paciente paciente;
        @BeforeAll
    public static void init(){

        paciente = new Paciente(1L, "Emiliano", "Cellilli", "42723977", LocalDate.of(2023, 10,4), new Domicilio(1L, "Roca", 1423, "Moron", "Buenos Aires"));
    }

    @Test
    @Order(1)
    void deberiaRegistrarUnPaciente() throws IllegalArgumentException, BadRequestException {
        Domicilio dom = new Domicilio(1L, "Roman", 1249, "Moron", "Buenos Aires");
        LocalDate fecha = LocalDate.of(2024,6,29);
        Paciente pacienteARegistrar = new Paciente(1L,"Emiliano", "Cellilli", "427239773",fecha , dom);
        PacienteDto pacienteDto = pacienteService.guardar(pacienteARegistrar);

        // Acá verifico que el resultado no sea nulo
        Assertions.assertNotNull(pacienteDto);
        Assertions.assertNotNull(pacienteDto.getId());
    }
    @Test
    @Order(2)
    void cuandoNoSeCumplaElFormatoDeDni_noDeberiaInsertarElPaciente(){
        LocalDate fechaNacimiento = LocalDate.parse("2023-06-28");
        Domicilio dom = new Domicilio(1L, "Roman", 1222, "Moron", "Buenos Aires");
        Paciente paciente = new Paciente(1L,"Emi", "Cellilli", "4671523047592", fechaNacimiento, dom);
        Assertions.assertThrows(BadRequestException.class, () -> pacienteService.guardar(paciente));

    }
    @Test
    @Order(3)
    void deberiaListarPacientes(){
        List<PacienteDto> pacienteDtoList = pacienteService.listarPacientes();
        Assertions.assertFalse(pacienteDtoList.isEmpty());
    }
    @Test
    @Order(4)
    void deberiaEliminarUnPaciente() throws BadRequestException, ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardar(paciente);
        pacienteService.eliminarPorId(pacienteDto.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPorId(1L));
    }
    }
