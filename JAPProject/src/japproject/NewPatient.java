/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iTextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import static japproject.HomePanel.currentPanel;

/**
 *
 * @author Jose
 */
public class NewPatient {
    
    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");//hago la conexion a BD
    public iPanel RePanel;//creo el iPanel        
    
    private final String DATABASE_URL = "jdbc:mysql://icomponents.net:3306/icompone_jose";
    private final String USERNAME = "icompone_jose";
    private final String PASSWORD = "m70Q(71X7k5v";
    
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
    private iButton btnRegisterAction;//Boton para el registrar
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
    
//    private JCheckBox chk_boxSolicitanteIgualPaciente;//Checkbox para ver si el Solicitante ("""NO""") es el Mismo Paciente
    private JCheckBox chk_boxSolicitanteDiferentePaciente;//Checkbox para ver si el Solicitante ("""NO""") es el Mismo Paciente
    //FIN de Controles Swing para Solicitante
    
    //Controles Swing para Paciente
    private JTabbedPane jTabbedPane0;//creo el JtabbedPane para hacer el JtabbedPane
    private iPanel Panel_tab1;
    private iPanel Panel_tab2;
    private iPanel Panel_tab3;
    
    //Controles swing para paciente
    private iButton btnRegisterActionPaciente;//Boton para registrar el paciente
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
    
    public NewPatient(iFrame if_) {
        currentPanel = "RePanel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.      
        RePanel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        RePanel.setBackground(new java.awt.Color(00, 52, 25));//le doy color al panel
        Ingresar(if_);
        
    }
    
    /**
     * Método que crea los componentes en el RePanel de Paciente
     *
     * @return Muestra los componentes en el RePanel del Paciente
     */
    private void ComponentesPaciente(){
  
        ////////////////Componentes para paciente, recordar ponerlos en setVisible(false); ,[y en el metodo setVisible(true) si es verdad, }else{setVisible(true);]
        btnRegisterActionPaciente = new iButton("REGISTRAR PACIENTE", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnRegisterActionPaciente.setText("REGISTRAR PACIENTE");

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
        ///////////////Fin de componentes de Paciente
        
    }
    
    
    /**
     * Método que crea los componentes en el RePanel de Solicitante
     *
     * @return Muestra los componentes en el RePanel del Solicitante
    */
    private void ComponentesSolicitante(){
    
        ///Componentes para Solicitante
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        btnRegisterAction = new iButton("REGISTRAR", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnRegisterAction.setText("REGISTRAR");

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

        lbl_NOTA = new iLabel("Nota: Si el Solicitante NO es el paciente, Marque el check.".toUpperCase());
        lbl_NOTA.setForeground(Color.GRAY.brighter());
//        lbl_NOTA.setText("Nota: Si el Solicitante NO es el paciente, Marque el check.".toUpperCase());
    
    }
    
    
    /**
     * Método que crea los componentes en el RePanel 
     *
     * @return Muestra los componentes en el RePanel
    */
    private void Ingresar(iFrame if_) {

        ComponentesSolicitante();//cargo el metodo de los componentes swing de Solicitante
        ComponentesPaciente();//cargo el metodo de los componentes swing de Paciente

//        chk_boxSolicitanteIgualPaciente = new JCheckBox();
//        chk_boxSolicitanteIgualPaciente.setText("PACIENTE = Solicitante".toUpperCase());
//        chk_boxSolicitanteIgualPaciente.setBackground(Color.GRAY);
        
        chk_boxSolicitanteDiferentePaciente = new JCheckBox();
        chk_boxSolicitanteDiferentePaciente.setText("PACIENTE != Solicitante".toUpperCase());
        chk_boxSolicitanteDiferentePaciente.setBackground(Color.GRAY);
        
        cbo_CargarParentesco();
        cbo_CargarClasificacionPaciente();
        cbo_CargarCurso();
        cbo_CargarHorario();
        cbo_CargarTipoPaciente();
        
        /////////////////componente,/////ancho,largo,posision//
        RePanel.AddObject(lbl_LogoULatina, 415, 120, 10);
        RePanel.AddObject(lbl_LogoPsicologia, 415, 120, 440);
        RePanel.newLine(); 

        RePanel.AddObject(lbl_TituloSolicitante, 415, 30, 2);
        RePanel.AddObject(lbl_TituloPaciente, 415, 30, 600);//agrego el titulo para poner verlo con
        lbl_TituloPaciente.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        RePanel.newLine();

        RePanel.AddObject(lbl_CedulaSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_CedulaSolicitantePaciente, 146, 30, 600);//para paciente
        lbl_CedulaSolicitantePaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_CedulaSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_CedulaSolicitantePaciente, 230, 30, 800);//para paciente
        txt_CedulaSolicitantePaciente.setVisible(true);//para paciente
        txt_CedulaSolicitantePaciente.setEnabled(true);
        RePanel.newLine();

        RePanel.AddObject(lbl_NombreSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_CedulaPaciente, 146, 30, 600);//para paciente
        lbl_CedulaPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_NombreSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_CedulaPaciente, 230, 30, 800);//para paciente
        txt_CedulaPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        
        RePanel.AddObject(lbl_DireccionSolicitante, 146, 30, 10);//para el paciente
        RePanel.AddObject(lbl_FechaNacimientoPaciente, 230, 30, 600);
        lbl_FechaNacimientoPaciente.setVisible(true);//para paciente
//        RePanel.AddObject(txt_FechaNacimiento, 230, 30, 175);
        RePanel.AddObject(txt_DireccionSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_FechaNacimientoPaciente, 230, 30, 800);//para paciente
        txt_FechaNacimientoPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        RePanel.AddObject(lbl_TelefonoSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_DireccionPaciente, 230, 30, 600);//para paciente
        lbl_DireccionPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_TelefonoSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_DireccionPaciente, 230, 30, 800);//para paciente
        txt_DireccionPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        RePanel.AddObject(lbl_ProfesionSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_TelefonoPaciente, 230, 30, 600);//para paciente
        lbl_TelefonoPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_ProfesionSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_TelefonoPaciente, 230, 30, 800);//para paciente
        txt_TelefonoPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        RePanel.AddObject(lbl_ActividadLaboralSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_ProfesionPaciente, 230, 30, 600);//para paciente
        lbl_ProfesionPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_ActividadLaboralSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_ProfesionPaciente, 230, 30, 800);//para paciente
        txt_ProfesionPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        RePanel.AddObject(lbl_MotivoConsultaSolicitante, 146, 30, 10);
        RePanel.AddObject(lbl_ActividadLaboralPaciente, 230, 30, 600);//para paciente
        lbl_ActividadLaboralPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_MotivoConsultaSolicitante, 230, 30, 175);
        RePanel.AddObject(txt_ActividadLaboralPaciente, 230, 30, 800);//para paciente
        txt_ActividadLaboralPaciente.setVisible(true);//para paciente
        RePanel.newLine();

        RePanel.AddObject(lbl_FechaReporte, 146, 30, 10);
        RePanel.AddObject(lbl_MotivoConsultaPaciente, 230, 30, 600);//para paciente
        lbl_MotivoConsultaPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_FechaReporte, 230, 30, 175);
        RePanel.AddObject(txt_MotivoConsultaPaciente, 230, 30, 800);//para paciente
        txt_MotivoConsultaPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_ParentescoPaciente, 230, 30, 600);
        lbl_ParentescoPaciente.setVisible(true);//para paciente
        RePanel.AddObject(cbo_Parentesco, 230, 30, 800);
        cbo_Parentesco.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_ClasificacionPaciente, 230, 30, 600);
        lbl_ClasificacionPaciente.setVisible(true);//para paciente
        RePanel.AddObject(cbo_ClasificacionPaciente, 230, 30, 800);
        cbo_ClasificacionPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_CursoPaciente, 230, 30, 600);
        lbl_CursoPaciente.setVisible(true);//para paciente
        RePanel.AddObject(cbo_CursoPaciente, 230, 30, 800);
        cbo_CursoPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_HorarioPaciente, 230, 30, 600);
        lbl_HorarioPaciente.setVisible(true);//para paciente
        RePanel.AddObject(cbo_HorarioPaciente, 230, 30, 800);
        cbo_HorarioPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_DetalleHorarioPaciente, 230, 30, 600);
        lbl_DetalleHorarioPaciente.setVisible(true);//para paciente
        RePanel.AddObject(txt_DetalleHorarioPaciente, 230, 30, 800);
        txt_DetalleHorarioPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_TipoPaciente, 230, 30, 600);
        lbl_TipoPaciente.setVisible(true);//para paciente
        RePanel.AddObject(cbo_TipoPaciente, 230, 30, 800);
        cbo_TipoPaciente.setVisible(true);//para paciente
        RePanel.newLine();
        
        RePanel.AddObject(lbl_NOTA, 500, 70, 10);
//        
//        //Para crear las ventanas de los JtabbedPanes
//        jTabbedPane0 = new JTabbedPane();//creo el panel para hacer el JtabbedPane
////        Panel_tab1 = new iPanel(0, 0, 0, 0, 0, if_);
////        Panel_tab2 = new iPanel();
////        Panel_tab3 = new iPanel();
//        
//        Panel_tab1.setBackground(new java.awt.Color(0, 151, 153));
//        Panel_tab2.setBackground(new java.awt.Color(0, 185, 155));
//        Panel_tab3.setBackground(new java.awt.Color(0, 250, 158));
//        
//        jTabbedPane0.addTab("Paciente 1", Panel_tab1);
//        jTabbedPane0.addTab("Paciente 2", Panel_tab2);
//        jTabbedPane0.addTab("Paciente 3", Panel_tab3);
//        
//                                    //largo, ancho, posicion  
//        RePanel.AddObject(jTabbedPane0, 500, 300, 600);//para paciente
//        jTabbedPane0.setVisible(false);//para paciente
//        //Fin de crear los TabbedPane
//        
        RePanel.newLine();
//        RePanel.AddObject(chk_boxSolicitanteIgualPaciente, 175, 30, 10);
//        RePanel.AddObject(chk_boxSolicitanteDiferentePaciente, 213, 30, 193);
        RePanel.AddObject(chk_boxSolicitanteDiferentePaciente, 175, 30, 10);
        RePanel.newLine();     
        RePanel.AddObject(btnRegisterAction, 175, 30, 10);

        btnRegisterAction.addActionListener((a) -> {

            btn_RegisterAction_MouseClicked();
            
        });
//
        RePanel.newLine();
//        //fin de crear tabbedPane y agregarlos al Repanel



        //Metodo para cuando el chk_boxSolicitanteIgualPaciente este marcado hara la funcion de no no muestra el msj
//        chk_boxSolicitanteDiferentePaciente.addActionListener((e) -> {
//            if (chk_boxSolicitanteDiferentePaciente.isSelected()) {
//                
////                chk_boxSolicitanteDiferentePaciente.setSelected(false);//para que no
//                
//                
//                System.out.println("MUESTRA MENSAJE MARCADO");
//                //PARA QUE SE MUESTREN
//                lbl_TituloPaciente.setVisible(true);
//                lbl_CedulaSolicitantePaciente.setVisible(true);
//                txt_CedulaSolicitantePaciente.setVisible(true);
//                lbl_CedulaPaciente.setVisible(true);
//                txt_CedulaPaciente.setVisible(true);
//                lbl_NombrePaciente.setVisible(true);
//                txt_NombrePaciente.setVisible(true);
//                lbl_FechaNacimientoPaciente.setVisible(true);
//                txt_FechaNacimientoPaciente.setVisible(true);
//                lbl_DireccionPaciente.setVisible(true);
//                txt_DireccionPaciente.setVisible(true);
//                lbl_TelefonoPaciente.setVisible(true);
//                txt_TelefonoPaciente.setVisible(true);
//                lbl_ProfesionPaciente.setVisible(true);
//                txt_ProfesionPaciente.setVisible(true);
//                lbl_ActividadLaboralPaciente.setVisible(true);
//                txt_ActividadLaboralPaciente.setVisible(true);
//                lbl_MotivoConsultaPaciente.setVisible(true);
//                txt_MotivoConsultaPaciente.setVisible(true);
//                lbl_ParentescoPaciente.setVisible(true);
//                cbo_Parentesco.setVisible(true);
//                lbl_ClasificacionPaciente.setVisible(true);
//                cbo_ClasificacionPaciente.setVisible(true);
//                lbl_CursoPaciente.setVisible(true);
//                cbo_CursoPaciente.setVisible(true);
//                lbl_HorarioPaciente.setVisible(true);
//                cbo_HorarioPaciente.setVisible(true);
//                lbl_DetalleHorarioPaciente.setVisible(true);
//                txt_DetalleHorarioPaciente.setVisible(true);
//                lbl_TipoPaciente.setVisible(true);
//                cbo_TipoPaciente.setVisible(true);
//                
//            } else {
//                //esto es debido a que arriba hay que agregar los objetos en la fila corresondiente
//                //y acomodarlo segun su posicion con los demas objetos
////                chk_boxSolicitanteDiferentePaciente.setSelected(true);
////                chk_boxSolicitanteIgualPaciente.setSelected(false);
//                chk_boxSolicitanteDiferentePaciente.setSelected(false);
//                
//                lbl_TituloPaciente.setVisible(false);
//                lbl_CedulaSolicitantePaciente.setVisible(false);
//                txt_CedulaSolicitantePaciente.setVisible(false);
//                lbl_CedulaPaciente.setVisible(false);
//                txt_CedulaPaciente.setVisible(false);
//                lbl_NombrePaciente.setVisible(false);
//                txt_NombrePaciente.setVisible(false);
//                lbl_FechaNacimientoPaciente.setVisible(false);
//                txt_FechaNacimientoPaciente.setVisible(false);
//                lbl_DireccionPaciente.setVisible(false);
//                txt_DireccionPaciente.setVisible(false);
//                lbl_TelefonoPaciente.setVisible(false);
//                txt_TelefonoPaciente.setVisible(false);
//                lbl_ProfesionPaciente.setVisible(false);
//                txt_ProfesionPaciente.setVisible(false);
//                lbl_ActividadLaboralPaciente.setVisible(false);
//                txt_ActividadLaboralPaciente.setVisible(false);
//                lbl_MotivoConsultaPaciente.setVisible(false);
//                txt_MotivoConsultaPaciente.setVisible(false);
//                lbl_ParentescoPaciente.setVisible(false);
//                cbo_Parentesco.setVisible(false);
//                lbl_ClasificacionPaciente.setVisible(false);
//                cbo_ClasificacionPaciente.setVisible(false);
//                lbl_CursoPaciente.setVisible(false);
//                cbo_CursoPaciente.setVisible(false);
//                lbl_HorarioPaciente.setVisible(false);
//                cbo_HorarioPaciente.setVisible(false);
//                lbl_DetalleHorarioPaciente.setVisible(false);
//                txt_DetalleHorarioPaciente.setVisible(false);
//                lbl_TipoPaciente.setVisible(false);
//                cbo_TipoPaciente.setVisible(false);
//
//            }
//
//        });
        
//        chk_boxSolicitanteDiferentePaciente.addActionListener((e) -> {
//            
//            if (chk_boxSolicitanteDiferentePaciente.isSelected()) {
//                chk_boxSolicitanteDiferentePaciente.setSelected(true);
//                chk_boxSolicitanteIgualPaciente.setSelected(false);
//                System.out.println("MUESTRA MENSAJE 2 MARCADO");
////                jTabbedPane0.setVisible(true);
//                lbl_TituloPaciente.setVisible(true);
//                
//            }else{
//                chk_boxSolicitanteDiferentePaciente.setSelected(false);
//                chk_boxSolicitanteIgualPaciente.setSelected(false);
////                jTabbedPane0.setVisible(false);
//                lbl_TituloPaciente.setVisible(false);
//                
//                
//            }
//            
//        });

        if_.add(RePanel);

    }//Fin del metodo ibtnIngresarPacienteaddActionListener
    
    
    public void btn_RegisterAction_MouseClicked(){
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Solicitante(Cedula, Nombre, Direccion, Telefono, Profesion, ActividadLaboral, MotivoConsulta, FechaReporte) VALUES (?,?,?,?,?,?,?,?)");
        
        pps.setString(1 , txt_CedulaSolicitante.getText());
        pps.setString(2 , txt_NombreSolicitante.getText());
        pps.setString(3 , txt_DireccionSolicitante.getText());
        pps.setString(4 , txt_TelefonoSolicitante.getText());
        pps.setString(5 , txt_ProfesionSolicitante.getText());
        pps.setString(6 , txt_ActividadLaboralSolicitante.getText());
        pps.setString(7 , txt_MotivoConsultaSolicitante.getText());
        pps.setString(8 , txt_FechaReporte.getText());
        
        pps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        PreparedStatement pps = con.prepareStatement("INSERT INTO JAW_Paciente(IdSolicitante, Cedula, Nombre, FechaNacimiento, Direccion, Telefono, Profesion, ActividadLaboral, MotivoConsulta, IdParentesco, IdClasificacionPaciente, IdCurso, IdHorario, DetalleHorario, IdTipoPaciente, IsNonGrato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pps.setString(1 , txt_CedulaSolicitantePaciente.getText());
        pps.setString(2 , txt_CedulaPaciente.getText());
        pps.setString(3 , txt_NombrePaciente.getText());
        pps.setString(4 , txt_FechaNacimientoPaciente.getText());
        pps.setString(5 , txt_DireccionPaciente.getText());
        pps.setString(6 , txt_TelefonoPaciente.getText());
        pps.setString(7 , txt_ProfesionPaciente.getText());
        pps.setString(8 , txt_ActividadLaboralPaciente.getText());
        pps.setString(9 , txt_MotivoConsultaPaciente.getText());
        pps.setString(10 , cbo_Parentesco.getSelectedItem().toString());
        pps.setString(11 , cbo_ClasificacionPaciente.getSelectedItem().toString());
        pps.setString(12 , cbo_CursoPaciente.getSelectedItem().toString());
        pps.setString(13 , cbo_HorarioPaciente.getSelectedItem().toString());
        pps.setString(14 , txt_DetalleHorarioPaciente.getText());
        pps.setString(15 , cbo_TipoPaciente.getSelectedItem().toString());
        pps.setString(16 , null);
        
        pps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    
    //Metodos para cargar los cbo`s de Paciente
        private JComboBox cbo_CargarTipoPaciente() {

            ResultSet rs = sql.SELECT("SELECT `NombreTipoPaciente` FROM JAW_TipoPaciente");

            try {
                while (rs.next()) {
                    cbo_TipoPaciente.addItem(rs.getObject("NombreTipoPaciente"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NewPatient.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cbo_Parentesco;
        }//Fin del cbo_CargarCurso
        //fin de metodos para cargar cbo`s de Paciente
}
