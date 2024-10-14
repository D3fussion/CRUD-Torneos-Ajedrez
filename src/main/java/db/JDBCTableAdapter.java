package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diegoaaron
 */
public class JDBCTableAdapter extends DefaultTableModel {

    private final boolean isEditable;
    private HashSet<Integer> indexPK = new HashSet<>();


    public JDBCTableAdapter(ResultSet res, boolean isEditable) {
        
        super();
        this.isEditable = isEditable;

        generateTable(res);


    }

    public JDBCTableAdapter(ResultSet res, boolean isEditable, HashSet<Integer> indexPK) {

        super();
        this.isEditable = isEditable;
        this.indexPK = indexPK;

        generateTable(res);


    }

    private void generateTable(ResultSet rs){

        int rows;
        int cols;

        Object[][] data = null;
        Object[] names = null;

        try {
            ResultSetMetaData metaData = rs.getMetaData();

            cols = metaData.getColumnCount();

            names = new Object[cols];
            String[] sqlColumnNames = new String[cols];
            int[] sqlColumnTypes = new int[cols];



            int c = 0;
            for (int i = 1; i <= cols; i++) {
                sqlColumnNames[c]  = metaData.getColumnName(i);
                sqlColumnTypes[c] = metaData.getColumnType(i);
                names[c++] = metaData.getColumnLabel(i);
            }


            rs.last();


            rows = rs.getRow();

            data = new Object[rows][cols];


            rs.beforeFirst();

            int r = 0;
            while ( rs.next() ) {
                c = 0;
                for (int i = 1; i <= cols; i++) {
                    data[r][c++] = rs.getString(i);
                }
                r++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        this.setDataVector(data, names);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(isEditable){
            return column != 0 && !indexPK.contains(column);
        }else{
            return false;
        }
    }


    

}
