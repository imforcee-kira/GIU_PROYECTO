/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

/**
 *
 * @author ezequ
 */
public class Clases {
    public int idClase = 0;
   private String nombreClase = "";
    
    public Clases(int idClase, String nombreClase) {
        this.idClase = idClase;
        this.nombreClase = nombreClase;
    }
    
    public String toString() {
        return nombreClase;
    }
    
    public int getIdClase() { 
        return idClase; 
    }
    
    public String getNombreClase() {
        return nombreClase;
    }
    
    
    
}
