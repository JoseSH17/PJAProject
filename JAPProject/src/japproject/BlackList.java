/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author Jose
 */
public class BlackList {

    public iPanel BlackList_Panel;

    //
    iTable RegistrosTable;
    iLabel SearchBar_lbl;
    iTextField SearchBar_txt;
    private iLabel lbl_LogoULatina;//Ulatina logo lbl display
    private iLabel lbl_LogoPsicologia;//Ulatina Psychology Dept logo lbl display

    //
    public BlackList(iFrame if_) {
        currentPanel = "BlackList_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.        
        try {
            BlackList_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);
            BlackList_Panel.setBackground(ColorPanels);

            lbl_LogoULatina = new iLabel("");
            lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

            lbl_LogoPsicologia = new iLabel("");
            lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
            BlackList_Panel.addSpace(10);
            BlackList_Panel.AddObject(lbl_LogoULatina, 618, 120, 30);
            BlackList_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);

            BlackList_Panel.newLine();
            BlackList_Panel.addSpace(25);
            ResultSet rr = sql.SELECT("SELECT * FROM JAW_VistaListaNegra");//query que selecciona todo de la vista                       
            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rr.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rr.getMetaData().getColumnName(i));
            }
//          Se crea la tabla y se le da los parametros
            SearchBar_txt = new iTextField("", 3);
            RegistrosTable = new iTable(Cols, SearchBar_txt);
            RegistrosTable.getTableHeader().setReorderingAllowed(false);
            RegistrosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            RegistrosTable.setRowSelectionAllowed(true);
            RegistrosTable.setBackground(ColorPanels);

            PopMenu(RegistrosTable, if_);//metodo que crea e implementa el popmenu 
            iScrollPane scrollPane2 = new iScrollPane(RegistrosTable, ColorPanels);
            scrollPane2.setViewportView(RegistrosTable);
            SetColumsSizes(RegistrosTable);

            if (sql.Exists(rr)) {//verifica que el query sea valido
                try {

                    while (rr.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rr.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rr.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rr.getObject(i);
                        }

                        RegistrosTable.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }

            AddComponentes(scrollPane2);
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo para crear el popmenu
     */
    public void PopMenu(iTable RegistrosTable, iFrame if_) {

        //
        RegistrosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = RegistrosTable.rowAtPoint(e.getPoint());

                if (r >= 0 && r < RegistrosTable.getRowCount()) {
                    RegistrosTable.setRowSelectionInterval(r, r);
                } else {
                    RegistrosTable.clearSelection();
                }

                int rowindex = RegistrosTable.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem ItemEditar = new JMenuItem("Eliminar de ListaNegra");

                    ItemEditar.addActionListener((ae) -> {
                        int IdPaciente = Integer.parseInt(RegistrosTable.getValueAt(0, 0).toString());
                        ArrayList<Object> obj2 = new ArrayList();//array para guardar data
                        obj2.addAll(Arrays.asList("No", IdPaciente));

                        String query2 = "UPDATE JAW_Paciente SET `IsNonGrato` = ? WHERE `IdPaciente`=?";

                        Boolean exq2 = sql.exec(query2, obj2);
                        if (exq2) {
                            JOptionPane.showMessageDialog(null, "ELIMINADO DE LA LISTA NEGRA EXITOSAMENTE!", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
                            SearchBar_txt.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR DE LA LISTA NEGRA", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    });
                    popup.add(ItemEditar);
                    RegistrosTable.setComponentPopupMenu(popup);
                    //

                }
            }
        });

    }

    /**
     *
     * Metodo para añadir los componentes
     */
    public void AddComponentes(iScrollPane scrollPane2) {
        SearchBar_lbl = new iLabel("Filtrar");
        SearchBar_lbl.setForeground(Color.WHITE); //Search txt to filter table results in real time.

        BlackList_Panel.AddObject(SearchBar_lbl, 200, 30, 30);
        BlackList_Panel.AddObject(SearchBar_txt, 200, 30, 110);//agrego el titulo para poner verlo con
        SearchBar_lbl.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        BlackList_Panel.newLine();

        BlackList_Panel.addSpace(20);
        BlackList_Panel.AddSingleObject(scrollPane2, 95.5f, 67f, CENTER);
        BlackList_Panel.newLine();
        BlackList_Panel.finalice();
        BlackList_Panel.setVisible(true);

    }

    /**
     *
     * Metodo Para setear los sizes de las columnas
     *
     * @return Para setear los sizes de las columnas
     */
    public void SetColumsSizes(iTable RegistrosTable) {
        //            Codigo para manipular los sizes de las columnas
        RegistrosTable.getColumnModel().getColumn(0).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(0).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(0).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(3).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(4).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(5).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(6).setPreferredWidth(180);
        RegistrosTable.getColumnModel().getColumn(7).setPreferredWidth(180);

        RegistrosTable.getColumnModel().getColumn(8).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(8).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(8).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(9).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(10).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(10).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(10).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(11).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(12).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(13).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(14).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(14).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(14).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(15).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(16).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(16).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(16).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(17).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(18).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(18).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(18).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(19).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(20).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(21).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(21).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(21).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(22).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(23).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(23).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(23).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(24).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(25).setPreferredWidth(140);

        RegistrosTable.getColumnModel().getColumn(26).setWidth(0);
        RegistrosTable.getColumnModel().getColumn(26).setMinWidth(0);
        RegistrosTable.getColumnModel().getColumn(26).setMaxWidth(0);

        RegistrosTable.getColumnModel().getColumn(27).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(28).setPreferredWidth(140);
        RegistrosTable.getColumnModel().getColumn(29).setPreferredWidth(140);

//fin de los parametros de la tabla
    }

}
