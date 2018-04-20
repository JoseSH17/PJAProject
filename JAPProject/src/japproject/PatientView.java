/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japproject;

import static iComponents.ComponentInterfaz.CENTER;
import iComponents.iButton;
import static japproject.JAPProject.sql;
import iComponents.iFrame;
import iComponents.iLabel;
import iComponents.iPanel;
import iComponents.iScrollPane;
import iComponents.iTable;
import iComponents.iTableRender.headerRender;
import iComponents.iTextField;
import static japproject.HomePanel.ColorFonts;
import static japproject.HomePanel.ColorPanels;
import static japproject.HomePanel.currentPanel;
import static japproject.HomePanel.ColorNonEditElementsFonts;
import static japproject.HomePanel.if_;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import japproject.Telefonos;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.table.TableColumnModel;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;


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
    public static List<String> tbl_Data = new ArrayList();
    EditPatient EP;
    //popmenu
    JPopupMenu popup;
    JMenuItem ItemEditar;
    JMenuItem ItemEditar2;
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
            ItemEditar2 = new JMenuItem("Edit Resize");
            ItemEditar2.addActionListener(this);
            popup.add(ItemEditar);
            popup.add(ItemEditar2);

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

    /**
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menu = (JMenuItem) event.getSource();

        if (menu == ItemEditar) {
            LlamarEditPatient(HomePanel.if_);

        } else if (menu == ItemEditar2) {
            scrollPane2.setSize(scrollPane2.getWidth(), scrollPane2.getHeight() / 2);
            PatientView_panel.repaint();

        }

    }

    public void toExcel(JTable table, File file) throws IOException {
        try {
            Date date = new Date();
            SimpleDateFormat formatting = new SimpleDateFormat("dd-MM-yyyy");                 
            HSSFWorkbook fWorkbook = new HSSFWorkbook();                                            //CREATE WORKBOOK USING APACHE POI
            HSSFSheet fSheet = fWorkbook.createSheet("Exportado el " + formatting.format(date));    //CREATE SHEET USING APACHE POI
            TableModel model =  table.getModel();
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
                HSSFRow fRow = fSheet.createRow((short) i+1);                    
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

    public static void deleteColumn( Sheet sheet, int columnToDelete ){
        int maxColumn = 0;
        for ( int r=0; r < sheet.getLastRowNum()+1; r++ ){
            Row row = sheet.getRow( r );

            // if no row exists here; then nothing to do; next!
            if ( row == null )
                continue;

            // if the row doesn't have this many columns then we are good; next!
            int lastColumn = row.getLastCellNum();
            if ( lastColumn > maxColumn )
                maxColumn = lastColumn;

            if ( lastColumn < columnToDelete )
                continue;

            for ( int x=columnToDelete+1; x < lastColumn + 1; x++ ){
                Cell oldCell    = row.getCell(x-1);
                if ( oldCell != null )
                    row.removeCell( oldCell );

                Cell nextCell   = row.getCell( x );
                if ( nextCell != null ){
                    Cell newCell    = row.createCell( x-1, nextCell.getCellType() );
                    cloneCell(newCell, nextCell);
                }
            }
        }


        // Adjust the column widths
        for ( int c=0; c < maxColumn; c++ ){
            sheet.setColumnWidth( c, sheet.getColumnWidth(c+1) );
        }
    }


    /*
     * Takes an existing Cell and merges all the styles and forumla
     * into the new one
     */
    private static void cloneCell( Cell cNew, Cell cOld ){
        cNew.setCellComment( cOld.getCellComment() );
        cNew.setCellStyle( cOld.getCellStyle() );

        switch ( cNew.getCellType() ){
            case Cell.CELL_TYPE_BOOLEAN:{
                cNew.setCellValue( cOld.getBooleanCellValue() );
                break;
            }
            case Cell.CELL_TYPE_NUMERIC:{
                cNew.setCellValue( cOld.getNumericCellValue() );
                break;
            }
            case Cell.CELL_TYPE_STRING:{
                cNew.setCellValue( cOld.getStringCellValue() );
                break;
            }
            case Cell.CELL_TYPE_ERROR:{
                cNew.setCellValue( cOld.getErrorCellValue() );
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                cNew.setCellFormula( cOld.getCellFormula() );
                break;
            }
        }

    }
    

}
