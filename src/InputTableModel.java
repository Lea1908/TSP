import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class InputTableModel extends DefaultTableModel {
    public static final String[] COLUMN_NAMES = {"Town", "x-coord.", "y-coord."};

    public InputTableModel() {
        super(COLUMN_NAMES, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0 && getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public void addRow(Vector<?> rowData) {


TownData tData = (TownData)rowData.get(0);


        add(tData);
    }

    public void add(TownData rowData) {
        if (rowData == null) {
            throw new IllegalArgumentException("rowData cannot be null");
        }
        Vector<Object> rowVector = new Vector<>();
        rowVector.add(rowData.getName());
        rowVector.add(rowData.getX());
        rowVector.add(rowData.getY());

        super.addRow(rowVector);
    }
}
