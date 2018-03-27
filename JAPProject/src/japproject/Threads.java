/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static japproject.JAPProject.pbSQL;
import static japproject.JAPProject.sql;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class Threads extends Thread {

    String nombre; //Helps to identify the thread to execute


    int ProgressValue = 0; //Manages Progress bars as they increase size during execution of tasks.

    public Threads(String nom) {
        this.nombre = nom;
    }

    public int ProgressBarCalc() {
        return ProgressValue = ProgressValue + 20;
    }

    @Override
    public void run() {
        boolean infinite = true;
        try {
            while (infinite) {
                if (this.nombre == "Loading") {
                    //Code
                    pbSQL.setValue(0);
                    pbSQL.setStringPainted(true);         
                    if (sql.connect() == null) {
                        ProgressBarCalc();
                        pbSQL.setValue(ProgressBarCalc());                        
                    } else {
                        JOptionPane.showMessageDialog(null, "DB is up and running", "SQL Progress Bar", JOptionPane.INFORMATION_MESSAGE);
                        infinite = false;
                    }

                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.exit(0);
    }

}
