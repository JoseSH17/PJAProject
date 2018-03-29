/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import java.awt.Color;
import java.awt.Point;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Jose
 */
public class HomePanel {

    public static String currentPanel; //Holds current panel running

    public Point initialClick;

    public static iFrame if_; //This is the main iFrame container, everything will be shown in here. 

    public LoadingProgressBars lpb;  //Calls methods from Loading Progress Bars class

    PatientView PV; //Panel to show Patient Data.
    NewPatient NP; //Panel to add a new Patient.
    EditPatient EP; //Panel to Edit patients
    Maintenance MT; //Panel to Add or Edit Helper Tables

    public HomePanel() {
        lpb = new LoadingProgressBars();
        if_ = new iFrame(1200, 900, 0, 30, "", EXIT_ON_CLOSE);
        initComponents(); //Do not move InitComponents from this place.          
        if_.finalice();
    }

    private void initComponents() {
        // lpb.ProgressSQL();
        HeaderMenu();
        PV = new PatientView(if_); //PatientView is the first point that shows when program is initiated.
    }

    public void HeaderMenu() {

        JMenuBar menuBar = new JMenuBar(); //Adding Menu Bar, this holds all main menus and submenus             
        JButton MenuHome = new JButton("Inicio"); //This is the Home Menu, redirects the user to the main screen where the patients list is shown        
        MenuHome.setOpaque(false);
        MenuHome.setContentAreaFilled(false);
        MenuHome.setBorderPainted(false);
        MenuHome.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.HOME, 20, Color.BLACK)); //Adding an icon that looks like a tiny home for the menu.

        MenuHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                PV = new PatientView(if_); //Calls PatientView class to show its Panel and contents
            }
        });

        JButton menuAgregarPaciente = new JButton("Nuevo Paciente");
        menuAgregarPaciente.setOpaque(false);
        menuAgregarPaciente.setContentAreaFilled(false);
        menuAgregarPaciente.setBorderPainted(false);
        menuAgregarPaciente.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON, 20, Color.BLACK));

        menuAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                NP = new NewPatient(if_);   //Calls NewPatient class to show its Panel and contents             
            }
        });

        JMenu menuMantenimiento = new JMenu("Mantenimiento");
        menuMantenimiento.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADJUST, 20, Color.BLACK));

        JMenuItem Cursos = new JMenuItem("Cursos");
        Cursos.addActionListener((ei) -> {
            RemovePanels();
            MT = new Maintenance(if_);

        });
        JMenuItem Horarios = new JMenuItem("Horarios");
        Horarios.addActionListener((ei) -> {
        RemovePanels();
        EP=new EditPatient(if_);
        });
        JMenuItem TPaciente = new JMenuItem("Tipo Paciente");
        TPaciente.addActionListener((o) -> {

        });
        JMenuItem CPaciente = new JMenuItem("Clasificación Paciente");
        CPaciente.addActionListener((ei) -> {

        });
        JMenuItem Parentesco = new JMenuItem("Parentesco");
        Parentesco.addActionListener((ei) -> {

        });

        menuMantenimiento.add(Cursos);
        menuMantenimiento.add(Horarios);
        menuMantenimiento.add(TPaciente);
        menuMantenimiento.add(CPaciente);
        menuMantenimiento.add(Parentesco);

        JMenu menuPersonalizacion = new JMenu("Personalizar");
        JMenuItem ColorPallet = new JMenuItem("Colores de Interfaz");
        menuPersonalizacion.add(ColorPallet);

        menuBar.add(MenuHome);
        menuBar.add(menuAgregarPaciente);
        menuBar.add(menuMantenimiento);
        menuBar.add(menuPersonalizacion);

        if_.AddObject(menuBar, if_.getWidth(), 40);
        if_.newLine();
    }

    /**
     * Gets the currentPanel being displayed and proceeds to dispose it and make
     * it invisible for the next panel called to be shown correctly.
     *
     */
    public void RemovePanels() {
        System.out.println("Panel actual : " + currentPanel);

        switch (currentPanel) {
            case "NewPatient_Panel":              //If current panel is NewPatient then remove it.                                     
                NP.NewPatient_Panel.dispose();
                NP.NewPatient_Panel.setVisible(false);
                break;
            case "PatientView_panel":      //If current panel is PatientView then remove it.           
                PV.PatientView_panel.dispose();
                PV.PatientView_panel.setVisible(false);
                break;
            case "EditPatient_Panel":  //If current panel is EditPatient_Panel then remove it.     
                EP.EditPatient_Panel.dispose();
                EP.EditPatient_Panel.setVisible(false);
                break;

            case "Maintenance_Curso_Panel":    //If current panel is Maintenance_Curso_Panel then remove it.    
                MT.Maintenance_Curso_Panel.dispose();     //THIS SHOULD BE UPDATED FOR THE TABBED PANEL WHEN ITS IMPLEMENTATION ITS COMPLETE
                MT.Maintenance_Curso_Panel.setVisible(false);
                break;
            default:
                System.out.println("Panel not handled");
                break;
        }

    }
}
