/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iPanel;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
/**
 *
 * @author Jose
 */
public class Appointments {
    
    public iPanel Appointments_Panel;
    
    public Appointments(iFrame if_){
    currentPanel = "Appointments_Panel";
    Appointments_Panel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//Defining iPanel dimensions
    Appointments_Panel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel
    
    Appointments_Panel.finalice();
    }
    
    
    
    
}
