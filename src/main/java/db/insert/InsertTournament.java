package db.insert;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.github.lgooddatepicker.components.DatePicker;
import db.Database;

public class InsertTournament extends javax.swing.JInternalFrame {

    private final HashMap ht;
    private final Database db;
    JDesktopPane desktopPane;
    int cambios = 0;

    public InsertTournament(Database db, JDesktopPane desktopPane) {
        super("", true, true,false,true);
        initComponents();
        this.db = db;
        this.desktopPane = desktopPane;
        cambios = 0;

        // Center in the screen
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = getSize();
        setLocation((desktopSize.width - frameSize.width)/2,
                (desktopSize.height- frameSize.height)/2);;

        ht = new HashMap<String, Integer>();
        try {
            String pk = db.getLastPrimaryKey("Tournaments", "tournament_id");
            txtID.setText(pk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Construir el combo con el ID y Nombre de proveedor
        buildCombo(db);

        this.addInternalFrameListener(new InternalFrameListener() {

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if(cambios>0) aplicar();
                else dispose();
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {

            }
        });

    }

    private void buildCombo(Database db) {
        final String sql = "select organizer_id, organizer_name FROM Tournament_Organizers";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);
                // Agregar nombre del proveedor al combo
                comboOrgID.addItem(label);
                // Guardar nombre y ID del proveedor
                ht.put(label, id);
            }
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon iconoOriginal = new ImageIcon("icono.png");
        Image imagen = iconoOriginal.getImage(); // transform it
        Image imagenNueva = imagen.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon iconoEscalado = new ImageIcon(imagenNueva);  // transform it back
        setFrameIcon(iconoEscalado);

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        fechaInicio = new DatePicker();
        fechaFin = new DatePicker();
        txtDetalles = new javax.swing.JTextField();
        comboOrgID = new javax.swing.JComboBox<>();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Torneo");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID de Torneo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Organizador:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Fecha de inicio:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Fecha fin:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre del torneo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Detalles:");

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N


        fechaInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechaFin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNombre.setColumns(11);
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDetalles.setColumns(11);
        txtDetalles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N


        okButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        okButton.setText("Agregar");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        comboOrgID.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtID)
                                                        .addComponent(comboOrgID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fechaInicio)
                                                        .addComponent(fechaFin)
                                                        .addComponent(txtNombre)
                                                        .addComponent(txtDetalles))))
                                .addContainerGap(25, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(okButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelButton)
                                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(comboOrgID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        JTextField[] textFields = {txtNombre, fechaFin.getComponentDateTextField(), fechaInicio.getComponentDateTextField()};
        if(checkValidFields(textFields)) {
            Integer id = (Integer) ht.get(comboOrgID.getSelectedItem());
            String nombre = txtNombre.getText();
            String otros_detalles = txtDetalles.getText();

            if (nombre.contains("'")) {
                nombre = nombre.replaceAll("'", "''");
            }
            if (otros_detalles.contains("'")) {
                otros_detalles = otros_detalles.replaceAll("'", "''");
            }

            StringBuilder sql;
            if(!otros_detalles.isEmpty()) {
                sql = new StringBuilder("INSERT INTO Tournaments (organizer_id, tournament_start_date, tournament_end_date, tournament_name, tournament_details) VALUES (\'");
                sql.append(id);
                sql.append("','");
                sql.append(fechaInicio.toString());
                sql.append("','");
                sql.append(fechaFin.toString());
                sql.append("','");
                sql.append(nombre);
                sql.append("',");
                sql.append(otros_detalles);
                sql.append(");");
            } else {
                sql = new StringBuilder("INSERT INTO Tournaments (organizer_id, tournament_start_date, tournament_end_date, tournament_name) VALUES (\'");
                sql.append(id);
                sql.append("','");
                sql.append(fechaInicio.toString());
                sql.append("','");
                sql.append(fechaFin.toString());
                sql.append("','");
                sql.append(nombre);
                sql.append("');");
            }
            System.out.println(sql.toString());

            try {
                db.update(sql.toString());
                cambios++;
            } catch (SQLException ex) {
                JOptionPane.showInternalMessageDialog(desktopPane, "Error trying to insert tournament\nThe previous changes will not be applied", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    db.con.rollback();
                } catch (SQLException ignored) {}
                System.out.println(ex.getMessage());
                dispose();
            }

            comboOrgID.setSelectedIndex(0);
            txtNombre.setText("");
            fechaInicio.setText("");
            fechaFin.setText("");
            txtDetalles.setText("");
            try {
                String pk = db.getLastPrimaryKey("Tournaments", "tournament_id");
                txtID.setText(pk);
            } catch (SQLException e) {
                e.printStackTrace();
            }



        } else {
            JOptionPane.showInternalMessageDialog(this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_okButtonActionPerformed


    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if(cambios>0) aplicar();
        else dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void aplicar() {
        int opcion = JOptionPane.showInternalOptionDialog(desktopPane, "Do you want to apply changes?", "Apply changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "No");
        try {
            if (opcion == JOptionPane.YES_OPTION) {
                db.con.commit();
            } else {
                db.con.rollback();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        dispose();
    }

    private boolean checkValidFields(JTextField[] textFields){
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton okButton;
    private JLabel txtID;
    private javax.swing.JTextField txtNombre;
    private DatePicker fechaInicio;
    private DatePicker fechaFin;
    private JComboBox<String> comboOrgID;
    private javax.swing.JTextField txtDetalles;
    // End of variables declaration//GEN-END:variables
}

