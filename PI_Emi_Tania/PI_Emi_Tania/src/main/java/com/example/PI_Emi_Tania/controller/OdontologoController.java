package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.services.implementaciones.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService; // creo la instanciade esa clase para llamar los metodos;

    @Autowired  //inyectar odontologoService
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // Obtener un Odontólogo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) {
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoDto != null) {
            return new ResponseEntity<>(odontologoDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping()
    public List<OdontologoDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    // Registrar un nuevo Odontólogo comentado
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        // Llama al servicio para registrar un nuevo Odontólogo, arriba es requestbody porque lo cargo del payload
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        if (odontologoDto != null) {
            // Retorna una respuesta exitosa con el DTO del Odontólogo registrado
            return new ResponseEntity<>(odontologoDto, HttpStatus.CREATED);
        } else {
            // Retorna una respuesta (bad request - 400) si no se pudo registrar el Odontólogo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            // ya que no se proporciona un cuerpo de respuesta adicional, por lo que se utiliza build() para construir
            // y retornar la instancia de ResponseEntity sin contenido.
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if (odontologoDto != null) {
            return new ResponseEntity<>(odontologoDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
    }

}

//Nota: ResponseEntity es una clase genérica proporcionada por Spring Framework que representa toda la respuesta HTTP
// (estado, codigo,encabezados y cuerpo) enviada desde el servidor al cliente, se puede personalizar.

//En el código del controlador, se utiliza ResponseEntity para envolver los resultados de los métodos y retornar respuestas HTTP con información adicional.
//Se controla el código de estado y el cuerpo de la respuesta según el resultado de la operación realizada en el controlador.
// Ejemplo: método buscarTurnoPorId, se utiliza ResponseEntity para retornar una respuesta exitosa con código 200 si se encuentra el turno solicitado,
// o una respuesta con código de error 400 si el turno no se encuentra.

