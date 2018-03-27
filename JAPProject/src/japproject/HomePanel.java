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
import java.awt.Point;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jose
 */
public class HomePanel {

    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");

    public Point initialClick;
    
    public static  iFrame if_; //This is the main iFrame container, everything will be shown in here.
     
    public static  iPanel HomePanel; //This is the Home Panel, it contains the main table which shows the Patients information.
        
    private final int projectWidth = 1200;
    private final int projectHeight = 900;
    
    
    public HomePanel() {
       if_ = new iFrame(projectWidth, projectHeight, 0, 0, "", EXIT_ON_CLOSE);
       HomePanel = new iPanel(0, 0, 100.0f, 100.0f, 0, 0, if_);       
       initComponents(); //Do not move InitComponents from this place. 
       if_.add(HomePanel);       
       if_.finalice();
    }
    
    private void initComponents() {
        HeaderMenu();
    }
        
    public void HeaderMenu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu MenuHome = new JMenu("HOME");//Creo los Submenus

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
        JMenuItem Pers1 = new JMenuItem("Color");
        JMenuItem Pers2 = new JMenuItem("Color2");
        menuPers.add(Pers1);
        menuPers.add(Pers2);

        menuBar.add(MenuHome);
        menuBar.add(menuArchivo);
        menuBar.add(menuPers);
        menuBar.setBackground(Color.yellow);

        HomePanel.AddObject(menuBar, projectWidth, 30);
        HomePanel.newLine();        
    }   
}
