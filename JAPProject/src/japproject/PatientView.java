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
import iComponents.iTableRender.headerRender;
import iComponents.iTextField;
import static japproject.HomePanel.ColorElementsFonts;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorNonEditElementsFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.if_;
import static japproject.JAPProject.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableColumnModel;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author TUMA & Jose
 */
public class PatientView implements ActionListener {

    public JTable RegTable;  //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    private iLabel lbl_LogoULatina;//Lbl para el logo de Ulatina
    private iLabel lbl_LogoPsicologia;//Lbl para el logo de Psicologia

    iLabel SearchBar_lbl;
    iTextField SearchBar_txt;
    JButton ExportDataToExcel;
    Telefonos T;

    //
    public static iPanel PatientView_panel;
    public iPanel CitasEmbedded;

    public static List<String> tbl_Data = new ArrayList();

    //COMPONENTES DE APPOINTMENT EMBEDDED DATA
    public iLabel lblCalendar; //lbl to indicate the user to select a date.
    public iCalendar calendar; //Calendar to filter main appointments view.
    public iTextField txtHiddenSearch; //Filter for the iTable that shows appointments, is hidden from the user.
    public JButton btnViewAll; //Removes filter from txtHidenSearch.
    public JButton btnScheduleAppointment; //Allows the user to schedule a new appointment.
    public JTable tblAppointments; //Table to display all appointments, can be filtered as user requests.
    public JPopupMenu popupCitas;
    public JMenuItem ItemEditarCitas;
    public JMenuItem ItemEliminarCitas;
    public EditAppointmentData EAP;
    public List<String> tbl_DataCitas = new ArrayList();
    public String dataCitas[][];  //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    iScrollPane scrollCitas;

    //FIN COMPONENTES DE APPOINTMENT EMBEDDED DATA
    EditPatient EP;
    //popmenu
    JPopupMenu popup;
    JMenuItem ItemEditar;
    JMenuItem ItemCitas;
    public iScrollPane scrollPane2;
    public String data[][];  //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO

    public PatientView(iFrame if_) {
        currentPanel = "PatientView_panel";  //Assign the value of currentPanel for RemovePanels method which handles panel transitions.        
        try {
            PatientView_panel = new iPanel(0, 70, 100.0f, 100.0f, 0, 0, if_);
            PatientView_panel.setBackground(ColorPanels);

            lbl_LogoULatina = new iLabel("");
            lbl_LogoULatina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO ULATINA.PNG")));
            lbl_LogoPsicologia = new iLabel("");
            lbl_LogoPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/LOGO DE PSICOLOGIA.PNG")));

            ResultSet rr = sql.SELECT("SELECT * FROM JAW_VistaPacientes");//query que selecciona todo de la vista                       
            ArrayList<String> Cols = new ArrayList();

            for (int i = 1; i < rr.getMetaData().getColumnCount() + 1; i++) {
                Cols.add(rr.getMetaData().getColumnName(i));
            }
            Cols.add("Tels");
            DefaultTableModel model = new DefaultTableModel(data, Cols.toArray()); //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
//          Se crea la tabla y se le da los parametros
            SearchBar_txt = new iTextField("", 3);
            SearchBar_txt.addActionListener(this);
            ExportDataToExcel = new JButton("Export to Excel...");
            ExportDataToExcel.setOpaque(false);
            ExportDataToExcel.setContentAreaFilled(false);
            ExportDataToExcel.setBorderPainted(false);
            ExportDataToExcel.setForeground(ColorFonts);
            ExportDataToExcel.setBackground(ColorPanels);
            ExportDataToExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/content/excelicon.PNG")));

            RegTable = new JTable(model);

            RegTable.getTableHeader().setReorderingAllowed(false);
            RegTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            RegTable.setRowSelectionAllowed(true);
            RegTable.setBackground(ColorFonts);

            RegTable.getTableHeader().setDefaultRenderer(new headerRender());
            RegTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            RegTable.setShowGrid(false);
            RegTable.setRowHeight(28);

            popup = new JPopupMenu();
            ItemEditar = new JMenuItem("Editar paciente");
            ItemEditar.addActionListener(this);
            ItemCitas = new JMenuItem("Ver Citas");
            ItemCitas.addActionListener(this);
            popup.add(ItemEditar);
            popup.add(ItemCitas);

            RegTable.setComponentPopupMenu(popup);
            RegTable.addMouseListener(new TableMouseListener(RegTable));

            scrollPane2 = new iScrollPane(RegTable, ColorPanels);
            scrollPane2.setViewportView(RegTable);
            SetColumsSizes();

            if (sql.Exists(rr)) {//verifica que el query sea valido
                try {

                    while (rr.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rr.getMetaData().getColumnCount() + 1];
                        for (int i = 1; i <= rr.getMetaData().getColumnCount(); i++) {
                            row[i - 1] = rr.getObject(i);
                        }
                        row[29] = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PHONE, 20, Color.CYAN.darker());
                        model.insertRow(model.getRowCount(), row);
                    }

                } catch (SQLException ex) {
                    System.out.println("no object fetch'd");
                }
            }

            // Agregar columna de botones  
            Action delete = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = RegTable.getSelectedRow();

                    int IdSolicitante = (int) RegTable.getValueAt(selectedRow, 1); //Value is static here, must change it if model is updated or DB View is updated

                    int IdPaciente = (int) RegTable.getValueAt(selectedRow, 10); //Value is static here, must change it if model is updated or DB View is updated                 

                    T = new Telefonos(IdSolicitante, IdPaciente);   //Calling Telefonos frame                 
                }
            };
            ButtonColumn h = new ButtonColumn(RegTable, delete, 29);
            AddComponentes(scrollPane2);

            ExportDataToExcel.addActionListener((a) -> {
                ExportDataToExcelActions();
            });

            //This Codes adds the row sorter from iTable to the JTable
            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(RegTable.getModel());

            RegTable.setRowSorter(sorter);
            SearchBar_txt.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent de) {
                    if (SearchBar_txt.getText().isEmpty() || SearchBar_txt.getText().equals(SearchBar_txt.getHover())) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + SearchBar_txt.getText()));    //Adding "(?i)" to make regex filter Case Insensitive                    
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    if (SearchBar_txt.getText().isEmpty() || SearchBar_txt.getText().equals(SearchBar_txt.getHover())) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + SearchBar_txt.getText()));    //Adding "(?i)" to make regex filter Case Insensitive                    
                    }

                }

                @Override
                public void changedUpdate(DocumentEvent de) {

                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ExportDataToExcelActions() {
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(RegTable);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();

            int len = filename.length();
            String ext = "";
            String file = "";

            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }

            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            } else {
                file = path + "\\" + filename + ".xls";
            }
            try {
                toExcel(RegTable, new File(file));
            } catch (IOException ex) {
                Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * Metodo para crear el popmenu
     */
    public void LlamarEditPatient(iFrame if_) {
        tbl_Data.clear();
        PatientView_panel.setVisible(false);
        ItemEditarActionListener();
        EP = new EditPatient(if_);
    }

    public void ItemEditarActionListener() {
        int selectedRow = RegTable.getSelectedRow();

        for (int j = 0; j < RegTable.getColumnCount() - 1; j++) {

            tbl_Data.add(RegTable.getColumnName(j) + "-" + RegTable.getValueAt(selectedRow, j).toString());
        }
    }

    /**
     *
     * Metodo para añadir los componentes
     *
     * @param scrollPane2
     */
    public void AddComponentes(iScrollPane scrollPane2) {
        SearchBar_lbl = new iLabel("BUSQUEDA".toUpperCase());
        SearchBar_lbl.setForeground(ColorFonts);

        PatientView_panel.AddObject(lbl_LogoULatina, 618, 120, 10);
        PatientView_panel.AddObject(lbl_LogoPsicologia, 486, 120, 600);//añade los logos oficiales de la clinica y de la universidad latina
        PatientView_panel.newLine();
        PatientView_panel.addSpace(15);

        PatientView_panel.AddObject(SearchBar_lbl, 200, 30, 10);
        PatientView_panel.AddObject(SearchBar_txt, 200, 30, 150);//agrego el titulo para poner verlo con
        PatientView_panel.AddObject(ExportDataToExcel, 250, 30, if_.getWidth() - 280); //Se agrega boton que se encarga de exportar data a Excel.
        SearchBar_lbl.setVisible(true);//lo desactivo para mantener el titulo sin verlo, cuando marque el check se mostrara (true) el titulo
        PatientView_panel.newLine();

        PatientView_panel.AddSingleObject(scrollPane2, 100.0f, 83.5f, CENTER);
        PatientView_panel.newLine();
        PatientView_panel.finalice();
        PatientView_panel.setVisible(true);

    }

    public void SetColumsSizes() {
        RegTable.getColumnModel().getColumn(0).setWidth(140);  //Numero de Expediente

        RegTable.getColumnModel().getColumn(1).setWidth(0);  //IdSolicitante
        RegTable.getColumnModel().getColumn(1).setMinWidth(0); //IdSolicitante
        RegTable.getColumnModel().getColumn(1).setMaxWidth(0); //IdSolicitante

        RegTable.getColumnModel().getColumn(2).setPreferredWidth(140); //CedulaSolicitante
        RegTable.getColumnModel().getColumn(3).setPreferredWidth(140); //Nombre Solicitante
        RegTable.getColumnModel().getColumn(4).setPreferredWidth(140); //Direccion Solicitante
        //RegTable.getColumnModel().getColumn(4).setPreferredWidth(140); //Telefono Solicitante ELIMINADA DE VISTA
        RegTable.getColumnModel().getColumn(5).setPreferredWidth(140); //Profesion Solicitante
        RegTable.getColumnModel().getColumn(6).setPreferredWidth(180); //Actividad Laboral Solicitante
        RegTable.getColumnModel().getColumn(7).setPreferredWidth(180); //MotivoConsulta Solicitante

        RegTable.getColumnModel().getColumn(8).setWidth(0); //IdParentesco
        RegTable.getColumnModel().getColumn(8).setMinWidth(0); //IdParentesco
        RegTable.getColumnModel().getColumn(8).setMaxWidth(0); //IdParentesco

        RegTable.getColumnModel().getColumn(9).setPreferredWidth(140); //Parentesco

        RegTable.getColumnModel().getColumn(10).setWidth(0); //IdPaciente
        RegTable.getColumnModel().getColumn(10).setMinWidth(0); //IdPaciente
        RegTable.getColumnModel().getColumn(10).setMaxWidth(0); //IdPaciente

        RegTable.getColumnModel().getColumn(11).setPreferredWidth(140); //Cedula Paciente
        RegTable.getColumnModel().getColumn(12).setPreferredWidth(140); //Nombre del Paciente
        RegTable.getColumnModel().getColumn(13).setPreferredWidth(140); //Fecha de Nacimiento

        RegTable.getColumnModel().getColumn(14).setWidth(0);  //IdClasificacionPaciente
        RegTable.getColumnModel().getColumn(14).setMinWidth(0); //IdClasificacionPaciente
        RegTable.getColumnModel().getColumn(14).setMaxWidth(0); //IdClasificacionPaciente

        RegTable.getColumnModel().getColumn(15).setPreferredWidth(140); //Clasifiacion Paciente

        RegTable.getColumnModel().getColumn(16).setWidth(0); //IdTipoPaciente
        RegTable.getColumnModel().getColumn(16).setMinWidth(0); //IdTipoPaciente
        RegTable.getColumnModel().getColumn(16).setMaxWidth(0); //IdTipoPaciente 

        RegTable.getColumnModel().getColumn(17).setPreferredWidth(140); //Tipo Paciente
        RegTable.getColumnModel().getColumn(18).setPreferredWidth(400); //Direccion
        //RegTable.getColumnModel().getColumn(19).setPreferredWidth(140); //Telefono Paciente ELIMINADA DE VISTA
        RegTable.getColumnModel().getColumn(19).setPreferredWidth(140); //Profesion        
        RegTable.getColumnModel().getColumn(20).setPreferredWidth(140); //Actividad Laboral        
        RegTable.getColumnModel().getColumn(21).setPreferredWidth(200); //Motivo de la Consulta

        RegTable.getColumnModel().getColumn(22).setWidth(0); //IdHorario
        RegTable.getColumnModel().getColumn(22).setMinWidth(0); //IdHorario
        RegTable.getColumnModel().getColumn(22).setMaxWidth(0); //IdHorario

        RegTable.getColumnModel().getColumn(23).setPreferredWidth(140); //Horario
        RegTable.getColumnModel().getColumn(24).setPreferredWidth(140); //DetalleHorario

        RegTable.getColumnModel().getColumn(25).setWidth(0); //IdCurso
        RegTable.getColumnModel().getColumn(25).setMinWidth(0); //IdCurso
        RegTable.getColumnModel().getColumn(25).setMaxWidth(0); //IdCurso

        RegTable.getColumnModel().getColumn(26).setPreferredWidth(240); //Curso
        RegTable.getColumnModel().getColumn(27).setPreferredWidth(140); //Fecha Llamada
        RegTable.getColumnModel().getColumn(28).setPreferredWidth(140); //Lista Relegados
        RegTable.getColumnModel().getColumn(29).setPreferredWidth(40); //Columna Botones Telefonos

//fin de los parametros de la tabla
    }

    public void toExcel(JTable table, File file) throws IOException {
        try {
            Date date = new Date();
            SimpleDateFormat formatting = new SimpleDateFormat("dd-MM-yyyy");
            HSSFWorkbook fWorkbook = new HSSFWorkbook();                                            //CREATE WORKBOOK USING APACHE POI
            HSSFSheet fSheet = fWorkbook.createSheet("Exportado el " + formatting.format(date));    //CREATE SHEET USING APACHE POI
            TableModel model = table.getModel();
            TableColumnModel tcm = table.getColumnModel();
            HSSFRow hRow = fSheet.createRow((short) 0);
            for (int i = 0; i < tcm.getColumnCount() - 1; i++) {
                if (RegTable.getColumnModel().getColumn(i).getWidth() == 0
                        && RegTable.getColumnModel().getColumn(i).getMinWidth() == 0
                        && RegTable.getColumnModel().getColumn(i).getMaxWidth() == 0) //Checking if column is hidden in model, if it is then don't export it
                {
                    // System.out.println("Column: " + model.getColumnName(i) + "is not exported to excel because is hidden");
                } else {

                    HSSFCell cell = hRow.createCell((short) i);
                    cell.setCellValue(tcm.getColumn(i).getHeaderValue().toString());
                }
            }

            for (int i = 0; i < RegTable.getRowCount(); i++) {
                HSSFRow fRow = fSheet.createRow((short) i + 1);
                for (int j = 0; j < model.getColumnCount() - 1; j++) {
                    if (RegTable.getColumnModel().getColumn(j).getWidth() == 0
                            && RegTable.getColumnModel().getColumn(j).getMinWidth() == 0
                            && RegTable.getColumnModel().getColumn(j).getMaxWidth() == 0) //Checking if column is hidden in model, if it is then don't export it
                    {
                        // System.out.println("Column: " + model.getColumnName(i) + "is not exported to excel because is hidden");
                    } else {
                        HSSFCell cell = fRow.createCell((short) j);
                        cell.setCellValue(model.getValueAt(i, j).toString());
                    }
                }

                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
                fWorkbook.write(bos);
                bos.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //METODOS CITAS EMBEDDED
    public void ComponentesCitasEmbedded(int IdSolicitante, int IdPaciente) {
        try {   
            this.EAP = new EditAppointmentData();
            CitasEmbedded = new iPanel(0, if_.getHeight() / 2, 100.0f, 100.0f, 0, 0, if_);
            CitasEmbedded.setBackground(ColorPanels);
            CitasEmbedded.addSpace(50);

            lblCalendar = new iLabel("Por favor seleccione una fecha: "); //Lbl Guide
            lblCalendar.setForeground(ColorFonts); //Calendar
            calendar = new iCalendar();
            calendar.setForeground(ColorElementsFonts);

            txtHiddenSearch = new iTextField("", 0); //Hidden txt that searches in table and updates result for used depending on selected date, this is not shown to the user.           

            CalendarUpdateTable(calendar); //Calling method to update table dynamically                                   

            btnViewAll = new JButton("Ver todas las citas");
            btnViewAll.setToolTipText("Elimina el filtro y muestra todas las citas");

            CitasEmbedded.addSpace(40); //Leaving space from top

            CitasEmbedded.AddObject(lblCalendar, 190, 30, 50);
            CitasEmbedded.AddObject(calendar, 100, 30, 193 + 47);
            CitasEmbedded.AddObject(btnViewAll, 140, 30, 303 + 47);
            CitasEmbedded.newLine();
            CitasEmbedded.addSpace(15);

            btnViewAll.addActionListener((ae) -> {
                txtHiddenSearch.setText(""); //Deletes filter defined on txtHiddenSearch and show all data

            });

            ArrayList<Object> obj1 = new ArrayList();//array para guardar data de id de Solicitante
            obj1.addAll(Arrays.asList(IdSolicitante,IdPaciente));
            ResultSet rs = sql.SELECT("SELECT * FROM JAW_VistaCitas WHERE IdPaciente = ? AND IdSolicitante = ? order by `Fecha Cita` desc", obj1);

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

            popupCitas = new JPopupMenu();
            ItemEditarCitas = new JMenuItem("Editar Cita");
            ItemEditarCitas.addActionListener(this);
            ItemEliminarCitas = new JMenuItem("Eliminar Cita");
            ItemEliminarCitas.addActionListener(this);

            popupCitas.add(ItemEditarCitas);
            popupCitas.add(ItemEliminarCitas);

            tblAppointments.setComponentPopupMenu(popupCitas);
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
            SetColumsSizesCitas();

            if (sql.Exists(rs)) {//verifica que el query sea valido
                try {

                    while (rs.next()) {//llena los rows de la tabla
                        Object[] row = new Object[rs.getMetaData().getColumnCount() + 1];
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

            // Agregar columna de botones  
            Action wadap = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tblAppointments.getSelectedRow();

                    int IdSolicitante = (int) tblAppointments.getValueAt(selectedRow, 8); //Value is static here, must change it if model is updated or DB View is updated

                    int IdPaciente = (int) tblAppointments.getValueAt(selectedRow, 3); //Value is static here, must change it if model is updated or DB View is updated                 

                    T = new Telefonos(IdSolicitante, IdPaciente);   //Calling Telefonos frame                 
                }
            };
            ButtonColumn h = new ButtonColumn(tblAppointments, wadap, 9);

            CitasEmbedded.addSpace(20);
            CitasEmbedded.AddSingleObject(scrollCitas, 41.5f, 20.0f, CENTER);

            CitasEmbedded.newLine();
            CitasEmbedded.finalice();

            CheckFirstExecution(txtHiddenSearch);

        } catch (SQLException ex) {
            Logger.getLogger(PatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public void ItemEditarActionListenerCitas() {

        int selectedRow = tblAppointments.getSelectedRow();

        for (int j = 0; j < tblAppointments.getColumnCount(); j++) {
            tbl_DataCitas.add(tblAppointments.getColumnName(j) + "-" + tblAppointments.getValueAt(selectedRow, j).toString());
        }
        ArrayList<Object> Values = new ArrayList<>();
        tbl_DataCitas.forEach((cnsmr) -> {
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

    public void CreateEditComponents() {
        CitasEmbedded.addSpace(15);
        
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

        CitasEmbedded.AddObject(lbl_NombrePsicologo, 190, 30, scrollCitas.getLocation().x);
        CitasEmbedded.AddObject(lbl_CedulaPaciente, 190, 30, scrollCitas.getLocation().x + 210);
        CitasEmbedded.AddObject(lbl_Direccion, 380, 30, scrollCitas.getLocation().x + 420);
        CitasEmbedded.AddObject(lbl_FechaCita, 100, 30, scrollCitas.getLocation().x + 840);
        CitasEmbedded.AddObject(lbl_HoraCita, 190, 30, scrollCitas.getLocation().x + 980);

        CitasEmbedded.newLine();
        CitasEmbedded.addSpace(15);

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

        CitasEmbedded.AddObject(txt_NombrePsicologo, 190 , 30,  scrollCitas.getLocation().x);
        CitasEmbedded.AddObject(txt_CedulaPaciente, 190, 30, scrollCitas.getLocation().x + 210);
        CitasEmbedded.AddObject(txt_Direccion, 380, 40, scrollCitas.getLocation().x + 420);
        CitasEmbedded.AddObject(editCalendar, 100, 30, scrollCitas.getLocation().x + 840);
        CitasEmbedded.AddObject(spinnerEditar, 100, 30, scrollCitas.getLocation().x + 980);
        CitasEmbedded.newLine();

        CitasEmbedded.repaint();
    }

    public void ItemEliminarActionListenerCitas() {
        int selectedRow = tblAppointments.getSelectedRow();
        System.out.println("ID CITA: " + tblAppointments.getValueAt(selectedRow, 0).toString());
        ArrayList<Object> objs = new ArrayList();
        objs.addAll(Arrays.asList(tblAppointments.getValueAt(selectedRow, 0).toString()));
        sql.exec("UPDATE JAW_Citas SET `Deleted` = 1 WHERE IdCita = ?", objs);
        
    }

    public void SetColumsSizesCitas() {
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

        tblAppointments.getColumnModel().getColumn(7).setPreferredWidth(140); //FechaCita

        tblAppointments.getColumnModel().getColumn(8).setWidth(0);  //IdSolicitante
        tblAppointments.getColumnModel().getColumn(8).setMinWidth(0);  //IdSolicitante
        tblAppointments.getColumnModel().getColumn(8).setMaxWidth(0);  //IdSolicitante

        tblAppointments.getColumnModel().getColumn(9).setPreferredWidth(40); //Boton Telefonos

    }
    //FIN METODOS CITAS EMBEDDED        
    
    
     /**
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();

        if (menu == ItemEditar) {
            LlamarEditPatient(HomePanel.if_);

        } else if (menu == ItemCitas) {
            scrollPane2.setSize(scrollPane2.getWidth(), scrollPane2.getHeight() / 3);
            PatientView_panel.setSize(PatientView_panel.getWidth(), PatientView_panel.getHeight() / 2);
            
            int selectedRow = RegTable.getSelectedRow();
            int IdSolicitante = (int) RegTable.getValueAt(selectedRow, 1); //Value is static here, must change it if model is updated or DB View is updated
            int IdPaciente = (int) RegTable.getValueAt(selectedRow, 10); //Value is static here, must change it if model is updated or DB View is updated
            ComponentesCitasEmbedded(IdSolicitante, IdPaciente);
                        
            PatientView_panel.repaint();

        } else if (menu == ItemEditarCitas) {
            ItemEditarActionListenerCitas();
        } else if (menu == ItemEliminarCitas) {
            System.out.println("Entro correctamente en ItemEliminar WUJUU!!");
            ItemEliminarActionListenerCitas();
        } else {
            System.out.println("Menu Not Handled");
        }
    }
}
