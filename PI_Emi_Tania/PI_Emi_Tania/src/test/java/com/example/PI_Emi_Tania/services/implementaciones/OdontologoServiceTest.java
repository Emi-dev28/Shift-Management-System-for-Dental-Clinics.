package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;  //lo inyecto para usarlo para el testeo

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologo() throws BadRequestException {
        // Creación de un objeto Odontologo para insertar
        Odontologo odontologoAInsertar = new Odontologo("AB-34567890", "Pavel", "Perez");
        // Llamada al método para registrar el Odontologo y obtener el resultado
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoAInsertar);

        // Verifica que el resultado no sea nulo
        Assertions.assertNotNull(odontologoDto);
        // Verifica que el ID del OdontologoDto no sea nulo
        Assertions.assertNotNull(odontologoDto.getId());
    }


    @Test
    @Order(2)
    void deberiaListarUnSoloOdontologo() {
        Odontologo odontologoACrear = new Odontologo("ad-23344", "Alejandro", "Marrero");
        OdontologoDto odontologodto1 = odontologoService.registrarOdontologo(odontologoACrear);
        // Obtiene la lista de OdontologoDto
        List<OdontologoDto> odontologosDtos = odontologoService.listarOdontologos();

        // Verifica que la lista tenga un solo elemento
        assertFalse(odontologosDtos.isEmpty());
    }

    // No se encontro el odontologo con id 1
    @Test
    @Order(3)
    void deberiaEliminarElOdontologoId1() throws ResourceNotFoundException {
        Odontologo odontologoACrear = new Odontologo("ad-23344", "Alejandro", "Marrero");
        OdontologoDto odontologodto1 = odontologoService.registrarOdontologo(odontologoACrear);

        // Elimina el Odontologo con ID 1
        odontologoService.eliminarOdontologo(odontologodto1.getId());

        // Verifica que se lance una ResourceNotFoundException al intentar eliminar nuevamente el mismo Odontologo
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}


