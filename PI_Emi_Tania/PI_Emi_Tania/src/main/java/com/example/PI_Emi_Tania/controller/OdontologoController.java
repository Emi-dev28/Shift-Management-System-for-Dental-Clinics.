package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.services.implementaciones.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService; // creo la instancia para llamar los metodos;

    @Autowired  //inyectar odontologoService
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public OdontologoDto buscarOdontologoPorId(@PathVariable int id) {
        return odontologoService.buscarOdontologoPorId(id);
    }

    @GetMapping()
    public List<Odontologo> listarOdontologos() {
        return odontologoService.listaDeOdontologos();
    }

    @PostMapping("/registrar")
    public OdontologoDto registrarOdontologo(@RequestBody Odontologo odontologo) { //se le carga como payload en el post
        return odontologoService.registrarOdontologo(odontologo);
    }

    @PutMapping("/actualizar")
    public OdontologoDto actualizarOdontologo(@RequestBody Odontologo odontologo) { //se le carga como payload en el post
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping
    public void eliminarOdontologo(@PathVariable int id) {
        odontologoService.eliminarOdontologo(id);
    }

}
