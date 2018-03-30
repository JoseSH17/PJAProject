/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

/**
 *
 * @author Pablo
 */
import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;

import iComponents.iTextField;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.if_;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Maintenance_Horario {
  
    public iPanel Maintenance_Horario_Panel;//creo el iPanel
    
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnAñadir;//Boton para añadir
    private iButton btnModificar;//Boton para modificar
    private iButton btnEliminar;//Boton para eliminar
    
    private iLabel lbl_Titulo_Mantenimiento;//Lbl para el Titulo de la pagina de mantenimiento en la que se encuentra
    private iLabel lbl_idHorario;//Lbl para el id curso
    private iTextField txt_idHorario;//TextField para el id curso
    private iLabel lbl_NombreHorario;//Lbl para el nombre del curso
    private iTextField txt_NombreHorario;//TextField para el nombre del curso
    
    public Maintenance_Horario(iFrame if_) {
        currentPanel = "Maintenance_Horario_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_Horario_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_Horario_Panel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel
        Mantenimiento_horario(if_);
    }
    
    private void Componentes_Curso(){
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
         
        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO HORARIOS");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());
        
        lbl_idHorario = new iLabel("ID Horario".toUpperCase());
        lbl_idHorario.setForeground(Color.GRAY.brighter());
        txt_idHorario = new iTextField("", 3);
        
        lbl_NombreHorario = new iLabel("Nombre Horario".toUpperCase());
        lbl_NombreHorario.setForeground(Color.GRAY.brighter());
        txt_NombreHorario = new iTextField("", 3);
        
        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para añadir horario
        btnModificar = new iButton("Modificar", 2, Color.GRAY, Color.BLACK);//boton para editar horario
        btnEliminar = new iButton("Eliminar", 2, Color.GRAY, Color.BLACK);//boton para eliminar horario
    }
    private void Mantenimiento_horario(iFrame if_) {
        Componentes_Curso();
        
        Maintenance_Horario_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
        Maintenance_Horario_Panel.AddObject(lbl_LogoPsicologia, 415, 120, 440);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_Horario_Panel.newLine(); 
 
        Maintenance_Horario_Panel.AddObject(lbl_Titulo_Mantenimiento, 415, 30, 5);//agrego el titulo
        Maintenance_Horario_Panel.newLine();
        
        Maintenance_Horario_Panel.AddObject(lbl_idHorario, 415, 30, 4);
        Maintenance_Horario_Panel.AddObject(txt_idHorario, 415, 30, 200);//agrega el label y el textfield del id horario
        Maintenance_Horario_Panel.newLine();
        
        Maintenance_Horario_Panel.AddObject(lbl_NombreHorario, 415, 30, 4);
        Maintenance_Horario_Panel.AddObject(txt_NombreHorario, 415, 30, 200);//agrega el label y el textfield del nombre del horario
        Maintenance_Horario_Panel.newLine();
        
        Maintenance_Horario_Panel.AddObject(btnAñadir, 130, 30, 200);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });
        
        Maintenance_Horario_Panel.AddObject(btnModificar, 130, 30, 340);
        btnModificar.addActionListener((a) -> {
            btnModificar_MouseClicked();
        });
        
        Maintenance_Horario_Panel.AddObject(btnEliminar, 130, 30, 480);
        btnEliminar.addActionListener((a) -> {
            btnEliminar_MouseClicked(); 
        });
        Maintenance_Horario_Panel.newLine();
        
        Tabla();
        
        if_.add(Maintenance_Horario_Panel);
    } 
    
    public void Tabla(){
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("ID Horario", "Nombre Horario"));
        iPanel ip = new iPanel(215, 90, 500, 400, 4);
        //iPanel ip = new iPanel(0, 70, 50.0f, 50.0f, 0, 0, if_);
        ip.setLocation(100, 300);
        iTable table = new iTable(cols);
        ip.setBackground(Color.black);

        ResultSet rs = sql.SELECT(""
                + "SELECT `IdHorario`, `NombreHorario` "
                + "FROM `JAW_Horario`");
        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("IdHorario"),
                        rs.getObject("NombreHorario")
                    };
                    table.addrow(result);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 500, 400);

        ip.add(scrollPane);
        ip.finalice(); 
        Maintenance_Horario_Panel.add(ip);
    }
    
    public void btnAñadir_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Horario(NombreHorario) VALUES (?)");
        
        pps.setString(1 , txt_NombreHorario.getText());
        
        pps.executeUpdate();
        Tabla();  //Esto se supone que debe de actualizar la pagina
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void btnModificar_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("UPDATE JAW_Horario SET NombreHorario=? WHERE IdHorario=?");
        
        pps.setString(1 , txt_NombreHorario.getText());
        pps.setString(2 , txt_idHorario.getText());
        
        pps.executeUpdate();
        Tabla();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void btnEliminar_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("DELETE FROM `JAW_Horario` where `IdHorario` = ?");
        
        pps.setString(1 , txt_idHorario.getText());
        
        pps.executeUpdate();
        Tabla();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}