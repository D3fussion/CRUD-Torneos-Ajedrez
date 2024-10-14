package db.read;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.Database;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;

public class TableBrowser extends javax.swing.JInternalFrame {
    private  JTable table = null;
    private final Database db;
    private final JDesktopPane desktopPane;
    int cambios = 0;

    public TableBrowser(String title, TableModel modelo, JDesktopPane desktopPane, boolean esEditable, Database db) {
        super(title,true,true,true,true);   
        this.initComponents(modelo);
        this.db = db;
        this.desktopPane = desktopPane;

        // Center in the screen
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = getSize();
        setLocation((desktopSize.width - frameSize.width)/2,
                (desktopSize.height- frameSize.height)/2);

        modelo.addTableModelListener((e) -> {
            cambios++;
        });

        this.addInternalFrameListener(new InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if(esEditable && cambios>0) aplicar();
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
    
    private  void initComponents(TableModel modelo) {
        ImageIcon iconoOriginal = new ImageIcon("icono.png");
        Image imagen = iconoOriginal.getImage(); // transform it
        Image imagenNueva = imagen.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon iconoEscalado = new ImageIcon(imagenNueva);  // transform it back
        setFrameIcon(iconoEscalado);

        // Crear la tabla con los datos de la consulta
        table = new JTable(modelo);
        table.setPreferredScrollableViewportSize(new Dimension(640, 320));
        table.setFillsViewportHeight(true);

        //Crear un panel con scroll y agregar la tabla.
        JScrollPane scrollPane = new JScrollPane(table);

        this.getContentPane().add(scrollPane);
        this.pack();       
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
}
