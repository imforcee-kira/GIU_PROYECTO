/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ezequ
 */
public class LoginUsuario {
    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;
    boolean logueado = false;
    private static final String SQL_LOGIN = ("SELECT * FROM project.usuarios WHERE nombreUsuario = ? AND contraseña = ? AND ci = ?");
    public boolean LoginUsuario  (String nombreUsuario, String contraseña, String CI) throws Exception{
      try{
            Connection con = cone.getConnection();
            ps = con.prepareStatement(SQL_LOGIN);
            ps.setString(1,nombreUsuario);
            ps.setString(2, contraseña);
            ps.setString(3, CI);
            
            rs = ps.executeQuery();
            if(rs.next()){
                logueado = true; 
            }
            con.close();
                
            }catch(Exception e){
                throw new Exception ("Error al loguear");
            }
            
            
          return logueado;  
        }
        
    
    }
    

