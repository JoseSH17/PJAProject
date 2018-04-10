/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.JProgressBar;

/**
 *
 * @author Jose
 */
public class LoadingProgressBars {
    public static  iFrame LoadingFrame = new iFrame(410, 110, 4, 20, "Loading...", EXIT_ON_CLOSE);
    public iLabel lblIndicador;
    public static JProgressBar pbSQL = new JProgressBar(0,100);
    
     
    //Method in charge of the ProgressBar
    public void ProgressSQL() {        
        lblIndicador = new iLabel("Accesando a base de datos local, un momento por favor...");
        lblIndicador.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATA_USAGE, 20, Color.BLACK));        
        LoadingFrame.AddSingleObject(lblIndicador, 390, 30, EXIT_ON_CLOSE);
        LoadingFrame.AddSingleObject(pbSQL, 390, 30, EXIT_ON_CLOSE); 
        LoadingFrame.setIconImage(new ImageIcon(getClass().getResource("/content/iconoUlatina.PNG")).getImage());
        LoadingFrame.finalice();       
    }
    
}
