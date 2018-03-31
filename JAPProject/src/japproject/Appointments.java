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
import iComponents.iTable;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.ResultSet;

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

        currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Appointments_Panel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//Defining iPanel dimensions
        Appointments_Panel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel               
        
        lblCalendar = new iLabel("Por favor Seleccione una fecha: "); //Lbl Guide
        lblCalendar.setForeground(Color.WHITE); //Calendar
        calendar = new iCalendar();
        Appointments_Panel.AddObject(lblCalendar, 190, 30,3);
        Appointments_Panel.AddObject(calendar, 70, 30, 193);
        Appointments_Panel.newLine();
        
        ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaCitas");
        
        //Appointments_Panel.AddObject(calendar, 10.0f, 5.0f,2);
        Appointments_Panel.finalice();
    }

}
