package com.example.PI_Emi_Tania.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/integrador;", "sa", "sa");

    }
    public static Connection crearDataBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/integrador;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
    }

}
