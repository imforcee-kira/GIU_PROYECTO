/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import CapaLogica.Login;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author eze
 */
public class FachadaLogin extends PersistenciaPersona{
    private static final String SQL_registro = ("INSERT INTO project.usuario(nombreUsuario, contraseña, cedula))VALUES(1,1)");
    public void register(Login login) throws Exception {
        try{
            Connection con = cone.getConnection();
            ps = cone.prepareStatement(SQL_registro);
            ps.setString (1, login.getNombreUsuario());
            ps.setString(2, login.getContraseña());
            ps.setString(3, login.getCI());
            int resultado = ps.executeUpdate();
            if(resultado <= 0){
                throw new Exception("No se pudo realizar el registro. Intente de nuevo");
            }
            con.close();
                    }
               catch(SQLException e){
                throw new Exception("Error al registrar. Intente de nuevo");
            }
            
            
            
        
        
        }
    
    
    }

