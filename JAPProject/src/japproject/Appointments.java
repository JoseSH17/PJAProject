/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iCalendar;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author Jose
 */
public class Appointments {

    public iPanel Appointments_Panel;
    public iLabel lblCalendar;
    public iTextField txtHiddenSearch;
    public iCalendar calendar;
    public JButton btnViewAll;
    public iTable tblCitas;

    public List<String> tbl_Data = new ArrayList();

    public Appointments(iFrame if_) {
        try {                        
            currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
            Appointments_Panel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//Defining iPanel dimensions
            Appointments_Panel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel               

            lblCalendar = new iLabel("Por favor seleccione una fecha: "); //Lbl Guide
            lblCalendar.setForeground(Color.WHITE); //Calendar
            calendar = new iCalendar();
            
            txtHiddenSearch = new iTextField("",0); //Hidden txt that searches in table and updates result for used depending on selected date, this is not shown to the user.           
            
            CalendarUpdateTable(calendar); //Calling method to update table dynamically
                       
            
            CheckFirstExecution(txtHiddenSearch);
            
            btnViewAll = new JButton("Ver todas las citas");
            btnViewAll.setToolTipText("Elimina el filtro y muestra todas las citas");
            
            Appointments_Panel.addSpace(20); //Leaving space from top
            
            Appointments_Panel.AddObject(lblCalendar, 190, 30, 3);
            Appointments_Panel.AddObject(calendar, 70, 30, 193);
            Appointments_Panel.AddObject(btnViewAll, 140, 30, 303);
            Appointments_Panel.newLine();
            
             btnViewAll.addActionListener((ae) -> {
                 txtHiddenSearch.setText(""); //Deletes filter defined on txtHiddenSearch and show all data
                 
             });
            

            ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaCitas order by `Fecha Cita` desc");

            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rs.getMetaData().getColumnName(i));
            }
//          Se crea la tabla y se le da los parametros
            System.out.println("Valor de txthiddensearch:" + txtHiddenSearch.getText());
            tblCitas = new iTable(Cols, txtHiddenSearch);
            tblCitas.getTableHeader().setReorderingAllowed(false);
            tblCitas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tblCitas.getTableHeader().setResizingAllowed(false);
            tblCitas.setRowSelectionAllowed(true);
            tblCitas.setSize(1100, 300);

            PopMenu(tblCitas, if_);//metodo que crea e implementa el popmenu 
            iScrollPane scrollCitas = new iScrollPane(tblCitas, null);
            scrollCitas.setViewportView(tblCitas);
            SetColumsSizes(tblCitas);

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rs.getObject(i);
                        }

                        tblCitas.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
            Appointments_Panel.addSpace(20);
            Appointments_Panel.AddObject(scrollCitas, 1100, 300);
            Appointments_Panel.newLine();
            Appointments_Panel.finalice();
        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CheckFirstExecution(iTextField check)
    {   
        if(check.getText().isEmpty())
        {
            Date date =  new Date(); //Getting current date from local host
            DateFormat currentDateFormatted = new SimpleDateFormat("yyyy/MM/dd"); //Formatting current date for initial search and table refresh            
            txtHiddenSearch.setText(currentDateFormatted.format(calendar.getDate()));
        }
    }
    
    public void CalendarUpdateTable(iCalendar calendar) {
        calendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calendarActionListener(calendar.getDate());                
            }
        });
    }

    public void calendarActionListener(Date date) {
        DateFormat currentDateFormatted = new SimpleDateFormat("yyyy/MM/dd");
        txtHiddenSearch.setText(currentDateFormatted.format(date));        
        System.out.println("Fecha Actual: " + currentDateFormatted.format(date));
    }

    public void PopMenu(iTable tblCitas, iFrame if_) {

        //
        tblCitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = tblCitas.rowAtPoint(e.getPoint());

                if (r >= 0 && r < tblCitas.getRowCount()) {
                    tblCitas.setRowSelectionInterval(r, r);
                } else {
                    tblCitas.clearSelection();
                }

                int rowindex = tblCitas.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem ItemEditar = new JMenuItem("Editar Cita");
                    JMenuItem ItemEliminar = new JMenuItem("Eliminar Cita");

                    ItemEditar.addActionListener((ae) -> {
                        ItemEditarActionListener(tblCitas);
                    });

                    ItemEliminar.addActionListener((ae) -> {
                        ItemEliminarActionListener(tblCitas);
                    });

                    popup.add(ItemEditar);
                    popup.add(ItemEliminar);
                    tblCitas.setComponentPopupMenu(popup);
                    //

                }
            }
        });

    }

    public void ItemEditarActionListener(iTable tblCitas) {

        int selectedRow = tblCitas.getSelectedRow();

        for (int j = 0; j < tblCitas.getColumnCount(); j++) {
            tbl_Data.add(tblCitas.getColumnName(j) + "-" + tblCitas.getValueAt(selectedRow, j).toString());
            System.out.println("tblData: " + tbl_Data.toString());
        }
    }

    public void ItemEliminarActionListener(iTable tblCitas) {
        int selectedRow = tblCitas.getSelectedRow();
        System.out.println("ID CITA: " + tblCitas.getValueAt(selectedRow, 0).toString());
        ArrayList<Object> objs = new ArrayList();
        objs.addAll(Arrays.asList(tblCitas.getValueAt(selectedRow, 0).toString()));
        sql.exec("UPDATE JAW_Citas SET `Deleted` = 1 WHERE IdCita = ?", objs);
    }

    public void SetColumsSizes(iTable tblCitas) {
        //Code to manage columns sizes
        tblCitas.getColumnModel().getColumn(0).setWidth(0);
        tblCitas.getColumnModel().getColumn(0).setMinWidth(0);
        tblCitas.getColumnModel().getColumn(0).setMaxWidth(0);

        tblCitas.getColumnModel().getColumn(1).setPreferredWidth(140);

        tblCitas.getColumnModel().getColumn(2).setWidth(0);
        tblCitas.getColumnModel().getColumn(2).setMinWidth(0);
        tblCitas.getColumnModel().getColumn(2).setMaxWidth(0);

        tblCitas.getColumnModel().getColumn(3).setPreferredWidth(140);

        tblCitas.getColumnModel().getColumn(4).setPreferredWidth(200);

        tblCitas.getColumnModel().getColumn(5).setPreferredWidth(400);

        tblCitas.getColumnModel().getColumn(6).setPreferredWidth(100);

        tblCitas.getColumnModel().getColumn(7).setPreferredWidth(150);

    }

}
