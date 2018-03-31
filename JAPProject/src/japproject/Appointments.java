/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iCalendar;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Appointments {

    public iPanel Appointments_Panel;
    public iLabel lblCalendar;

    public iCalendar calendar;

    public iTable tblCitas;

    public Appointments(iFrame if_) {
        try {
            currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
            Appointments_Panel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//Defining iPanel dimensions
            Appointments_Panel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel               

            lblCalendar = new iLabel("Por favor Seleccione una fecha: "); //Lbl Guide
            lblCalendar.setForeground(Color.WHITE); //Calendar
            calendar = new iCalendar();
            Appointments_Panel.AddObject(lblCalendar, 190, 30, 3);
            Appointments_Panel.AddObject(calendar, 70, 30, 193);
            Appointments_Panel.newLine();

            ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaCitas");

            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rs.getMetaData().getColumnName(i));
            }
//          Se crea la tabla y se le da los parametros

            tblCitas = new iTable(Cols);
            tblCitas.getTableHeader().setReorderingAllowed(false);
            tblCitas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tblCitas.getTableHeader().setResizingAllowed(false);
            tblCitas.setRowSelectionAllowed(true);
            tblCitas.setSize(1100, 300);

            iScrollPane scrollCitas = new iScrollPane(tblCitas, null);
            scrollCitas.setViewportView(tblCitas);
            SetColumsSizes(tblCitas);

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rs.getObject(i);
                        }

                        tblCitas.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
            Appointments_Panel.addSpace(20);
            Appointments_Panel.AddObject(scrollCitas, 1100, 300);
            Appointments_Panel.newLine();
            Appointments_Panel.finalice();
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SetColumsSizes(iTable tblCitas) {
        //Code to manage columns sizes
        tblCitas.getColumnModel().getColumn(0).setWidth(0);
        tblCitas.getColumnModel().getColumn(0).setMinWidth(0);
        tblCitas.getColumnModel().getColumn(0).setMaxWidth(0);

        tblCitas.getColumnModel().getColumn(1).setPreferredWidth(140);

        tblCitas.getColumnModel().getColumn(2).setWidth(0);
        tblCitas.getColumnModel().getColumn(2).setMinWidth(0);
        tblCitas.getColumnModel().getColumn(2).setMaxWidth(0);

        tblCitas.getColumnModel().getColumn(3).setPreferredWidth(140);

        tblCitas.getColumnModel().getColumn(4).setPreferredWidth(200);

        tblCitas.getColumnModel().getColumn(5).setPreferredWidth(400);

        tblCitas.getColumnModel().getColumn(6).setPreferredWidth(100);
        
        tblCitas.getColumnModel().getColumn(7).setPreferredWidth(150);
        
    }

}
