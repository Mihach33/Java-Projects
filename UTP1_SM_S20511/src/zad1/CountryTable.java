package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.*;

public class CountryTable extends JTable{
    private String countriesFileName;
    JTable countriesTable = new JTable();


    public CountryTable(String countriesFileName) {
        this.countriesFileName = countriesFileName;
    }


    public JTable create() throws IOException {
        File file = new File(countriesFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String firstline = br.readLine();
        String[] columnsName = firstline.split("\\t");
        DefaultTableModel model = (DefaultTableModel)countriesTable.getModel();
        model.setColumnIdentifiers(columnsName);



        Object[] tabLines = br.lines().toArray();
        for(int i = 0;  i < tabLines.length; i++){
            String line = tabLines[i].toString();
            String dataRow [] = line.split("\\t");
            Object [] obj = {dataRow[0], dataRow[1], Integer.parseInt(dataRow[2])};
            model.addRow(obj);
        }



        countriesTable = new JTable(model);
        countriesTable.setRowHeight(30);
        for (int i = 0; i < countriesTable.getColumnModel().getColumnCount(); i++) {
            TableColumn column = countriesTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        countriesTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                c.setForeground((column == 2 && Integer.parseInt(value.toString()) > 20000) ? Color.RED : Color.BLACK);
                return c;
            }
        });

        return countriesTable;
    }
}
