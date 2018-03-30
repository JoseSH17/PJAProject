/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iSQL;
import static japproject.LoadingProgressBars.LoadingFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jose
 */
public final class JAPProject {

    public static LoadingProgressBars lpb = new LoadingProgressBars();
    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");

    public JAPProject() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> { 
        System.out.println("Iniciando Programa...");
        lpb.ProgressSQL(); //Calling Progress Bar
        Thread t = new Thread(new Threads("Loading"));  //Defining Thread which is going to call HomePanel once the SQL connection is established.
        t.start();       //Initiating Thread.
        });
    }
}
