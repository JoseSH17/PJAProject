/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jose
 */
public class EditAppointmentData {
    
    String IdCita;
    
    String getIdCita() {
        return IdCita;
    }

    void setIdCita(String IdCita) {
        this.IdCita = IdCita;
    }
    
    String IdPsicologo;
    
    String getIdPsicologo() {
        return IdCita;
    }

    void setIdPsicologo(String IdPsicologo) {
        this.IdPsicologo = IdPsicologo;
    }
    
    
    String NombrePsicologo;
    
    String getNombrePsicologo(){
            return NombrePsicologo;
    }
    
    void setNombrePsicologo(String NombrePsicologo)
    {
    this.NombrePsicologo = NombrePsicologo;
    }
    
    String IdPaciente;
    
    String getIdPaciente() {
        return IdPaciente;
    }

    void setIdPaciente(String IdPaciente) {
        this.IdPaciente = IdPaciente;
    }
    
    String CedulaPaciente;
    
    String getCedulaPaciente() {
        return CedulaPaciente;
    }

    void setCedulaPaciente(String CedulaPaciente) {
        this.CedulaPaciente = CedulaPaciente;
    }
    
    
    String NombrePaciente;
    
    String getNombrePaciente(){
            return NombrePaciente;
    }
    
    void setNombrePaciente(String NombrePaciente)
    {
    this.NombrePaciente = NombrePaciente;
    }
    
    String DireccionPaciente;
    
    String getDireccionPaciente(){
            return DireccionPaciente;
    }
    
    void setDireccionPaciente(String DireccionPaciente)
    {
    this.DireccionPaciente = DireccionPaciente;
    }
    
    
    String TelefonoPaciente;
    
    String getTelefonoPaciente() {
        return TelefonoPaciente;
    }

    void setTelefonoPaciente(String TelefonoPaciente) {
        this.TelefonoPaciente = TelefonoPaciente;
    }
    
    String FechaCita; 
    
    
    String getFechaCita() {
        return FechaCita;
    }

    void setFechaCita(String FechaString) {
            FechaString = FechaString.replace("/", "-");
            FechaString = FechaString.replace("AM", "");
            FechaString = FechaString.replace("PM", "");
            FechaString = FechaString.substring(0, 16);
            FechaString = FechaString.concat(":00.000");

        this.FechaCita = FechaString;
    }
    
    String FechaCitaInsert;
    
    String getFechaCitaInsert(){
    return FechaCitaInsert;
    }
    
    void setFechaCitaInsert(String FechaCitaInsert) throws ParseException
        {
            FechaCitaInsert = FechaCitaInsert.replace("/", "-");
            String anio = FechaCitaInsert.substring(6, 10);
            String mes = FechaCitaInsert.substring(0, 2);
            String dia = FechaCitaInsert.substring(3, 5);            
            FechaCitaInsert = anio + "-" + mes + "-" + dia;
            this.FechaCitaInsert = FechaCitaInsert;
        }
    
    String HoraCita;
    
    String getHoraCita()
    {
        return HoraCita;        
    }
    
    void setHoraCita(String FechaString)
    {
        FechaString = FechaString.substring(11, 19);       
        this.HoraCita = FechaString;
    }
    
    String IdSolicitante;
    
    String getIdSolicitante() {
        return IdCita;
    }

    void setIdSolicitante(String IdSolicitante) {
        this.IdSolicitante = IdSolicitante;
    }
    
}
