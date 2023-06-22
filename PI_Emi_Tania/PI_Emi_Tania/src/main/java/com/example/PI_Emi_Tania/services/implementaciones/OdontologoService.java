package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.services.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao; //crearlo para poder llamar a los metodos
    private ObjectMapper objectMapper;

    //constructor

@Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao, ObjectMapper objectMapper) {
        this.odontologoIDao = odontologoIDao;
        this.objectMapper = new ObjectMapper();
    }

    @Override
        public OdontologoDto buscarOdontologoPorId (int id){
            Odontologo odontologo = odontologoIDao.buscarPorId(id);
            return objectMapper.convertValue(odontologo, OdontologoDto.class);
        }

        @Override
        public List<Odontologo> listaDeOdontologos () {
            List<Odontologo> odontologos = odontologoIDao.listarTodos();
            return objectMapper.convertValue(odontologos, objectMapper.getTypeFactory().constructCollectionType(List.class, OdontologoDto.class));
        }

        @Override
        public OdontologoDto registrarOdontologo (Odontologo odontologo){
            odontologoIDao.guardar(odontologo);
            return objectMapper.convertValue(odontologo, OdontologoDto.class);
        }

        @Override
        public OdontologoDto actualizarOdontologo (Odontologo odontologo){
            odontologoIDao.actualizar(odontologo);
            return objectMapper.convertValue(odontologo, OdontologoDto.class);
        }

        @Override
        public void eliminarOdontologo ( int id){
            odontologoIDao.eliminar(id);
        }



    }
