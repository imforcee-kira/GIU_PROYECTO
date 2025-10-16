/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {

    //)
    private static final String SQL_REGISTRO = "INSERT INTO usuarios (nombreUsuario,contraseña,ci,rol) VALUES (?,?,?,?)";
    
   
    public Conexion cone = new Conexion(); 
    public PreparedStatement ps; 
    public ResultSet rs; 
    
    public boolean registroUsuario(String nombreUsuario, String contraseña, String CI, String rol) throws Exception {
        
        Connection con = null;
        PreparedStatement ps = null; 

        try {
            
            con = cone.getConnection(); 
            ps = con.prepareStatement(SQL_REGISTRO); 
            
           
            ps.setString(1, nombreUsuario);    
            ps.setString(2, contraseña);  
            ps.setString(3, CI);
            ps.setString(4, rol);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                return true; 
            } else {
                throw new Exception("No se pudo registrar (La base de datos no afectó filas)");
            }

        } catch (SQLException e) {
           
            System.err.println("ERROR SQL DETALLADO al registrar: " + e.getMessage());
            
            throw new Exception("Error al registrar");
            
        } finally {
            
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }
    }
}
