/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iLabel;
import java.awt.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.JProgressBar;

/**
 *
 * @author Jose
 */
public class LoadingProgressBars {
    public iFrame LoadingFrame;
    public iLabel lblIndicador;
    public  JProgressBar pbSQL = new JProgressBar();
    
     
    //Method in charge of the ProgressBar
    public void ProgressSQL() {
        LoadingFrame = new iFrame(410, 110, 4, 20, "Loading...", EXIT_ON_CLOSE);
        lblIndicador = new iLabel("Accesando a base de datos local, un momento por favor...");
        lblIndicador.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATA_USAGE, 20, Color.BLACK));        
        LoadingFrame.AddSingleObject(lblIndicador, 390, 30, EXIT_ON_CLOSE);
        LoadingFrame.AddSingleObject(pbSQL, 390, 30, EXIT_ON_CLOSE);       
        LoadingFrame.finalice();                      
    }
    
}
