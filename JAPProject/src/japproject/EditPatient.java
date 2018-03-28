/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.LEFT;
import static iComponents.ComponentInterfaz.RIGHT;
import static japproject.PatientView.tbl_Data;
import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iTextField;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        
        PatientView_panelRight=new iPanel(0, 70, 50.0f, 80.0f, 0, 0, if_);
        PatientView_panelRight.setBackground(Color.RED);
        
        PatientView_panelTable=new iPanel(0, 0, 100.0f, 10.0f, 0, 0, if_);
        PatientView_panelTable.setBackground(Color.CYAN);
        
        
        
        if_.AddObject(PatientView_panelLeft,if_.getWidth()/2,700,LEFT);
        if_.AddObject(PatientView_panelRight,if_.getWidth()/2,80,RIGHT);
        if_.newLine();
        if_.AddObject(PatientView_panelTable,400,400,BOTTOM);
        if_.newLine();
                
    }
    
    
    
    public void tblUpdater(List<String> info, String tbl_Name) {
        /*Arrays to Handle Data*/
        ArrayList<JLabel> dynamicLabels = new ArrayList();
        ArrayList<iTextField> dynamicTextFields = new ArrayList();

        System.out.println("Gathered Data " + info);

        ArrayList<String> cols = new ArrayList();
        ArrayList<String> rows = new ArrayList();
        info.forEach((jKeyPair) -> {
            cols.add(jKeyPair.split("-")[0]);
            rows.add(jKeyPair.split("-")[1]);
        });
        System.out.println("Cols " + cols);
        System.out.println("Rows " + rows);

        JLabel lbl_info3 = new JLabel();
        PatientView_panelLeft.AddObject(lbl_info3, 280, 30, iPanel.LEFT);
        PatientView_panelLeft.newLine();

        for (int i = 0; i < cols.size(); i++) {
            JLabel lbl_Columns = new JLabel(cols.get(i).toString());
            PatientView_panelLeft.AddObject(lbl_Columns, 100, 30 );
            dynamicLabels.add(lbl_Columns);
        }
       PatientView_panelLeft.newLine();
        PatientView_panelLeft.repaint();

        for (int i = 0; i < rows.size(); i++) {
            iTextField txt_Rows = new iTextField( rows.get(i).toString(), 2);
            PatientView_panelLeft.AddObject(txt_Rows, 100, 30);
            dynamicTextFields.add(txt_Rows);
        }
        PatientView_panelLeft.newLine();
        PatientView_panelLeft.repaint();

        JLabel lbl_ident = new JLabel();
        PatientView_panelLeft.AddObject(lbl_ident, 530, 10);
        PatientView_panelLeft.newLine();

        /*Adding Final Control Button*/
        JLabel lbl_ident2 = new JLabel();
        iButton btn_Done = new iButton("", 3, Color.BLACK, Color.WHITE);
       PatientView_panelLeft.AddObject(lbl_ident2, 380, 30);
       PatientView_panelLeft.AddObject(btn_Done, 100, 30);
       PatientView_panelLeft.newLine();
      PatientView_panelLeft.repaint();

        btn_Done.addActionListener((al) -> {
            String sqlCommand = "";
            ArrayList<Object> objs = new ArrayList<>();
            for (int j = 0; j < cols.size(); j++) {
                if (j != cols.size() - 1) {
                    sqlCommand += " `" + dynamicLabels.get(j).getText() + "` =?, ";
                    objs.add(dynamicTextFields.get(j).getText());
                } else {
                    sqlCommand += " `" + dynamicLabels.get(j).getText() + "` =? ";
                    objs.add(dynamicTextFields.get(j).getText());
                }
            }
            System.out.println("Checking Query" + sqlCommand);
            /*Including*/
            
            
            
            System.out.println("Final Query: " + "UPDATE TABLE `" + tbl_Name + "` SET " + sqlCommand + " WHERE " + dynamicLabels.get(0).getText() + " = " + dynamicTextFields.get(0).getText());
            
            System.out.println("Objs: " + objs.toString());
            Boolean exq = sql.exec("UPDATE `" + tbl_Name + "` SET " + sqlCommand + " WHERE `" + dynamicLabels.get(0).getText() + "` = " + rows.get(0),objs);//                                    
            if (exq) {
                JOptionPane.showMessageDialog(null, "Table Updated", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error ocurred while attempting to update the table", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
    
    

    
}
