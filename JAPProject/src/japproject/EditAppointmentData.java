/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
}
