/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import static japproject.PatientView.RePanel;
import java.awt.Color;

/**
 *
 * @author Jose
 */
public class NewPatient {
    
    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    
    public static iPanel RePanel;
    
    
    public NewPatient(iFrame if_) {
    
        RePanel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);
         
        RePanel.setBackground(Color.GREEN);
        
    }
    
}
