/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iButton;
import iComponents.iCalendar;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import static japproject.JAPProject.sql;
import iComponents.iTextField;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import static japproject.HomePanel.currentPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



/**
 *
 * @author Jose
 */
public class NewPatient {
    
    public iPanel NewPatient_Panel;//creo el iPanel
    
    
    public iPanel TabbedPane_Panel;//creo el iPanel para mi JTabbedPane
    private JTabbedPane jTabbedPane;//creo el JtabbedPane
    
    private iLabel vacio = new iLabel("");//lo creo para hacer newline()
    
    //cbo`s para Paciente
    private JComboBox cbo_TipoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_HorarioPaciente;//son de seleccion por base de datos
    private JComboBox cbo_ClasificacionPaciente;//son de seleccion por base de datos
    private JComboBox cbo_CursoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_Parentesco;//son de seleccion por base de datos
    private JComboBox cbo_IsNonGrato;//son de seleccion por base de datos//((((lista negra)))))
    //fin cbo`s para Paciente
    
    //cbo`s para Paciente
    private JComboBox cbo_TipoPacienteT;//son de seleccion por base de datos
    private JComboBox cbo_HorarioPacienteT;//son de seleccion por base de datos
    private JComboBox cbo_ClasificacionPacienteT;//son de seleccion por base de datos
    private JComboBox cbo_CursoPacienteT;//son de seleccion por base de datos
    private JComboBox cbo_ParentescoT;//son de seleccion por base de datos
    private JComboBox cbo_IsNonGratoT;//son de seleccion por base de datos//((((lista negra)))))
    //fin cbo`s para Paciente
    
    //Controles swing para NewPatient_Panel
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnRegister;//Boton para el registrar
    private iButton btnNuevoPaciente;//Boton para el registrar
    private iButton btnNuevoPacienteT;//Boton para el registrar
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
    private iCalendar FechaReporte;
    private iLabel lbl_NOTA;//Lbl para la NOTA(si preciona el CheckBox) del Solicitante
    
    private JCheckBox chk_boxSolicitanteDiferentePaciente;//Checkbox para ver si el Solicitante ("""NO""") es el Mismo Paciente
    //FIN de Controles Swing para Solicitante
    
    
    //Controles swing para paciente = Solicitante
    private iButton btnRegisterPaciente;//Boton para registrar el paciente
    private iLabel lbl_TituloPaciente;//Lbl para el Titulo del Paciente
    private iLabel lbl_CedulaPaciente;//Lbl para la Cedula del Solicitante
    private iTextField txt_CedulaPaciente;//TextField para cedula del Solicitante en la parte de paciente(hay que hacerle un setEditable(false))
    private iLabel lbl_NombrePaciente;//Lbl para el Nombre del Paciente
    private iTextField txt_NombrePaciente;//TextField para el Nombre del Paciente
    private iLabel lbl_FechaNacimientoPaciente;//Lbl para la Fecha de Nacimiento del Paciente
    private iCalendar FechaNacimientoPaciente;//TextField para la fecha de Nacimiento del Paciente(cambiar por JDateChooser)
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
    
    //Controles swing para paciente = Solicitante
    private iButton btnRegisterPacienteT;//Boton para registrar el paciente
    private iLabel lbl_TituloPacienteT;//Lbl para el Titulo del Paciente
    private iLabel lbl_CedulaPacienteT;//Lbl para la Cedula del Solicitante
    private iTextField txt_CedulaPacienteT;//TextField para cedula del Solicitante en la parte de paciente(hay que hacerle un setEditable(false))
    private iLabel lbl_NombrePacienteT;//Lbl para el Nombre del Paciente
    private iTextField txt_NombrePacienteT;//TextField para el Nombre del Paciente
    private iLabel lbl_FechaNacimientoPacienteT;//Lbl para la Fecha de Nacimiento del Paciente
    private iCalendar FechaNacimientoPacienteT;//TextField para la fecha de Nacimiento del Paciente(cambiar por JDateChooser)
    private iLabel lbl_DireccionPacienteT;//Lbl para la Direccion del Paciente
    private iTextField txt_DireccionPacienteT;//TextField para la Direccion del Paciente
    private iLabel lbl_TelefonoPacienteT;//Lbl para la el Telefono del Paciente
    private iTextField txt_TelefonoPacienteT;//TextField para el Telefono del Paciente
    private iLabel lbl_ProfesionPacienteT;//Lbl para la Profesion del Paciente
    private iTextField txt_ProfesionPacienteT;//TextField para la Profesion del Paciente
    private iLabel lbl_ActividadLaboralPacienteT;//Lbl para la Actividad Laboral del Paciente
    private iTextField txt_ActividadLaboralPacienteT;//TextField para la Actividad Laboral del Paciente
    private iLabel lbl_MotivoConsultaPacienteT;//Lbl para la El Motivo Consulta del Paciente
    private iTextField txt_MotivoConsultaPacienteT;//TextField para el Motivo Consulta del Paciente(cambiar por JTextArea)
    private iLabel lbl_ParentescoPacienteT;//Lbl para el Parentesco del Paciente
    private iLabel lbl_ClasificacionPacienteT;//Lbl para la Clasificacion del Paciente
    private iLabel lbl_CursoPacienteT;//Lbl para la el Curso del Paciente
    private iLabel lbl_HorarioPacienteT;//Lbl para la el Horario del Paciente
    private iLabel lbl_DetalleHorarioPacienteT;//Lbl para el Detalle Horario del Paciente
    private iTextField txt_DetalleHorarioPacienteT;//TextField para el Detalle Horario del Paciente
    private iLabel lbl_TipoPacienteT;//Lbl para el Tipo de Paciente 
    private iLabel lbl_IsNonGratoT;//Lbl para el Tipo de Lista Negra  
    //FIN de Controles Swing para Paciente
    
    public NewPatient(iFrame if_) {
        currentPanel = "NewPatient_Panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.
        NewPatient_Panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        NewPatient_Panel.setBackground(Color.decode("#006738"));//le doy color al panel
        
                                     //x  ,   y, width(largo), height(alto), Elements Margin, if_
        TabbedPane_Panel = new iPanel(600, 200, 38.0f, 70.0f, 0, 0, if_);//le doy propiedades al iPanel
        TabbedPane_Panel.setBackground(Color.decode("#0732"));//le doy color al panel
        TabbedPane_Panel.setVisible(false);
        Ingresar(if_);
        
    }
    
    /**
     * Método que crea los componentes en el NewPatient_Panel de Solicitante
     *
     * @return Muestra los componentes en el NewPatient_Panel del Solicitante
    */
    private void ComponentesSolicitante(){
    
        ///Componentes para Solicitante
        lbl_LogoULatina = new iLabel("");
        lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));

        lbl_LogoPsicologia = new iLabel("");
        lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

        lbl_TituloSolicitante = new iLabel("SOLICITANTE");
        lbl_TituloSolicitante.setForeground(Color.GRAY.brighter());

        lbl_CedulaSolicitante = new iLabel("Cédula".toUpperCase());
        lbl_CedulaSolicitante.setForeground(Color.GRAY.brighter());
        txt_CedulaSolicitante = new iTextField("", 15);
        txt_CedulaSolicitante.setForeground(Color.red);

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
        FechaReporte = new iCalendar();
        
        lbl_NOTA = new iLabel("Nota: Si el Solicitante NO es el paciente, Marque el check.".toUpperCase());
        lbl_NOTA.setForeground(Color.GRAY.brighter());
    
        btnRegister = new iButton("REGISTRAR", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
    }
    
    
    /**
     * Método que crea los componentes en el NewPatient_Panel de Paciente
     *
     * @return Muestra los componentes en el NewPatient_Panel del Paciente
     */
    private void ComponentesPaciente(){
  
        ////////////////Componentes para paciente, recordar ponerlos en setVisible(false); ,[y en el metodo setVisible(true) si es verdad, }else{setVisible(true);]
        
        lbl_TituloPaciente = new iLabel("PACIENTE");
        lbl_TituloPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TituloPaciente.setForeground(Color.GRAY.brighter());

        lbl_CedulaPaciente = new iLabel("Cédula Paciente".toUpperCase());
        lbl_CedulaPaciente.setForeground(Color.GRAY.brighter());
        txt_CedulaPaciente = new iTextField("", 15);

        lbl_NombrePaciente = new iLabel("Nombre Paciente".toUpperCase());
        lbl_NombrePaciente.setForeground(Color.GRAY.brighter());
        txt_NombrePaciente = new iTextField("", 15);

        lbl_FechaNacimientoPaciente = new iLabel("Fecha de Nacimiento Paciente".toUpperCase());
        lbl_FechaNacimientoPaciente.setForeground(Color.GRAY.brighter());
        FechaNacimientoPaciente = new iCalendar();

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
        
        btnNuevoPaciente = new iButton("NUEVO PACIENTE(+)", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnRegisterPaciente = new iButton("REGISTRAR PACIENTE", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        ///////////////Fin de componentes de Paciente
        
    }//fin de metodo componentes paciente
    
    
    
    /**
     * Método que crea los componentes en el NewPatient_Panel de Paciente
     *
     * @return Muestra los componentes en el NewPatient_Panel del Paciente
     */
    private void ComponentesPacienteT(){
  
        ////////////////Componentes para paciente, recordar ponerlos en setVisible(false); ,[y en el metodo setVisible(true) si es verdad, }else{setVisible(true);]
        
        lbl_TituloPacienteT = new iLabel("PACIENTE");
        lbl_TituloPacienteT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TituloPacienteT.setForeground(Color.GRAY.brighter());

        lbl_CedulaPacienteT = new iLabel("Cédula Paciente".toUpperCase());
        lbl_CedulaPacienteT.setForeground(Color.GRAY.brighter());
        txt_CedulaPacienteT = new iTextField("", 15);

        lbl_NombrePacienteT = new iLabel("Nombre Paciente".toUpperCase());
        lbl_NombrePacienteT.setForeground(Color.GRAY.brighter());
        txt_NombrePacienteT = new iTextField("", 15);

        lbl_FechaNacimientoPacienteT = new iLabel("Fecha de Nacimiento Paciente".toUpperCase());
        lbl_FechaNacimientoPacienteT.setForeground(Color.GRAY.brighter());
        FechaNacimientoPacienteT = new iCalendar();

        lbl_DireccionPacienteT = new iLabel("Dirección Paciente".toUpperCase());
        lbl_DireccionPacienteT.setForeground(Color.GRAY.brighter());
        txt_DireccionPacienteT = new iTextField("", 15);

        lbl_TelefonoPacienteT = new iLabel("Teléfono Paciente".toUpperCase());
        lbl_TelefonoPacienteT.setForeground(Color.GRAY.brighter());
        txt_TelefonoPacienteT = new iTextField("", 15);

        lbl_ProfesionPacienteT = new iLabel("Profesión Paciente".toUpperCase());
        lbl_ProfesionPacienteT.setForeground(Color.GRAY.brighter());
        txt_ProfesionPacienteT = new iTextField("", 15);

        lbl_ActividadLaboralPacienteT = new iLabel("Actividad Laboral Paciente".toUpperCase());
        lbl_ActividadLaboralPacienteT.setForeground(Color.GRAY.brighter());
        txt_ActividadLaboralPacienteT = new iTextField("", 15);

        lbl_MotivoConsultaPacienteT = new iLabel("Motivo Consulta Paciente".toUpperCase());
        lbl_MotivoConsultaPacienteT.setForeground(Color.GRAY.brighter());
        txt_MotivoConsultaPacienteT = new iTextField("", 15);

        lbl_ParentescoPacienteT = new iLabel("Parentesco Paciente".toUpperCase());
        lbl_ParentescoPacienteT.setForeground(Color.GRAY.brighter());
        cbo_ParentescoT = new JComboBox();//son de seleccion por base de datos
        
        lbl_ClasificacionPacienteT = new iLabel("Clasificación Paciente".toUpperCase());
        lbl_ClasificacionPacienteT.setForeground(Color.GRAY.brighter());
        cbo_ClasificacionPacienteT = new JComboBox();//son de seleccion por base de datos

        lbl_CursoPacienteT = new iLabel("Curso".toUpperCase());
        lbl_CursoPacienteT.setForeground(Color.GRAY.brighter());
        cbo_CursoPacienteT = new JComboBox();//son de seleccion por base de datos

        lbl_HorarioPacienteT = new iLabel("Horario Paciente".toUpperCase());
        lbl_HorarioPacienteT.setForeground(Color.GRAY.brighter());
        cbo_HorarioPacienteT = new JComboBox();//son de seleccion por base de datos

        lbl_DetalleHorarioPacienteT = new iLabel("Detalle Horario Paciente".toUpperCase());
        lbl_DetalleHorarioPacienteT.setForeground(Color.GRAY.brighter());
        txt_DetalleHorarioPacienteT = new iTextField("", 15);

        lbl_TipoPacienteT = new iLabel("Tipo Paciente Paciente".toUpperCase());
        lbl_TipoPacienteT.setForeground(Color.GRAY.brighter());
        cbo_TipoPacienteT = new JComboBox();//son de seleccion por base de datos
        lbl_IsNonGratoT = new iLabel("Lista Negra".toUpperCase());
        lbl_IsNonGratoT.setForeground(Color.GRAY.brighter()); 
        cbo_IsNonGratoT = new JComboBox();//son los de seleccion por paciente en lista negra
        
        btnNuevoPacienteT = new iButton("NUEVO PACIENTE(+)", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        btnRegisterPacienteT = new iButton("REGISTRAR PACIENTE", 2, Color.GRAY, Color.BLACK);//boton para registrar paciente
        ///////////////Fin de componentes de Paciente
        
    }//fin de metodo componentes paciente
    
    public String fechaActualFechaReporte(){
        DateFormat formatoFechaFechaReporte = new SimpleDateFormat("yyyy/MM/dd");//le doy formato a la fecha
        System.out.println(formatoFechaFechaReporte.format(FechaReporte.getDate()));//para ver la fecha si esta bien
        FechaReporte.setText(FechaReporte.getDate());////le seteo la fecha con el formato especificado
        return formatoFechaFechaReporte.format(FechaReporte.getDate());
    }
    
    public String fechaActualFechaNacimiento(){
        DateFormat formatoFechaFechaNacimiento = new SimpleDateFormat("yyyy/MM/dd");//le doy formato a la fecha
        System.out.println(formatoFechaFechaNacimiento.format(FechaNacimientoPaciente.getDate()));//para ver la fecha si esta bien
        FechaNacimientoPaciente.setText(FechaNacimientoPaciente.getText());//le seteo la fecha con el formato especificado
        return formatoFechaFechaNacimiento.format(FechaNacimientoPaciente.getDate()); 
    }
    
    
//    /**
//     * Método que crea los componentes en el NewPatient_Panel 
//     *
//     * @return Muestra los componentes en el NewPatient_Panel
//    */
//    private void Ingresar(iFrame if_) {
//        
//        ComponentesSolicitante();//cargo el metodo de los componentes swing de Solicitante
//        ComponentesPaciente();//cargo el metodo de los componentes swing de Paciente
//        
//        
//        chk_boxSolicitanteDiferentePaciente = new JCheckBox();
//        chk_boxSolicitanteDiferentePaciente.setText("PACIENTE != Solicitante".toUpperCase());
//        chk_boxSolicitanteDiferentePaciente.setBackground(Color.GRAY);
//        
//        cbo_CargarParentesco();
//        cbo_CargarClasificacionPaciente();
//        cbo_CargarCurso();
//        cbo_CargarHorario();
//        cbo_CargarTipoPaciente();
//        cbo_CargarIsNonGrato();//recien agregado
//        
//        /////////////////componente,/////ancho,largo,posision//
//        NewPatient_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
//        NewPatient_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);
//        NewPatient_Panel.newLine();  
//        
//        NewPatient_Panel.AddObject(vacio, 0,30,2);//esto fue agregado para hacer salto de linea en el mismo codigo
//        NewPatient_Panel.newLine();//aqui paso de linea al momento de hacer u know
//        NewPatient_Panel.AddObject(lbl_TituloSolicitante, 75, 30, 175);
//        NewPatient_Panel.AddObject(lbl_TituloPaciente, 56, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//        
//
//        NewPatient_Panel.AddObject(lbl_CedulaSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_CedulaPaciente, 146, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_CedulaSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_CedulaPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_NombreSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_NombrePaciente, 230, 30, 600);//para paciente 
//        NewPatient_Panel.AddObject(txt_NombreSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_NombrePaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        
//        NewPatient_Panel.AddObject(lbl_DireccionSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_FechaNacimientoPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_DireccionSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(FechaNacimientoPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_TelefonoSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_DireccionPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_TelefonoSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_DireccionPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_ProfesionSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_TelefonoPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_ProfesionSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_TelefonoPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_ActividadLaboralSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_ProfesionPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_ActividadLaboralSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_ProfesionPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_MotivoConsultaSolicitante, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_ActividadLaboralPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(txt_MotivoConsultaSolicitante, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_ActividadLaboralPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//
//        NewPatient_Panel.AddObject(lbl_FechaReporte, 146, 30, 10);
//        NewPatient_Panel.AddObject(lbl_MotivoConsultaPaciente, 230, 30, 600);//para paciente
//        NewPatient_Panel.AddObject(FechaReporte, 230, 30, 175);
//        NewPatient_Panel.AddObject(txt_MotivoConsultaPaciente, 230, 30, 800);//para paciente
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_ParentescoPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_Parentesco, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_ClasificacionPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_ClasificacionPaciente, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_CursoPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_CursoPaciente, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_HorarioPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_HorarioPaciente, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_DetalleHorarioPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(txt_DetalleHorarioPaciente, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_TipoPaciente, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_TipoPaciente, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        NewPatient_Panel.AddObject(lbl_IsNonGrato, 230, 30, 600);//
//        NewPatient_Panel.AddObject(cbo_IsNonGrato, 230, 30, 800);//
//        NewPatient_Panel.newLine();
//        
//        NewPatient_Panel.AddObject(lbl_NOTA, 500, 70, 10);
//        
//        //Para crear las ventanas de los JtabbedPanes
//        jTabbedPane = new JTabbedPane();//creo el panel para hacer el JtabbedPane
//        jTabbedPane.add("UNO", new iLabel("En la pestaña uno"));//falta cambiar por el panel estatico
//        jTabbedPane.add("DOS", new iLabel("En la pestaña dos"));
////        jTabbedPane.add("DOS", new iPanel(0, 0, 0, 0, 0, if_));//aun falta en preceso de 
//        jTabbedPane.add("TRES", new iLabel("En la pestaña tres"));
//        
//        jTabbedPane.setBackgroundAt(0, Color.YELLOW);
//        jTabbedPane.setBackgroundAt(1, Color.RED);
//        jTabbedPane.setBackgroundAt(2, Color.GREEN);
//                                       //largo, ancho   , posicion  
//        NewPatient_Panel.AddObject(jTabbedPane, 445, 570, 600);//para paciente
//        jTabbedPane.setVisible(false); 
//    
//                                        
//        //Fin de crear los TabbedPane
//        NewPatient_Panel.newLine();
//        NewPatient_Panel.AddObject(chk_boxSolicitanteDiferentePaciente, 175, 30, 10);
//        NewPatient_Panel.newLine();
//        NewPatient_Panel.AddObject(btnRegister, 175, 30, 10);
//        NewPatient_Panel.AddObject(btnNuevoPaciente, 175, 30, 600);
//        btnNuevoPaciente.setVisible(false);
//        NewPatient_Panel.newLine();
//        
//        setJTexFieldChanged(txt_CedulaSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_NombreSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_DireccionSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_TelefonoSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_ProfesionSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_ActividadLaboralSolicitante);//este metedo es para escribir dinamicamente
//        setJTexFieldChanged(txt_MotivoConsultaSolicitante);//este metedo es para escribir dinamicamente
//        
//        if(chk_boxSolicitanteDiferentePaciente.isSelected()==false){
//            jTabbedPane.setVisible(false);
//        }else{
//            jTabbedPane.setVisible(true);
//        }
//        
//        chk_boxSolicitanteDiferentePaciente.addActionListener((e) -> {
//            
//            if (chk_boxSolicitanteDiferentePaciente.isSelected() == true) {
//                System.out.println("MUESTRA MENSAJE DE CHECKBOX ACTIVO");
////                JOptionPane.showMessageDialog(null, "AHORA SELECCIONO EL CHECKBOX, PACIENTE ES DISTINTO AL SOLICITANTE", "ERROR", JOptionPane.ERROR_MESSAGE);
//                jTabbedPane.setVisible(true);
//                btnNuevoPaciente.setVisible(true);
//                cbo_Parentesco.setEnabled(true);
//                cbo_IsNonGrato.setEnabled(true);
////                jTabbedPane.setVisible(true);
//                 
//            }else {  
//                System.out.println("MUESTRA MENSAJE DE CHECKBOC DESACTIVADO");
//                jTabbedPane.setVisible(false);
//                btnNuevoPaciente.setVisible(false);
//                cbo_Parentesco.setSelectedItem("Nulo");
//                cbo_Parentesco.setEnabled(false);
//                cbo_IsNonGrato.setSelectedItem("No");
//                cbo_IsNonGrato.setEnabled(false); 
////                jTabbedPane.setVisible(false);
//            }
//        });
//        
//        btnRegister.addActionListener((a) -> {
//            btn_Register_MouseClicked();
//        });
//        
//        btnNuevoPaciente.addActionListener((a) -> {//este metodo es para registrar los pacientes cuando el paciente es distinto al solicitante
//            
//        });
//        
//        btnRegisterPaciente.addActionListener((aa) -> {
//            
//        });
//        
//        if_.add(NewPatient_Panel);//me aniade el panel al frame
//
//    }//Fin del metodo ibtnIngresarPacienteaddActionListener
    
    
    
    
    
    
    
    
    
    
    /**
     * Método que crea los componentes en el NewPatient_Panel 
     *
     * @return Muestra los componentes en el NewPatient_Panel
    */
    private void Ingresar(iFrame if_) {
        
        ComponentesSolicitante();//cargo el metodo de los componentes swing de Solicitante
        ComponentesPaciente();//cargo el metodo de los componentes swing de Paciente
        ComponentesPacienteT();
        
        chk_boxSolicitanteDiferentePaciente = new JCheckBox();
        chk_boxSolicitanteDiferentePaciente.setText("PACIENTE != Solicitante".toUpperCase());
        chk_boxSolicitanteDiferentePaciente.setBackground(Color.GRAY);
        
        cbo_CargarParentesco();
        cbo_CargarClasificacionPaciente();
        cbo_CargarCurso();
        cbo_CargarHorario();
        cbo_CargarTipoPaciente();
        cbo_CargarIsNonGrato();//recien agregado
        
        /////////////////componente,/////ancho,largo,posision//
        NewPatient_Panel.AddObject(lbl_LogoULatina, 415, 120, 10);
        NewPatient_Panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);
        NewPatient_Panel.newLine();  
        
        NewPatient_Panel.AddObject(vacio, 0,30,2);//esto fue agregado para hacer salto de linea en el mismo codigo
        NewPatient_Panel.newLine();//aqui paso de linea al momento de hacer u know
        NewPatient_Panel.AddObject(lbl_TituloSolicitante, 75, 30, 175);
        NewPatient_Panel.AddObject(lbl_TituloPaciente, 56, 30, 800);//para paciente
        NewPatient_Panel.newLine();
        

        NewPatient_Panel.AddObject(lbl_CedulaSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_CedulaPaciente, 146, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_CedulaSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_CedulaPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_NombreSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_NombrePaciente, 230, 30, 600);//para paciente 
        NewPatient_Panel.AddObject(txt_NombreSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_NombrePaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        
        NewPatient_Panel.AddObject(lbl_DireccionSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_FechaNacimientoPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_DireccionSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(FechaNacimientoPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_TelefonoSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_DireccionPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_TelefonoSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_DireccionPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_ProfesionSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_TelefonoPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_ProfesionSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_TelefonoPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_ActividadLaboralSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_ProfesionPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_ActividadLaboralSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_ProfesionPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_MotivoConsultaSolicitante, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_ActividadLaboralPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(txt_MotivoConsultaSolicitante, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_ActividadLaboralPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();

        NewPatient_Panel.AddObject(lbl_FechaReporte, 146, 30, 10);
        NewPatient_Panel.AddObject(lbl_MotivoConsultaPaciente, 230, 30, 600);//para paciente
        NewPatient_Panel.AddObject(FechaReporte, 230, 30, 175);
        NewPatient_Panel.AddObject(txt_MotivoConsultaPaciente, 230, 30, 800);//para paciente
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_ParentescoPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_Parentesco, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_ClasificacionPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_ClasificacionPaciente, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_CursoPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_CursoPaciente, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_HorarioPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_HorarioPaciente, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_DetalleHorarioPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(txt_DetalleHorarioPaciente, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_TipoPaciente, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_TipoPaciente, 230, 30, 800);//
        NewPatient_Panel.newLine();
        NewPatient_Panel.AddObject(lbl_IsNonGrato, 230, 30, 600);//
        NewPatient_Panel.AddObject(cbo_IsNonGrato, 230, 30, 800);//
        NewPatient_Panel.newLine();
        
        NewPatient_Panel.AddObject(lbl_NOTA, 500, 70, 10);
        
        //Fin de crear los TabbedPane
        NewPatient_Panel.newLine();
        NewPatient_Panel.AddObject(chk_boxSolicitanteDiferentePaciente, 175, 30, 10);
        NewPatient_Panel.newLine();
        NewPatient_Panel.AddObject(btnRegister, 175, 30, 10);
        btnNuevoPaciente.setVisible(false);
        NewPatient_Panel.newLine();
        
        setJTexFieldChanged(txt_CedulaSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_NombreSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_DireccionSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_TelefonoSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_ProfesionSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_ActividadLaboralSolicitante);//este metedo es para escribir dinamicamente
        setJTexFieldChanged(txt_MotivoConsultaSolicitante);//este metedo es para escribir dinamicamente
        
        if(chk_boxSolicitanteDiferentePaciente.isSelected()==false){
//            jTabbedPane.setVisible(false);
            TabbedPane_Panel.setVisible(false);//el panel
        }else{
//            jTabbedPane.setVisible(true);
            TabbedPane_Panel.setVisible(true);//el panel
        }
        
        
        
        TabbedPane_Panel.AddObject(lbl_TituloPacienteT, 56, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_CedulaPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_CedulaPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_NombrePacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_NombrePacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_FechaNacimientoPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(FechaNacimientoPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_DireccionPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_DireccionPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_TelefonoPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_TelefonoPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_ProfesionPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_ProfesionPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_ActividadLaboralPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_ActividadLaboralPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_MotivoConsultaPacienteT, 146, 30, 10);//para paciente
        TabbedPane_Panel.AddObject(txt_MotivoConsultaPacienteT, 230, 30, 170);//para paciente
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(lbl_ParentescoPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_ParentescoT, 230, 30, 170);//
        TabbedPane_Panel.newLine();
        
        TabbedPane_Panel.AddObject(lbl_ClasificacionPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_ClasificacionPacienteT, 230, 30, 170);//
        TabbedPane_Panel.newLine();

        TabbedPane_Panel.AddObject(lbl_CursoPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_CursoPacienteT, 230, 30, 170);//
        TabbedPane_Panel.newLine();

        TabbedPane_Panel.AddObject(lbl_HorarioPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_HorarioPacienteT, 230, 30, 170);//
        TabbedPane_Panel.newLine();

        TabbedPane_Panel.AddObject(lbl_DetalleHorarioPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(txt_DetalleHorarioPacienteT, 230, 30, 170);//
        TabbedPane_Panel.newLine();

        TabbedPane_Panel.AddObject(lbl_TipoPacienteT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_TipoPacienteT, 230, 30, 170);//
        TabbedPane_Panel.newLine();
        
        TabbedPane_Panel.AddObject(lbl_IsNonGratoT, 146, 30, 10);//
        TabbedPane_Panel.AddObject(cbo_IsNonGratoT, 230, 30, 170);//
        TabbedPane_Panel.newLine();

        TabbedPane_Panel.AddObject(btnRegisterPacienteT, 175, 30, 170);
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(vacio, 175, 30, 170);
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(vacio, 175, 30, 170);
        TabbedPane_Panel.newLine();
        TabbedPane_Panel.AddObject(btnNuevoPacienteT, 175, 30, 10);
        TabbedPane_Panel.newLine();
        
        
//        //Para crear las ventanas de los JtabbedPanes
//        jTabbedPane = new JTabbedPane();//creo el panel para hacer el JtabbedPane
//        jTabbedPane.setVisible(false);

        
        chk_boxSolicitanteDiferentePaciente.addActionListener((e) -> {
            
            if (chk_boxSolicitanteDiferentePaciente.isSelected() == true) {
                System.out.println("MUESTRA MENSAJE DE CHECKBOX ACTIVO");
//                JOptionPane.showMessageDialog(null, "AHORA SELECCIONO EL CHECKBOX, PACIENTE ES DISTINTO AL SOLICITANTE", "ERROR", JOptionPane.ERROR_MESSAGE);
//                jTabbedPane.setVisible(true);
                btnNuevoPaciente.setVisible(true);
                cbo_Parentesco.setEnabled(true);
                cbo_IsNonGrato.setEnabled(true);
                TabbedPane_Panel.setVisible(true);//el panel
//                btnNuevoPacienteT.setVisible(true);
                
                
                
                
                
                
                
                 
            }else {  
                System.out.println("MUESTRA MENSAJE DE CHECKBOC DESACTIVADO");
//                jTabbedPane.setVisible(false);
                btnNuevoPaciente.setVisible(false);
                cbo_Parentesco.setSelectedItem("Nulo");
                cbo_Parentesco.setEnabled(false);
                cbo_IsNonGrato.setSelectedItem("No");
                cbo_IsNonGrato.setEnabled(false);
                TabbedPane_Panel.setVisible(false);//el panel
//                btnNuevoPacienteT.setVisible(false);
            }
        });
        
        btnRegister.addActionListener((a) -> {
            btn_Register_MouseClicked();
        });
        
        btnNuevoPaciente.addActionListener((a) -> {//este metodo es para registrar los pacientes cuando el paciente es distinto al solicitante
            
        });
        
        btnRegisterPaciente.addActionListener((aa) -> {
            
        });
        
        if_.add(NewPatient_Panel);//me aniade el panel al frame
        

    }//Fin del metodo ibtnIngresarPacienteaddActionListener
    

    
    
    
    
    
    
    
    
    
    /**
     * Método hace inserts de los datos escritos en newPatient
     *
     * @return Ingresa los datos en las tablas
    */
    public void btn_Register_MouseClicked(){

        if(chk_boxSolicitanteDiferentePaciente.isSelected() == false){
        
            System.out.println("El Solicitante es el paciente".toUpperCase());
            
            
            ArrayList<Object> obj1 = new ArrayList();//array para guardar data
//            String query1 = "INSERT INTO JAW_Solicitante(Cedula, Nombre, Direccion, Telefono, Profesion, ActividadLaboral, MotivoConsulta, FechaReporte) VALUES (?,?,?,?,?,?,?,?)";
            String query1 = "SELECT InsertNewRequester(?,?,?,?,?,?,?,?)";
            obj1.addAll(Arrays.asList(  txt_CedulaSolicitante.getText(), txt_NombreSolicitante.getText(),
                                        txt_DireccionSolicitante.getText(), txt_TelefonoSolicitante.getText(),
                                        txt_ProfesionSolicitante.getText(), txt_ActividadLaboralSolicitante.getText(),
                                        txt_MotivoConsultaSolicitante.getText(), fechaActualFechaReporte() ));
        
//            Boolean exq1 = sql.exec(query1, obj1); 
            int exq1 = sql.SelectReturnValue(query1, obj1);
            System.out.println("Valor desde NewPatient " + exq1);
            if (exq1 != 0) {
                JOptionPane.showMessageDialog(null, "SOLICITANTE REGISTRADO EXITOSAMENTE!", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            }else{
                System.out.println("El Solicitante no es el paciente, esta en proceso".toUpperCase());
            }//fin del metodo si solicitante es el paciente 
            
            //ahora si el solicitante se ingreso hago el insert de paciente
            ArrayList<Object> obj2 = new ArrayList();//array para guardar data
            String query2 = "INSERT INTO JAW_Paciente(IdSolicitante, Cedula, Nombre, FechaNacimiento, Direccion, Telefono, Profesion, ActividadLaboral, MotivoConsulta, IdParentesco, IdClasificacionPaciente, IdCurso, IdHorario, DetalleHorario, IdTipoPaciente, IsNonGrato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            obj2.addAll(Arrays.asList(  exq1, txt_CedulaPaciente.getText(),
                                        txt_NombrePaciente.getText(), fechaActualFechaNacimiento(), 
                                        txt_DireccionPaciente.getText(), txt_TelefonoPaciente.getText(),
                                        txt_ProfesionPaciente.getText(), txt_ActividadLaboralPaciente.getText(),
                                        txt_MotivoConsultaPaciente.getText(), cbo_Parentesco.getSelectedIndex()+1, 
                                        cbo_ClasificacionPaciente.getSelectedIndex()+1, cbo_CursoPaciente.getSelectedIndex()+1,
                                        cbo_HorarioPaciente.getSelectedIndex()+1, txt_DetalleHorarioPaciente.getText(),
                                        cbo_TipoPaciente.getSelectedIndex()+1, cbo_IsNonGrato.getSelectedItem().toString() ));
            Boolean exq2 = sql.exec(query2, obj2);
            if (exq2) {
                JOptionPane.showMessageDialog(null, "PACIENTE REGISTRADO EXITOSAMENTE!", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR LOS DATOS", "ERROR", JOptionPane.ERROR_MESSAGE);
            }//fin de if exq2
            
        } else {
//            JOptionPane.showMessageDialog(null, "AHORA SELECCIONO EL CHECKBOX, PACIENTE ES DISTINTO AL SOLICITANTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }//fin de if exq1

        
        
    }//fin del metodo btn_Register_MouseClicked()

    
    /**
     * Método hace que toma el txt y lo hace dinamicamente
     *
     * @return hace que el texto escrito en el txt lo pase al otro txt
    */
    private void setJTexFieldChanged(iTextField txt){
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
    }
 
    private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
            
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
           txtEjemploJTextFieldChanged();
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
            txtEjemploJTextFieldChanged();
        }
    }
    
    
    /**
     * Método le paso por parametro los txt y los hago dinamicos
     *
     * @return seteo los txt y los pasa al otro txt y hace que el txt no se pueda editar 
    */
    private void txtEjemploJTextFieldChanged() {
        //Copiar el contenido del jtextfield al jlabel
        txt_CedulaPaciente.setText(txt_CedulaSolicitante.getText());
        txt_CedulaPaciente.setEnabled(false);
        txt_NombrePaciente.setText(txt_NombreSolicitante.getText());
        txt_NombrePaciente.setEnabled(false);
        txt_DireccionPaciente.setText(txt_DireccionSolicitante.getText());
        txt_DireccionPaciente.setEnabled(false);
        txt_TelefonoPaciente.setText(txt_TelefonoSolicitante.getText());
        txt_TelefonoPaciente.setEnabled(false);
        txt_ProfesionPaciente.setText(txt_ProfesionSolicitante.getText());
        txt_ProfesionPaciente.setEnabled(false);
        txt_ActividadLaboralPaciente.setText(txt_ActividadLaboralSolicitante.getText());
        txt_ActividadLaboralPaciente.setEnabled(false);
        txt_MotivoConsultaPaciente.setText(txt_MotivoConsultaSolicitante.getText());
        txt_MotivoConsultaPaciente.setEnabled(false);
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
        
    private JComboBox cbo_CargarIsNonGrato() {
        String[] fillCbo = {"Si","No"};//para matar gente
        cbo_IsNonGrato = new JComboBox(fillCbo);//para quemar los cuerpos
        return cbo_IsNonGrato;//
    }//Fin del cbo_CargarCurso
    //fin de metodos para cargar cbo`s de Paciente
}//Fin de la clase new patient
