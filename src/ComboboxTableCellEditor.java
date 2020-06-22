import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;

public class ComboboxTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JComboBox editor;
    private List<String> masterValues;

    public ComboboxTableCellEditor(List<String> masterValues) {
        this.editor = new JComboBox();
        this.masterValues = masterValues;
    }

    @Override
    public Object getCellEditorValue() { return editor.getSelectedItem();}

    public void setComboBoxEditor(JComboBox editor) {
        this.editor = editor;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        DefaultComboBoxModel model = new DefaultComboBoxModel(masterValues.toArray(new String[masterValues.size()]));

        for (int index = 0; index < table.getRowCount(); index++) {
            if (index != row) {
                String cellValue = (String) table.getValueAt(index, column);
                if(cellValue != "") {
                    model.removeElement(cellValue);
                }
            }
        }



        editor.setModel(model);
        editor.setSelectedItem(value);

        return editor;

    }
    public Component setSelectedValue(JTable table, Object value, boolean isSelected, int row, int column) {
        editor.setSelectedItem(value);
        return editor;

    }
}

