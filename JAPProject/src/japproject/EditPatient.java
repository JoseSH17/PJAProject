/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static japproject.PatientView.tbl_Data;
import iComponents.iButton;
import iComponents.iCalendar;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import static japproject.HomePanel.ColorPanels;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import static japproject.HomePanel.currentPanel;
import static japproject.PatientView.PatientView_panel;
import static japproject.JAPProject.sql;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author anfer
 */
public class EditPatient {

    //cbo`s para Paciente
    private JComboBox cbo_TipoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_HorarioPaciente;//son de seleccion por base de datos
    private JComboBox cbo_ClasificacionPaciente;//son de seleccion por base de datos
    private JComboBox cbo_CursoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_Parentesco;//son de seleccion por base de datos
    private JComboBox cbo_IsNonGrato;//son de seleccion por base de datos//((((lista negra)))))
    //fin cbo`s para Paciente

    //Controles swing para NewPatient_Panel
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnEditarAction;//Boton para el editar
    //FIN de Controles swing para NewPatient_Panel

    //Controles Swing para Solicitante
    private iLabel lbl_TituloSolicitante;//Lbl para el titulo de Solicitante
    private iLabel lbl_CedulaSolicitante;//Lbl para la cedula del solicitante
    private iTextField txt_CedulaSolicitante;//TextField para la cedula del solicitante
    private iLabel lbl_NombreSolicitante;//Lbl para el nombre del Solicitante
    private iTextField txt_NombreSolicitante;//TextField para el nombre del solicitante
    private iLabel lbl_DireccionSolicitante;//Lbl para la direccion del Solicitante
    private iTextField txt_DireccionSolicitante;//TextField para la direccion del solicitante(cambiar por JTextArea)
    private iLabel lbl_TelefonoSolicitante;//Lbl para el telefono del Solicitante
    private iTextField txt_TelefonoSolicitante;//TextField para el telefono del solicitante
    private iLabel lbl_ProfesionSolicitante;//Lbl para la profesion del Solicitante
    private iTextField txt_ProfesionSolicitante;//TextField para la profesion del solicitante
    private iLabel lbl_ActividadLaboralSolicitante;//Lbl para la Actividad Laboral del Solicitante
    private iTextField txt_ActividadLaboralSolicitante;//TextField para la Actividad Laboral del solicitante
    private iLabel lbl_MotivoConsultaSolicitante;//Lbl para el Motivo Consulta del Solicitante
    private iTextField txt_MotivoConsultaSolicitante;//TextField para el Motivo Consulta de  Solicitante(cambiar por JTextArea)
    private iLabel lbl_FechaReporte;//Lbl para la FechaReporte del Solicitante
    private iCalendar txt_FechaReporte;

    //FIN de Controles Swing para Solicitante
    //Controles swing para paciente
    private iLabel lbl_TituloPaciente;//Lbl para el Titulo del Paciente
    private iLabel lbl_CedulaPaciente;//Lbl para la Cedula del Solicitante
    private iTextField txt_CedulaPaciente;//TextField para cedula del Solicitante en la parte de paciente(hay que hacerle un setEditable(false))
    private iLabel lbl_NombrePaciente;//Lbl para la Cedula del Paciente
    private iTextField txt_NombrePaciente;//TextField para cedula del Paciente
    private iLabel lbl_FechaNacimientoPaciente;//Lbl para la Fecha de Nacimiento del Paciente
    private iCalendar txt_FechaNacimientoPaciente;//TextField para la fecha de Nacimiento del Paciente(cambiar por JDateChooser)
    private iLabel lbl_DireccionPaciente;//Lbl para la Direccion del Paciente
    private iTextField txt_DireccionPaciente;//TextField para la Direccion del Paciente
    private iLabel lbl_TelefonoPaciente;//Lbl para la el Telefono del Paciente
    private iTextField txt_TelefonoPaciente;//TextField para el Telefono del Paciente
    private iLabel lbl_ProfesionPaciente;//Lbl para la Profesion del Paciente
    private iTextField txt_ProfesionPaciente;//TextField para la Profesion del Paciente
    private iLabel lbl_ActividadLaboralPaciente;//Lbl para la Actividad Laboral del Paciente
    private iTextField txt_ActividadLaboralPaciente;//TextField para la Actividad Laboral del Paciente
    private iLabel lbl_MotivoConsultaPaciente;//Lbl para la El Motivo Consulta del Paciente
    private iTextField txt_MotivoConsultaPaciente;//TextField para el Motivo Consulta del Paciente(cambiar por JTextArea)
    private iLabel lbl_ParentescoPaciente;//Lbl para el Parentesco del Paciente
    private iLabel lbl_ClasificacionPaciente;//Lbl para la Clasificacion del Paciente
    private iLabel lbl_CursoPaciente;//Lbl para la el Curso del Paciente
    private iLabel lbl_HorarioPaciente;//Lbl para la el Horario del Paciente
    private iLabel lbl_DetalleHorarioPaciente;//Lbl para el Detalle Horario del Paciente
    private iTextField txt_DetalleHorarioPaciente;//TextField para el Detalle Horario del Paciente
    private iLabel lbl_TipoPaciente;//Lbl para el Tipo de Paciente
    private iLabel lbl_IsNonGrato;//Lbl para el Tipo de Lista Negra  

    //FIN de Controles Swing para Paciente
    //
    //
    public int IdPaciente;
    public int IdSolicitante;
    public static iPanel EditPatient_Panel;
    iTextField SearchBar_txt;// para actualizar la tabla

    public EditPatient(iFrame if_) {
        currentPanel = "EditPatient_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.  
        EditPatient_Panel = new iPanel(0, 70, 100f, 100.0f, 0, 0, if_);
        EditPatient_Panel.setBackground(ColorPanels);
        AddComponentes(if_);
    }

//Metodos para cargar los cbo`s de Paciente
    private JComboBox cbo_CargarTipoPaciente() {

        ResultSet rs = sql.SELECT("SELECT `NombreTipoPaciente` FROM JAW_TipoPaciente");

        try {
            while (rs.next()) {
                cbo_TipoPaciente.addItem(rs.getObject("NombreTipoPaciente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_TipoPaciente;
    }//Fin del cbo_CargarTipoPaciente

    private JComboBox cbo_CargarHorario() {

        ResultSet rs = sql.SELECT("SELECT `NombreHorario` FROM JAW_Horario");

        try {
            while (rs.next()) {
                cbo_HorarioPaciente.addItem(rs.getObject("NombreHorario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_HorarioPaciente;
    }//Fin del cbo_CargarHorario

    private JComboBox cbo_CargarClasificacionPaciente() {

        ResultSet rs = sql.SELECT("SELECT `NombreClasificacion` FROM JAW_ClasificacionPaciente");

        try {
            while (rs.next()) {
                cbo_ClasificacionPaciente.addItem(rs.getObject("NombreClasificacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_ClasificacionPaciente;

    }//Fin del cbo_CargarClasificacionPaciente

    private JComboBox cbo_CargarCurso() {

        ResultSet rs = sql.SELECT("SELECT `NombreCurso` FROM JAW_Curso");

        try {
            while (rs.next()) {
                cbo_CursoPaciente.addItem(rs.getObject("NombreCurso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_CursoPaciente;
    }//Fin del cbo_CargarCurso

    private JComboBox cbo_CargarParentesco() {

        ResultSet rs = sql.SELECT("SELECT `DescripcionParentesco` FROM JAW_Parentesco");

        try {
            while (rs.next()) {
                cbo_Parentesco.addItem(rs.getObject("DescripcionParentesco"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbo_Parentesco;
    }//Fin del cbo_CargarCurso

    private JComboBox cbo_CargarIsNonGrato() {
        String[] fillCbo = {"Si", "No"};//para matar gente
        cbo_IsNonGrato = new JComboBox(fillCbo);//para quemar los cuerpos
        return cbo_IsNonGrato;
    }

    public iScrollPane llenarTable(List<String> info) {
        ArrayList<String> cols = new ArrayList();

        ArrayList<String> rows = new ArrayList();
        info.forEach((jKeyPair) -> {
            cols.add(jKeyPair.split("-")[0]);
            rows.add(jKeyPair.split("-")[1]);
        });
        SearchBar_txt = new iTextField("", 0);
        iTable EditarRegistrosTable = new iTable(cols, SearchBar_txt);
        EditarRegistrosTable.setSize(800, 90);
        EditarRegistrosTable.getTableHeader().setReorderingAllowed(false);
        EditarRegistrosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        EditarRegistrosTable.getTableHeader().setResizingAllowed(false);
        EditarRegistrosTable.setRowSelectionAllowed(true);
        SetColumsSizes(EditarRegistrosTable);
        
        EditarRegistrosTable.addrow(rows.toArray());
        SearchBar_txt.setText("");
        iScrollPane ScrollPane = new iScrollPane(EditarRegistrosTable, null);
        //setear valores textfiles solicitante
        IdSolicitante = Integer.parseInt(EditarRegistrosTable.getValueAt(0, 10).toString());
        txt_CedulaSolicitante.setText(EditarRegistrosTable.getValueAt(0, 1).toString());
        txt_NombreSolicitante.setText(EditarRegistrosTable.getValueAt(0, 2).toString());
        txt_DireccionSolicitante.setText(EditarRegistrosTable.getValueAt(0, 3).toString());
        txt_TelefonoSolicitante.setText(EditarRegistrosTable.getValueAt(0, 4).toString());
        txt_ProfesionSolicitante.setText(EditarRegistrosTable.getValueAt(0, 5).toString());
        txt_ActividadLaboralSolicitante.setText(EditarRegistrosTable.getValueAt(0, 6).toString());
        txt_MotivoConsultaSolicitante.setText(EditarRegistrosTable.getValueAt(0, 7).toString());

        String TemporalReportDate = EditarRegistrosTable.getValueAt(0, 28).toString().replace("/", "-");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(TemporalReportDate);
            txt_FechaReporte.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //setear valores textfiles paciente
        IdPaciente = Integer.parseInt(EditarRegistrosTable.getValueAt(0, 0).toString());

        txt_CedulaPaciente.setText(EditarRegistrosTable.getValueAt(0, 11).toString());
        txt_NombrePaciente.setText(EditarRegistrosTable.getValueAt(0, 12).toString());

        String TemporalPatientBirthDate = EditarRegistrosTable.getValueAt(0, 13).toString().replace("/", "-");

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(TemporalPatientBirthDate);
            txt_FechaNacimientoPaciente.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(EditPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        txt_DireccionPaciente.setText(EditarRegistrosTable.getValueAt(0, 18).toString());
        txt_TelefonoPaciente.setText(EditarRegistrosTable.getValueAt(0, 19).toString());
        txt_ProfesionPaciente.setText(EditarRegistrosTable.getValueAt(0, 20).toString());
        txt_ActividadLaboralPaciente.setText(EditarRegistrosTable.getValueAt(0, 21).toString());
        txt_MotivoConsultaPaciente.setText(EditarRegistrosTable.getValueAt(0, 22).toString());
        txt_DetalleHorarioPaciente.setText(EditarRegistrosTable.getValueAt(0, 25).toString());
        //set cbo
        cbo_Parentesco.setSelectedIndex(Integer.parseInt(EditarRegistrosTable.getValueAt(0, 8).toString()) - 1);
        cbo_ClasificacionPaciente.setSelectedIndex(Integer.parseInt(EditarRegistrosTable.getValueAt(0, 14).toString()) - 1);
        cbo_TipoPaciente.setSelectedIndex(Integer.parseInt(EditarRegistrosTable.getValueAt(0, 16).toString()) - 1);
        cbo_HorarioPaciente.setSelectedIndex(Integer.parseInt(EditarRegistrosTable.getValueAt(0, 23).toString()) - 1);
        cbo_CursoPaciente.setSelectedIndex(Integer.parseInt(EditarRegistrosTable.getValueAt(0, 26).toString()) - 1);
        cbo_IsNonGrato.setSelectedItem(EditarRegistrosTable.getValueAt(0, 29).toString());

        //
        return ScrollPane;
    }

    private void btnEditarAction_MouseClicked(iFrame if_) {
        DateFormat currentDateFormatted = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Object> obj1 = new ArrayList();//array para guardar data
        obj1.addAll(Arrays.asList(
                txt_CedulaSolicitante.getText(),
                txt_NombreSolicitante.getText(),
                txt_DireccionSolicitante.getText(),
                txt_TelefonoSolicitante.getText(),
                txt_ProfesionSolicitante.getText(),
                txt_ActividadLaboralSolicitante.getText(),
                txt_MotivoConsultaSolicitante.getText(),
                currentDateFormatted.format(txt_FechaReporte.getDate()),
                IdSolicitante
        ));

        String query1 = "UPDATE JAW_Solicitante SET `Cedula`=?, `Nombre`=?, `Direccion`=?, `Telefono`=?, `Profesion`=?, `ActividadLaboral`=?, `MotivoConsulta`=?, `FechaReporte`=? WHERE `IdSolicitante`=?";

        Boolean exq1 = sql.exec(query1, obj1);

        ArrayList<Object> obj2 = new ArrayList();//array para guardar data
        obj2.addAll(Arrays.asList(
                txt_CedulaPaciente.getText(),
                txt_NombrePaciente.getText(),
                currentDateFormatted.format(txt_FechaNacimientoPaciente.getDate()),
                txt_DireccionPaciente.getText(),
                txt_TelefonoPaciente.getText(),
                txt_ProfesionPaciente.getText(),
                txt_ActividadLaboralPaciente.getText(),
                txt_MotivoConsultaPaciente.getText(),
                cbo_Parentesco.getSelectedIndex() + 1,
                cbo_ClasificacionPaciente.getSelectedIndex() + 1,
                cbo_CursoPaciente.getSelectedIndex() + 1,
                cbo_HorarioPaciente.getSelectedIndex() + 1,
                txt_DetalleHorarioPaciente.getText(),
                cbo_TipoPaciente.getSelectedIndex() + 1,
                cbo_IsNonGrato.getSelectedItem(),
                IdPaciente
        ));
        String query2 = "UPDATE  JAW_Paciente SET `Cedula`=?, `Nombre`=?, `FechaNacimiento`=?, `Direccion`=?, `Telefono`=?, `Profesion`=?, `ActividadLaboral`=?, `MotivoConsulta`=?, `IdParentesco`=?, `IdClasificacionPaciente`=?, `IdCurso`=?, `IdHorario`=?, `DetalleHorario`=?, `IdTipoPaciente`=?, `IsNonGrato`=? WHERE `IdPaciente`=?";

        Boolean exq2 = sql.exec(query2, obj2);
        if (exq2 && exq1) {
            SearchBar_txt.setText("");
            JOptionPane.showMessageDialog(null, "EDITADO EXITOSAMENTE!", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            EditPatient_Panel.dispose();
            EditPatient_Panel.setVisible(false);
            PatientView_panel.setVisible(true);
            currentPanel = "PatientView_panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.        
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL EDITAR LOS DATOS", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initSoliComponents() {

        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        btnEditarAction = new iButton("Editar", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnEditarAction.setText("Editar");

        lbl_TituloSolicitante = new iLabel("SOLICITANTE");
        lbl_TituloSolicitante.setForeground(Color.GRAY.brighter());

        lbl_CedulaSolicitante = new iLabel("Cédula".toUpperCase());
        lbl_CedulaSolicitante.setForeground(Color.GRAY.brighter());
        txt_CedulaSolicitante = new iTextField("", 15);

        lbl_NombreSolicitante = new iLabel("Nombre".toUpperCase());
        lbl_NombreSolicitante.setForeground(Color.GRAY.brighter());
        txt_NombreSolicitante = new iTextField("", 15);

        lbl_DireccionSolicitante = new iLabel("Dirección".toUpperCase());
        lbl_DireccionSolicitante.setForeground(Color.GRAY.brighter());
        txt_DireccionSolicitante = new iTextField("", 15);

        lbl_TelefonoSolicitante = new iLabel("Teléfono".toUpperCase());
        lbl_TelefonoSolicitante.setForeground(Color.GRAY.brighter());
        txt_TelefonoSolicitante = new iTextField("", 15);

        lbl_ProfesionSolicitante = new iLabel("Profesión".toUpperCase());
        lbl_ProfesionSolicitante.setForeground(Color.GRAY.brighter());
        txt_ProfesionSolicitante = new iTextField("", 15);

        lbl_ActividadLaboralSolicitante = new iLabel("Actividad Laboral".toUpperCase());
        lbl_ActividadLaboralSolicitante.setForeground(Color.GRAY.brighter());
        txt_ActividadLaboralSolicitante = new iTextField("", 15);

        lbl_MotivoConsultaSolicitante = new iLabel("Motivo Consulta".toUpperCase());
        lbl_MotivoConsultaSolicitante.setForeground(Color.GRAY.brighter());
        txt_MotivoConsultaSolicitante = new iTextField("", 15);

        lbl_FechaReporte = new iLabel("Fecha Reporte".toUpperCase());
        lbl_FechaReporte.setForeground(Color.GRAY.brighter());
        txt_FechaReporte = new iCalendar();
        Date date = new Date(); //Getting current date from local host
        DateFormat currentDateFormatted = new SimpleDateFormat("yyyy/MM/dd"); //Formatting current date for initial search and table refresh            
        txt_FechaReporte.setText(currentDateFormatted.format(txt_FechaReporte.getDate()));

    }

    public void initPaciComponents() {

        lbl_TituloPaciente = new iLabel("PACIENTE");

        lbl_TituloPaciente.setForeground(Color.GRAY.brighter());

        lbl_CedulaPaciente = new iLabel("Cédula Paciente".toUpperCase());
        lbl_CedulaPaciente.setForeground(Color.GRAY.brighter());
        txt_CedulaPaciente = new iTextField("", 15);

        lbl_NombrePaciente = new iLabel("Nombre Paciente".toUpperCase());
        lbl_NombrePaciente.setForeground(Color.GRAY.brighter());
        txt_NombrePaciente = new iTextField("", 15);

        lbl_FechaNacimientoPaciente = new iLabel("Fecha de Nacimiento Paciente".toUpperCase());
        lbl_FechaNacimientoPaciente.setForeground(Color.GRAY.brighter());
        txt_FechaNacimientoPaciente = new iCalendar();
        Date date = new Date(); //Getting current date from local host
        DateFormat currentDateFormatted = new SimpleDateFormat("yyyy/MM/dd"); //Formatting current date for initial search and table refresh            
        txt_FechaNacimientoPaciente.setText(currentDateFormatted.format(txt_FechaNacimientoPaciente.getDate()));

        lbl_DireccionPaciente = new iLabel("Dirección Paciente".toUpperCase());
        lbl_DireccionPaciente.setForeground(Color.GRAY.brighter());
        txt_DireccionPaciente = new iTextField("", 15);

        lbl_TelefonoPaciente = new iLabel("Teléfono Paciente".toUpperCase());
        lbl_TelefonoPaciente.setForeground(Color.GRAY.brighter());
        txt_TelefonoPaciente = new iTextField("", 15);

        lbl_ProfesionPaciente = new iLabel("Profesión Paciente".toUpperCase());
        lbl_ProfesionPaciente.setForeground(Color.GRAY.brighter());
        txt_ProfesionPaciente = new iTextField("", 15);

        lbl_ActividadLaboralPaciente = new iLabel("Actividad Laboral Paciente".toUpperCase());
        lbl_ActividadLaboralPaciente.setForeground(Color.GRAY.brighter());
        txt_ActividadLaboralPaciente = new iTextField("", 15);

        lbl_MotivoConsultaPaciente = new iLabel("Motivo Consulta Paciente".toUpperCase());
        lbl_MotivoConsultaPaciente.setForeground(Color.GRAY.brighter());
        txt_MotivoConsultaPaciente = new iTextField("", 15);

        lbl_ParentescoPaciente = new iLabel("Parentesco Paciente".toUpperCase());
        lbl_ParentescoPaciente.setForeground(Color.GRAY.brighter());
        cbo_Parentesco = new JComboBox();//son de seleccion por base de datos

        lbl_ClasificacionPaciente = new iLabel("Clasificación Paciente".toUpperCase());
        lbl_ClasificacionPaciente.setForeground(Color.GRAY.brighter());
        cbo_ClasificacionPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_CursoPaciente = new iLabel("Curso".toUpperCase());
        lbl_CursoPaciente.setForeground(Color.GRAY.brighter());
        cbo_CursoPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_HorarioPaciente = new iLabel("Horario Paciente".toUpperCase());
        lbl_HorarioPaciente.setForeground(Color.GRAY.brighter());
        cbo_HorarioPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_DetalleHorarioPaciente = new iLabel("Detalle Horario Paciente".toUpperCase());
        lbl_DetalleHorarioPaciente.setForeground(Color.GRAY.brighter());
        txt_DetalleHorarioPaciente = new iTextField("", 15);

        lbl_TipoPaciente = new iLabel("Tipo Paciente Paciente".toUpperCase());
        lbl_TipoPaciente.setForeground(Color.GRAY.brighter());
        cbo_TipoPaciente = new JComboBox();//son de seleccion por base de datos
        lbl_IsNonGrato = new iLabel("Lista Negra".toUpperCase());
        lbl_IsNonGrato.setForeground(Color.GRAY.brighter());
        cbo_IsNonGrato = new JComboBox();//son los de seleccion por paciente en lista negra

    }

    public void AddComponentes(iFrame if_) {
        initPaciComponents();
        initSoliComponents();
        cbo_CargarParentesco();
        cbo_CargarClasificacionPaciente();
        cbo_CargarCurso();
        cbo_CargarHorario();
        cbo_CargarTipoPaciente();
        cbo_CargarIsNonGrato();

        EditPatient_Panel.AddObject(lbl_LogoULatina, 415, 120, 50);
        EditPatient_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);
        EditPatient_Panel.newLine();

        iLabel espacioLabel2 = new iLabel("");
        EditPatient_Panel.AddObject(espacioLabel2, 415, 15, 2);
        EditPatient_Panel.newLine();

        EditPatient_Panel.AddSingleObject(llenarTable(tbl_Data), 95f, 90, 0);
        EditPatient_Panel.newLine();

        iLabel espacioLabel = new iLabel("");
        EditPatient_Panel.AddObject(espacioLabel, 415, 15, 2);
        EditPatient_Panel.newLine();

        EditPatient_Panel.AddObject(lbl_TituloSolicitante, 415, 30, 195);
        EditPatient_Panel.AddObject(lbl_TituloPaciente, 415, 30, 630);//agrego el titulo para poner verlo con
        lbl_TituloPaciente.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        EditPatient_Panel.newLine();

        EditPatient_Panel.AddObject(lbl_CedulaSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_CedulaPaciente, 146, 30, 600);//para paciente
        lbl_NombrePaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_CedulaSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_CedulaPaciente, 230, 30, 800);//para paciente
        txt_NombrePaciente.setVisible(true);//para paciente
        txt_NombrePaciente.setEnabled(true);
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_NombreSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_NombrePaciente, 146, 30, 600);//para paciente
        lbl_CedulaPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_NombreSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_NombrePaciente, 230, 30, 800);//para paciente
        txt_CedulaPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_DireccionSolicitante, 146, 30, 50);//para el paciente
        EditPatient_Panel.AddObject(lbl_FechaNacimientoPaciente, 230, 30, 600);
        lbl_FechaNacimientoPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_DireccionSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_FechaNacimientoPaciente, 230, 30, 800);//para paciente
        txt_FechaNacimientoPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_TelefonoSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_DireccionPaciente, 230, 30, 600);//para paciente
        lbl_DireccionPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_TelefonoSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_DireccionPaciente, 230, 30, 800);//para paciente
        txt_DireccionPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_ProfesionSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_TelefonoPaciente, 230, 30, 600);//para paciente
        lbl_TelefonoPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_ProfesionSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_TelefonoPaciente, 230, 30, 800);//para paciente
        txt_TelefonoPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_ActividadLaboralSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_ProfesionPaciente, 230, 30, 600);//para paciente
        lbl_ProfesionPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_ActividadLaboralSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_ProfesionPaciente, 230, 30, 800);//para paciente
        txt_ProfesionPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_MotivoConsultaSolicitante, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_ActividadLaboralPaciente, 230, 30, 600);//para paciente
        lbl_ActividadLaboralPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_MotivoConsultaSolicitante, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_ActividadLaboralPaciente, 230, 30, 800);//para paciente
        txt_ActividadLaboralPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_FechaReporte, 146, 30, 50);
        EditPatient_Panel.AddObject(lbl_MotivoConsultaPaciente, 230, 30, 600);//para paciente
        lbl_MotivoConsultaPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_FechaReporte, 230, 30, 195);
        EditPatient_Panel.AddObject(txt_MotivoConsultaPaciente, 230, 30, 800);//para paciente
        txt_MotivoConsultaPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_ParentescoPaciente, 230, 30, 600);
        lbl_ParentescoPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(cbo_Parentesco, 230, 30, 800);
        cbo_Parentesco.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_ClasificacionPaciente, 230, 30, 600);
        lbl_ClasificacionPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(cbo_ClasificacionPaciente, 230, 30, 800);
        cbo_ClasificacionPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_CursoPaciente, 230, 30, 600);
        lbl_CursoPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(cbo_CursoPaciente, 230, 30, 800);
        cbo_CursoPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_HorarioPaciente, 230, 30, 600);
        lbl_HorarioPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(cbo_HorarioPaciente, 230, 30, 800);
        cbo_HorarioPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_DetalleHorarioPaciente, 230, 30, 600);
        lbl_DetalleHorarioPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(txt_DetalleHorarioPaciente, 230, 30, 800);
        txt_DetalleHorarioPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(lbl_TipoPaciente, 230, 30, 600);
        lbl_TipoPaciente.setVisible(true);//para paciente
        EditPatient_Panel.AddObject(cbo_TipoPaciente, 230, 30, 800);
        cbo_TipoPaciente.setVisible(true);//para paciente
        
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);
        
        EditPatient_Panel.AddObject(btnEditarAction, 175, 30, 50);
        EditPatient_Panel.AddObject(lbl_IsNonGrato, 230, 30, 600);
        EditPatient_Panel.AddObject(cbo_IsNonGrato, 230, 30, 800);
        EditPatient_Panel.newLine();
        EditPatient_Panel.addSpace(5);

        btnEditarAction.addActionListener((a) -> {
            btnEditarAction_MouseClicked(if_);
        });
//

        EditPatient_Panel.finalice();

    }

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
