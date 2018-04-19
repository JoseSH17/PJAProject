package japproject;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.EditPatient.EditPatient_Panel;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


public class Maintenance_psicologos implements ActionListener {

    public iPanel Maintenance_Psicologos_Panel;//creo el iPanel
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

    public Maintenance_psicologos(iFrame if_) {
        currentPanel = "Maintenance_Psicologos_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
        Maintenance_Psicologos_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        Maintenance_Psicologos_Panel.setBackground(ColorPanels);//le doy color al panel

        ip = new iPanel(115, 300, 500, 400, 4);
        ip.setBackground(Color.black);

        //popmenu
        popup = new JPopupMenu();
        ItemEditar = new JMenuItem("Editar Psicologo");
        ItemEditar.addActionListener(this);
        ItemEliminar = new JMenuItem("Eliminar Psicologo");
        ItemEliminar.addActionListener(this);
        popup.add(ItemEditar);
        popup.add(ItemEliminar);
        LlenarTabla();
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
        AddComponents(ip);
        if_.add(Maintenance_Psicologos_Panel);
    }

    private void Componentes_Curso() {
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        lbl_Titulo_Mantenimiento = new iLabel("MANTENIMIENTO PSICOLOGOS");
        lbl_Titulo_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Titulo_Mantenimiento.setForeground(Color.GRAY.brighter());

        lbl_NombreCurso = new iLabel("Nombre Psicologo".toUpperCase());
        lbl_NombreCurso.setForeground(Color.GRAY.brighter());
        txt_NombreCurso = new iTextField("", 15);

        btnAñadir = new iButton("Añadir", 2, Color.GRAY, Color.BLACK);//boton para añadir curso

    }

    private void LlenarTabla() {
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("id", "Nombre Curso"));
        table = new iTable(cols);

        ResultSet rs = sql.SELECT(""
                + "SELECT `Id_psicologo`,`Nombre` "
                + "FROM `JAW_Psicologo`");
        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("Id_psicologo"),
                        rs.getObject("Nombre")
                    };
                    table.addrow(result);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
    }

    public void AddComponents(iPanel scrollPane) {
        Componentes_Curso();

        Maintenance_Psicologos_Panel.AddObject(lbl_LogoULatina, 618, 120, 10);
        Maintenance_Psicologos_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);//añade los logos oficiales de la clinica y de la universidad latina
        Maintenance_Psicologos_Panel.newLine();
        Maintenance_Psicologos_Panel.addSpace(20);

        Maintenance_Psicologos_Panel.AddObject(lbl_Titulo_Mantenimiento, 415, 30, 200);//agrego el titulo
        Maintenance_Psicologos_Panel.newLine();
        Maintenance_Psicologos_Panel.addSpace(20);

        Maintenance_Psicologos_Panel.AddObject(lbl_NombreCurso, 415, 30, 115);
        Maintenance_Psicologos_Panel.AddObject(txt_NombreCurso, 350, 30, 260);//agrega el label y el textfield del nombre del curso
        Maintenance_Psicologos_Panel.newLine();
        Maintenance_Psicologos_Panel.addSpace(5);

        Maintenance_Psicologos_Panel.AddObject(btnAñadir, 130, 30, 345);
        btnAñadir.addActionListener((a) -> {
            btnAñadir_MouseClicked();
        });

        Maintenance_Psicologos_Panel.newLine();

        Maintenance_Psicologos_Panel.add(scrollPane);
        Maintenance_Psicologos_Panel.add(Editar());

    }

    public iPanel Editar() {
        ip2 = new iPanel(630, 300, 420, 150, 20);
//    ip.setBackground(Color.black);
        iLabel NombrePsicologo_lbl = new iLabel("Nombre Psicologo");
        NombrePsicologo_txt = new iTextField("", 15);

        iButton EditButton = new iButton("Editar", 15, Color.WHITE, Color.BLACK);
        
        EditButton.addActionListener((ae) -> {
            
             ArrayList<Object> obj2 = new ArrayList();//array para guardar data
        obj2.addAll(Arrays.asList(
                NombrePsicologo_txt.getText(),
                 tbl_Data2.get(0)     
        ));
        Boolean exq = sql.exec("UPDATE JAW_Psicologo SET Nombre=? WHERE Id_psicologo=?");

        if (exq) {
            JOptionPane.showMessageDialog(null, "EDITADO CORRECTAMENTE");
            table.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL EDITAR EL PSICOLOGO", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        Boolean exq = sql.exec("INSERT INTO JAW_Psicologo(Nombre) VALUES (?)");

        if (exq) {
            JOptionPane.showMessageDialog(null, "AÑADIDO CORRECTAMENTE");
            table.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL AÑADIR EL PSICOLOGO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        //Esto se supone que debe de actualizar la pagina
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
        Boolean exq = sql.exec("DELETE FROM `JAW_Curso` where `IdCurso` = ?");

        if (exq) {
            JOptionPane.showMessageDialog(null, "ELIMINADO CORRECTAMENTE");
            table.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR EL PSICOLOGO", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            ItemEditarActionListener();
            ItemEliminarActionListener();
        }

    }

}
