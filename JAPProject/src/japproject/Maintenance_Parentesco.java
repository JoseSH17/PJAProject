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
import iComponents.iTable;

import iComponents.iTextField;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Maintenance_Parentesco {
  
    public iPanel Maintenance_Parentesco_Panel;//creo el iPanel
    
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnAñadir;//Boton para añadir
    private iButton btnModificar;//Boton para modificar
    private iButton btnEliminar;//Boton para eliminar
    
    private iLabel lbl_Titulo_Mantenimiento;//Lbl para el Titulo de la pagina de mantenimiento en la que se encuentra
    private iLabel lbl_idParentesco;//Lbl para el id curso
    private iTextField txt_idParentesco;//TextField para el id curso
    private iLabel lbl_Descripción;//Lbl para el nombre del curso
    private iTextField txt_Descripcion;//TextField para el nombre del curso
    
    public Maintenance_Parentesco(iFrame if_) {
        currentPanel = "Maintenance_Parentesco_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_Parentesco_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_Parentesco_Panel.setBackground(Color.decode("#006738"));//le doy color al panel
        Mantenimiento_Parentesco(if_);
    }
    
    private void Componentes_Parentesco(){
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
        
        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO PARENTESCO");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());
        
        lbl_idParentesco = new iLabel("ID Parentesco".toUpperCase());
        lbl_idParentesco.setForeground(Color.GRAY.brighter());
        txt_idParentesco = new iTextField("", 15);
        
        lbl_Descripción = new iLabel("Descripción".toUpperCase());
        lbl_Descripción.setForeground(Color.GRAY.brighter());
        txt_Descripcion = new iTextField("", 15);
        
        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para añadir curso
        btnModificar = new iButton("Modificar", 2, Color.GRAY, Color.BLACK);//boton para editar curso
        btnEliminar = new iButton("Eliminar", 2, Color.GRAY, Color.BLACK);//boton para eliminar curso
    }
    private void Mantenimiento_Parentesco(iFrame if_) {
        Componentes_Parentesco();
        
        Maintenance_Parentesco_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
        Maintenance_Parentesco_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_Parentesco_Panel.newLine(); 
        Maintenance_Parentesco_Panel.addSpace(5);
 
        Maintenance_Parentesco_Panel.AddObject(lbl_Titulo_Mantenimiento, 415, 30, 5);//agrego el titulo
        Maintenance_Parentesco_Panel.newLine();
        Maintenance_Parentesco_Panel.addSpace(5);
        
        Maintenance_Parentesco_Panel.AddObject(lbl_idParentesco, 415, 30, 30);
        Maintenance_Parentesco_Panel.AddObject(txt_idParentesco, 415, 30, 200);//agrega el label y el textfield del id curso
        Maintenance_Parentesco_Panel.newLine();
        Maintenance_Parentesco_Panel.addSpace(5);
        
        Maintenance_Parentesco_Panel.AddObject(lbl_Descripción, 415, 30, 30);
        Maintenance_Parentesco_Panel.AddObject(txt_Descripcion, 415, 30, 200);//agrega el label y el textfield del nombre del curso
        Maintenance_Parentesco_Panel.newLine();
        Maintenance_Parentesco_Panel.addSpace(5);
        
        Maintenance_Parentesco_Panel.AddObject(btnAñadir, 130, 30, 200);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });
        
        Maintenance_Parentesco_Panel.AddObject(btnModificar, 130, 30, 345);
        btnModificar.addActionListener((a) -> {
            btnModificar_MouseClicked();
        });
        
        Maintenance_Parentesco_Panel.AddObject(btnEliminar, 130, 30, 486);
        btnEliminar.addActionListener((a) -> {
            btnEliminar_MouseClicked(); 
        });
        Maintenance_Parentesco_Panel.newLine();
        
        Tabla();
        
        if_.add(Maintenance_Parentesco_Panel);
    } 
    
    public void Tabla(){
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("ID Parentesco", "Descripción"));
        iPanel ip = new iPanel(215, 90, 500, 400, 4);
        //iPanel ip = new iPanel(0, 70, 50.0f, 50.0f, 0, 0, if_);
        ip.setLocation(115, 300);
        iTable table = new iTable(cols);
        ip.setBackground(Color.black);

        ResultSet rs = sql.SELECT(""
                + "SELECT `IdParentesco`, `DescripcionParentesco` "
                + "FROM `JAW_Parentesco`");
        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("IdParentesco"),
                        rs.getObject("DescripcionParentesco")
                    };
                    table.addrow(result);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 500, 400);

        //ip.add(btn_filter);
        ip.add(scrollPane);
        ip.finalice();
        Maintenance_Parentesco_Panel.add(ip);
    }
    
    public void btnAñadir_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Parentesco(DescripcionParentesco) VALUES (?)");
        
        pps.setString(1 , txt_Descripcion.getText());
        
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
        PreparedStatement pps = con.prepareStatement("UPDATE JAW_Parentesco SET DescripcionParentesco=? WHERE IdParentesco=?");
        
        pps.setString(1 , txt_Descripcion.getText());
        pps.setString(2 , txt_idParentesco.getText());
        
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
        PreparedStatement pps = con.prepareStatement("DELETE FROM `JAW_Parentesco` where `IdParentesco` = ?");
        
        pps.setString(1 , txt_idParentesco.getText());
        
        pps.executeUpdate();
        Tabla();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
