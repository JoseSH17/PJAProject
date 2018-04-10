/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import static japproject.JAPProject.sql;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.currentPanel;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author TUMA
 */
public class PatientView implements ActionListener {

    //
    public iTable RegistrosTable;
    iLabel SearchBar_lbl;
    iTextField SearchBar_txt;

    //
    public static iPanel PatientView_panel;
    public static List<String> tbl_Data = new ArrayList();
    EditPatient EP;
    //popmenu
    JPopupMenu popup;
    JMenuItem ItemEditar;

    public PatientView(iFrame if_) {

        currentPanel = "PatientView_panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.        
        try {
            PatientView_panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);
            PatientView_panel.setBackground(Color.DARK_GRAY);

            ResultSet rr = sql.SELECT("SELECT * FROM JAW_VistaPacientes");//query que selecciona todo de la vista                       
            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rr.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rr.getMetaData().getColumnName(i));
            }
            Cols.add("Telefonos");

//          Se crea la tabla y se le da los parametros
            SearchBar_txt = new iTextField("", 3);
            RegistrosTable = new iTable(Cols, SearchBar_txt);
            RegistrosTable.getTableHeader().setReorderingAllowed(false);
            RegistrosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            RegistrosTable.setRowSelectionAllowed(true);
            RegistrosTable.setSize(500, 500);
            RegistrosTable.setBackground(Color.DARK_GRAY);

            popup = new JPopupMenu();
            ItemEditar = new JMenuItem("Editar paciente");
            ItemEditar.addActionListener(this);

            popup.add(ItemEditar);

            RegistrosTable.setComponentPopupMenu(popup);
            RegistrosTable.addMouseListener(new TableMouseListener(RegistrosTable));

            iScrollPane scrollPane2 = new iScrollPane(RegistrosTable, Color.DARK_GRAY);
            scrollPane2.setViewportView(RegistrosTable);
            SetColumsSizes();

            if (sql.Exists(rr)) {//verifica que el query sea valido
                try {

                    while (rr.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rr.getMetaData().getColumnCount() + 1];
                        for (int i = 1; i <= rr.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rr.getObject(i);
                        }
                          row[30]="Telefonos";            
                        RegistrosTable.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
            // Agregar columna de botones   
            RegistrosTable.getColumnModel().getColumn(30).setCellRenderer(new ButtonRenderer());;
            //SET CUSTOM EDITOR TO TEAMS COLUMN
            RegistrosTable.getColumnModel().getColumn(30).setCellEditor(new ButtonEditor(new JTextField()));
            //
            AddComponentes(scrollPane2);
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo para crear el popmenu
     */
    public void LlamarEditPatient(iFrame if_) {
        tbl_Data.clear();
        //PatientView_panel.dispose();
        PatientView_panel.setVisible(false);
        ItemEditarActionListener();
        EP = new EditPatient(if_);
    }

    public void ItemEditarActionListener() {

        int selectedRow = RegistrosTable.getSelectedRow();

        for (int j = 0; j < RegistrosTable.getColumnCount()-1; j++) {

            tbl_Data.add(RegistrosTable.getColumnName(j) + "-" + RegistrosTable.getValueAt(selectedRow, j).toString());
        }
    }

    /**
     *
     * Metodo para añadir los compone ntes
     *
     * @param scrollPane2
     */
    public void AddComponentes(iScrollPane scrollPane2) {
        SearchBar_lbl = new iLabel("BUSQUEDA".toUpperCase());
        SearchBar_lbl.setForeground(Color.WHITE);

        PatientView_panel.AddObject(SearchBar_lbl, 200, 30, 10);
        PatientView_panel.AddObject(SearchBar_txt, 200, 30, 150);//agrego el titulo para poner verlo con
        SearchBar_lbl.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        PatientView_panel.newLine();

        PatientView_panel.AddSingleObject(scrollPane2, 100.0f, 89f, CENTER);
        //scrollPane2.setResponsiveHeight(100.0f, 100);
        scrollPane2.setResponsiveExtendedPixelHeight(208);
        PatientView_panel.newLine();
        PatientView_panel.finalice();
        PatientView_panel.setVisible(true);

    }

    /**
     *
     * Metodo Para setear los sizes de las columnas
     *
     * @param RegistrosTable
     */
    public void SetColumsSizes() {
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
        RegistrosTable.getColumnModel().getColumn(29).setPreferredWidth(75);
        RegistrosTable.getColumnModel().getColumn(30).setPreferredWidth(140);

//fin de los parametros de la tabla
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();

        if (menu == ItemEditar) {
            LlamarEditPatient(HomePanel.if_);

        }

    }

    //Metodos override para la tabla botones
    class ButtonRenderer extends JButton implements TableCellRenderer {

        //CONSTRUCTOR
        public ButtonRenderer() {
            //SET BUTTON PROPERTIES
            setOpaque(true);

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object obj,
                boolean selected, boolean focused, int row, int col) {

            //SET PASSED OBJECT AS BUTTON TEXT
            setText((obj == null) ? "" : obj.toString());

            return this;
        }

    }

//BUTTON EDITOR CLASS
    class ButtonEditor extends DefaultCellEditor {

        protected JButton btn;
        private String lbl;
        private Boolean clicked;

        public ButtonEditor(JTextField txt) {
            super(txt);

            btn = new JButton();
            btn.setOpaque(true);
           
            
            btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("WOOOOOOOOOOOOOOOOOOOOO~~~");
            }
        });
            
            
            
            //WHEN BUTTON IS CLICKED
            btn.addMouseListener(new MouseAdapter() {
   

                @Override
                public void mouseClicked(MouseEvent o) {
                    
                    System.out.println("hola");
                    clicked = true;
                }
            });
        }

        //OVERRIDE A COUPLE OF METHODS
        @Override
        public Component getTableCellEditorComponent(JTable table, Object obj,
                boolean selected, int row, int col) {

            //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
            lbl = (obj == null) ? "" : obj.toString();
            btn.setText(lbl);
            clicked = true;
            return btn;
        }

        //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
        @Override
        public Object getCellEditorValue() {

            if (clicked) {
                //SHOW US SOME MESSAGE
                JOptionPane.showMessageDialog(btn, lbl + " Clicked");
            }
            //SET IT TO FALSE NOW THAT ITS CLICKED
            clicked = false;
            return new String(lbl);
        }

        @Override
        public boolean stopCellEditing() {

            //SET CLICKED TO FALSE FIRST
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            // TODO Auto-generated method stub
            super.fireEditingStopped();
        }
    }
}
