/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author eze
 */
public class Conexion {
    public static Connection getConnection() throws Exception {
    Connection con = null;
    try {
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
    }   catch (SQLException sqle) {
        throw new Exception("Error de conexion a la base de datos");
    }
    return con;

    
    }

    PreparedStatement prepareStatement(String SQL_registro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


