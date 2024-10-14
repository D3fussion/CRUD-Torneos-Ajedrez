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

import com.github.lgooddatepicker.components.DateTimePicker;
import db.Database;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


public class InsertMatches extends javax.swing.JInternalFrame {

    private final HashMap htTorneoID, htJugador1ID,htJugador2ID, htResults;
    private final Database db;
    private String pk = "";
    JDesktopPane desktopPane;
    int cambios = 0;

    public InsertMatches(Database db, JDesktopPane desktopPane) {
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

        htTorneoID = new HashMap<String, Integer>();
        htJugador1ID = new HashMap<String, Integer>();
        htJugador2ID = new HashMap<String, Integer>();
        htResults = new HashMap<String, Integer>();
        // Construir el combo con el ID y Nombre de proveedor
        buildComboTorneoID(db);
        buildComboJugador1ID(db);
        buildComboJugador2ID(db);
        buildComboResults(db);

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


    private void buildComboTorneoID(Database db) {
        final String sql = "select tournament_id, tournament_name FROM Tournaments";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);
                // Agregar nombre del proveedor al combo
                comboTorneoID.addItem(label);
                // Guardar nombre y ID del proveedor
                htTorneoID.put(label, id);
            }
        } catch (SQLException ex) {

        }
    }

    private void buildComboJugador1ID(Database db) {
        final String sql = "select player_id, first_name, last_name FROM Players";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2)+" "+rs.getString(3);
                // Agregar nombre del proveedor al combo
                comboJugador1ID.addItem(label);
                // Guardar nombre y ID del proveedor
                htJugador1ID.put(label, id);
            }
        } catch (SQLException ex) {

        }
    }

    private void buildComboJugador2ID(Database db) {
        final String sql = "select player_id, first_name, last_name FROM Players";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2)+" "+rs.getString(3);
                // Agregar nombre del proveedor al combo
                comboJugador2ID.addItem(label);
                // Guardar nombre y ID del proveedor
                htJugador2ID.put(label, id);
            }
        } catch (SQLException ex) {

        }
    }

    private void buildComboResults(Database db) {
        final String sql = "select result_code, result_description FROM Ref_Result_Codes";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);
                // Agregar nombre del proveedor al combo
                comboResultado.addItem(String.valueOf(id));
                // Guardar nombre y ID del proveedor
                htResults.put(label, id);
            }
        } catch (Exception ignored) {
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

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        fechaFin = new DateTimePicker();
        fechaInicio = new DateTimePicker();
        comboResultado = new javax.swing.JComboBox<>();
        comboJugador1ID = new javax.swing.JComboBox<>();
        comboJugador2ID = new javax.swing.JComboBox<>();
        comboTorneoID = new javax.swing.JComboBox<>();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Match");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tournament:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Player 1:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Player 2:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Start Date and Time:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("End Date and Time:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Result:");


        fechaInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechaFin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N



        okButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        okButton.setText("Add");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        //comboProv.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

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
                                                        .addComponent(comboTorneoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comboJugador1ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comboJugador2ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fechaInicio)
                                                        .addComponent(fechaFin)
                                                        .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        )))
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
                                        .addComponent(comboTorneoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(comboJugador1ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(comboJugador2ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

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
        String[] fechas = {fechaFin.toString(), fechaInicio.toString()};

        if(checkValidFields(fechas)) {
            Integer torneo_id = (Integer) htTorneoID.get(comboTorneoID.getSelectedItem());
            Integer jug1 = (Integer) htJugador1ID.get(comboJugador1ID.getSelectedItem());
            Integer jug2 = (Integer) htJugador2ID.get(comboJugador2ID.getSelectedItem());
            Integer resultado = Integer.parseInt(comboResultado.getSelectedItem().toString());


            StringBuilder sql
                    = new StringBuilder("INSERT INTO Matches (tournament_id, player_id1, player_id2, " +
                    "match_start_datetime, match_end_datetime, result_code) VALUES (\'");
            sql.append(torneo_id);
            sql.append("\',\'");
            sql.append(jug1);
            sql.append("\',\'");
            sql.append(jug2);
            sql.append("\',\'");
            sql.append(fechaInicio.toString());
            sql.append("\',\'");
            sql.append(fechaFin.toString());
            sql.append("\',\'");
            sql.append(resultado);
            sql.append("\');");

            System.out.println(sql);

            try {
                db.update(sql.toString());
                cambios++;
            } catch (SQLException ex) {
                JOptionPane.showInternalMessageDialog(desktopPane, "Error inserting match. This may be because the player does not participate in that tournament.\nThe previous changes will not be applied", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    db.con.rollback();
                } catch (SQLException ignored) {}
                System.out.println(ex.getMessage());
                dispose();
            }

            fechaInicio.clear();
            fechaFin.clear();
            comboTorneoID.setSelectedIndex(0);
            comboJugador1ID.setSelectedIndex(0);
            comboJugador2ID.setSelectedIndex(0);
            comboResultado.setSelectedIndex(0);


        } else {
            JOptionPane.showInternalMessageDialog(this, "Start and end dates must be filled.", "Error", JOptionPane.ERROR_MESSAGE);

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

    private boolean checkValidFields(String[] valores){
        for (String valore : valores) {
            if (valore.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> comboTorneoID;
    private javax.swing.JComboBox<String> comboJugador1ID;
    private javax.swing.JComboBox<String> comboJugador2ID;
    private javax.swing.JComboBox<String> comboResultado;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton okButton;
    private DateTimePicker fechaInicio;
    private DateTimePicker fechaFin;
    // End of variables declaration//GEN-END:variables
}
