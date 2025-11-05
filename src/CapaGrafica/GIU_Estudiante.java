/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaGrafica;

/**
 *
 * @author eze
 */
import CapaPersistencia.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class GIU_Estudiante extends javax.swing.JFrame {

    /**
     * Creates new form GIU_Estudiante
     * 
     */
    private String ciEstudianteLogueado; // Variable de instancia
    private DefaultTableModel modelo; // Asegúrate de que esta exista
    
    
    
   
    public GIU_Estudiante(String ci, String rol) {
        this.ciEstudianteLogueado = ci;
        initComponents();
        cargarTablaFaltasPorClase();
        
        
    }
    private void cargarTablaFaltasPorClase(){
        Registro registroDB = new Registro(); // Necesario para buscarIdClasePorEstudiante
    String idClase = null;
    try {
        // 1. Uso de la variable seteada en el constructor: ¡CORREGIDO!
        idClase = registroDB.buscarIdClasePorEstudiante(this.ciEstudianteLogueado);
        
        if (idClase == null || idClase.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar la clase a la que pertenece el estudiante.", "Error de Configuración", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener las faltas (Llamada al método static que retorna la lista)
        // Nota: Registro.buscarFaltasPorClase() debe ser static y retornar List<Object[]>
        List<Object[]> filasFaltas = Registro.buscarFaltasPorClase(idClase); 
            
        // 3. Crear el modelo y llenar la tabla
        modelo = new DefaultTableModel();
            
        // Establecer nombres de columna
        String[] titulos = {"Fecha Inicio", "Fecha Fin", "Motivo", "Profesor"};
        modelo.setColumnIdentifiers(titulos);
            
        // Llenar filas directamente desde la lista
        for (Object[] fila : filasFaltas) {
            modelo.addRow(fila);
        }
            
        // Asignar el modelo a tu JTable (Asegúrate de que tu tabla se llame 'tablaFaltas')
        modeloTablaFaltas.setModel(modelo);
        
        if (filasFaltas.isEmpty()) {
             JOptionPane.showMessageDialog(this, "No hay faltas registradas para la clase " + idClase + ".", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Fallo al cargar la tabla de faltas: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
    }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modeloTablaFaltas = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Estudiante");

        modeloTablaFaltas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(modeloTablaFaltas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
   
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modeloTablaFaltas;
    // End of variables declaration//GEN-END:variables
}
