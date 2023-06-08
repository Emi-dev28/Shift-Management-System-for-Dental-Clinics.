package com.example.PI_Emi_Tania.controller;

import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.services.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")


public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

    @PostMapping("/actualizar")
    public Paciente actualizar(@RequestBody Paciente paciente) {
        return pacienteService.actualizar(paciente);
    }

    @DeleteMapping("eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        pacienteService.eliminarPorId(id);
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listarPacientes();
    }
}