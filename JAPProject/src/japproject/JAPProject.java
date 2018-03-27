/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iSQL;
import java.awt.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Jose
 */
public class JAPProject {

    public static JProgressBar pbSQL = new JProgressBar();
    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    iFrame LoadingFrame = new iFrame(310, 110, 4, 20, "Loading Bar", EXIT_ON_CLOSE);

    public JAPProject() {
        initComponents();
    }

    public void initComponents() {
        iLabel lblIndicador = new iLabel("Accesando a base de datos local...");
        lblIndicador.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATA_USAGE, 20, Color.BLACK));
        LoadingFrame.AddSingleObject(lblIndicador, 140, 30, EXIT_ON_CLOSE);
        LoadingFrame.AddSingleObject(pbSQL, 240, 30, EXIT_ON_CLOSE);
        LoadingFrame.finalice();
        
         Thread t = new Thread((Runnable) new Threads("Loading"));
         t.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(()
                -> {
            HomePanel mp = new HomePanel();
        });
    }

}
