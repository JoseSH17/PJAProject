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
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.if_;
import static japproject.JAPProject.sql;
import static japproject.Maintenance_Parentesco.tbl_Data2;
import static japproject.Maintenance_psicologos.tbl_Data2;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class Maintenance_tPaciente implements ActionListener{
  
    public iPanel Maintenance_TipPaciente_Panel;//creo el iPanel
    
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnAñadir;//Boton para añadir
    private iButton btnModificar;//Boton para modificar
    private iButton btnEliminar;//Boton para eliminar
    
    private iLabel lbl_Titulo_Mantenimiento;//Lbl para el Titulo de la pagina de mantenimiento en la que se encuentra
    private iLabel lbl_idTipoPaciente;//Lbl para el id curso
    private iTextField txt_idTipPaciente;//TextField para el id curso
    private iLabel lbl_NombreTipoPaciente;//Lbl para el nombre del curso
    iTextField txt_NombreTipoPaciente;//TextField para el nombre del curso
    iPanel ip;
    iPanel ip2;
    private iTable table;
    //popmenu
    JPopupMenu popup;
    JMenuItem ItemEditar;
    JMenuItem ItemEliminar;
    public static List<String> tbl_Data2 = new ArrayList();
    
    public Maintenance_tPaciente(iFrame if_) {
        currentPanel = "Maintenance_TipPaciente_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_TipPaciente_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_TipPaciente_Panel.setBackground(ColorPanels);//le doy color al panel
        PanelTabla();
        if_.add(Maintenance_TipPaciente_Panel);
    }
    
    private iPanel PanelTabla() {
        ip = new iPanel(115, 300, 500, 400, 4);
        ip.setBackground(Color.black);

        //popmenu
        popup = new JPopupMenu();
        ItemEditar = new JMenuItem("Editar Tipo Paciente");
        ItemEditar.addActionListener(this);
        ItemEliminar = new JMenuItem("Eliminar Tipo Paciente");
        ItemEliminar.addActionListener(this);
        popup.add(ItemEditar);
        popup.add(ItemEliminar);
        Tabla();
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(true);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.setComponentPopupMenu(popup);
        table.addMouseListener(new TableMouseListener(table));

        //
        iScrollPane scrollPane = new iScrollPane(table, null);
        scrollPane.setBounds(0, 0, 500, 400);

        ip.add(scrollPane);
        ip.finalice();

        //
        Mantenimiento_tPaciente(ip);

        return ip;
    }
    
    private void Componentes_Tipo_Paciente(){
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
         
        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO TIPO PACIENTE");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());
        
        lbl_NombreTipoPaciente = new iLabel("Nombre Tipo Paciente".toUpperCase());
        lbl_NombreTipoPaciente.setForeground(Color.GRAY.brighter());
        txt_NombreTipoPaciente = new iTextField("", 15);
        
        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para añadir horario
    }
    private void Mantenimiento_tPaciente(iPanel scrollPane) {
        Componentes_Tipo_Paciente();
        
        Maintenance_TipPaciente_Panel.AddObject(lbl_LogoULatina, 618, 120, 10);
        Maintenance_TipPaciente_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_TipPaciente_Panel.newLine();
        Maintenance_TipPaciente_Panel.addSpace(5);
 
        Maintenance_TipPaciente_Panel.AddObject(lbl_Titulo_Mantenimiento, 415, 30, 5);//agrego el titulo
        Maintenance_TipPaciente_Panel.newLine();
        Maintenance_TipPaciente_Panel.addSpace(5);
        
        Maintenance_TipPaciente_Panel.AddObject(lbl_NombreTipoPaciente, 415, 30, 30);
        Maintenance_TipPaciente_Panel.AddObject(txt_NombreTipoPaciente, 415, 30, 200);//agrega el label y el textfield del nombre del horario
        Maintenance_TipPaciente_Panel.newLine();
        Maintenance_TipPaciente_Panel.addSpace(5);
        
        Maintenance_TipPaciente_Panel.AddObject(btnAñadir, 130, 30, 200);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });
        Maintenance_TipPaciente_Panel.newLine();
        Maintenance_TipPaciente_Panel.add(scrollPane);
        Maintenance_TipPaciente_Panel.add(Editar());
    } 
    
    public void Tabla(){
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("ID Tipo Paciente", "Nombre Tipo Paciente"));
        iTable table = new iTable(cols);
        ResultSet rs = sql.SELECT(""
                + "SELECT `IdTipoPaciente`, `NombreTipoPaciente` "
                + "FROM `JAW_TipoPaciente`");
        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("IdTipoPaciente"),
                        rs.getObject("NombreTipoPaciente")
                    };
                    table.addrow(result);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
    }
    public iPanel Editar() {
        ip2 = new iPanel(630, 300, 420, 150, 20);
//    ip.setBackground(Color.black);
        iLabel NombrePsicologo_lbl = new iLabel("Tipo Paciente");
        txt_NombreTipoPaciente = new iTextField("", 15);

        iButton EditButton = new iButton("Editar", 15, Color.WHITE, Color.BLACK);

        EditButton.addActionListener((ae) -> {

            ArrayList<Object> obj2 = new ArrayList();//array para guardar data
            obj2.addAll(Arrays.asList(
                    txt_NombreTipoPaciente.getText(),
                    tbl_Data2.get(0)
            ));
            Boolean exq = sql.exec("UPDATE JAW_TipoPaciente SET NombreTipoPaciente=? WHERE IdTipoPaciente=?", obj2);

            if (exq) {
                JOptionPane.showMessageDialog(null, "EDITADO CORRECTAMENTE");
                txt_NombreTipoPaciente.setText("");
//            Maintenance_psicologos h = new Maintenance_psicologos(HomePanel.if_);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR EL TIPO PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });
        iButton CancelButton = new iButton("Cancelar", 15, Color.WHITE, Color.BLACK);
        CancelButton.addActionListener((ai) -> {
            txt_NombreTipoPaciente.setText("");

        });
        ip2.AddObject(NombrePsicologo_lbl, 200, 30, 50);
        ip2.AddObject(txt_NombreTipoPaciente, 200, 30, 170);
        ip2.newLine();
        ip2.addSpace(5);
        ip2.AddObject(EditButton, 100, 30, 76 - 1);
        ip2.AddObject(CancelButton, 100, 30, 200);
        ip2.newLine();

        ip2.finalice();

        return ip2;
    }
        public void btnAñadir_MouseClicked() {
        ArrayList<Object> obj2 = new ArrayList();//array para guardar data
        obj2.addAll(Arrays.asList(
                txt_NombreTipoPaciente.getText()
        ));
        Boolean exq = sql.exec("INSERT INTO JAW_TipoPaciente(NombreTipoPaciente) VALUES (?)", obj2);

        if (exq) {
            JOptionPane.showMessageDialog(null, "AÑADIDO CORRECTAMENTE");
            txt_NombreTipoPaciente.setText("");
//            Maintenance_psicologos h = new Maintenance_psicologos(HomePanel.if_);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL AÑADIR EL TIPO PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void ItemEditarActionListener() {

        int selectedRow = table.getSelectedRow();

        for (int j = 0; j < table.getColumnCount(); j++) {

            tbl_Data2.add(table.getValueAt(selectedRow, j).toString());
        }
    }

    public void ItemEliminarActionListener() {

        ArrayList<Object> obj2 = new ArrayList();//array para guardar data
        obj2.addAll(Arrays.asList(
                tbl_Data2.get(0).toString()
        ));
        Boolean exq = sql.exec("DELETE FROM `JAW_TipoPaciente` where `IdTipoPaciente` = ?",obj2);

        if (exq) {
            JOptionPane.showMessageDialog(null, "ELIMINADO CORRECTAMENTE");
                txt_NombreTipoPaciente.setText("");
//            Maintenance_psicologos h = new Maintenance_psicologos(HomePanel.if_);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR EL TIPO PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();

        if (menu == ItemEditar) {
            tbl_Data2.clear();
            ItemEditarActionListener();
            txt_NombreTipoPaciente.setText(tbl_Data2.get(1).toString());

        } else if (menu == ItemEliminar) {
            tbl_Data2.clear();
            ItemEditarActionListener();
            ItemEliminarActionListener();
        }

    }

    
}