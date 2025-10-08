/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaExcepciones;

/**
 *
 * @author ezequ
 */
public class personaException extends Exception {

    // Constructor por defecto
    public personaException() {
        super();
    }

    // Constructor que acepta un mensaje personalizado
    public personaException(String mensaje) {
        super(mensaje);
    }

    // Constructor que acepta un mensaje y una causa (otra excepción)
    public personaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    // Constructor que acepta solo una causa
    public personaException(Throwable causa) {
        super(causa);
    }
}