/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.LEFT;
import static iComponents.ComponentInterfaz.RIGHT;
import static japproject.PatientView.tbl_Data;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTextField;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import static javax.swing.SwingConstants.BOTTOM;

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
    //fin cbo`s para Paciente

    //Controles swing para RePanel
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnEditarAction;//Boton para el editar
    //FIN de Controles swing para RePanel

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
    private iTextField txt_FechaReporte;//TextField para FechaReporte del Solicitante(cambiar por JDateChooser)
    private iLabel lbl_NOTA;//Lbl para la NOTA(si preciona el CheckBox) del Solicitante

    //FIN de Controles Swing para Solicitante
    //Controles swing para paciente
    private iLabel lbl_TituloPaciente;//Lbl para el Titulo del Paciente
    private iLabel lbl_CedulaPaciente;//Lbl para la Cedula del Solicitante
    private iTextField txt_CedulaPaciente;//TextField para cedula del Solicitante en la parte de paciente(hay que hacerle un setEditable(false))
    private iLabel lbl_CedulaSolicitantePaciente;//Lbl para la Cedula del Paciente
    private iTextField txt_CedulaSolicitantePaciente;//TextField para cedula del Paciente
    private iLabel lbl_NombrePaciente;//Lbl para el Nombre del Paciente
    private iTextField txt_NombrePaciente;//TextField para el Nombre del Paciente
    private iLabel lbl_FechaNacimientoPaciente;//Lbl para la Fecha de Nacimiento del Paciente
    private iTextField txt_FechaNacimientoPaciente;//TextField para la fecha de Nacimiento del Paciente(cambiar por JDateChooser)
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
    //FIN de Controles Swing para Paciente

    public static iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");
    public static iPanel PatientView_panelLeft;

    public EditPatient(iFrame if_) {
        PatientView_panelLeft = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);
        PatientView_panelLeft.setBackground(Color.BLUE);
        cargarCombobox();
        initPaciComponents();
        initSoliComponents();
        AddComponentes(if_);
        

       
    }

    public void tblUpdater(List<String> info, String tbl_Name) {
        /*Arrays to Handle Data*/
        ArrayList<JLabel> dynamicLabels = new ArrayList();
        ArrayList<iTextField> dynamicTextFields = new ArrayList();

        System.out.println("Gathered Data " + info);

        ArrayList<String> cols = new ArrayList();
        ArrayList<String> rows = new ArrayList();
        info.forEach((jKeyPair) -> {
            cols.add(jKeyPair.split("-")[0]);
            rows.add(jKeyPair.split("-")[1]);
        });
        System.out.println("Cols " + cols);
        System.out.println("Rows " + rows);

        JLabel lbl_info3 = new JLabel();
        PatientView_panelLeft.AddObject(lbl_info3, 280, 30, iPanel.LEFT);
        PatientView_panelLeft.newLine();

        for (int i = 0; i < cols.size(); i++) {
            JLabel lbl_Columns = new JLabel(cols.get(i).toString());
            PatientView_panelLeft.AddObject(lbl_Columns, 100, 30);
            dynamicLabels.add(lbl_Columns);
        }
        PatientView_panelLeft.newLine();
        PatientView_panelLeft.repaint();

        for (int i = 0; i < rows.size(); i++) {
            iTextField txt_Rows = new iTextField(rows.get(i).toString(), 2);
            PatientView_panelLeft.AddObject(txt_Rows, 100, 30);
            dynamicTextFields.add(txt_Rows);
        }
        PatientView_panelLeft.newLine();
        PatientView_panelLeft.repaint();

        JLabel lbl_ident = new JLabel();
        PatientView_panelLeft.AddObject(lbl_ident, 530, 10);
        PatientView_panelLeft.newLine();

        /*Adding Final Control Button*/
        JLabel lbl_ident2 = new JLabel();
        iButton btn_Done = new iButton("", 3, Color.BLACK, Color.WHITE);
        PatientView_panelLeft.AddObject(lbl_ident2, 380, 30);
        PatientView_panelLeft.AddObject(btn_Done, 100, 30);
        PatientView_panelLeft.newLine();
        PatientView_panelLeft.repaint();

        btn_Done.addActionListener((al) -> {
            String sqlCommand = "";
            ArrayList<Object> objs = new ArrayList<>();
            for (int j = 0; j < cols.size(); j++) {
                if (j != cols.size() - 1) {
                    sqlCommand += " `" + dynamicLabels.get(j).getText() + "` =?, ";
                    objs.add(dynamicTextFields.get(j).getText());
                } else {
                    sqlCommand += " `" + dynamicLabels.get(j).getText() + "` =? ";
                    objs.add(dynamicTextFields.get(j).getText());
                }
            }
            System.out.println("Checking Query" + sqlCommand);
            /*Including*/

            System.out.println("Final Query: " + "UPDATE TABLE `" + tbl_Name + "` SET " + sqlCommand + " WHERE " + dynamicLabels.get(0).getText() + " = " + dynamicTextFields.get(0).getText());

            System.out.println("Objs: " + objs.toString());
            Boolean exq = sql.exec("UPDATE `" + tbl_Name + "` SET " + sqlCommand + " WHERE `" + dynamicLabels.get(0).getText() + "` = " + rows.get(0), objs);//                                    
            if (exq) {
                JOptionPane.showMessageDialog(null, "Table Updated", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error ocurred while attempting to update the table", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

//Metodos para cargar los cbo`s de Paciente
    private void cargarCombobox() {
        try {
            ResultSet rs = sql.SELECT("SELECT `NombreTipoPaciente` FROM JAW_TipoPaciente");

            while (rs.next()) {
                cbo_TipoPaciente.addItem(rs.getObject("NombreTipoPaciente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Fin del cbo_CargarTipoPaciente
        try {
            ResultSet ra = sql.SELECT("SELECT `NombreHorario` FROM JAW_Horario");

            while (ra.next()) {
                cbo_HorarioPaciente.addItem(ra.getObject("NombreHorario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Fin del cbo_CargarHorario
        ResultSet rr = sql.SELECT("SELECT `NombreClasificacion` FROM JAW_ClasificacionPaciente");

        try {
            while (rr.next()) {
                cbo_ClasificacionPaciente.addItem(rr.getObject("NombreClasificacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Fin del cbo_CargarClasificacionPaciente
        try {
            ResultSet rt = sql.SELECT("SELECT `NombreCurso` FROM JAW_Curso");

            while (rt.next()) {
                cbo_CursoPaciente.addItem(rt.getObject("NombreCurso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Fin del cbo_CargarCurso
        try {
            ResultSet rf = sql.SELECT("SELECT `DescripcionParentesco` FROM JAW_Parentesco");

            while (rf.next()) {
                cbo_Parentesco.addItem(rf.getObject("DescripcionParentesco"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //fin de metodos para cargar cbo`s de Paciente
    }

    public void initSoliComponents() {
        btnEditarAction = new iButton("REGISTRAR", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnEditarAction.setText("REGISTRAR");

        lbl_TituloSolicitante = new iLabel("SOLICITANTE");
//        lbl_TituloSolicitante.setText("SOLICITANTE");
        lbl_TituloSolicitante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TituloSolicitante.setForeground(Color.GRAY.brighter());

        lbl_CedulaSolicitante = new iLabel("Cédula".toUpperCase());
//        lbl_CedulaSolicitante.setText("Cédula".toUpperCase());
        lbl_CedulaSolicitante.setForeground(Color.GRAY.brighter());
        txt_CedulaSolicitante = new iTextField("", 3);
//        txt_CedulaSolicitante.setBackground(Color.BLACK);
        txt_CedulaSolicitante.setForeground(Color.red);

        lbl_NombreSolicitante = new iLabel("Nombre".toUpperCase());
//        lbl_NombreSolicitante.setText("Nombre".toUpperCase());
        lbl_NombreSolicitante.setForeground(Color.GRAY.brighter());
        txt_NombreSolicitante = new iTextField("", 3);

        lbl_DireccionSolicitante = new iLabel("Dirección".toUpperCase());
//        lbl_DireccionSolicitante.setText("Dirección".toUpperCase());
        lbl_DireccionSolicitante.setForeground(Color.GRAY.brighter());
        txt_DireccionSolicitante = new iTextField("", 3);

        lbl_TelefonoSolicitante = new iLabel("Teléfono".toUpperCase());
//        lbl_TelefonoSolicitante.setText("Teléfono".toUpperCase());
        lbl_TelefonoSolicitante.setForeground(Color.GRAY.brighter());
        txt_TelefonoSolicitante = new iTextField("", 3);

        lbl_ProfesionSolicitante = new iLabel("Profesión".toUpperCase());
//        lbl_ProfesionSolicitante.setText("Profesión".toUpperCase());
        lbl_ProfesionSolicitante.setForeground(Color.GRAY.brighter());
        txt_ProfesionSolicitante = new iTextField("", 3);

        lbl_ActividadLaboralSolicitante = new iLabel("Actividad Laboral".toUpperCase());
//        lbl_ActividadLaboralSolicitante.setText("Actividad Laboral".toUpperCase());
        lbl_ActividadLaboralSolicitante.setForeground(Color.GRAY.brighter());
        txt_ActividadLaboralSolicitante = new iTextField("", 3);

        lbl_MotivoConsultaSolicitante = new iLabel("Motivo Consulta".toUpperCase());
//        lbl_MotivoConsultaSolicitante.setText("Motivo Consulta".toUpperCase());//text_Area
        lbl_MotivoConsultaSolicitante.setForeground(Color.GRAY.brighter());
        txt_MotivoConsultaSolicitante = new iTextField("", 3);

        lbl_FechaReporte = new iLabel("Fecha Reporte".toUpperCase());
//        lbl_FechaReporte.setText("Fecha Reporte".toUpperCase());//text_Area
        lbl_FechaReporte.setForeground(Color.GRAY.brighter());
        //iCalendar Fecha = new iCalendar(date);
        txt_FechaReporte = new iTextField("", 3);

    }

    public void initPaciComponents() {

        lbl_TituloPaciente = new iLabel("PACIENTE");
//        lbl_TituloPaciente.setText("PACIENTE");
        lbl_TituloPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TituloPaciente.setForeground(Color.GRAY.brighter());

        lbl_CedulaPaciente = new iLabel("Cédula Paciente".toUpperCase());
//        lbl_CedulaPaciente.setText("Cédula Paciente".toUpperCase());
        lbl_CedulaPaciente.setForeground(Color.GRAY.brighter());
        txt_CedulaPaciente = new iTextField("", 3);
//        txt_CedulaPaciente.setBackground(Color.BLACK);//no sirve
//        txt_CedulaPaciente.setForeground(Color.red);//no sirve

        lbl_CedulaSolicitantePaciente = new iLabel("Cédula Solicitante".toUpperCase());
//        lbl_CedulaSolicitantePaciente.setText("Cédula Solicitante".toUpperCase());
        lbl_CedulaSolicitantePaciente.setForeground(Color.GRAY.brighter());
        txt_CedulaSolicitantePaciente = new iTextField("", 3);
//        txt_CedulaSolicitantePaciente.setBackground(Color.BLACK);//no sirven
//        txt_CedulaSolicitantePaciente.setForeground(Color.red);//no sirven

        lbl_NombrePaciente = new iLabel("Nombre Paciente".toUpperCase());
//        lbl_NombrePaciente.setText("Nombre Paciente".toUpperCase());
        lbl_NombrePaciente.setForeground(Color.GRAY.brighter());
        txt_NombrePaciente = new iTextField("", 3);

        lbl_FechaNacimientoPaciente = new iLabel("Fecha de Nacimiento Paciente".toUpperCase());
//        lbl_FechaNacimientoPaciente.setText("Fecha de Nacimiento Paciente".toUpperCase());
        lbl_FechaNacimientoPaciente.setForeground(Color.GRAY.brighter());
        //iCalendar Fecha = new iCalendar(date);
        txt_FechaNacimientoPaciente = new iTextField("", 3);

        lbl_DireccionPaciente = new iLabel("Dirección Paciente".toUpperCase());
//        lbl_DireccionPaciente.setText("Dirección Paciente".toUpperCase());
        lbl_DireccionPaciente.setForeground(Color.GRAY.brighter());
        txt_DireccionPaciente = new iTextField("", 3);

        lbl_TelefonoPaciente = new iLabel("Teléfono Paciente".toUpperCase());
//        lbl_TelefonoPaciente.setText("Teléfono Paciente".toUpperCase());
        lbl_TelefonoPaciente.setForeground(Color.GRAY.brighter());
        txt_TelefonoPaciente = new iTextField("", 3);

        lbl_ProfesionPaciente = new iLabel("Profesión Paciente".toUpperCase());
//        lbl_ProfesionPaciente.setText("Profesión Paciente".toUpperCase());
        lbl_ProfesionPaciente.setForeground(Color.GRAY.brighter());
        txt_ProfesionPaciente = new iTextField("", 3);

        lbl_ActividadLaboralPaciente = new iLabel("Actividad Laboral Paciente".toUpperCase());
//        lbl_ActividadLaboralPaciente.setText("Actividad Laboral Paciente".toUpperCase());
        lbl_ActividadLaboralPaciente.setForeground(Color.GRAY.brighter());
        txt_ActividadLaboralPaciente = new iTextField("", 3);

        lbl_MotivoConsultaPaciente = new iLabel("Motivo Consulta Paciente".toUpperCase());
//        lbl_MotivoConsultaPaciente.setText("Motivo Consulta Paciente".toUpperCase());//text_Area
        lbl_MotivoConsultaPaciente.setForeground(Color.GRAY.brighter());
        txt_MotivoConsultaPaciente = new iTextField("", 3);

        lbl_ParentescoPaciente = new iLabel("Parentesco Paciente".toUpperCase());
//        lbl_ParentescoPaciente.setText("Parentesco Paciente".toUpperCase());
        lbl_ParentescoPaciente.setForeground(Color.GRAY.brighter());
        cbo_Parentesco = new JComboBox();//son de seleccion por base de datos

        lbl_ClasificacionPaciente = new iLabel("Clasificación Paciente Paciente".toUpperCase());
//        lbl_ClasificacionPaciente.setText("Clasificación Paciente Paciente".toUpperCase());
        lbl_ClasificacionPaciente.setForeground(Color.GRAY.brighter());
        cbo_ClasificacionPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_CursoPaciente = new iLabel("Curso".toUpperCase());
//        lbl_CursoPaciente.setText("Curso".toUpperCase());
        lbl_CursoPaciente.setForeground(Color.GRAY.brighter());
        cbo_CursoPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_HorarioPaciente = new iLabel("Horario Paciente".toUpperCase());
//        lbl_HorarioPaciente.setText("Horario Paciente".toUpperCase());
        lbl_HorarioPaciente.setForeground(Color.GRAY.brighter());
        cbo_HorarioPaciente = new JComboBox();//son de seleccion por base de datos

        lbl_DetalleHorarioPaciente = new iLabel("Detalle Horario Paciente".toUpperCase());
//        lbl_DetalleHorarioPaciente.setText("Detalle Horario Paciente".toUpperCase());//text_Area
        lbl_DetalleHorarioPaciente.setForeground(Color.GRAY.brighter());
        txt_DetalleHorarioPaciente = new iTextField("", 3);

        lbl_TipoPaciente = new iLabel("Tipo Paciente Paciente".toUpperCase());
//        lbl_TipoPaciente.setText("Tipo Paciente Paciente".toUpperCase());
        lbl_TipoPaciente.setForeground(Color.GRAY.brighter());
        cbo_TipoPaciente = new JComboBox();//son de seleccion por base de datos

    }

    public void AddComponentes(iFrame if_) {

        PatientView_panelLeft.AddObject(lbl_LogoULatina, 415, 120, 10);
        PatientView_panelLeft.AddObject(lbl_LogoPsicologia, 415, 120, 440);
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_TituloSolicitante, 415, 30, 2);
        PatientView_panelLeft.AddObject(lbl_TituloPaciente, 415, 30, 600);//agrego el titulo para poner verlo con
        lbl_TituloPaciente.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_CedulaSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_CedulaSolicitantePaciente, 146, 30, 600);//para paciente
        lbl_CedulaSolicitantePaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_CedulaSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_CedulaSolicitantePaciente, 230, 30, 800);//para paciente
        txt_CedulaSolicitantePaciente.setVisible(true);//para paciente
        txt_CedulaSolicitantePaciente.setEnabled(true);
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_NombreSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_CedulaPaciente, 146, 30, 600);//para paciente
        lbl_CedulaPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_NombreSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_CedulaPaciente, 230, 30, 800);//para paciente
        txt_CedulaPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_DireccionSolicitante, 146, 30, 10);//para el paciente
        PatientView_panelLeft.AddObject(lbl_FechaNacimientoPaciente, 230, 30, 600);
        lbl_FechaNacimientoPaciente.setVisible(true);//para paciente
//        RePanel.AddObject(txt_FechaNacimiento, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_DireccionSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_FechaNacimientoPaciente, 230, 30, 800);//para paciente
        txt_FechaNacimientoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_TelefonoSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_DireccionPaciente, 230, 30, 600);//para paciente
        lbl_DireccionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_TelefonoSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_DireccionPaciente, 230, 30, 800);//para paciente
        txt_DireccionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_ProfesionSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_TelefonoPaciente, 230, 30, 600);//para paciente
        lbl_TelefonoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_ProfesionSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_TelefonoPaciente, 230, 30, 800);//para paciente
        txt_TelefonoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_ActividadLaboralSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_ProfesionPaciente, 230, 30, 600);//para paciente
        lbl_ProfesionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_ActividadLaboralSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_ProfesionPaciente, 230, 30, 800);//para paciente
        txt_ProfesionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_MotivoConsultaSolicitante, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_ActividadLaboralPaciente, 230, 30, 600);//para paciente
        lbl_ActividadLaboralPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_MotivoConsultaSolicitante, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_ActividadLaboralPaciente, 230, 30, 800);//para paciente
        txt_ActividadLaboralPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_FechaReporte, 146, 30, 10);
        PatientView_panelLeft.AddObject(lbl_MotivoConsultaPaciente, 230, 30, 600);//para paciente
        lbl_MotivoConsultaPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_FechaReporte, 230, 30, 175);
        PatientView_panelLeft.AddObject(txt_MotivoConsultaPaciente, 230, 30, 800);//para paciente
        txt_MotivoConsultaPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_ParentescoPaciente, 230, 30, 600);
        lbl_ParentescoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(cbo_Parentesco, 230, 30, 800);
        cbo_Parentesco.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_ClasificacionPaciente, 230, 30, 600);
        lbl_ClasificacionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(cbo_ClasificacionPaciente, 230, 30, 800);
        cbo_ClasificacionPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_CursoPaciente, 230, 30, 600);
        lbl_CursoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(cbo_CursoPaciente, 230, 30, 800);
        cbo_CursoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_HorarioPaciente, 230, 30, 600);
        lbl_HorarioPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(cbo_HorarioPaciente, 230, 30, 800);
        cbo_HorarioPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_DetalleHorarioPaciente, 230, 30, 600);
        lbl_DetalleHorarioPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(txt_DetalleHorarioPaciente, 230, 30, 800);
        txt_DetalleHorarioPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_TipoPaciente, 230, 30, 600);
        lbl_TipoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.AddObject(cbo_TipoPaciente, 230, 30, 800);
        cbo_TipoPaciente.setVisible(true);//para paciente
        PatientView_panelLeft.newLine();

        PatientView_panelLeft.AddObject(lbl_NOTA, 500, 70, 10);
         PatientView_panelLeft.newLine();     
            
       PatientView_panelLeft.AddObject(btnEditarAction, 175, 30, 10);

        btnEditarAction.addActionListener((a) -> {

            btnEditarAction_MouseClicked();
            
        });
//
        PatientView_panelLeft.newLine();
        
         
         
        PatientView_panelLeft.AddObject(llenarTable(tbl_Data), 800, 200); 
       if_.add(PatientView_panelLeft);
    }
    public iScrollPane llenarTable(List<String> info)
    {
        
       ArrayList<String> cols = new ArrayList();
        ArrayList<String> rows = new ArrayList();
        info.forEach((jKeyPair) -> {
            cols.add(jKeyPair.split("-")[0]);
            rows.add(jKeyPair.split("-")[1]);
        });
         iTable Table = new iTable(cols);
        System.out.println("Cols " + cols);
        System.out.println("Rows " + rows);
        Table.addrow(rows);
        iScrollPane ScrollPane=new iScrollPane(Table, null);
        
        
        
  return ScrollPane;
    }

    private void btnEditarAction_MouseClicked() {
        
    }
}
