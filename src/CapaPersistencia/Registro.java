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

   
    private static final String SQL_REGISTRO = ("INSERT INTO usuarios (nombreUsuario,contraseña,ci,rol) VALUES (?,?,?,?)");
    private static final String SQL_LOGIN = ("SELECT  * FROM usuarios WHERE ci = ? AND contraseña = ?");
    private static final String SQL_REGISTRAR_FALTA = ("INSERT INTO faltas (desde, hasta, motivo,ciDocente) VALUES (?, ?, ?, ?)");

    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;
    private boolean resultado;

    public void registroUsuario(String nombreUsuario, String contraseña, String ci, String rol) throws Exception {

        

        try {
            Connection conn;
            conn = cone.getConnection();
            ps = conn.prepareStatement(SQL_REGISTRO);

            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            ps.setString(3, ci);
            ps.setString(4, rol);

            int resultado = ps.executeUpdate();

            if (resultado <= 0) {
                 throw new Exception("No se pudo registrar (La base de datos no afectó filas)");
               
               
            }

        } catch (SQLException e) {

            System.err.println("ERROR SQL DETALLADO al registrar: " + e.getMessage());

            throw new Exception("Error al registrar");

        }
    }

    public String iniciar(String ci, String contraseña) throws Exception {
        String rolObtenido = null;
        String usuarioObtenido = null;
        String resultadoString = null;
       

        try {
             Connection con;
            con = cone.getConnection();
            ps = con.prepareStatement(SQL_LOGIN);

            ps.setString(1, ci);
            ps.setString(2, contraseña);
           

            rs = ps.executeQuery();

            if (rs.next()) {
                rolObtenido = rs.getString("rol");
                usuarioObtenido = rs.getString("nombreUsuario");
                resultadoString = rolObtenido + "|" + usuarioObtenido;

            }

        } catch (SQLException e) {
            System.err.println("ERROR SQL DETALLADO en Login: " + e.getMessage());
            throw new Exception("Error al verificar cr\n"
                    + "    }edenciales.");

        }
        
    return resultadoString;
    }
    public void RegistrarFalta(String desde, String hasta, String motivo, String ciDocente) throws Exception {
        
        
        try { 
            Connection con = cone.getConnection();
            ps = con.prepareStatement(SQL_REGISTRAR_FALTA);
        
            
            ps.setString(1, desde);
            ps.setString(2, hasta);
            ps.setString(3, motivo);
            ps.setString(4, ciDocente);
        
            int resultado = ps.executeUpdate();
            
            if (resultado <= 0){
                throw new Exception("No se pudo registrar (La base de datos no afectó filas)");
            }    
            }catch (SQLException e) {

            System.err.println("ERROR SQL DETALLADO al registrar: " + e.getMessage());

            throw new Exception("Error al registrar");

        }
        
        
        }
    
    
    }
    
    
 
