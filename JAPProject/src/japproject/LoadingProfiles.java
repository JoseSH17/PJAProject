/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import com.sun.glass.ui.InvokeLaterDispatcher;
import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import static japproject.HomePanel.ColorElementsFonts;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.JAPProject.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author anfer
 */
public class LoadingProfiles {
    //Componentes

    //
    public LoadingProgressBars lpb;  //Calls methods from Loading Progress Bars class

    iFrame LoadingProfile_frame;//Frame
    iPanel LoadingProfile_panel;
    JComboBox cbo_Perfiles;

    public LoadingProfiles() {
        lpb = new LoadingProgressBars();
        LoadingProfile_frame = new iFrame(700, 500, 0, 30, "Inicio", EXIT_ON_CLOSE);
        LoadingProfile_frame.setBackground(ColorPanels);
        LoadingProfile_frame.setIconImage(new ImageIcon(getClass().getResource("/content/iconoUlatina.PNG")).getImage());
        initComponents(); //Do not move InitComponents from this place.          
        LoadingProfile_frame.finalice();
    }

    private void initComponents() {
        LoadingProfile_panel = new iPanel(0, 0, 700, 500, 50, LoadingProfile_frame);
        LoadingProfile_panel.setBackground(ColorPanels);

        //Logos
        iLabel lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        iLabel lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        //
        // Label y textfield
        iLabel NombrePerfilLabel = new iLabel("Nombre del perfil");
        NombrePerfilLabel.setForeground(ColorFonts);
        cbo_Perfiles = new JComboBox();
        cbo_CargarCurso();

        //Btn
        iButton Cargar_btn = new iButton("Cargar", 15, ColorPanels.brighter(), ColorFonts);
        Cargar_btn.addActionListener((ae) -> {

            LoadingProfile_frame.setVisible(false);
            HomePanel HP = new HomePanel();
            
        });

        //AddComponents
        LoadingProfile_panel.AddObject(lbl_LogoULatina, 618, 120, 90);
        LoadingProfile_panel.newLine();

        LoadingProfile_panel.AddObject(NombrePerfilLabel, 100, 50, 280);
        LoadingProfile_panel.newLine();
        LoadingProfile_panel.addSpace(-100);
        LoadingProfile_panel.AddObject(cbo_Perfiles, 150, 30, 280);
        LoadingProfile_panel.newLine();

        LoadingProfile_panel.AddObject(Cargar_btn, 100, 50, 300);
        LoadingProfile_panel.newLine();

        LoadingProfile_panel.finalice();
    }

    private JComboBox cbo_CargarCurso() {

        ResultSet rs = sql.SELECT("SELECT `Nombre_Perfil` FROM JAW_Perfiles");

        try {
            while (rs.next()) {
                cbo_Perfiles.addItem(rs.getObject("Nombre_Perfil"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_Perfiles;
    }

}
