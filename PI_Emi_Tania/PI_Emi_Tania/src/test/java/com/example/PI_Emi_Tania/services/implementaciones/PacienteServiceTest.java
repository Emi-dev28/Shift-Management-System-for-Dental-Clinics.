package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.utils.LocalDateAdapter;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarUnPaciente(){
        Domicilio dom = new Domicilio("Roman", 1249, "Moron", "Buenos Aires");
        LocalDate fechaNacimiento = LocalDate.parse("2023-06-28");
        Paciente pacienteARegistrar = new Paciente("Emiliano", "Cellilli", "42723977", fechaNacimiento, dom);
        PacienteDto pacienteDto = pacienteService.guardar(pacienteARegistrar);

        // Acá verifico que el resultado no sea nulo
        Assertions.assertNotNull(pacienteDto);
        Assertions.assertNotNull(pacienteDto.getId());
    }
    @Test
    @Order(2)
    void cuandoNoSeCumplaElFormatoDeDni_noDeberiaInsertarElPaciente(){
        LocalDate fechaNacimiento = LocalDate.parse("2023-06-28");
        Domicilio dom = new Domicilio("Roman", 1222, "Moron", "Buenos Aires");
        Paciente paciente = new Paciente("Emi", "Cellilli", "422442332", fechaNacimiento, dom);
        Assertions.assertThrows(ConstraintViolationException.class, () -> pacienteService.guardar(paciente));

    }
    @Test
    @Order(3)
    void deberiaListarUnSoloPaciente(){
        List<PacienteDto> pacienteDtoList = pacienteService.listarPacientes();
        assertEquals(1,pacienteDtoList.size());
    }
}