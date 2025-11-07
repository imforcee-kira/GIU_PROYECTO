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
    private String ciEstudianteLogueado; 
    private DefaultTableModel modelo;
    private String nombreVentana;
    
    
    
   
    public GIU_Estudiante(String ci, String rol, String nombre) {
        this.ciEstudianteLogueado = ci;
        this.nombreVentana = nombre;
        initComponents();
        cargarTablaFaltasPorClase();
        nombreUsuario.setText(nombreVentana);
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        
    }
    private void cargarTablaFaltasPorClase(){
        Registro registroDB = new Registro(); 
    String idClase = null;
    try {
        
        idClase = registroDB.buscarIdClasePorEstudiante(this.ciEstudianteLogueado);
        
        if (idClase == null || idClase.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar la clase a la que pertenece el estudiante.", "Error de Configuración", JOptionPane.WARNING_MESSAGE);
            return;
        }

       
        List<Object[]> filasFaltas = Registro.buscarFaltasPorClase(idClase); 
            
        
        modelo = new DefaultTableModel();
        String[] titulos = {"Fecha Inicio", "Fecha Fin", "Motivo", "Profesor"};
        modelo.setColumnIdentifiers(titulos);
            
        for (Object[] fila : filasFaltas) {
            modelo.addRow(fila);
        }
            
        
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
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modeloTablaFaltas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCerrarsesion = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Estudiante:");

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

        nombreUsuario.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        nombreUsuario.setText("a");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(nombreUsuario))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        menuCerrarsesion.setForeground(new java.awt.Color(51, 51, 51));
        menuCerrarsesion.setIcon(new javax.swing.ImageIcon("C:\\Users\\ezequ\\Downloads\\menu_16dp_000000_FILL0_wght400_GRAD0_opsz20.png")); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ezequ\\Downloads\\logout_16dp_000000_FILL0_wght400_GRAD0_opsz20.png")); // NOI18N
        jMenuItem1.setText("Cerrar sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuCerrarsesion.add(jMenuItem1);

        menuSalir.setIcon(new javax.swing.ImageIcon("C:\\Users\\ezequ\\Downloads\\close_16dp_000000_FILL0_wght400_GRAD0_opsz20.png")); // NOI18N
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        menuCerrarsesion.add(menuSalir);

        jMenuBar1.add(menuCerrarsesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
int confirm = JOptionPane.showConfirmDialog(this,
        "¿Está seguro que desea cerrar la sesión actual?", 
        "Confirmar Cierre de Sesión", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);

    if (confirm == JOptionPane.YES_OPTION) {
        this.dispose(); 
        
        
        GIU_LOGIN login = new GIU_LOGIN();
        login.setVisible(true);
    }    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
int confirm = JOptionPane.showConfirmDialog(this,
        "¿Está seguro que desea cerrar el programa por completo?", 
        "Confirmar Salida", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE); 

    if (confirm == JOptionPane.YES_OPTION) {
        System.exit(0); 
    }    }//GEN-LAST:event_menuSalirActionPerformed

   
   
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuCerrarsesion;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JTable modeloTablaFaltas;
    private javax.swing.JLabel nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
