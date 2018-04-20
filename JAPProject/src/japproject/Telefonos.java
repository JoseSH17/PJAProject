/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iPanel;
import javax.swing.JFrame;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.ColorNonEditElementsFonts;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Jose
 */
public class Telefonos {

    public static JFrame frameTelefonos; //This is the Phones iFrame container, everything related to phones will be shown in here.     
    private JTabbedPane paneTelefonos;
    private iPanel SolicitantesTelefonosPanel;
    private iPanel PacientesTelefonosPanel;

    public Telefonos(int IdSolicitante, int IdPaciente) {
        frameTelefonos =  new JFrame("Telefonos");        
        frameTelefonos.setResizable(false);        
        frameTelefonos.setIconImage(new ImageIcon(getClass().getResource("/content/iconoUlatina.PNG")).getImage());
        
        
        paneTelefonos = new JTabbedPane();
        SolicitantesTelefonosPanel =  new iPanel(frameTelefonos.getHeight(), frameTelefonos.getWidth(),0,0,0);
        PacientesTelefonosPanel =  new iPanel(frameTelefonos.getHeight(), frameTelefonos.getWidth(),0,0,0);
        
        ArrayList<Object> obj1 = new ArrayList();//array para guardar data de id de Solicitante
        if (IdSolicitante == -1) {
            System.out.println("no se indicaron ids para el solicitante");
        } else {
            obj1.addAll(Arrays.asList(IdSolicitante));
            ResultSet rsS = sql.SELECT("Select * from JAW_Telefonos WHERE Propietario = 'Solicitante' AND IdRelacion = ?", obj1);
        }
        
        
        ArrayList<Object> obj2 = new ArrayList();//array para guardar data de id de Paciente
        if (IdPaciente == -1) {
            System.out.println("no se indicaron ids para el paciente");
        } else {
            obj2.addAll(Arrays.asList(IdPaciente));
            ResultSet rsP = sql.SELECT("Select * from JAW_Telefonos WHERE Propietario = 'Paciente' AND IdRelacion = ?", obj2);
        }
               
        paneTelefonos.addTab("Solicitante", SolicitantesTelefonosPanel);
        paneTelefonos.addTab("Paciente", PacientesTelefonosPanel);                                
                
        frameTelefonos.add(paneTelefonos);      
        frameTelefonos.setSize(400, 350);
        frameTelefonos.setPreferredSize(new Dimension(400,350));
        frameTelefonos.pack();
        frameTelefonos.setVisible(true);
    }

}
