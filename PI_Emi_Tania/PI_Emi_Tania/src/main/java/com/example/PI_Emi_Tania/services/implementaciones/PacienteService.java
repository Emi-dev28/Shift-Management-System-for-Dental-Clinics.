package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.IDao.IDao;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.services.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }



    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    @Override
    public Paciente buscarPorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public void eliminarPorId(int id) {
    pacienteIDao.eliminar(id);
    }



}
