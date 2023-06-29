package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.services.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("registrar")
    public ResponseEntity<PacienteDto> registrar(@RequestBody Paciente paciente) throws BadRequestException {
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.guardar(paciente);
        if(pacienteDto != null) return respuesta = new ResponseEntity<>(pacienteDto,null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizar(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.actualizar(paciente);
        if(pacienteDto != null) return respuesta = new ResponseEntity<>(pacienteDto,null,HttpStatus.OK);
        else
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }



    @DeleteMapping("eliminar/{id}")
    public void eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPorId(id);
    }
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPorId(id);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto,null,HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }
    @GetMapping
    public List<PacienteDto> listar() {
        return pacienteService.listarPacientes();
    }
}