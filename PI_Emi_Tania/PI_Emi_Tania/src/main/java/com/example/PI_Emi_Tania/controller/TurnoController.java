package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Turno;
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
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable int id){
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.buscarPorId(id);
        if(turnoDto != null){
            respuesta = new ResponseEntity<>(turnoDto,null, HttpStatus.OK);

        }else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }
    @GetMapping
    public List<TurnoDto> listarTurnos(){
        return turnoService.listar();
    }
    @PostMapping("registrar")
    public ResponseEntity<TurnoDto> regsitrar(@RequestBody Turno turno){
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.guardar(turno);
        if(turnoDto != null)
            respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.CREATED);
            else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return respuesta;
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id){
        turnoService.eliminar(id);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizar(@RequestBody Turno turno ){
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.actualizar(turno);
        if(turno !=null) return respuesta = new ResponseEntity<>(turnoDto,null,HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

}
