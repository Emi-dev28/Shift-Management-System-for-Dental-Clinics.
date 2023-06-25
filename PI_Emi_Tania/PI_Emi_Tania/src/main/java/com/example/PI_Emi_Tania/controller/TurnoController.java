package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.services.ITurnoService;
import com.example.PI_Emi_Tania.services.implementaciones.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;
    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarPorId(id), null, HttpStatus.OK);
    }
    @GetMapping
    public List<TurnoDto> listarTurnos(){
        return turnoService.listar();
    }

    @PostMapping("registrar")
    public ResponseEntity<TurnoDto> regsitrar(@RequestBody Turno turno) throws BadRequestException {
        return new ResponseEntity<>(turnoService.guardar(turno), null,HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Turno Eliminado");

    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizar(@RequestBody Turno turno ){
        return new ResponseEntity<>(turnoService.actualizar(turno), null, HttpStatus.OK);
    }

}
