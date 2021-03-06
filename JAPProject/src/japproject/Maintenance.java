package japproject;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class Maintenance implements ActionListener {

    public iPanel Maintenance_Curso_Panel;//creo el iPanel
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnAñadir;//Boton para añadir

    private iLabel lbl_Titulo_Mantenimiento;//Lbl para el Titulo de la pagina de mantenimiento en la que se encuentra
    private iLabel lbl_NombreCurso;//Lbl para el nombre del curso
    private iTextField txt_NombreCurso;//TextField para el nombre del curso
    iTextField NombrePsicologo_txt;
    iPanel ip;
    iPanel ip2;
    private iTable table;
    //popmenu
    JPopupMenu popup;
    JMenuItem ItemEditar;
    JMenuItem ItemEliminar;
    public static List<String> tbl_Data2 = new ArrayList();

    public Maintenance(iFrame if_) {
        currentPanel = "Maintenance_Curso_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_Curso_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_Curso_Panel.setBackground(ColorPanels);//le doy color al panel

        PanelTabla();
        if_.add(Maintenance_Curso_Panel);
    }

    private iPanel PanelTabla() {
        ip = new iPanel(580, 300, 500, 400, 4);
        ip.setBackground(Color.black);

        //popmenu
        popup = new JPopupMenu();
        ItemEditar = new JMenuItem("Editar Curso");
        ItemEditar.addActionListener(this);
        ItemEliminar = new JMenuItem("Eliminar Curso");
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
        Mantenimiento_curso(ip);

        return ip;
    }

    private void Componentes_Curso() {
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO CURSOS");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());

        lbl_NombreCurso = new iLabel("Nombre Curso".toUpperCase());
        lbl_NombreCurso.setForeground(Color.GRAY.brighter());
        txt_NombreCurso = new iTextField("", 15);

        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para añadir curso
    }

    private void Mantenimiento_curso(iPanel scrollPane) {
        Componentes_Curso();

        Maintenance_Curso_Panel.AddObject(lbl_LogoULatina, 618, 120, 10);
        Maintenance_Curso_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_Curso_Panel.newLine();
        Maintenance_Curso_Panel.addSpace(20);

        Maintenance_Curso_Panel.AddObject(lbl_Titulo_Mantenimiento, 415, 30, 665);//agrego el titulo
        Maintenance_Curso_Panel.newLine();
        Maintenance_Curso_Panel.addSpace(20);

        Maintenance_Curso_Panel.AddObject(lbl_NombreCurso, 415, 30, 580);
        Maintenance_Curso_Panel.AddObject(txt_NombreCurso, 350, 30, 725);//agrega el label y el textfield del nombre del curso
        Maintenance_Curso_Panel.newLine();
        Maintenance_Curso_Panel.addSpace(5);

        Maintenance_Curso_Panel.AddObject(btnAñadir, 130, 30, 810);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });
        Maintenance_Curso_Panel.newLine();
        Maintenance_Curso_Panel.add(scrollPane);
        Maintenance_Curso_Panel.add(Editar());
    }

    public void Tabla() {
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("ID Curso", "Nombre Curso"));
        table = new iTable(cols);

        ResultSet rs = sql.SELECT(""
                + "SELECT `IdCurso`, `NombreCurso` "
                + "FROM `JAW_Curso`");
        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("IdCurso"),
                        rs.getObject("NombreCurso")
                    };
                    table.addrow(result);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
    }

//    public void btnAñadir_MouseClicked(){
//        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        ArrayList<Object> obj2 = new ArrayList();//array para guardar data
//        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
//        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Curso(NombreCurso) VALUES (?)");
//        pps.setString(1 , txt_NombreCurso.getText());
//        
//        pps.executeUpdate();
//        Tabla();  //Esto se supone que debe de actualizar la pagina
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//    }
    

    public iPanel Editar() {
        ip2 = new iPanel(1095, 300, 420, 150, 20);
//    ip.setBackground(Color.black);
        iLabel NombrePsicologo_lbl = new iLabel("Nombre Curso");
        NombrePsicologo_txt = new iTextField("", 15);

        iButton EditButton = new iButton("Editar", 15, Color.WHITE, Color.BLACK);

        EditButton.addActionListener((ae) -> {

            ArrayList<Object> obj2 = new ArrayList();//array para guardar data
            obj2.addAll(Arrays.asList(
                    NombrePsicologo_txt.getText(),
                    tbl_Data2.get(0)
            ));
            Boolean exq = sql.exec("UPDATE JAW_Curso SET NombreCurso=? WHERE IdCurso=?", obj2);

            if (exq) {
                JOptionPane.showMessageDialog(null, "EDITADO CORRECTAMENTE");
                table.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR EL CURSO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });
        iButton CancelButton = new iButton("Cancelar", 15, Color.WHITE, Color.BLACK);
        CancelButton.addActionListener((ai) -> {
            NombrePsicologo_txt.setText("");

        });
        ip2.AddObject(NombrePsicologo_lbl, 200, 30, 50);
        ip2.AddObject(NombrePsicologo_txt, 200, 30, 170);
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
                txt_NombreCurso.getText()
        ));
        Boolean exq = sql.exec("INSERT INTO JAW_Curso(NombreCurso) VALUES (?)", obj2);
        if (exq) {
            JOptionPane.showMessageDialog(null, "AÑADIDO CORRECTAMENTE");
            txt_NombreCurso.setText("");
            table.repaint();
//            Maintenance_psicologos h = new Maintenance_psicologos(HomePanel.if_);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL AÑADIR EL CURSO", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        Boolean exq = sql.exec("DELETE FROM `JAW_Curso` where `IdCurso` = ?", obj2);

        if (exq) {
            JOptionPane.showMessageDialog(null, "ELIMINADO CORRECTAMENTE");
            NombrePsicologo_txt.setText("");
            txt_NombreCurso.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR EL CURSO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();

        if (menu == ItemEditar) {
            tbl_Data2.clear();
            ItemEditarActionListener();
            NombrePsicologo_txt.setText(tbl_Data2.get(1).toString());

        } else if (menu == ItemEliminar) {
            tbl_Data2.clear();
            ItemEditarActionListener();
            ItemEliminarActionListener();
        }

    }
}
