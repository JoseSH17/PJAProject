/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iScrollPane;
import iComponents.iTable;
import java.awt.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author TUMA
 */
public class PatientView {

   public static iSQL sql;

    public static iPanel PatientView_panel;

    public PatientView(iFrame if_) {
        try {
            //        try {
  sql=new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
            PatientView_panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);

            PatientView_panel.setBackground(Color.yellow);

         ResultSet rr=sql.SELECT("SELECT * FROM JAW_VistaPacientes");//query que selecciona todo de la vista
         
            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rr.getMetaData().getColumnCount(); i++) {
                Cols.add(rr.getMetaData().getColumnLabel(i));
            }
//          Se crea la tabla y se le da los parametros

            iTable RegistrosTable = new iTable(Cols);
            RegistrosTable.getTableHeader().setReorderingAllowed(false);
            RegistrosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            RegistrosTable.getTableHeader().setResizingAllowed(false);
            RegistrosTable.setSize(500, 500);

            iScrollPane scrollPane2 = new iScrollPane(RegistrosTable, Color.red);
//            scrollPane2.setViewportView(RegistrosTable);

//            Codigo para manipular los sizes de las columnas
            RegistrosTable.getColumnModel().getColumn(0).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(0).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(0).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(1).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(2).setPreferredWidth(140);

            RegistrosTable.getColumnModel().getColumn(3).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(3).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(3).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(5).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(5).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(5).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(6).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(7).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(8).setPreferredWidth(140);

            RegistrosTable.getColumnModel().getColumn(9).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(9).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(9).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(10).setPreferredWidth(140);

            RegistrosTable.getColumnModel().getColumn(11).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(11).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(11).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(12).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(13).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(14).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(15).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(16).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(17).setPreferredWidth(140);

            RegistrosTable.getColumnModel().getColumn(18).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(18).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(18).setMaxWidth(0);

            RegistrosTable.getColumnModel().getColumn(19).setPreferredWidth(140);
            RegistrosTable.getColumnModel().getColumn(20).setPreferredWidth(140);

            RegistrosTable.getColumnModel().getColumn(21).setWidth(0);
            RegistrosTable.getColumnModel().getColumn(21).setMinWidth(0);
            RegistrosTable.getColumnModel().getColumn(21).setMaxWidth(0);
//fin de los parametros de la tabla

            if (sql.Exists(rr)) {//verifica que el query sea valido
                try {

                    while (rr.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rr.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rr.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rr.getObject(i);
                        }

                        RegistrosTable.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }

            PatientView_panel.AddSingleObject(scrollPane2,20.0f,20.0f,CENTER);
            PatientView_panel.newLine();
            PatientView_panel.finalice();
            PatientView_panel.setVisible(true);

            if_.add(PatientView_panel);
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
