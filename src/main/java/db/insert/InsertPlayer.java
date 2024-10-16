package db.insert;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import db.Database;


public class InsertPlayer extends javax.swing.JInternalFrame {

    private final HashMap ClubIDht;
    private final HashMap RankingIDht;
    private final Database db;
    private String pk = "";
    JDesktopPane desktopPane;
    int cambios = 0;

    public InsertPlayer(Database db, JDesktopPane desktopPane) {
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

        ClubIDht = new HashMap<String, Integer>();
        RankingIDht = new HashMap<String, Integer>();
        try {
           pk = db.getLastPrimaryKey("Players", "player_id");
           txtID.setText(pk);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        buildComboClubID(db);
        buildComboRankingID(db);

        this.addInternalFrameListener(new InternalFrameListener() {

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if(cambios > 0) aplicar();
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

    private void buildComboClubID(Database db) {
        final String sql = "select club_id, club_name FROM Chess_Clubs";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            String noClub = "Doesn't belong to a Club";
            comboClubID.addItem(noClub);
            ClubIDht.put(noClub, null);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);

                // Se recorta el label si es muy largo
                if(label.length() > 23) {
                    label = label.substring(0, 23) + "...";
                }

                // Agregar nombre del proveedor al combo
                comboClubID.addItem(label);
                // Guardar nombre y ID del proveedor
                ClubIDht.put(label, id);
            }
        } catch (SQLException ignored) {

        }
    }

    private void buildComboRankingID(Database db) {
        final String sql = "select ranking_code, ranking_description FROM Ref_Ranking_Codes";

        try {
            // Enviar consulta a la base de datos
            ResultSet rs = db.query(sql);
            while ( rs.next() ) {
                int id = rs.getInt(1);
                String label = rs.getString(2);

                // Se recorta el label si es muy largo
                if(label.length() > 23) {
                    label = label.substring(0, 23) + "...";
                }

                // Agregar nombre del proveedor al combo
                comboRankingID.addItem(label);
                // Guardar nombre y ID del proveedor
                RankingIDht.put(label, id);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        txtID = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtDetalles = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        comboClubID = new javax.swing.JComboBox<>();
        comboRankingID =  new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Player");
        setResizable(false);



        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Player ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Club:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Ranking:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("First Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Last Name:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Address:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Phone Number:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Other Details:");

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        /*
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                getNextID(evt);
            }


        });

         */

        txtNombre.setColumns(11);
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtApellido.setColumns(11);
        txtApellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDireccion.setColumns(11);
        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCelular.setColumns(11);
        txtCelular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDetalles.setColumns(11);
        txtDetalles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCorreo.setColumns(11);
        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N


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

        comboClubID.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        comboRankingID.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N


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
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboClubID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(comboRankingID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtNombre)
                                                        .addComponent(txtApellido)
                                                        .addComponent(txtDireccion)
                                                        .addComponent(txtCelular)
                                                        .addComponent(txtCorreo)
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
                                        .addComponent(comboClubID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(comboRankingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
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
        JTextField[] jTextFields = new JTextField[]{txtNombre, txtApellido, txtCorreo, txtDireccion, txtCelular};


        if(checkPhoneNumber(txtCelular.getText()) && checkEmail(txtCorreo.getText()) && checkValidFields(jTextFields) && checkClub()) {
            Integer club_id = (Integer) ClubIDht.get(comboClubID.getSelectedItem());
            Integer ranking = (Integer) RankingIDht.get(comboRankingID.getSelectedItem());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String direccion = txtDireccion.getText();
            String correo = txtCorreo.getText();
            String otros_detalles = txtDetalles.getText();
            String cel = txtCelular.getText();

            if (nombre.contains("'")) {
                nombre = nombre.replaceAll("'", "''");
            }
            if (apellido.contains("'")) {
                apellido = apellido.replaceAll("'", "''");
            }
            if (direccion.contains("'")) {
                direccion = direccion.replaceAll("'", "''");
            }
            if (otros_detalles.contains("'")) {
                otros_detalles = otros_detalles.replaceAll("'", "''");
            }
            if (correo.contains("'")) {
                correo = correo.replaceAll("'", "''");
            }

            StringBuilder sql;
            if(!otros_detalles.isEmpty()) {
                sql = new StringBuilder("INSERT INTO Players (club_id,ranking_code,first_name," +
                        "last_name,address,phone_number,email_address,other_player_details) VALUES (");
                sql.append(club_id);
                sql.append(",");
                sql.append(ranking);
                sql.append(",'");
                sql.append(nombre);
                sql.append("','");
                sql.append(apellido);
                sql.append("',");
                sql.append(direccion);
                sql.append(",");
                sql.append(cel);
                sql.append(",'");
                sql.append(correo);
                sql.append("',");
                sql.append(otros_detalles);
                sql.append(");");
            } else {
                sql = new StringBuilder("INSERT INTO Players (club_id,ranking_code,first_name," +
                        "last_name,address,phone_number,email_address) VALUES (");
                sql.append(club_id);
                sql.append(",");
                sql.append(ranking);
                sql.append(",'");
                sql.append(nombre);
                sql.append("','");
                sql.append(apellido);
                sql.append("','");
                sql.append(direccion);
                sql.append("',");
                sql.append(cel);
                sql.append(",'");
                sql.append(correo);
                sql.append("');");
            }

            System.out.println(sql);

            try {
                db.update(sql.toString());
                cambios++;
            } catch (SQLException ex) {
                JOptionPane.showInternalMessageDialog(desktopPane, "Error inserting player\nThe previous changes will not be applied", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    db.con.rollback();
                } catch (SQLException ignored) {}
                System.out.println(ex.getMessage());
                dispose();
            }

            comboClubID.setSelectedIndex(0);
            comboRankingID.setSelectedIndex(0);
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtCelular.setText("");
            txtCorreo.setText("");
            txtDetalles.setText("");
            try {
                pk = db.getLastPrimaryKey("Players", "player_id");
                txtID.setText(pk);
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }//GEN-LAST:event_okButtonActionPerformed

    private boolean checkClub(){
        if(Objects.requireNonNull(comboClubID.getSelectedItem()).toString().contentEquals("Doesn't belong to a Club")){
            JOptionPane.showInternalMessageDialog(this, "Player must belong to a Club", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPhoneNumber(String text){
        try{
            long n = Long.parseLong(text);
            if(text.length() != 10) {
                JOptionPane.showInternalMessageDialog(this, "Your phone number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
                txtCelular.setText("");
                return false;
            }
            else return true;
        }catch(Exception e){
            JOptionPane.showInternalMessageDialog(this, "Your phone number must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            txtCelular.setText("");
            return false;
        }
    }

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

    private boolean checkEmail(String text) {
        if(text.contains("@") && text.contains(".")){
            return true;
        } else {
            JOptionPane.showInternalMessageDialog(this, "Invalid email address");
            txtCorreo.setText("");
            return false;
        }
    }

    private boolean checkValidFields(JTextField[] textFields){
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> comboClubID;
    private javax.swing.JComboBox<String> comboRankingID;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton okButton;
    private JLabel txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDetalles;
}
