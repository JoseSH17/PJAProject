/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iSQL;
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

    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");

    public static String currentPanel; //Holds current panel running

    public Point initialClick;

    public static iFrame if_; //This is the main iFrame container, everything will be shown in here. 

    PatientView pv; //Panel to show Patient Data.
    NewPatient NP; //Panel to add a new Patient.
    EditPatient pe;

    public HomePanel() {
        if_ = new iFrame(1200, 900, 0, 30, "", EXIT_ON_CLOSE);
        initComponents(); //Do not move InitComponents from this place.    
        if_.finalice();
    }

    private void initComponents() {
        HeaderMenu();
        pv = new PatientView(if_); //PatientView is the first point that shows when program is initiated.
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
//                pv = new PatientView(if_); //Calls PatientView class to show its Panel and contents
                pe=new EditPatient(if_);
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

        });
        JMenuItem Horarios = new JMenuItem("Horarios");
        Horarios.addActionListener((ei) -> {

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

    public void RemovePanels() {
        switch (currentPanel) {
            case "RePanel":
                System.out.println("Estoy tratando de entrar a patient view");
                NP.RePanel.dispose();
                NP.RePanel.setVisible(false);
                //pv.PatientView_panel.dispose();
                //pv.PatientView_panel.setVisible(false);
                break;
            case "PatientView_panel":
                System.out.println("Estoy tratando de entrar a New Patient");
                pv.PatientView_panel.dispose();
                pv.PatientView_panel.setVisible(false);
               // NP.RePanel.dispose();
               // NP.RePanel.setVisible(false);
                break;
            default:
                System.out.println("Panel not handled");
                break;
        }

    }
}
