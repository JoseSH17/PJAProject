/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verticalmenubar;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jose
 */

class VerticalMenuBar extends JMenuBar {
  private static final LayoutManager grid = new GridLayout(0,1);
  public VerticalMenuBar() {
    setLayout(grid);
  }




    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // TODO code application logic here
    JFrame frame = new JFrame("MenuSample Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar menuBar = new VerticalMenuBar();

    // File Menu, F - Mnemonic
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    
    JMenuItem test = new JMenuItem("New");
    fileMenu.add(test);
    menuBar.add(fileMenu);
    
    

    JMenu editMenu = new JMenu("Edit");
    menuBar.add(editMenu);
    
    frame.setJMenuBar(menuBar);
    frame.setSize(350, 250);
    frame.setVisible(true);   
    }
} 

