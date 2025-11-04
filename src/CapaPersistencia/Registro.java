/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.security.auth.login.Configuration;

public class Registro {

   
    private static final String SQL_REGISTRO = ("INSERT INTO usuario (ciUsuario,nombre,contraseña,rol) VALUES (?,?,?,?)");
    private static final String SQL_LOGIN = ("SELECT  * FROM usuario WHERE ciUsuario = ? AND contraseña = ?");
    private static final String SQL_REGISTRAR_FALTA = ("INSERT INTO faltas (fechaInicio, fechaFin, motivo,ciDocente, idClase) VALUES (?, ?, ?, ?,?)");
    private static final String SQL_BUSCAR_DOCENTE = ("SELECT ciUsuario FROM usuario WHERE rol = 'Docente'");
    private static final String SQL_AGREGAR_DOCENTECLASE = ("INSERT INTO docenteclase(ciDocente, idClase) VALUES(?,?)");
    private static final String SQL_VERIFICAR_DOCENTECLASE =("SELECT COUNT(*) FROM DocenteClase WHERE ciDocente = ? AND idClase = ?");
    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;
    private boolean resultado;
    
    
    
    public boolean verificarAsignacionDocenteClase(String ciDocente, String idClase) throws Exception {
    
    
    try (Connection con = cone.getConnection();
         PreparedStatement ps = con.prepareStatement(SQL_AGREGAR_DOCENTECLASE)) {
        
        ps.setString(1, ciDocente);
        ps.setString(2, idClase);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                
                return rs.getInt(1) > 0; 
            }
        }
    } catch (SQLException e) {
        throw new Exception("Error de LA BASE DE DATOS al verificar la asignación : " + e.getMessage());
    }
    return false;
}
    public boolean verificarDocentePorCI(String ciDocente) throws Exception {
    
    String sql = "SELECT COUNT(*) FROM usuario WHERE ciUsuario = ? AND rol = 'Docente'";
    
    try (Connection con = cone.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, ciDocente);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es > 0, es docente.
            }
        }
    } catch (SQLException e) {
        throw new Exception("Error de conexión/SQL al verificar docente: " + e.getMessage());
    }
    return false;
}
    
    public void asignarDocenteClase(String ciDocente, String idClase)throws Exception {
      try{
        Connection con = cone.getConnection();
        PreparedStatement ps = con.prepareStatement(SQL_AGREGAR_DOCENTECLASE);
        ps.setString(1, ciDocente);
        ps.setString(2, idClase);
        
        int resultado = ps.executeUpdate();
        
        if(resultado == 0){
            throw new Exception("La asignacion de clase no se pudo realizar. Intente nuevamente.");
        }
        
        
      }catch(SQLException e){
          if(e.getErrorCode() == 1062){
              throw new Exception("El docente ya ha sido asignado a una clase");
              
          }
          throw new Exception("Error d ebase de datos al asignar"+ e.getMessage());
          
      } catch (Exception e) {
        throw new Exception("Error interno al intentar asignar docente: " + e.getMessage());
    }  
    }

    public void registroUsuario(String ciUsuario, String nombre, String contraseña, String rol) throws Exception {

        

        try {
            Connection conn;
            conn = cone.getConnection();
            ps = conn.prepareStatement(SQL_REGISTRO);

            ps.setString(1, ciUsuario);
            ps.setString(2, nombre);
            ps.setString(3, contraseña);
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

    public String iniciar(String ciUsuario, String contraseña) throws Exception {
        String rolObtenido = null;
        String usuarioObtenido = null;
        String resultadoString = null;
       

        try {
             Connection con;
            con = cone.getConnection();
            ps = con.prepareStatement(SQL_LOGIN);

            ps.setString(1, ciUsuario);
            ps.setString(2, contraseña);
           

            rs = ps.executeQuery();

            if (rs.next()) {
                rolObtenido = rs.getString("rol");
                usuarioObtenido = rs.getString("nombre");
                resultadoString = rolObtenido + "|" + usuarioObtenido;

            }

        } catch (SQLException e) {
            System.err.println("ERROR SQL DETALLADO en Login: " + e.getMessage());
            throw new Exception("Error al verificar cr\n"
                    + "    }edenciales.");

        }
        
    return resultadoString;
    }
    public void RegistrarFalta(String fechaInicio, String fechaFin, String motivo, String ciDocente, String idClase) throws Exception {
        
        
        
        try { 
            Connection con = cone.getConnection();
            ps = con.prepareStatement(SQL_REGISTRAR_FALTA);
        
            
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            ps.setString(3, motivo);
            ps.setString(4, ciDocente);
            ps.setString(5, idClase);
        
            int resultado = ps.executeUpdate();
            
            if (resultado <= 0){
                throw new Exception("No se pudo registrar (La base de datos no afectó filas)");
            }    
            }catch (SQLException e) {

            System.err.println("ERROR SQL DETALLADO al registrar: " + e.getMessage());

            throw new Exception("Error al registrar");

        }
        
        
        }
        public ArrayList<String> buscarCIDOCENTE() throws Exception{
            ArrayList<String> listaCI = new ArrayList();
            
            try{
                Connection con = cone.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_BUSCAR_DOCENTE);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    listaCI.add(rs.getString("ci"));
                }
                
                } catch (SQLException e) {
                    System.err.println("ERROR SQL DETALLADO al obtener CIs: " + e.getMessage());
                    throw new Exception("Error al cargar la lista de docentes: " + e.getMessage());
                    }
    
                    return listaCI; // Devuelve la lista de CIs de docentes
}
                
                
            
            }
        
    
    
    
    
    
 
