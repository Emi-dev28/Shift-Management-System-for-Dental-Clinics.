package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.services.implementaciones.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService; // creo la instancia para llamar los metodos;

    @Autowired  //inyectar odontologoService
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public Odontologo buscarOdontologoPorID(@PathVariable int id) {
        return odontologoService.buscarOdontologoPorId(id);
    }

    @PostMapping("/registrar")
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) { //se le carga como payload en el post
        return odontologoService.registrarOdontologo(odontologo);
    }

    @PutMapping("/actualizar")
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo) { //se le carga como payload en el post
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping
    public void eliminarOdontologo(@PathVariable int id) {
        odontologoService.eliminarOdontologo(id);
    }
}
