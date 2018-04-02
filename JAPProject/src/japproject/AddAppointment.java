/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

/**
 *
 * @author Jose
 */
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


public class AddAppointment {
    public iPanel AddAppointments_Panel; //Panel to view, edit and add appointments.
    public JButton btnScheduleAppointment; //Allows the user to schedule a new appointment.
    public iTextField txtSearch; //Filter for the iTable that shows the user information as filtered by the user.
    public iTable tblPatientsForAppointments; //Table to display all appointments, can be filtered as user requests.
    
    
    public AddAppointment(iFrame if_)
    {
          try { 
            currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
            AddAppointments_Panel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//Defining iPanel dimensions
            AddAppointments_Panel.setBackground(Color.decode("#006738"));//le doy color al panel  
            
            
            btnScheduleAppointment = new JButton("Agendar Cita");
            btnScheduleAppointment.setToolTipText("Permite Agendar una nueva cita");
              
            
            ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaAgregarCitas order by `Fecha Cita` desc");

            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rs.getMetaData().getColumnName(i));
            }
            
            
              
              } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
