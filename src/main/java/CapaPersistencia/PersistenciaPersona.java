/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import CapaLogica.Personas;
import Capa_Exception.BDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author eze
 */
import Capa_Exception.BDException;
import CapaLogica.Personas;
import CapaPersistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author eze
 */
public class PersistenciaPersona {

    private static final String SQL_GUARDAR_PERSONA = ("INSERT INTO persona.personas(CI, Nombre, Apellido)VALUES(?,?,?)");
    private static final String SQL_CONSULTAR_PERSONA = ("SELECT FROM personas where CI=?");
    public Conexion cone = new Conexion();
    public PreparedStatement ps;
    public ResultSet rs;

    public void GuardarPersonas(Personas per) throws Exception, BDException {

        try {
            int resultado = 0;
            Connection con = cone.getConnection();
            ps = (PreparedStatement) con.prepareStatement(SQL_GUARDAR_PERSONA);
            ps.setString(1, per.getCi());
            ps.setString(2, per.getNombre());
            ps.setString(3, per.getApellido());

            resultado = ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Error en conectar");
        }

    }

    public Personas busquedaPorCi(String ci) throws Exception, BDException, SQLException {
            Personas perso = new Personas();

        try {
            Connection con;
            con = cone.getConnection();
            ps = (PreparedStatement) con.prepareStatement(SQL_CONSULTAR_PERSONA);
            ps.setString(1, ci);
            rs = ps.executeQuery();

            if (rs.next()) {

                String Ci = rs.getString("Ci");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");

                perso.setCi(Ci);

                perso.setNombre(nombre);
                perso.setApellido(apellido);

            } else {
                throw new Exception("La persona no esta ingresada, porfavor ingrese una");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("No se encontro el usuario");
        }
        return perso;

    }

    public void eliminarPersona(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }

