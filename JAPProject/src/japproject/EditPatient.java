/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.LEFT;
import static iComponents.ComponentInterfaz.RIGHT;
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import java.awt.Color;
import static javax.swing.SwingConstants.BOTTOM;




/**
 *
 * @author anfer
 */
public class EditPatient {

    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    public static iPanel PatientView_panelLeft;
    public static iPanel PatientView_panelRight;
    public static iPanel PatientView_panelTable;

    public EditPatient(iFrame if_) {
        PatientView_panelLeft=new iPanel(0, 70,50.0f, 80.0f, 0, 0, if_);
        PatientView_panelLeft.setBackground(Color.BLUE);
        
        PatientView_panelRight=new iPanel(0, 70, 50.0f, 50.0f, 0, 0, if_);
        PatientView_panelRight.setBackground(Color.RED);
        
        PatientView_panelTable=new iPanel(0, 0, 100.0f, 10.0f, 0, 0, if_);
        PatientView_panelTable.setBackground(Color.CYAN);
        
        
        
        if_.AddObject(PatientView_panelLeft,if_.getWidth()/2,700,LEFT);
        if_.AddObject(PatientView_panelRight,if_.getWidth()/2,700,RIGHT);
        if_.newLine();
        if_.AddObject(PatientView_panelTable,400,400,BOTTOM);
        if_.newLine();
                
    }
    
    

    
}
