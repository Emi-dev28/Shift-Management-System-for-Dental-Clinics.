package com.example.PI_Emi_Tania.Repository;

import com.example.PI_Emi_Tania.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


}
