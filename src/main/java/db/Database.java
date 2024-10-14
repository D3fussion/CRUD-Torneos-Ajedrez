package db;

import javax.swing.*;
import java.io.*;
import java.sql.*;


public class Database {

    public Connection con;

    //    private final String URL_DB = "jdbc:postgresql://148.225.64.49:5432/Prueba 222206011";

    public static Database DB = null;
    private String ip;
    private String puerto;
    private String nombreBD;
    private String usuario;
    private String contrasena;

    private Database(JDesktopPane desktopPane, boolean primeraVez) {
        super();
        con = null;
        File parametros = new File("parametros.isi");
        if(!primeraVez) parametros.delete();
        while (true) {
            if (parametros.exists()) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new java.io.FileReader(parametros));
                    ip = br.readLine();
                    puerto = br.readLine();
                    nombreBD = br.readLine();
                    usuario = br.readLine();
                    contrasena = br.readLine();
                    br.close();

                    String DRIVER = "org.postgresql.Driver";
                    Class.forName(DRIVER);

                    String URL_DB = "jdbc:postgresql://" + ip + ":" + puerto + "/" + nombreBD;

                    con = DriverManager.getConnection(URL_DB, usuario, contrasena);
                    con.setAutoCommit(false);

                    break;
                } catch (Exception e) {
                    try {
                        assert br != null;
                        br.close();
                    } catch (Exception ignored) {}
                    parametros.delete();
                    JOptionPane.showInternalMessageDialog(desktopPane, "There is an error in the database parameters, please enter the data again.", "Parameters", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String x;
                String y;
                String z;
                String w;
                String v;
                try {
                    JOptionPane.showInternalMessageDialog(desktopPane, "To use the program, you have to enter the database information.", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                    while (true) {
                        try {
                            x = JOptionPane.showInternalInputDialog(desktopPane, "Enter the server's IP", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                            if (x.isEmpty())
                                JOptionPane.showInternalMessageDialog(desktopPane, "Server's IP can't be null", "Error", JOptionPane.ERROR_MESSAGE);
                            else break;
                        } catch (Exception e) {JOptionPane.showInternalMessageDialog(desktopPane, "Server's IP can't be null", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                    while (true) {
                        try {
                            y = JOptionPane.showInternalInputDialog(desktopPane, "Enter the server's port", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                            if (y.isEmpty())
                                JOptionPane.showInternalMessageDialog(desktopPane, "Server's port can't be null", "Error", JOptionPane.ERROR_MESSAGE);
                            else break;
                        } catch (Exception e) {JOptionPane.showInternalMessageDialog(desktopPane, "Server's port can't be null", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                    while (true) {
                        try {
                            z = JOptionPane.showInternalInputDialog(desktopPane, "Enter the name of the database", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                            if (z.isEmpty())
                                JOptionPane.showInternalMessageDialog(desktopPane, "Database name can't be null", "Error", JOptionPane.ERROR_MESSAGE);
                            else break;
                        } catch (Exception e) {JOptionPane.showInternalMessageDialog(desktopPane, "Database name can't be null", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                    while (true) {
                        try {
                            w = JOptionPane.showInternalInputDialog(desktopPane, "Enter the database user", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                            if (w.isEmpty())
                                JOptionPane.showInternalMessageDialog(desktopPane, "Database user can't be null", "Error", JOptionPane.ERROR_MESSAGE);
                            else break;
                        } catch (Exception e) {JOptionPane.showInternalMessageDialog(desktopPane, "Database user can't be null", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                    while (true){
                        try {
                            v = JOptionPane.showInternalInputDialog(desktopPane, "Enter the database password", "Parameters", JOptionPane.INFORMATION_MESSAGE);
                            if (v.isEmpty())
                                JOptionPane.showInternalMessageDialog(desktopPane, "Database password can't be null", "Error", JOptionPane.ERROR_MESSAGE);
                            else break;
                        } catch (Exception e) {JOptionPane.showInternalMessageDialog(desktopPane, "Database password can't be null", "Error", JOptionPane.ERROR_MESSAGE);}
                    }

                    parametros.createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(parametros));
                    bw.write(x); bw.newLine();
                    bw.write(y); bw.newLine();
                    bw.write(z); bw.newLine();
                    bw.write(w); bw.newLine();
                    bw.write(v);
                    bw.close();
                } catch (Exception e) {
                    System.out.println("Error en la creacion: " + e.getMessage());
                    System.out.println(e);
                }
            }
        }
    }

    public static Database getDatabase(JDesktopPane desktopPane, boolean primeraVez) {
        if (DB == null) {
            DB = new Database(desktopPane, primeraVez);
        }
        return DB;
    }

    public ResultSet query(String sql) throws SQLException {

        ResultSet rs;
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = statement.executeQuery(sql);

        return rs;
    }

    public ResultSet query(String sql, int scroll, int concur) throws SQLException {

        ResultSet rs;

        Statement statement = con.createStatement(scroll, concur);
        rs = statement.executeQuery(sql);

        return rs;
    }

    public String getLastPrimaryKey(String tablename, String column) throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "SELECT "+column+" FROM "+tablename+";";
        ResultSet rs = stmt.executeQuery( sql );
        String pk;
        int id = 0;

        while ( rs.next() ) {
            if(rs.getInt(column) > id) id = rs.getInt(column);
        }

        pk = String.valueOf(++id);
        rs.close();

        stmt.close();
        return pk;
    }

    public String getColumnName(int index, String tablename) {
        Statement stmt;
        ResultSet rs;
        String name = "";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM "+tablename);
            var rsmd = rs.getMetaData();
            name= rsmd.getColumnName(++index);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }



    public int update(String sql) throws SQLException {
        int result;

        Statement statement = con.createStatement(ResultSet.CONCUR_UPDATABLE,
                ResultSet.TYPE_FORWARD_ONLY);
        result = statement.executeUpdate(sql);
        return result;
    }
}
