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
import java.util.Locale;
import javax.swing.JTextArea;

/**
 *
 * @author Jose
 */
public class Appointments implements ActionListener {

    public iPanel Appointments_Panel; //Panel to view, edit and add appointments.
    public iPanel EditAppointments_Panel; //Panel that appears only when edit an appointment is chosen.
    public iLabel lblCalendar; //lbl to indicate the user to select a date.
    public iCalendar calendar; //Calendar to filter main appointments view.
    public iTextField txtHiddenSearch; //Filter for the iTable that shows appointments, is hidden from the user.
    public JButton btnViewAll; //Removes filter from txtHidenSearch.
    public JButton btnScheduleAppointment; //Allows the user to schedule a new appointment.
    public iTable tblAppointments; //Table to display all appointments, can be filtered as user requests.
    public JPopupMenu popup;
    public JMenuItem ItemEditar;
    public JMenuItem ItemEliminar;
    public EditAppointmentData EAP;

    private iLabel lbl_LogoULatina;//Ulatina logo lbl display
    private iLabel lbl_LogoPsicologia;//Ulatina Psychology Dept logo lbl display

    public List<String> tbl_Data = new ArrayList();

    public Appointments(iFrame if_) {
        try {
            currentPanel = "Appointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions. 
            this.EAP = new EditAppointmentData();
            
            Appointments_Panel = new iPanel(0, 70, 100.0f, 50.0f, 0, 0, if_);//Defining iPanel dimensions
            Appointments_Panel.setBackground(ColorPanels);//le doy color al panel  
            
            EditAppointments_Panel = new iPanel(0,520,100.0f,50.0f,0,0,if_);
            EditAppointments_Panel.setBackground(Color.BLACK);//le doy color al panel  

            lbl_LogoULatina = new iLabel("");
            lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

            lbl_LogoPsicologia = new iLabel("");
            lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

            Appointments_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
            Appointments_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);
            Appointments_Panel.newLine();

            lblCalendar = new iLabel("Por favor seleccione una fecha: "); //Lbl Guide
            lblCalendar.setForeground(Color.decode("#330000")); //Calendar
            calendar = new iCalendar();

            txtHiddenSearch = new iTextField("", 0); //Hidden txt that searches in table and updates result for used depending on selected date, this is not shown to the user.           

            CalendarUpdateTable(calendar); //Calling method to update table dynamically                                   

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
            tblAppointments = new iTable(Cols, txtHiddenSearch);
            tblAppointments.getTableHeader().setReorderingAllowed(false);
            tblAppointments.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tblAppointments.getTableHeader().setResizingAllowed(false);
            tblAppointments.setRowSelectionAllowed(true);
            tblAppointments.setBackground(Color.decode("#006738"));
            tblAppointments.setSize(1100, 200);

            popup = new JPopupMenu();
            ItemEditar = new JMenuItem("Editar Cita");
            ItemEditar.addActionListener(this);
            ItemEliminar = new JMenuItem("Eliminar Cita");
            ItemEliminar.addActionListener(this);

            popup.add(ItemEditar);
            popup.add(ItemEliminar);

            tblAppointments.setComponentPopupMenu(popup);
            tblAppointments.addMouseListener(new TableMouseListener(tblAppointments));

            //PopMenu(tblAppointments, if_);//metodo que crea e implementa el popmenu 
            iScrollPane scrollCitas = new iScrollPane(tblAppointments, Color.decode("#006738"));
            scrollCitas.setViewportView(tblAppointments);
            SetColumsSizes();

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rs.getObject(i);
                        }

                        tblAppointments.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }
            Appointments_Panel.addSpace(20);
            Appointments_Panel.AddObject(scrollCitas, 1100, 200);

            Appointments_Panel.newLine();
            Appointments_Panel.finalice();
                                                
            EditAppointments_Panel.finalice();
 
            CheckFirstExecution(txtHiddenSearch);

        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CreateEditComponents()
    {
        EditAppointments_Panel.addSpace(15);
        
        iLabel lbl_NombrePsicologo = new iLabel("Nombre Psicologo");
        lbl_NombrePsicologo.setForeground(Color.WHITE);
        iTextField txt_NombrePsicologo = new iTextField("", 15);    
        txt_NombrePsicologo.setText(EAP.getNombrePsicologo());
        EditAppointments_Panel.AddObject(lbl_NombrePsicologo, 120, 30, 20);
        EditAppointments_Panel.AddObject(txt_NombrePsicologo, 120, 30, 150);
        EditAppointments_Panel.newLine();      
        
        EditAppointments_Panel.addSpace(5);
        
        iLabel lbl_CedulaPaciente = new iLabel("Cédula Paciente");
        lbl_CedulaPaciente.setForeground(Color.WHITE);
        iTextField txt_CedulaPaciente = new iTextField("", 15);
        txt_CedulaPaciente.setEnabled(false);        
        txt_CedulaPaciente.setText(EAP.getCedulaPaciente());
        EditAppointments_Panel.AddObject(lbl_CedulaPaciente, 120, 30, 20);
        EditAppointments_Panel.AddObject(txt_CedulaPaciente, 120, 30, 150);
        EditAppointments_Panel.newLine();
        
        EditAppointments_Panel.addSpace(5);
        
        iLabel lbl_Direccion = new iLabel("Dirección");
        lbl_Direccion.setForeground(Color.WHITE);
        JTextArea txt_Direccion = new JTextArea();
        txt_Direccion.setAutoscrolls(true);
        txt_Direccion.setEnabled(false);
        txt_Direccion.setText(EAP.getDireccionPaciente());
        EditAppointments_Panel.AddObject(lbl_Direccion, 120, 30, 20);
        EditAppointments_Panel.AddObject(txt_Direccion, 120, 60, 150);
        EditAppointments_Panel.newLine();
        
        EditAppointments_Panel.addSpace(5);
        
        iLabel lbl_Telefono = new iLabel("Teléfono");
        lbl_Telefono.setForeground(Color.WHITE);
        iTextField txt_Telefono = new iTextField("", 15);
        txt_Telefono.setEnabled(false);  
        txt_Telefono.setText(EAP.getTelefonoPaciente());
        EditAppointments_Panel.AddObject(lbl_Telefono, 120, 30, 20);
        EditAppointments_Panel.AddObject(txt_Telefono, 120, 30, 150);
        EditAppointments_Panel.newLine();
        
        EditAppointments_Panel.addSpace(5);
        
        iLabel lbl_FechaCita = new iLabel("Fecha Cita");  
        lbl_FechaCita.setForeground(Color.WHITE);
        iCalendar editCalendar = new iCalendar();
        
        
        try {
            String TemporaryDate = EAP.getFechaCita().substring(0, 10);
            System.out.println("TemporaryDate: " + TemporaryDate);
            Date date;              
            date = new SimpleDateFormat("yyyy-MM-dd").parse(TemporaryDate);
            System.out.println("Date: " + date);
            editCalendar.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
        EditAppointments_Panel.AddObject(lbl_FechaCita, 120, 30, 20);
        EditAppointments_Panel.AddObject(editCalendar, 120, 30, 150);
        EditAppointments_Panel.newLine();
        
        EditAppointments_Panel.addSpace(5);
        
        iLabel lbl_HoraCita = new iLabel("Hora Cita");
        lbl_HoraCita.setForeground(Color.WHITE);
        EditAppointments_Panel.AddObject(lbl_HoraCita, 120, 30, 20);
        EditAppointments_Panel.newLine();
        
        EditAppointments_Panel.repaint();
            
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
        System.out.println("tblData: " + tbl_Data.toString());
        ArrayList <Object> Values = new ArrayList<>();
        tbl_Data.forEach((cnsmr) ->{
            Values.add(cnsmr.split("-")[1]);  //Adding only values to Values arrayList            
        });
        System.out.println("Contenido de Values: " + Values);
        //Assigning Values to Encapsulated variables.
        EAP.setIdCita((String) Values.get(0));
        EAP.setNombrePsicologo((String) Values.get(1));
        EAP.setIdPaciente((String) Values.get(2));
        EAP.setCedulaPaciente((String) Values.get(3));
        EAP.setNombrePaciente((String) Values.get(4));
        EAP.setDireccionPaciente((String) Values.get(5));
        EAP.setTelefonoPaciente((String) Values.get(6));
        EAP.setFechaCita((String) Values.get(7));
        
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
        tblAppointments.getColumnModel().getColumn(0).setWidth(0);
        tblAppointments.getColumnModel().getColumn(0).setMinWidth(0);
        tblAppointments.getColumnModel().getColumn(0).setMaxWidth(0);

        tblAppointments.getColumnModel().getColumn(1).setPreferredWidth(140);

        tblAppointments.getColumnModel().getColumn(2).setWidth(0);
        tblAppointments.getColumnModel().getColumn(2).setMinWidth(0);
        tblAppointments.getColumnModel().getColumn(2).setMaxWidth(0);

        tblAppointments.getColumnModel().getColumn(3).setPreferredWidth(140);

        tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(200);

        tblAppointments.getColumnModel().getColumn(5).setPreferredWidth(400);

        tblAppointments.getColumnModel().getColumn(6).setPreferredWidth(100);

        tblAppointments.getColumnModel().getColumn(7).setPreferredWidth(150);

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
