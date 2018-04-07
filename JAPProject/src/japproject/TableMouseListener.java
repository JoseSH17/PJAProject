package japproject;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

/**
 * A mouse listener for a JTable component. tuma
 *
 */
public class TableMouseListener extends MouseAdapter {

    private JTable table;
    public int currentRow;

    public TableMouseListener(JTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        Point point = event.getPoint();
        currentRow = -1;
        currentRow = table.rowAtPoint(point);
        if (currentRow == -1) {
            System.out.println("jesus se la come");

        }else{
            table.setRowSelectionInterval(currentRow, currentRow);
        }
       

    }
}
