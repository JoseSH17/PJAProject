/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iSQL;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jose
 */
public final class JAPProject {
    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    

    public JAPProject() {
        initComponents();
    }

    public void initComponents() {
            System.out.println("Iniciando Programa...");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(()
                -> { 
           // Thread t = new Thread(new Threads("Loading"));
           // t.start();
            HomePanel mp = new HomePanel();
        });
    }

}
