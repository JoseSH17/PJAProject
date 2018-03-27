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

    public Point initialClick;
    
    public static  iFrame if_; //This is the main iFrame container, everything will be shown in here.      
        
    private final int projectWidth = 1200;
    private final int projectHeight = 900;
    private final PatientView pv;
    
    
    
    public HomePanel() {
       if_ = new iFrame(projectWidth, projectHeight, 0, 30, "", EXIT_ON_CLOSE);  
       this.pv = new PatientView();
       initComponents(); //Do not move InitComponents from this place.    
       if_.finalice();
    }
    
    private void initComponents() {
        HeaderMenu();
    }
        
    public void HeaderMenu() {

        JMenuBar menuBar = new JMenuBar(); //Adding Menu Bar, this holds all main menus and submenus             
        JMenu MenuHome = new JMenu(); //This is the Home Menu, redirects the user to the main screen where the patients list is shown
        MenuHome.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.HOME, 16, new Color(240, 240, 240))); //Adding an icon that looks like a tiny home for the menu.
                
        MenuHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {            
            }
        });

        JMenu menuArchivo = new JMenu("ARCHIVO");
        JMenuItem Edit = new JMenuItem("Editar");
        Edit.addActionListener((ei) -> {
          
        });
        JMenuItem Print = new JMenuItem("Imprimir");
        JMenuItem Exit = new JMenuItem("Salir");
        Exit.addActionListener((o) -> {

        });
        menuArchivo.add(Edit);
        menuArchivo.add(Print);
        menuArchivo.add(Exit);

        JMenu menuPers = new JMenu("PERSONALIZAR");
        JMenuItem ColorPallet = new JMenuItem("Color");        
        menuPers.add(ColorPallet);        

        menuBar.add(MenuHome);
        menuBar.add(menuArchivo);
        menuBar.add(menuPers);        

        if_.AddObject(menuBar, if_.getWidth(), 40);
        if_.newLine();        
    }   
}
