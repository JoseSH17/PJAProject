/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import iComponents.iPanel;
import iComponents.iTable;
import javax.swing.JFrame;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.ColorNonEditElementsFonts;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Jose
 */
public class Telefonos {

    public static JFrame frameTelefonos; //This is the Phones iFrame container, everything related to phones will be shown in here.     
    private JTabbedPane paneTelefonos;
    private iPanel SolicitantesTelefonosPanel;
    private iPanel PacientesTelefonosPanel;
    private iTable TelSoli;
    private iTable TelPaci;
    private JScrollPane scrollSolicitante;
    private JScrollPane scrollPaciente;

    public Telefonos(int IdSolicitante, int IdPaciente) {
        frameTelefonos = new JFrame("Telefonos");
        frameTelefonos.setResizable(false);
        frameTelefonos.setIconImage(new ImageIcon(getClass().getResource("/content/iconoUlatina.PNG")).getImage());

        paneTelefonos = new JTabbedPane();
        SolicitantesTelefonosPanel = new iPanel(frameTelefonos.getHeight(), frameTelefonos.getWidth(), 0, 0, 0);
        PacientesTelefonosPanel = new iPanel(frameTelefonos.getHeight(), frameTelefonos.getWidth(), 0, 0, 0);

        //Tabla de solicitante
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("Num. Telefono", "Tipo"));
        
        TelSoli = new iTable(cols);
//
        ArrayList<Object> obj1 = new ArrayList();//array para guardar data de id de Solicitante
        if (IdSolicitante == -1) {
            System.out.println("no se indicaron ids para el solicitante");
        } else {
            obj1.addAll(Arrays.asList(IdSolicitante));
            ResultSet rsS = sql.SELECT("Select * from JAW_TelefonosSolicitantes WHERE IdRelacion = ?", obj1);
            if (sql.Exists(rsS)) {//verifica que el query sea valido
                try {

                    while (rsS.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rsS.getMetaData().getColumnCount() + 1];
                        for (int i = 1; i <= rsS.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rsS.getObject(i);
                        }         
                        TelSoli.addrow(row);
                    }

                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
        }
        
         TelPaci = new iTable(cols);
        
        ArrayList<Object> obj2 = new ArrayList();//array para guardar data de id de Paciente
        if (IdPaciente == -1) {
            System.out.println("no se indicaron ids para el paciente");
        } else {
            obj2.addAll(Arrays.asList(IdPaciente));
            ResultSet rsP = sql.SELECT("Select * from JAW_TelefonosPacientes WHERE IdRelacion = ?", obj2);
            if (sql.Exists(rsP)) {//verifica que el query sea valido
                try {

                    while (rsP.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rsP.getMetaData().getColumnCount() + 1];
                        for (int i = 1; i <= rsP.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rsP.getObject(i);
                        }         
                        TelPaci.addrow(row);
                    }

                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
        }

        scrollSolicitante = new JScrollPane(TelSoli);
        scrollPaciente = new JScrollPane(TelPaci);
        
        SolicitantesTelefonosPanel.AddSingleObject(scrollSolicitante,100.0f, 83.5f, CENTER);
        PacientesTelefonosPanel.AddSingleObject(scrollPaciente,frameTelefonos.getWidth() , frameTelefonos.getHeight(), 0);
        paneTelefonos.addTab("Solicitante", SolicitantesTelefonosPanel);
        paneTelefonos.addTab("Paciente", PacientesTelefonosPanel);

        frameTelefonos.add(paneTelefonos);
        frameTelefonos.setSize(400, 350);
        frameTelefonos.setPreferredSize(new Dimension(600, 500));
        frameTelefonos.pack();
        frameTelefonos.setVisible(true);
    }

}
