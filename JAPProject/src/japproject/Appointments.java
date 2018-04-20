/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import iComponents.iCalendar;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTableRender.headerRender;
import iComponents.iTextField;
import static japproject.HomePanel.ColorElementsFonts;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorNonEditElementsFonts;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import static japproject.HomePanel.ColorPanels;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Jose
 */
public class Appointments implements ActionListener {

    public iPanel Appointments_Panel; //Panel to view, edit and add appointments.
    public iLabel lblCalendar; //lbl to indicate the user to select a date.
    public iCalendar calendar; //Calendar to filter main appointments view.
    public iTextField txtHiddenSearch; //Filter for the iTable that shows appointments, is hidden from the user.
    public JButton btnViewAll; //Removes filter from txtHidenSearch.
    public JButton btnScheduleAppointment; //Allows the user to schedule a new appointment.
    public JTable tblAppointments; //Table to display all appointments, can be filtered as user requests.
    public JPopupMenu popup;
    public JMenuItem ItemEditar;
    public JMenuItem ItemEliminar;
    public EditAppointmentData EAP;

    private iLabel lbl_LogoULatina;//Ulatina logo lbl display
    private iLabel lbl_LogoPsicologia;//Ulatina Psychology Dept logo lbl display    

    public List<String> tbl_Data = new ArrayList();
    public String data[][];  //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    iScrollPane scrollCitas;
    

    public Appointments(iFrame if_) {
        try {
            currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions. 
            this.EAP = new EditAppointmentData();

            Appointments_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//Defining iPanel dimensions
            Appointments_Panel.setBackground(ColorPanels);//le doy color al panel  


            lbl_LogoULatina = new iLabel("");
            lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

            lbl_LogoPsicologia = new iLabel("");
            lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));
            Appointments_Panel.addSpace(10);
            Appointments_Panel.AddObject(lbl_LogoULatina, 618, 120, 50);
            Appointments_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 660);
              
            Appointments_Panel.newLine();            

            lblCalendar = new iLabel("Por favor seleccione una fecha: "); //Lbl Guide
            lblCalendar.setForeground(ColorFonts); //Calendar
            calendar = new iCalendar();
            calendar.setForeground(ColorElementsFonts);

            txtHiddenSearch = new iTextField("", 0); //Hidden txt that searches in table and updates result for used depending on selected date, this is not shown to the user.           

            CalendarUpdateTable(calendar); //Calling method to update table dynamically                                   

            btnViewAll = new JButton("Ver todas las citas");
            btnViewAll.setToolTipText("Elimina el filtro y muestra todas las citas");

            Appointments_Panel.addSpace(40); //Leaving space from top

            Appointments_Panel.AddObject(lblCalendar, 190, 30, 50);
            Appointments_Panel.AddObject(calendar, 100, 30, 193 + 47);
            Appointments_Panel.AddObject(btnViewAll, 140, 30, 303 + 47);
            Appointments_Panel.newLine();
            Appointments_Panel.addSpace(15);

            btnViewAll.addActionListener((ae) -> {
                txtHiddenSearch.setText(""); //Deletes filter defined on txtHiddenSearch and show all data

            });

            ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaCitas order by `Fecha Cita` desc");

            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rs.getMetaData().getColumnName(i));
            }
//          Se crea la tabla y se le da los parametros
            Cols.add("Tels");
            DefaultTableModel model = new DefaultTableModel(data, Cols.toArray()); //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
            tblAppointments = new JTable(model);
            
            tblAppointments.getTableHeader().setReorderingAllowed(false);
            tblAppointments.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tblAppointments.setRowSelectionAllowed(true);
            tblAppointments.setBackground(ColorFonts);
            
            tblAppointments.getTableHeader().setDefaultRenderer(new headerRender());
            tblAppointments.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            tblAppointments.setShowGrid(false);            
            tblAppointments.setRowHeight(28);
                     
            popup = new JPopupMenu();
            ItemEditar = new JMenuItem("Editar Cita");
            ItemEditar.addActionListener(this);
            ItemEliminar = new JMenuItem("Eliminar Cita");
            ItemEliminar.addActionListener(this);

            popup.add(ItemEditar);
            popup.add(ItemEliminar);

            tblAppointments.setComponentPopupMenu(popup);
            tblAppointments.addMouseListener(new TableMouseListener(tblAppointments));
                                                  
            //This Codes adds the row sorter from iTable to the JTable
            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblAppointments.getModel());

            tblAppointments.setRowSorter(sorter);
            txtHiddenSearch.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent de) {
                    if (txtHiddenSearch.getText().isEmpty() || txtHiddenSearch.getText().equals(txtHiddenSearch.getHover())) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtHiddenSearch.getText()));    //Adding "(?i)" to make regex filter Case Insensitive                    
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    if (txtHiddenSearch.getText().isEmpty() || txtHiddenSearch.getText().equals(txtHiddenSearch.getHover())) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtHiddenSearch.getText()));    //Adding "(?i)" to make regex filter Case Insensitive                    
                    }

                }

                @Override
                public void changedUpdate(DocumentEvent de) {

                }
            });

            //PopMenu(tblAppointments, if_);//metodo que crea e implementa el popmenu 
            scrollCitas = new iScrollPane(tblAppointments, ColorPanels);
            scrollCitas.setViewportView(tblAppointments);
            SetColumsSizes();

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount()+1];
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rs.getObject(i);
                        }                          
                        row[9] = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PHONE, 20, Color.CYAN.darker());    
                        model.insertRow(model.getRowCount(), row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
                   
            Appointments_Panel.addSpace(20);
            Appointments_Panel.AddSingleObject(scrollCitas, 40.0f, 20.0f, CENTER);

            Appointments_Panel.newLine();
            Appointments_Panel.finalice();


            CheckFirstExecution(txtHiddenSearch);

        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CreateEditComponents() {
        Appointments_Panel.addSpace(15);

        //lbl for Psychologist name
        iLabel lbl_NombrePsicologo = new iLabel("Nombre Psicologo");
        lbl_NombrePsicologo.setForeground(ColorFonts);

        //lbl for Patients UniqueID
        iLabel lbl_CedulaPaciente = new iLabel("Cédula Paciente");
        lbl_CedulaPaciente.setForeground(ColorFonts);

        //lbl for Patients Address
        iLabel lbl_Direccion = new iLabel("Dirección");
        lbl_Direccion.setForeground(ColorFonts);

        //lbl for Appointment Date
        iLabel lbl_FechaCita = new iLabel("Fecha Cita");
        lbl_FechaCita.setForeground(ColorFonts);

        //lbl for Appointment Time
        iLabel lbl_HoraCita = new iLabel("Hora Cita");
        lbl_HoraCita.setForeground(ColorFonts);

        Appointments_Panel.AddObject(lbl_NombrePsicologo, 190, 30, 20);
        Appointments_Panel.AddObject(lbl_CedulaPaciente, 190, 30, 230);
        Appointments_Panel.AddObject(lbl_Direccion, 380, 30, 440);
        Appointments_Panel.AddObject(lbl_FechaCita, 100, 30, 860);
        Appointments_Panel.AddObject(lbl_HoraCita, 190, 30, 1000);

        Appointments_Panel.newLine();
        Appointments_Panel.addSpace(15);

        iTextField txt_NombrePsicologo = new iTextField("", 15);
        txt_NombrePsicologo.setForeground(ColorElementsFonts);
        txt_NombrePsicologo.setText(EAP.getNombrePsicologo());

        iTextField txt_CedulaPaciente = new iTextField("", 15);
        txt_CedulaPaciente.setForeground(ColorElementsFonts);
        txt_CedulaPaciente.setDisabledTextColor(ColorNonEditElementsFonts);
        txt_CedulaPaciente.setEnabled(false);
        txt_CedulaPaciente.setText(EAP.getCedulaPaciente());

        JTextArea txt_Direccion = new JTextArea();
        txt_Direccion.setWrapStyleWord(true);
        txt_Direccion.setLineWrap(true);
        txt_Direccion.setAutoscrolls(true);
        txt_Direccion.setDisabledTextColor(ColorNonEditElementsFonts);
        txt_Direccion.setEnabled(false);
        txt_Direccion.setForeground(ColorElementsFonts);
        txt_Direccion.setText(EAP.getDireccionPaciente());

        iCalendar editCalendar = new iCalendar();
        editCalendar.setForeground(ColorElementsFonts);

        try {
            String TemporaryDate = EAP.getFechaCita().substring(0, 10);
            Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(TemporaryDate);
            editCalendar.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSpinner spinnerEditar = new JSpinner();
        try {
            String TemporaryDate = EAP.getHoraCita();
            Date date;
            date = new SimpleDateFormat("h:mm a").parse(TemporaryDate);
            SpinnerDateModel sm
                    = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
            spinnerEditar.setModel(sm);
            JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerEditar, "h:mm a");
            spinnerEditar.setEditor(de);
        } catch (ParseException ex) {
            Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }

        Appointments_Panel.AddObject(txt_NombrePsicologo, 190, 30, 20);
        Appointments_Panel.AddObject(txt_CedulaPaciente, 190, 30, 230);
        Appointments_Panel.AddObject(txt_Direccion, 380, 40, 440);        
        Appointments_Panel.AddObject(editCalendar, 100, 30, 860);
        Appointments_Panel.AddObject(spinnerEditar, 100, 30, 1000);
        Appointments_Panel.newLine();

        Appointments_Panel.repaint();
    }

    public void CheckFirstExecution(iTextField check) {
        if (check.getText().isEmpty()) {
            Date date = new Date(); //Getting current date from local host
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

    public void ItemEditarActionListener() {

        int selectedRow = tblAppointments.getSelectedRow();

        for (int j = 0; j < tblAppointments.getColumnCount(); j++) {
            tbl_Data.add(tblAppointments.getColumnName(j) + "-" + tblAppointments.getValueAt(selectedRow, j).toString());
        }        
        ArrayList<Object> Values = new ArrayList<>();
        tbl_Data.forEach((cnsmr) -> {
            Values.add(cnsmr.split("-")[1]);  //Adding only values to Values arrayList            
        });
        //Assigning Values to Encapsulated variables.
        EAP.setIdCita((String) Values.get(0));
        EAP.setIdPsicologo((String) Values.get(1));
        EAP.setNombrePsicologo((String) Values.get(2));
        EAP.setIdPaciente((String) Values.get(3));
        EAP.setCedulaPaciente((String) Values.get(4));
        EAP.setNombrePaciente((String) Values.get(5));
        EAP.setDireccionPaciente((String) Values.get(6));        
        EAP.setFechaCita((String) Values.get(7));
        EAP.setHoraCita((String) Values.get(7));
        EAP.setIdSolicitante((String) Values.get(8));

        CreateEditComponents();     //Calls method in charge of creating elements in EditPanel
    }

    public void ItemEliminarActionListener() {
        int selectedRow = tblAppointments.getSelectedRow();
        System.out.println("ID CITA: " + tblAppointments.getValueAt(selectedRow, 0).toString());
        ArrayList<Object> objs = new ArrayList();
        objs.addAll(Arrays.asList(tblAppointments.getValueAt(selectedRow, 0).toString()));
        sql.exec("UPDATE JAW_Citas SET `Deleted` = 1 WHERE IdCita = ?", objs);
    }

    public void SetColumsSizes() {
        //Code to manage columns sizes
        tblAppointments.getColumnModel().getColumn(0).setWidth(0);        //IdCita
        tblAppointments.getColumnModel().getColumn(0).setMinWidth(0);     //IdCita
        tblAppointments.getColumnModel().getColumn(0).setMaxWidth(0);     //IdCita

        tblAppointments.getColumnModel().getColumn(1).setWidth(0);  //IdPsicologo
        tblAppointments.getColumnModel().getColumn(1).setMinWidth(0);  //IdPsicologo
        tblAppointments.getColumnModel().getColumn(1).setMaxWidth(0);  //IdPsicologo

        tblAppointments.getColumnModel().getColumn(2).setPreferredWidth(140); //Nombre Psicologo
        
        tblAppointments.getColumnModel().getColumn(3).setWidth(0);  //IdPaciente
        tblAppointments.getColumnModel().getColumn(3).setMinWidth(0);  //IdPaciente
        tblAppointments.getColumnModel().getColumn(3).setMaxWidth(0);  //IdPaciente               

        tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(140); //Cedula Paciente
        
        tblAppointments.getColumnModel().getColumn(5).setPreferredWidth(200); //Nombre Paciente

        tblAppointments.getColumnModel().getColumn(6).setPreferredWidth(400); //Direccion

        tblAppointments.getColumnModel().getColumn(7).setPreferredWidth(100); //FechaCita
        
        tblAppointments.getColumnModel().getColumn(8).setWidth(0);  //IdSolicitante
        tblAppointments.getColumnModel().getColumn(8).setMinWidth(0);  //IdSolicitante
        tblAppointments.getColumnModel().getColumn(8).setMaxWidth(0);  //IdSolicitante

        tblAppointments.getColumnModel().getColumn(9).setPreferredWidth(40); //Boton Telefonos

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();
        if (menu == ItemEditar) {
            ItemEditarActionListener();
        } else if (menu == ItemEliminar) {
            System.out.println("Entro correctamente en ItemEliminar WUJUU!!");
            ItemEliminarActionListener();
        } else {
            System.out.println("Menu Not Handled");
        }
    }
}
