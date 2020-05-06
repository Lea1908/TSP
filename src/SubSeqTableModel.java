import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class SubSeqTableModel extends DefaultTableModel {
    public static final String[] COLUMN_NAMES = {"Subsequence 1"};

    public SubSeqTableModel() {
        super(COLUMN_NAMES, 0);
    }

    @Override
    public void setNumRows(int rowCount) {
        super.setNumRows(rowCount);
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
        TownData tData = (TownData) rowData.get(0);
        add(tData);
    }

    public boolean isCellEditable(int row, int col) {
        return false;
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