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
import java.util.List;
import javax.security.auth.login.Configuration;

public class Registro {

   
    private static final String SQL_REGISTRO = ("INSERT INTO usuario (ciUsuario,nombre,contraseña,rol) VALUES (?,?,?,?)");
    private static final String SQL_LOGIN = ("SELECT  * FROM usuario WHERE ciUsuario = ? AND contraseña = ?");
    private static final String SQL_REGISTRAR_FALTA = ("INSERT INTO faltas (fechaInicio, fechaFin, motivo,ciDocente, idClase) VALUES (?, ?, ?, ?,?)");
    private static final String SQL_BUSCAR_DOCENTE = ("SELECT ciUsuario FROM usuario WHERE rol = 'Docente'");
    private static final String SQL_AGREGAR_DOCENTECLASE = ("INSERT INTO docenteclase(ciDocente, idClase) VALUES(?,?)");
    private static final String SQL_VERIFICAR_DOCENTECLASE =("SELECT COUNT(*) FROM docenteclase WHERE ciDocente = ? AND idClase = ?");
    private static final String SQL_BUSCARFALTA = ("SELECT T1.fechaInicio, T1.fechaFin, T1.motivo, T2.nombre" +
                 "FROM falta T1 " +
                 "JOIN suario T2 ON T1.ciDocente = T2.ciUsuario " + 
                 "WHERE T1.idClase = ? " +
                 "ORDER BY T1.fechaInicio DESC");
    private static final String SQL_BUSCARCLASE_POR_ESTUDIANTE =("SELECT idClase FROM usuario WHERE ciUsuario = ? AND rol = 'Alumno' ");
    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;
    private boolean resultado;
    
    
    
    
    
    
    public void asignarClaseAEstudiante(String ciEstudiante, String idClase) throws Exception {
    
    // 💥 NUEVO SQL: UPDATE para modificar la columna idClase en la tabla usuarios
    String sql = "UPDATE usuario SET idClase = ? WHERE ciUsuario = ?";
    
    try (Connection con = cone.getConnection(); // O cone.getConnection()
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, idClase);
        ps.setString(2, ciEstudiante);
        
        int filasAfectadas = ps.executeUpdate();
        
        if (filasAfectadas == 0) {
            throw new Exception("La C.I. del alumno no existe o la asignación no se pudo realizar.");
        }
        
    } catch (SQLException e) {
        throw new Exception("Error al asignar clase: " + e.getMessage());
    }
}
    public String buscarIdClasePorEstudiante(String ciEstudiante) throws Exception {
    // Usamos try-with-resources para asegurar que la conexión y el PreparedStatement se cierren.
    // **Asegúrate de que 'getConnection()' sea tu método real de conexión a DB**
    try (Connection con = cone.getConnection(); 
         PreparedStatement ps = con.prepareStatement(SQL_BUSCARCLASE_POR_ESTUDIANTE)) {
        
        ps.setString(1, ciEstudiante);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Si se encuentra, retorna el ID de la clase
                return rs.getString("idClase"); 
            }
        }
    } catch (SQLException e) {
        // En caso de error de DB, lanza una excepción que será atrapada en la GUI
        throw new Exception("Error de base de datos al buscar la clase del estudiante: " + e.getMessage());
    }
    
    // Retorna null si la C.I. no se encontró en la tabla Estudiante
    return null; 
}
    public static List<Object[]> buscarFaltasPorClase(String idClase) throws Exception {
    List<Object[]> filasFaltas = new ArrayList<>();
    
    // Consulta SQL: T1.fechaDesde, T1.fechaHasta, T1.motivo, T2.nombreCompleto
    String sql = "SELECT T1.fechaInicio, T1.fechaFin, T1.motivo, T2.nombre " +
                 "FROM faltas T1 " + 
                 "JOIN usuario T2 ON T1.ciDocente = T2.ciUsuario " + 
                 "WHERE T1.idClase = ? " +
                 "ORDER BY T1.fechaInicio DESC";
    
    // Asume que 'obtenerConexionEstatica()' o equivalente existe y retorna la conexión.
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, idClase);
        
        try (ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                // Array de 4 objetos (para FechaInicio, FechaFin, Motivo, Profesor)
                Object[] fila = new Object[4];
                fila[0] = rs.getString("fechaInicio"); 
                fila[1] = rs.getString("fechaFin"); 
                fila[2] = rs.getString("motivo");     
                fila[3] = rs.getString("nombre");
                
                filasFaltas.add(fila);
            }
        }
    } catch (SQLException e) {
        throw new Exception("Error de DB al buscar las faltas por clase: " + e.getMessage());
    }
    
    return filasFaltas;
}
    public boolean verificarAsignacionDocenteClase(String ciDocente, String idClase) throws Exception {
    
    
    try (Connection con = cone.getConnection();
         PreparedStatement ps = con.prepareStatement(SQL_VERIFICAR_DOCENTECLASE)) {
        
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
        
    
    
    
    
    
 
