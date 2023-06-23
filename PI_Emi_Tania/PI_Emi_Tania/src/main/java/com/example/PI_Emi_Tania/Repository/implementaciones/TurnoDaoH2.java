//package com.example.PI_Emi_Tania.Repository.implementaciones;

//import com.example.PI_Emi_Tania.Repository.IDao;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/*@Repository
public class TurnoDaoH2 implements IDao<Turno> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoDaoH2.class);
    private final PacienteDaoH2 pacienteDaoH2;
    private final OdontologoDaoH2 odontologoDaoH2;
    private List<Turno> turnoList;


    @Autowired
    public TurnoDaoH2(PacienteDaoH2 pacienteDaoH2, OdontologoDaoH2 odontologoDaoH2) {
        this.pacienteDaoH2 = pacienteDaoH2;
        this.odontologoDaoH2 = odontologoDaoH2;
        turnoList = new ArrayList<>();
    }



    @Override
    public Turno guardar(Turno turno) {
        Turno guardado = null;
        //Paciente paciente = pacienteDaoH2.buscarPorId(turno.getPaciente().getId());
        //Odontologo odontologo = odontologoDaoH2.buscarPorId(turno.getOdontologo().getId());
        /*if(paciente != null && odontologo != null){
            guardado = turno;
            turnoList.add(guardado);
        }
        else LOGGER.error("no se pudo guardar");
        return guardado;

    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("Este es el listado de turnos {}" + turnoList);
        return turnoList;
    }

    @Override
    public void eliminar(int id) {
    Turno turno = buscarPorId(id);
    turnoList.remove(turno);
    }

    @Override
    public Turno buscarPorId(int id) {
        return (Turno) turnoList.
                                stream().filter(t -> t.getId() == id);
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);
        return turno;
    }
 }
 */
