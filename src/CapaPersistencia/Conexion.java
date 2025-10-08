/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Conexion {

    // Parámetros de Conexión
    private static final String URL = "jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = ""; 

    public static Connection getConnection() throws Exception {
        Connection con = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 

    
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);
            
        } catch (SQLException e) {
            
            throw new Exception("Error SQL al intentar conectar: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            
            throw new Exception("Error: No se encontró el Driver JDBC. ¿Añadiste el JAR a las librerías?");
        }
        return con;
    }
    
   
     public PreparedStatement prepareStatement(String SQL_registro) throws Exception {
        Connection con = getConnection(); // Obtiene la conexión activa

        // Verifica que la conexión no sea nula antes de intentar crear el PreparedStatement
        if (con != null) {
            try {
                return con.prepareStatement(SQL_registro);
            } catch (SQLException e) {
                System.err.println("Error al crear el PreparedStatement: " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}

