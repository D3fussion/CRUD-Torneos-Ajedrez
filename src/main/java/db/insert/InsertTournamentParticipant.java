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

import db.Database;


public class InsertTournamentParticipant extends javax.swing.JInternalFrame {

    private final HashMap htTorneoID, htJugadorID;
    private final Database db;
    private String pk = "";
    JDesktopPane desktopPane;
    int cambios;

    public InsertTournamentParticipant(Database db, JDesktopPane desktopPane) {
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
        htJugadorID =new HashMap<String, Integer>();
        buildComboTorneoID(db);
        buildComboJugadorID(db);

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

    //combo box for insert of tournament
    private void buildComboTorneoID(Database db) {
        final String sql = "select tournament_id, tournament_name FROM Tournaments";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);

                // Se recorta el label si es muy largo
                if(label.length() > 18) {
                    label = label.substring(0, 18) + "...";
                }

                // Agregar nombre del proveedor al combo
                comboTorneoID.addItem(label);
                // Guardar nombre y ID del proveedor
                htTorneoID.put(label, id);
            }
        } catch (SQLException ignored) {

        }
    }

    private void buildComboJugadorID(Database db) {
        final String sql = "select player_id, first_name, last_name FROM Players";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2)+" "+rs.getString(3);

                // Se recorta el label si es muy largo
                if(label.length() > 18) {
                    label = label.substring(0, 18) + "...";
                }

                // Agregar nombre del proveedor al combo
                comboJugadorID.addItem(label);
                // Guardar nombre y ID del proveedor
                htJugadorID.put(label, id);
            }
        } catch (SQLException ignored) {

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
        txtResult = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        comboTorneoID = new javax.swing.JComboBox<>();
        comboJugadorID = new javax.swing.JComboBox<>();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Participant");
        setResizable(false);


        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tour.:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Player:");


        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Final Result:");


        txtResult.setColumns(11);
        txtResult.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        comboTorneoID.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N


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
                                                        .addComponent(jLabel3))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(comboTorneoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(comboJugadorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtResult)
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
                                        .addComponent(comboJugadorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if(!checkFinalResult(txtResult.getText())){
            return;
        }
        Integer tournament_id = (Integer) htTorneoID.get(comboTorneoID.getSelectedItem());
        Integer player_id = (Integer) htJugadorID.get(comboJugadorID.getSelectedItem());
        txtResult.setText("\'"+txtResult.getText()+"\'");
        String result = txtResult.getText();



        StringBuilder sql
                = new StringBuilder("INSERT INTO Player_Tournament_Participation (player_id, tournament_id, " +
                "final_result) VALUES (");
        sql.append( player_id );
        sql.append(",\'");
        sql.append(tournament_id);
        sql.append("\',");
        sql.append( result );
        sql.append(");");

        System.out.println( sql.toString() );

        try {
            db.update( sql.toString() );
            cambios++;
        } catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(desktopPane, "Error inserting tournament participant.\nThe previous changes will not be applied", "Error", JOptionPane.ERROR_MESSAGE);
            try {
                db.con.rollback();
            } catch (SQLException ignored) {}
            System.out.println( ex.getMessage() );
            dispose();
        }

        comboJugadorID.setSelectedIndex(0);
        comboTorneoID.setSelectedIndex(0);
        txtResult.setText("");

    }//GEN-LAST:event_okButtonActionPerformed


    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if(cambios>0) aplicar();
        else dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private boolean checkFinalResult(String result){
        if(result.equals("Win") || result.equals("Loss") || result.equals("Draw")){
            return true;
        } else {
            JOptionPane.showInternalMessageDialog(this, "Final result must be Win, Loss or Draw.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> comboTorneoID;
    private javax.swing.JComboBox<String> comboJugadorID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField txtResult;
    // End of variables declaration//GEN-END:variables
}
