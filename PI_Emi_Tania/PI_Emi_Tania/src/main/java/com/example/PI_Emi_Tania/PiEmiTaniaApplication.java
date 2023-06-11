package com.example.PI_Emi_Tania;

import com.example.PI_Emi_Tania.Repository.H2Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class PiEmiTaniaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(PiEmiTaniaApplication.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        H2Connection.getConnection();
        SpringApplication.run(PiEmiTaniaApplication.class, args);
        LOGGER.info("Proyecto Integrador Emi y Tania is running...");
    }

}
