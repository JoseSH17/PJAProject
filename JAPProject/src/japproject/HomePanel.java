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
import static japproject.EditPatient.EditPatient_Panel;
import javax.swing.plaf.ColorUIResource;

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
    Maintenance MT; //Panel to Add or Edit Helper Tables
    Maintenance_Clasif_paciente MCP; //Panel to Add or Edit Helper Tables
    Maintenance_Horario MH; //Panel to Add or Edit Helper Tables
    Maintenance_tPaciente MTP; //Panel to Add or Edit Helper Tables
    Maintenance_Parentesco MP; //Panel to Add or Edit Helper Tables
    Appointments AP; //Panel to View and Edit appointments.
    AddAppointment AAP; //Panel to Add appointments
    BlackList BL; //Panel to View Patients in BlackList
    public static Color ColorPanels=new ColorUIResource(Color.WHITE);
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
        MenuHome.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.HOME, 20, Color.cyan.darker())); //Adding an icon that looks like a tiny home for the menu.

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
        menuAgregarPaciente.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON, 20, Color.cyan.darker()));

        menuAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                NP = new NewPatient(if_);   //Calls NewPatient class to show its Panel and contents             
            }
        });

        JMenu menuMantenimiento = new JMenu("Mantenimiento");
        menuMantenimiento.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUILD, 20, Color.cyan.darker()));

        JMenuItem Cursos = new JMenuItem("Cursos");
        Cursos.addActionListener((ei) -> {
            RemovePanels();
            MT = new Maintenance(if_);
        });
        JMenuItem Horarios = new JMenuItem("Horarios");
        Horarios.addActionListener((ei) -> {
            RemovePanels();
            MH = new Maintenance_Horario(if_);
        });
        JMenuItem TPaciente = new JMenuItem("Tipo Paciente");
        TPaciente.addActionListener((o) -> {
            RemovePanels();
            MTP = new Maintenance_tPaciente(if_);
        });
        JMenuItem CPaciente = new JMenuItem("Clasificación Paciente");
        CPaciente.addActionListener((ei) -> {
            RemovePanels();
            MCP = new Maintenance_Clasif_paciente(if_);
        });
        JMenuItem Parentesco = new JMenuItem("Parentesco");
        Parentesco.addActionListener((ei) -> {
            RemovePanels();
            MP = new Maintenance_Parentesco(if_);
        });

        menuMantenimiento.add(Cursos);
        menuMantenimiento.add(Horarios);
        menuMantenimiento.add(TPaciente);
        menuMantenimiento.add(CPaciente);
        menuMantenimiento.add(Parentesco);

        JMenu menuPersonalizacion = new JMenu("Personalizar");
        menuPersonalizacion.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BRUSH, 20, Color.cyan.darker()));
        JMenuItem ColorPallet = new JMenuItem("Colores de Interfaz");
        menuPersonalizacion.add(ColorPallet);

        JMenu menuCitas = new JMenu("Citas");       
        menuCitas.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ARCHIVE, 20, Color.cyan.darker()));
        JMenuItem viewAppointments = new JMenuItem("Ver Citas");
                viewAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                AP = new Appointments(if_); //Calls Appointments class to show its Panel and contents
            }
        });
        
        JMenuItem addAppointments = new JMenuItem("Agregar Cita");

        addAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                AAP = new AddAppointment(if_); //Calls Appointments class to show its Panel and contents
            }
        });
        menuCitas.add(viewAppointments);
        menuCitas.add(addAppointments);
                
        JButton menuBlackSheep = new JButton("Lista Negra");
        menuBlackSheep.setOpaque(false);
        menuBlackSheep.setContentAreaFilled(false);
        menuBlackSheep.setBorderPainted(false);
        menuBlackSheep.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BLOCK, 20, Color.cyan.darker()));
        
        menuBlackSheep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePanels();
                BL =  new BlackList(if_);
            }
        });
        
        menuBar.add(MenuHome);
        menuBar.add(menuAgregarPaciente);
        menuBar.add(menuMantenimiento);
        menuBar.add(menuPersonalizacion);
        menuBar.add(menuCitas);
        menuBar.add(menuBlackSheep);

        if_.AddObject(menuBar, if_.getWidth(), 40);
        if_.newLine();
    }

    /**
     * Gets the currentPanel being displayed and proceeds to dispose it and make
     * // * it invisible for the next panel called to be shown correctly.
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
                    EditPatient_Panel.dispose();
                    EditPatient_Panel.setVisible(false);
                break;
            case "Maintenance_Curso_Panel":    //If current panel is Maintenance_Curso_Panel then remove it.    
                MT.Maintenance_Curso_Panel.dispose();     //THIS SHOULD BE UPDATED FOR THE TABBED PANEL WHEN ITS IMPLEMENTATION ITS COMPLETE
                MT.Maintenance_Curso_Panel.setVisible(false);
                break;
            case "Maintenance_Clasif_Paciente_Panel": //If current panel is Maintenance_clasif_Paciente then remove it.
                MCP.Maintenance_Clasif_Paciente_Panel.dispose();
                MCP.Maintenance_Clasif_Paciente_Panel.setVisible(false);
                break;
            case "Maintenance_Horario_Panel": //If current panel is Maintenance_Horario_Panel then remove it.
                MH.Maintenance_Horario_Panel.dispose();
                MH.Maintenance_Horario_Panel.setVisible(false);
                break;
            case "Maintenance_TipPaciente_Panel":  //If current panel is Maintenance_TipPaciente_Panel then remove it.
                MTP.Maintenance_TipPaciente_Panel.dispose();
                MTP.Maintenance_TipPaciente_Panel.setVisible(false);
                break;
            case "Maintenance_Parentesco_Panel":  //If current panel is Maintenance_Parentesco_Panel then remove it.
                MP.Maintenance_Parentesco_Panel.dispose();
                MP.Maintenance_Parentesco_Panel.setVisible(false);
                break;
            case "Appointments_Panel":  //If current panel is Appointments_Panel then remove it.
                AP.Appointments_Panel.dispose(); //If current panel is Appointments_Panel then remove it. 
                AP.Appointments_Panel.setVisible(false);
                break;
            case "AddAppointments_Panel":  //If current panel is AddAppointments_Panel then remove it.
                AAP.AddAppointments_Panel.dispose(); //If current panel is AddAppointments_Panel then remove it. 
                AAP.AddAppointments_Panel.setVisible(false);
                break; 
            case "BlackList_Panel": //If current panel is BlackList_Panel then remove it.
                BL.BlackList_Panel.dispose(); //If current panel is BlackList_Panel then remove it.
                BL.BlackList_Panel.setVisible(false);
                break;
            default:
                System.out.println("Panel not handled");
                break;
        }
    }

    /**
     *
     * @param NestedPanel
     */
    public void InvokeNestedPanels(String NestedPanel) {
        switch (NestedPanel) {            
            case "EditPatient_Panel":
              
                break;
            default:
                break;
        }
    }
}
