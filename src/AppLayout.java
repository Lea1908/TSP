import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class AppLayout extends JFrame{//inheriting JFrame
    // Initialize Town input field
    static JTextField town_textfield = new JTextField();
    // Initialize x input field
    static JTextField x_textfield = new JTextField();
    // Initialize y input field
    static JTextField y_textfield = new JTextField();


    // Constructor
    AppLayout(){


        // Create Container
        Container container = getContentPane();
        GridBagLayout grid_bag_layout = new GridBagLayout();
        container.setLayout(grid_bag_layout);

        // Initialize clear TSP Button
        JButton clear_tsp = new JButton("Clear TSP");
        // Initialize load TSP Button
        JButton load_tsp = new JButton("Load TSP");

        // Initialize header City Input
        JLabel city_input = new JLabel("City Input");
        city_input.setFont(new Font("Serif", Font.BOLD, 20));
        // Initialize Town input label
        JLabel town_label = new JLabel("Town: ");
        // Initialize x input label
        JLabel x_label = new JLabel("x-coord.: ");
        // Initialize y input label
        JLabel y_label = new JLabel("y-coord.: ");
        // Initialize Add to List Button
        JButton add_to_list = new JButton("Add to list");
        // Initialize Input Table
        JTable input_table = new JTable();
        input_table.setModel(new InputTableModel());

        input_table.setShowGrid(true);
        input_table.setGridColor(Color.GRAY);
        JScrollPane scroll_input_table = new JScrollPane(input_table);
        // Initialize dropdown for deletion
        JComboBox delete_town = new JComboBox();
        //use delete_town.additem(...); in a for loop to add items to the dropdown menu
        delete_town.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }));
        // Initialize delete selected town button
        JButton delete_town_button = new JButton("Delete selected town");

        //Initialize Optional input header
        JLabel optional_input = new JLabel("Optional input");
        optional_input.setFont(new Font("Serif", Font.BOLD, 18));
        // Initialize maximal runtime label
        JLabel max_runtime_label = new JLabel("Max. runtime: ");
        // Initialize maximal runtime textfield
        JTextField max_runtime_textfield = new JTextField("20");
        // Initialize unit label for maximal runtime
        JLabel unit_max_runtime = new JLabel("Seconds");




        //Initialize header Results
        JLabel results = new JLabel("Results");
        results.setFont(new Font("Serif", Font.BOLD, 20));

        // Initialize solution dropdown
        JComboBox solutions = new JComboBox();
        // use solutions.additem(...); in a for loop to add items to the dropdown menu
        solutions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox selectedchoice = (JComboBox) e.getSource();
                if("TEST".equals(selectedchoice.getSelectedItem())){
                    System.out.println("AppLayout");
                }
            }
        });


        addComponent(container, grid_bag_layout, clear_tsp, 0, 0, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, load_tsp, 3, 0, 3, 1, 0.0, 0.0);

        // City Inputs
        addComponent(container, grid_bag_layout, city_input,            0, 1, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, town_label,            0, 2, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, town_textfield,        1, 2, 1, 1, 1.0, 0.0);
        addComponent(container, grid_bag_layout, x_label,               2, 2, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, x_textfield,           3, 2, 1, 1, 1.0, 0.0);
        addComponent(container, grid_bag_layout, y_label,               4, 2, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, y_textfield,           5, 2, 1, 1, 1.0, 0.0);
        addComponent(container, grid_bag_layout, add_to_list,           5, 3, 1, 1, 1.0, 0.0);
        addComponent(container, grid_bag_layout, scroll_input_table,    0, 4, 6, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_town,           0, 5, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_town_button,    3, 5, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, optional_input,        0, 6, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, max_runtime_label,     0, 7, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, max_runtime_textfield, 1, 7, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, unit_max_runtime,      2, 7, 1, 1, 0.0, 0.0);



        // Results
        addComponent(container, grid_bag_layout, results,               6, 0, 6, 1, 6., 0.0);
        addComponent(container, grid_bag_layout, solutions,             6, 1, 6, 1, 6., 0.0);

        // Add listener
        add_to_list.addActionListener(new ButtonListener(input_table));

        // Define basic layout for the main window of the app
        setSize(1000, 800);  // define the size of the window
        setVisible(true);  // we need this to be able to see the application
        setLocation(0,0);  // this moves the window to the position (0, 0)


    }



    static void addComponent(Container container, GridBagLayout grid_bag_layout, Component component,
                             int x, int y, int width, int height, double weightx, double weighty){
        GridBagConstraints grid_bag_const = new GridBagConstraints();
        grid_bag_const.fill = GridBagConstraints.BOTH;
        grid_bag_const.gridx = x;
        grid_bag_const.gridy = y;
        grid_bag_const.gridwidth = width;
        grid_bag_const.gridheight = height;
        grid_bag_const.weightx = weightx;
        grid_bag_const.weighty = weighty;
        grid_bag_layout.setConstraints(component, grid_bag_const);
        container.add(component);


    }


    public static void main(String[] args) {
        new AppLayout();
    }}


class InputTableModel_old extends AbstractTableModel{
    ArrayList<TownData> row_data = new ArrayList<TownData>();
    String[] column_names = {"Town", "x-coord.", "y-coord."};

    public InputTableModel_old(){
        row_data.add(new TownData("Essen", "3", "4"));
    }

    public  int getRowCount(){
        return row_data.size();
    }

    public int getColumnCount(){
        return column_names.length;
    }

    public String getColumnName(int col){
        return column_names[col];
    }

    public Object getValueAt(int row, int col){
        TownData townObj = row_data.get(row);
        switch(col){
            case 0: return townObj.getName();
            case 1: return townObj.getX();
            case 2: return townObj.getY();
            default: return null;
        }
    }

    public  boolean isCellEditable(int row, int col){
        return false;
    }


    public void addData(TownData townObj){
        row_data.add(townObj);
        fireTableRowsInserted(row_data.size() - 1, row_data.size() - 1);
    }

}

class ButtonListener implements ActionListener{
    JTable input_table;
    ButtonListener(JTable table){
        input_table = table;
        System.out.println(input_table.getModel());
    }
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Add to list")){
            addRowData();


        }
    }

    private void addRowData() {
        System.out.println("add to List");
        int t = input_table.getColumnCount();
        System.out.println(t);

        // Collect Data
        String town = AppLayout.town_textfield.getText();
        String x = AppLayout.x_textfield.getText();
        String y = AppLayout.y_textfield.getText();

        if(town.length() != 0 && x.length() != 0 && y.length() != 0){
            DefaultTableModel model = (DefaultTableModel) input_table.getModel();
            Vector data_vec = model.getDataVector();
            Vector data = new Vector(Arrays.asList(new TownData(town, x, y)));
            for(Object obj : data_vec){

                Vector vec =  (Vector)obj;
                String td = (String)vec.get(0);
                System.out.println(td);

            }
            model.addRow(data);


        }
    }

}

class TownData{
    String name;
    String x;
    String y;

    TownData(String name, String x, String y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
    //Integer.parseint()

    public String getName(){
        return this.name;
    }

    public String getX(){
        return this.x;
    }

    public String getY(){
        return this.y;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setX(String x){
        this.x = x;
    }

    public void setY(String y){
        this.y = y;
    }
}
