package db.update;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import db.Database;
import db.JDBCTableAdapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author diegoaaron
 */
public class Updater extends Component implements TableModelListener  {
    
    private final Database db;
    private String tableName;
    private String columnPK;
    private String[] columnsPK;
    private boolean manyPK;
    private String value;

    public Updater(Database d, String tableName, String columnPK) {
        super();
        db = d;
        this.tableName = tableName;
        this.columnPK = columnPK;
        manyPK = false;
    }

    public Updater(Database d, String tableName, String[] columnsPK) {
        super();
        db = d;
        this.tableName = tableName;
        this.columnsPK = columnsPK;
        manyPK = true;
    }


    @Override
    public void tableChanged(TableModelEvent event) {
        JDBCTableAdapter modelo = (JDBCTableAdapter) event.getSource();
        int row = event.getFirstRow();
        int column = event.getColumn();
        int type = event.getType();
        value = (String) modelo.getValueAt(row, column);

        if(comprobarCosas(db.getColumnName(column, tableName), value)) {

            if (value.contains("'")) {
                value = value.replaceAll("'", "''");
            }

            StringBuilder sql = new StringBuilder(String.format(
                    "UPDATE " + tableName + " SET" + " %s " + "= '%s' WHERE ",
                    db.getColumnName(column, tableName), value));


            if (column == 0) {
                return;
            }

            if (!manyPK) {
                sql.append(columnPK).append(" = '").append(modelo.getValueAt(row, 0)).append("'");
            } else {
                for (int i = 0; i < columnsPK.length; i++) {
                    if (columnsPK.length - 1 == i) {
                        sql.append(columnsPK[i]).append(" = '").append(modelo.getValueAt(row, i)).append("'");
                    } else {
                        sql.append(columnsPK[i]).append(" = '").append(modelo.getValueAt(row, i)).append("' AND ");
                    }
                }
            }

            try {
                db.update(sql.toString());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(sql);
        }
    }

    private boolean comprobarCosas(String nombreColumna, String valor){
        if(valor.isEmpty() || valor.trim().isEmpty()) {
            if (!(nombreColumna.equals("tournament_details") || nombreColumna.equals("other_player_details") || nombreColumna.equals("other_sponsor_details") || nombreColumna.equals("organizer_details") || nombreColumna.equals("other_club_details"))) {
                JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nYou must enter a value", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                value = "No details";
                return true;
            }
        }
        switch (tableName) {
            case "Chess_Clubs" -> {
                if(nombreColumna.equals("organizer_id")){
                    try{
                        Long n = Long.parseLong(valor);
                        return true;
                    } catch(Exception e){
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nOrganizer ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            case "Players" -> {
                if(nombreColumna.equals("email_address")){
                    if(!valor.contains("@") || !valor.contains(".")){
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nEmail address must be valid", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    } else return true;
                } else if (nombreColumna.equals("phone_number")){
                    try{
                        long n = Long.parseLong(valor);
                        if(valor.length() != 10) {
                            JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nYour phone number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                        else return true;
                    } catch(Exception e){
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nYour phone number must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            case "Ref_Result_Codes" -> {
                if(nombreColumna.equals("result_description")) {
                    if(valor.equals("Win") || valor.equals("Lose") || valor.equals("Draw")) return true;
                    else{
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nResult description must be Win, Lose or Draw", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            case "List_of_Sponsors" -> {
                if (nombreColumna.equals("sponsor_phone")){
                    try{
                        long n = Long.parseLong(valor);
                        if(valor.length() != 10) {
                            JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nYour phone number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                        else return true;
                    } catch(Exception e){
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nYour phone number must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            case "Player_Tournament_Participation" -> {
                if(nombreColumna.equals("final_result")) {
                    if(valor.equals("Win") || valor.equals("Lose") || valor.equals("Draw")) return true;
                    else{
                        JOptionPane.showInternalMessageDialog(this, "Invalid entry, the variable will not be updated\nResult description must be Win, Lose or Draw", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}
