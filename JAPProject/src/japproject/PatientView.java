/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import static japproject.HomePanel.currentPanel;
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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author TUMA
 */
public class PatientView {

    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");

    public iPanel PatientView_panel;

    public PatientView(iFrame if_) {
        currentPanel = "PatientView_panel";
        try {
            //        try {          

            PatientView_panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);

            PatientView_panel.setBackground(Color.yellow);

            ResultSet rr = sql.SELECT("SELECT * FROM JAW_VistaPacientes");//query que selecciona todo de la vista

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

            PopMenu(RegistrosTable);//metodo que crea e implementa el popmenu 
            iScrollPane scrollPane2 = new iScrollPane(RegistrosTable, null);
            scrollPane2.setViewportView(RegistrosTable);
            SetColumsSizes(RegistrosTable);

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
            AddComponentes(scrollPane2);
            if_.add(PatientView_panel);
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 *
 * Metodo para crear el popmenu 
 */
    public static void PopMenu(iTable RegistrosTable)
    {
     JPopupMenu popup = new JPopupMenu();
            JMenuItem ItemEditar = new JMenuItem("Editar paciente");
            ItemEditar.addActionListener((ae) -> {
               
                addActionListenerEditarPopmenu();
                
            });

            popup.add(ItemEditar);
            RegistrosTable.setComponentPopupMenu(popup);
    
    }
    
    
    /**
 *
 * Metodo darle funcionalidad al ItemEditar del popmenu 
 */
    public static void addActionListenerEditarPopmenu()
    {
    
    
    }
    
 /**
 *
 * Metodo para añadir los componentes 
 */
    public void AddComponentes(iScrollPane scrollPane2) {
        PatientView_panel.AddSingleObject(scrollPane2, 100.0f, 92.4f, CENTER);
        PatientView_panel.newLine();
        PatientView_panel.finalice();
        PatientView_panel.setVisible(true);

    }

    /**
 *
 * Metodo Para setear los sizes de las columnas 
 * @return Para setear los sizes de las columnas
 */
    public void SetColumsSizes(iTable RegistrosTable) {
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

    }

}
