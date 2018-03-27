/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import java.awt.Color;




/**
 *
 * @author anfer
 */
public class EditPatient {

    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    public static iPanel PatientView_panel;

    public EditPatient(iFrame if_) {
        PatientView_panel=new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);
        PatientView_panel.setBackground(Color.BLUE);
        
        
                
    }

    
}
