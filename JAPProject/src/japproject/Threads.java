/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static japproject.JAPProject.sql;
import static japproject.LoadingProgressBars.LoadingFrame;
import static japproject.LoadingProgressBars.pbSQL;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        return ProgressValue = ProgressValue + 12; //Fills the Progress Bar by 10% each iteration.
    }

    @Override
    public void run() {
        boolean infinite = true;          
        while (infinite) {
            if (this.nombre.equals("Loading")) {                              
                pbSQL.setValue(0);
                pbSQL.setStringPainted(true);
                if (sql.connect() != null) {
                    infinite = false;                 
                    pbSQL.setValue(100); //Completes the Progress Bar and finishes the loading process.                                       
                } else {
                    pbSQL.setValue(ProgressBarCalc()); //Fills the Progress Bar by 10% each iteration.
                }                
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(pbSQL.getValue() == 100)
                {
                LoadingFrame.dispose();
                LoadingFrame.setVisible(false);
                infinite = false;
                Thread.currentThread().interrupt();
                HomePanel HP = new HomePanel();
                }
            }
        }
    }

}
