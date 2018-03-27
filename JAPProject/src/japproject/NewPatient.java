/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iSQL;
import iComponents.iTextField;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Jose
 */
public class NewPatient {
    
    public final iSQL sql = new iSQL("icomponents.net", "icompone_jose", "icompone_jose", "m70Q(71X7k5v");//hago la conexion a BD
    public static iPanel RePanel;//creo el iPanel
    
    //cbo`s para Paciente
    private JComboBox cbo_TipoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_HorarioPaciente;//son de seleccion por base de datos
    private JComboBox cbo_ClasificacionPaciente;//son de seleccion por base de datos
    private JComboBox cbo_CursoPaciente;//son de seleccion por base de datos
    private JComboBox cbo_Parentesco;//son de seleccion por base de datos
    //fin cbo`s para Paciente
    
    //Controles swing para RePanel
    private JLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private JLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia
    private iButton btnRegisterAction;//Boton para el registrar
    //FIN de Controles swing para RePanel
    
    //Controles Swing para Solicitante
    private JLabel lbl_TituloSolicitante;//Lbl para el titulo de Solicitante
    private JLabel lbl_CedulaSolicitante;//Lbl para la cedula del solicitante
    private iTextField txt_CedulaSolicitante;//TextField para la cedula del solicitante
    private JLabel lbl_NombreSolicitante;//Lbl para el nombre del Solicitante
    private iTextField txt_NombreSolicitante;//TextField para el nombre del solicitante
    private JLabel lbl_DireccionSolicitante;//Lbl para la direccion del Solicitante
    private iTextField txt_DireccionSolicitante;//TextField para la direccion del solicitante(cambiar por JTextArea)
    private JLabel lbl_TelefonoSolicitante;//Lbl para el telefono del Solicitante
    private iTextField txt_TelefonoSolicitante;//TextField para el telefono del solicitante
    private JLabel lbl_ProfesionSolicitante;//Lbl para la profesion del Solicitante
    private iTextField txt_ProfesionSolicitante;//TextField para la profesion del solicitante
    private JLabel lbl_ActividadLaboralSolicitante;//Lbl para la Actividad Laboral del Solicitante
    private iTextField txt_ActividadLaboralSolicitante;//TextField para la Actividad Laboral del solicitante
    private JLabel lbl_MotivoConsultaSolicitante;//Lbl para el Motivo Consulta del Solicitante
    private iTextField txt_MotivoConsultaSolicitante;//TextField para el Motivo Consulta de  Solicitante(cambiar por JTextArea)
    private JLabel lbl_FechaReporte;//Lbl para la FechaReporte del Solicitante
    private iTextField txt_FechaReporte;//TextField para FechaReporte del Solicitante(cambiar por JDateChooser)
    private JLabel lbl_NOTA;//Lbl para la NOTA(si preciona el CheckBox) del Solicitante
    private JCheckBox chk_boxSolicitanteIgualPaciente;//Checkbox para ver si el Solicitante ("""NO""") es el Mismo Paciente
    private JCheckBox chk_boxSolicitanteDiferentePaciente;//Checkbox para ver si el Solicitante ("""NO""") es el Mismo Paciente
    //FIN de Controles Swing para Solicitante
    
    //Controles Swing para Paciente
    private JTabbedPane jTabbedPane0;//creo el JtabbedPane para hacer el JtabbedPane
    private JPanel Panel_tab1;
    private JPanel Panel_tab2;
    private JPanel Panel_tab3;
    
    //Controles swing para paciente
    private iButton btnRegisterActionPaciente;//Boton para registrar el paciente
    private JLabel lbl_TituloPaciente;//Lbl para el Titulo del Paciente
    private JLabel lbl_CedulaPaciente;//Lbl para la Cedula del Solicitante
    private iTextField txt_CedulaPaciente;//TextField para cedula del Solicitante en la parte de paciente(hay que hacerle un setEditable(false))
    private JLabel lbl_CedulaSolicitantePaciente;//Lbl para la Cedula del Paciente
    private iTextField txt_CedulaSolicitantePaciente;//TextField para cedula del Paciente
    private JLabel lbl_NombrePaciente;//Lbl para el Nombre del Paciente
    private iTextField txt_NombrePaciente;//TextField para el Nombre del Paciente
    private JLabel lbl_FechaNacimientoPaciente;//Lbl para la Fecha de Nacimiento del Paciente
    private iTextField txt_FechaNacimientoPaciente;//TextField para la fecha de Nacimiento del Paciente(cambiar por JDateChooser)
    private JLabel lbl_DireccionPaciente;//Lbl para la Direccion del Paciente
    private iTextField txt_DireccionPaciente;//TextField para la Direccion del Paciente
    private JLabel lbl_TelefonoPaciente;//Lbl para la el Telefono del Paciente
    private iTextField txt_TelefonoPaciente;//TextField para el Telefono del Paciente
    private JLabel lbl_ProfesionPaciente;//Lbl para la Profesion del Paciente
    private iTextField txt_ProfesionPaciente;//TextField para la Profesion del Paciente
    private JLabel lbl_ActividadLaboralPaciente;//Lbl para la Actividad Laboral del Paciente
    private iTextField txt_ActividadLaboralPaciente;//TextField para la Actividad Laboral del Paciente
    private JLabel lbl_MotivoConsultaPaciente;//Lbl para la El Motivo Consulta del Paciente
    private iTextField txt_MotivoConsultaPaciente;//TextField para el Motivo Consulta del Paciente(cambiar por JTextArea)
    private JLabel lbl_ParentescoPaciente;//Lbl para el Parentesco del Paciente
    private JLabel lbl_ClasificacionPaciente;//Lbl para la Clasificacion del Paciente
    private JLabel lbl_CursoPaciente;//Lbl para la el Curso del Paciente
    private JLabel lbl_HorarioPaciente;//Lbl para la el Horario del Paciente
    private JLabel lbl_DetalleHorarioPaciente;//Lbl para el Detalle Horario del Paciente
    private iTextField txt_DetalleHorarioPaciente;//TextField para el Detalle Horario del Paciente
    private JLabel lbl_TipoPaciente;//Lbl para el Tipo de Paciente
    //FIN de Controles Swing para Paciente
    
    public NewPatient(iFrame if_) {
    
        RePanel = new iPanel(0, 70, if_.getWidth(), 100.0f, 0, 0, if_);//le doy propiedades al iPanel
        RePanel.setBackground(Color.GREEN);//le doy color al panel
        
        
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
