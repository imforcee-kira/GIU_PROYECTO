/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import CapaLogica.Login;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ezequ
 */
public class Registro {
    private static final String SQL_REGISTRO = ("INSERT INTO project.usuarios(nombreUsuario,contraseña,ci,rol )VALUES (?,?,?,?)");
    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;
   
    boolean registrado = false;
    public boolean registroUsuario(String nombreUsuario, String contraseña, String CI, String rol) throws Exception{
        try{
            
            Connection con = cone.getConnection();
            ps = cone.prepareStatement(SQL_REGISTRO);
            ps.setString(1, nombreUsuario);     
            ps.setString(2, contraseña);  
            ps.setString(3, CI);          
            ps.setString(4, rol);   
            
            int resultado = ps.executeUpdate();
            if(resultado <= 0 ){
                throw new Exception("No se pudo registrar");
            }else{
                registrado = false;
            }
            con.close();
            
        }catch (SQLException e){
            throw new Exception("Error al registrar");
        
            
        }
        return false;
    }
    
}
