/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Exception;

import CapaLogica.Personas;
import CapaPersistencia.PersistenciaPersona;

/**
 *
 * @author ezequ
 */
public class FachadaPersona {
    public void GuardarPersonas(Personas persona) throws Exception{
        PersistenciaPersona pers = new PersistenciaPersona();
        pers.GuardarPersonas(persona);
        
    }
    
    public Personas busquedaporci (String ci) throws personaException, Exception{
        Personas per = new Personas();
        PersistenciaPersona pers = new PersistenciaPersona();
        per = pers.busquedaPorCi(ci);
        return per;
        
    }

    public void eliminarPersona(String ci) throws personaException, Exception{
        PersistenciaPersona pers = new PersistenciaPersona();
        pers.eliminarPersona(ci);
    }
}
