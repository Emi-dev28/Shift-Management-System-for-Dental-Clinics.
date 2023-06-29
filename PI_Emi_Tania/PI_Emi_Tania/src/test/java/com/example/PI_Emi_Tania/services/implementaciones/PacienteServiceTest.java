package com.example.PI_Emi_Tania.services.implementaciones;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Paciente;


import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarUnPaciente() throws IllegalArgumentException, BadRequestException {
        Domicilio dom = new Domicilio(1L, "Roman", 1249, "Moron", "Buenos Aires");
        LocalDate fecha = LocalDate.of(2024,6,29);
        Paciente pacienteARegistrar = new Paciente(1L,"Emiliano", "Cellilli", "42723977",fecha , dom);
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
        Paciente paciente = new Paciente(1L,"Emi", "Cellilli", "422442332", fechaNacimiento, dom);
        Assertions.assertThrows(IllegalArgumentException.class, () -> pacienteService.guardar(paciente));

    }
    @Test
    @Order(3)
    void deberiaListarPacientes(){
        List<PacienteDto> pacienteDtoList = pacienteService.listarPacientes();
        Assertions.assertFalse(pacienteDtoList.isEmpty());
    }
    @Test
    @Order(4)
    void deberiaEliminarUnPaciente() throws ResourceNotFoundException, BadRequestException {
        Domicilio domicilio = new Domicilio(1L, "Calle x", 1234, "nose", "Buenos aires");
        LocalDate fecha = LocalDate.now();
        Paciente paciente = new Paciente(1L, "German", "Grisolia", "33456890", fecha, domicilio);
        pacienteService.guardar(paciente);
        pacienteService.eliminarPorId(paciente.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.buscarPorId(paciente.getId());
        });
    }
}