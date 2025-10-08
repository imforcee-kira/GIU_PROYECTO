/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaExcepciones;
import java.sql.SQLException;
/**
 *
 * @author eze
 */
public class BDException extends Exception {
    public BDException(String string, SQLException slqe){
        super(string);
    }
       
}
