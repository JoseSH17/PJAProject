package japproject;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iTextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static japproject.HomePanel.currentPanel;

public class Maintenance {
    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");//hago la conexion a BD
    public iPanel Maintenance_Curso;//creo el iPanel
    
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnAñadir;//Boton para añadir
    private iButton btnModificar;//Boton para modificar
    private iButton btnEliminar;//Boton para eliminar
    
    private iLabel lbl_Titulo_Mantenimiento;//Lbl para el Titulo de la pagina de mantenimiento en la que se encuentra
    private iLabel lbl_idCurso;//Lbl para el id curso
    private iTextField txt_idCurso;//TextField para el id curso
    private iLabel lbl_NombreCurso;//Lbl para el nombre del curso
    private iTextField txt_NombreCurso;//TextField para el nombre del curso
    
    public Maintenance(iFrame if_) {
        currentPanel = "Maintenance_Curso";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_Curso = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_Curso.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel
        Mantenimiento_curso(if_);
    }
    
    private void Componentes_Curso(){
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
        
        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO CURSOS");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());
        
        lbl_idCurso = new iLabel("ID Curso".toUpperCase());
        lbl_idCurso.setForeground(Color.GRAY.brighter());
        txt_idCurso = new iTextField("", 3);
        
        lbl_NombreCurso = new iLabel("Nombre Curso".toUpperCase());
        lbl_NombreCurso.setForeground(Color.GRAY.brighter());
        txt_NombreCurso = new iTextField("", 3);
        
        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnModificar = new iButton("Modificar", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnEliminar = new iButton("Eliminar", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
    }
    private void Mantenimiento_curso(iFrame if_) {
        Componentes_Curso();
        
        Maintenance_Curso.AddObject(lbl_LogoULatina, 415, 120, 10);
        Maintenance_Curso.AddObject(lbl_LogoPsicologia, 415, 120, 440);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_Curso.newLine(); 

        Maintenance_Curso.AddObject(lbl_idCurso, 415, 30, 2);
        Maintenance_Curso.AddObject(txt_idCurso, 415, 30, 600);//agrega el label y el textfield del id curso
        Maintenance_Curso.newLine();
        
        Maintenance_Curso.AddObject(lbl_NombreCurso, 415, 30, 2);
        Maintenance_Curso.AddObject(txt_NombreCurso, 415, 30, 600);//agrega el label y el textfield del nombre del curso
        Maintenance_Curso.newLine();
        
        Maintenance_Curso.AddObject(btnAñadir, 175, 30, 10);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });
        
        Maintenance_Curso.AddObject(btnModificar, 175, 30, 10);
        btnModificar.addActionListener((a) -> {
            btnModificar_MouseClicked();
        });
        
        Maintenance_Curso.AddObject(btnEliminar, 175, 30, 10);
        btnEliminar.addActionListener((a) -> {
            btnEliminar_MouseClicked(); 
        });
        Maintenance_Curso.newLine();
        
        if_.add(Maintenance_Curso);
    } 
    
    public void btnAñadir_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Curso(IdCurso, NombreCurso) VALUES (?,?)");
        
        pps.setString(1 , txt_idCurso.getText());
        pps.setString(2 , txt_NombreCurso.getText());
        
        pps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void btnModificar_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("UPDATE JAW_Curso SET NombreCurso=? WHERE IdCurso=?");
        
        pps.setString(1 , txt_NombreCurso.getText());
        pps.setString(2 , txt_idCurso.getText());
        
        pps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void btnEliminar_MouseClicked(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("DELETE FROM `JAW_Curso` where `IdCurso` = ?");
        
        pps.setString(1 , txt_idCurso.getText());
        
        pps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}