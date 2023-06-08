package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.Repository.IDao;
import com.example.PI_Emi_Tania.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }

    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
