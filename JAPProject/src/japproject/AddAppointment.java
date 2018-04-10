/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

/**
 *
 * @author Jose
 */
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.JAPProject.sql;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class AddAppointment {

    public iPanel AddAppointments_Panel; //Panel to view, edit and add appointments.
    public JButton btnScheduleAppointment; //Allows the user to schedule a new appointment.
    public iLabel lblSearch; //lbl to indicate the user that a search into the table is available. 
    public iTextField txtSearch; //Filter for the iTable that shows the user information as filtered by the user.
    public iTable tblPatientsForAppointments; //Table to display all appointments, can be filtered as user requests.
    private iLabel lbl_LogoULatina;//Ulatina logo lbl display
    private iLabel lbl_LogoPsicologia;//Ulatina Psychology Dept logo lbl display

    public AddAppointment(iFrame if_) {
        try {
            currentPanel = "AddAppointments_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.   
            AddAppointments_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//Defining iPanel dimensions
            AddAppointments_Panel.setBackground(ColorPanels);//le doy color al panel  

            lbl_LogoULatina = new iLabel("");
            lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

            lbl_LogoPsicologia = new iLabel("");
            lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

            AddAppointments_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
            AddAppointments_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);
            AddAppointments_Panel.newLine();

            lblSearch = new iLabel("Buscar en Tabla");
            lblSearch.setForeground(ColorFonts); //SearchBar for tblPatientsForAppointments
            txtSearch = new iTextField("", 15);            
            AddAppointments_Panel.addSpace(20);
            AddAppointments_Panel.AddObject(lblSearch, 130, 30, 13);
            AddAppointments_Panel.AddObject(txtSearch, 250, 30, 153);
            AddAppointments_Panel.newLine();

            ResultSet rs = sql.SELECT("SELECT * FROM JAW_AgendarCita");

            ArrayList<String> Cols = new ArrayList();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rs.getMetaData().getColumnName(i));
            }

            tblPatientsForAppointments = new iTable(Cols, txtSearch);
            tblPatientsForAppointments.getTableHeader().setReorderingAllowed(false);
            tblPatientsForAppointments.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tblPatientsForAppointments.getTableHeader().setResizingAllowed(false);
            tblPatientsForAppointments.setRowSelectionAllowed(true);
            tblPatientsForAppointments.setBackground(ColorPanels);
            tblPatientsForAppointments.setSize(1100, 200);

            iScrollPane scrollAgendarCitas = new iScrollPane(tblPatientsForAppointments, ColorPanels);
            scrollAgendarCitas.setViewportView(tblPatientsForAppointments);
            SetColumsSizes(tblPatientsForAppointments);

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount()];
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rs.getObject(i);
                        }

                        tblPatientsForAppointments.addrow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }

            AddAppointments_Panel.addSpace(20);
            AddAppointments_Panel.AddObject(scrollAgendarCitas, 1100, 200);
            AddAppointments_Panel.newLine();

            btnScheduleAppointment = new JButton("Agendar Cita");
            btnScheduleAppointment.setToolTipText("Permite Agendar una nueva cita");

            AddAppointments_Panel.addSpace(20);
            AddAppointments_Panel.AddObject(btnScheduleAppointment, 120, 30, 1030);
            AddAppointments_Panel.newLine();

            AddAppointments_Panel.finalice();

        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void SetColumsSizes(iTable tblAgendarCitas) {
        //Code to manage columns sizes
        tblAgendarCitas.getColumnModel().getColumn(0).setPreferredWidth(140);  //Nombre del Paciente

        tblAgendarCitas.getColumnModel().getColumn(1).setWidth(0);    //IdParentesco
        tblAgendarCitas.getColumnModel().getColumn(1).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(1).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(2).setPreferredWidth(180);   //Parentesco con Solicitante

        tblAgendarCitas.getColumnModel().getColumn(3).setWidth(0);    //IdPaciente
        tblAgendarCitas.getColumnModel().getColumn(3).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(3).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(4).setPreferredWidth(140); //Cédula Paciente

        tblAgendarCitas.getColumnModel().getColumn(5).setPreferredWidth(140);  //Fecha de Nacimiento

        tblAgendarCitas.getColumnModel().getColumn(6).setWidth(0);     //IdClasificacionPaciente
        tblAgendarCitas.getColumnModel().getColumn(6).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(6).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(7).setPreferredWidth(150); //Clasificación Paciente`

        tblAgendarCitas.getColumnModel().getColumn(8).setWidth(0);    //IdTipoPaciente
        tblAgendarCitas.getColumnModel().getColumn(8).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(8).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(9).setPreferredWidth(150); //Tipo Paciente

        tblAgendarCitas.getColumnModel().getColumn(10).setPreferredWidth(150); //Dirección

        tblAgendarCitas.getColumnModel().getColumn(11).setPreferredWidth(150); //Teléfono Paciente

        tblAgendarCitas.getColumnModel().getColumn(12).setPreferredWidth(150); //Profesión

        tblAgendarCitas.getColumnModel().getColumn(13).setPreferredWidth(150); //Actividad Laboral

        tblAgendarCitas.getColumnModel().getColumn(14).setPreferredWidth(450);  //Motivo de la Consulta    

        tblAgendarCitas.getColumnModel().getColumn(15).setWidth(0); //IdHorario
        tblAgendarCitas.getColumnModel().getColumn(15).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(15).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(16).setPreferredWidth(150); //Horario

        tblAgendarCitas.getColumnModel().getColumn(17).setPreferredWidth(300); //Detalle Horario

        tblAgendarCitas.getColumnModel().getColumn(18).setWidth(0);     //IdCurso
        tblAgendarCitas.getColumnModel().getColumn(18).setMinWidth(0);
        tblAgendarCitas.getColumnModel().getColumn(18).setMaxWidth(0);

        tblAgendarCitas.getColumnModel().getColumn(19).setPreferredWidth(200); //Curso

        tblAgendarCitas.getColumnModel().getColumn(20).setPreferredWidth(100); //ListaNegra

    }
}
