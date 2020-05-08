import EntityManager.CityManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

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
        delete_town.addItem("");
        //use delete_town.additem(...); in a for loop to add items to the dropdown menu
        delete_town.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }));
        // Initialize delete selected town button
        JButton delete_town_button = new JButton("Delete selected town");

        // Initialize Optional input header
        JLabel optional_input = new JLabel("Optional input");
        optional_input.setFont(new Font("Serif", Font.BOLD, 18));
        // Initialize maximal runtime label
        JLabel max_runtime_label = new JLabel("Max. runtime: ");
        // Initialize maximal runtime textfield
        JTextField max_runtime_textfield = new JTextField("20");
        // Initialize unit label for maximal runtime
        JLabel unit_max_runtime = new JLabel("Seconds");
        // Initialize start town dropdown menu
        JComboBox start_town = new JComboBox();
        start_town.addItem("");
        // Initialize start town label
        JLabel start_town_label = new JLabel("Select town to start: ");
        // Initialize end town dropdown menu
        JComboBox end_town = new JComboBox();
        end_town.addItem("");
        // Initialize end town label
        JLabel end_town_label = new JLabel(" Select town to end: ");

        // Initialize Subsequences
        JLabel supseq = new JLabel("Define a subsequence of towns");
        supseq.setFont(new Font("Serif", Font.BOLD, 18));
        //Initialize label to add town...
        JLabel add_town_to = new JLabel("Add the town ");
        // Initialize dropdown menu of the available towns
        JComboBox town_to_add_to_subseq = new JComboBox();
        // Initialize label for subseq
        JLabel to_supseq = new JLabel("to the subsequence");
        // Initialize dropdown for subseq
        JComboBox subseq_dropdown = new JComboBox();
        subseq_dropdown.addItem("Subsequence 1");
        // Initialize do button
        JButton do_add_town = new JButton("Do");

        // Initialize Subsequence table
        List<String> test = new ArrayList<>(5);
        test.add("Essen");
        test.add("Oberhausen");
        test.add("Köstendorf");
        test.add("Dortmund");
        test.add("Bochum");

        //ComboboxTableCellEditor editor = new ComboboxTableCellEditor(test);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Subsequence 1"}, 0);
        JTable subseq_table = new JTable(model);
        //subseq_table.setModel(new SubSeqTableModel());
        //DefaultTableModel model = (DefaultTableModel) subseq_table.getModel();
        //model.setNumRows(5);
        //subseq_table.getColumnModel().getColumn(0).setCellEditor(editor);
        subseq_table.setShowGrid(true);
        subseq_table.setGridColor(Color.GRAY);
        JScrollPane scroll_subseq_table = new JScrollPane(subseq_table);

        // Initialize add new subsequence
        JButton add_new_subsequence = new JButton("Add another subsequence");
        // Initialize dropdown delete subseq
        JComboBox delete_subseq = new JComboBox();
        delete_subseq.addItem("Subsequence 1");
        // Initialize button delete subseq
        JButton delete_subseq_button = new JButton("Delete selected subsequence");


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
        addComponent(container, grid_bag_layout, town_textfield,        1, 2, 1, 1, 0.75, 0.0);
        addComponent(container, grid_bag_layout, x_label,               2, 2, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, x_textfield,           3, 2, 1, 1, 0.75, 0.0);
        addComponent(container, grid_bag_layout, y_label,               4, 2, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, y_textfield,           5, 2, 1, 1, 0.75, 0.0);
        addComponent(container, grid_bag_layout, add_to_list,           5, 3, 1, 1, 1.0, 0.0);
        addComponent(container, grid_bag_layout, scroll_input_table,    0, 4, 6, 2, 0.0, 1.0);
        addComponent(container, grid_bag_layout, delete_town,           0, 6, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_town_button,    3, 6, 3, 1, 0.0, 0.0);

        addComponent(container, grid_bag_layout, optional_input,        0, 7, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, max_runtime_label,     0, 8, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, max_runtime_textfield, 1, 8, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, unit_max_runtime,      2, 8, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, start_town_label,      0, 9, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, start_town,            1, 9, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, end_town_label,        2, 9, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, end_town,              3, 9, 1, 1, 0.0, 0.0);

        addComponent(container, grid_bag_layout, supseq,                0, 10, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, add_town_to,           0, 11, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, town_to_add_to_subseq, 1, 11, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, to_supseq,             2, 11, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, subseq_dropdown,       3, 11, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, do_add_town,           4, 11, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, scroll_subseq_table,   0, 12, 6, 2, 0.0, 1.0);
        addComponent(container, grid_bag_layout, add_new_subsequence,   0, 14, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_subseq,         0, 15, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_subseq_button,  1, 15, 1, 1, 0.0, 0.0);




        // Results
        addComponent(container, grid_bag_layout, results,               6, 0, 6, 1, 6., 0.0);
        addComponent(container, grid_bag_layout, solutions,             6, 1, 6, 1, 6., 0.0);

        // Add listener Input
        add_to_list.addActionListener(new ButtonListenerInput(input_table, delete_town, start_town, end_town, subseq_table));
        delete_town_button.addActionListener(new ButtonListenerInput(input_table, delete_town, start_town, end_town, subseq_table));

        // Add listener Subsequence
        add_new_subsequence.addActionListener(new ButtonListenerSubSeq(subseq_table, input_table));

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
    }
}


class ButtonListenerInput implements ActionListener{
    JTable input_table;
    JComboBox delete_town;
    JComboBox start_town;
    JComboBox end_town;
    JTable subseq_table;

    ButtonListenerInput(JTable table, JComboBox dropdown1, JComboBox dropdown2, JComboBox dropdown3, JTable table2){
        input_table = table;
        delete_town = dropdown1;
        start_town = dropdown2;
        end_town = dropdown3;
        subseq_table = table2;
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Add to list")){
            addRowData();
        }
        else if(command.equals("Delete selected town")){
            deleteTownFromTable();
        }
    }

    private void addRowData() {
        System.out.println("add to List");
        int t = input_table.getColumnCount();
        System.out.println(t);

        // Collect Data
        String town_cand = AppLayout.town_textfield.getText();
        String x_cand = AppLayout.x_textfield.getText();
        String y_cand = AppLayout.y_textfield.getText();

        // Only if all three dataslots are given we are considering the input as a new row entry for the list
        if(town_cand.length() != 0 && x_cand.length() != 0 && y_cand.length() != 0){
            // get the model of our table so that we can use our 'own' methods
            DefaultTableModel model = (DefaultTableModel) input_table.getModel();
            // get the current data of the table stored in a vector
            Vector data_vec = model.getDataVector();
            // create a vector with the row data we would like to add to the table
            Vector data = new Vector(Arrays.asList(new TownData(town_cand, x_cand, y_cand)));

            // initialize a flag which tells us if the new row data will be added or not
            boolean add_row_to_list = true;

            // initialize a list with the towns for the subsequence table which is filled in the loop below
            List<String> towns = new ArrayList<>(data_vec.size() + 1);

            // iterate through the vector of the current data to check if the input the user has made is valid
            for(Object obj : data_vec){
                // get the next rowdata
                Vector vec =  (Vector)obj;

                // get the data contained in the row
                String town = (String)vec.get(0);
                String x = (String)vec.get(1);
                String y = (String)vec.get(2);

                // fill the town list
                towns.add(town);

                /*// insert town in db
                Double x_d = Double.parseDouble(x);
                Double y_d = Double.parseDouble(y);
                CityManager cityManager = new CityManager();
                cityManager.create(town, x_d, y_d);*/
                
                if (town_cand.equals(town) || (x_cand.equals(x) && y_cand.equals(y))){
                    add_row_to_list = false;
                    //TODO construct a warning-pop-up window to tell the user why (s)he could not add his/her input
                }
            }

            if(add_row_to_list){
                // finally add the rowdata candidate to the table
                model.addRow(data);
                towns.add(town_cand);
                // We need to update the dropdown menu for the deletion of an entry
                delete_town.addItem(town_cand);
                start_town.addItem(town_cand);
                end_town.addItem(town_cand);

                DefaultTableModel model_subseq = (DefaultTableModel) subseq_table.getModel();
                model_subseq.setNumRows(data_vec.size());
                int column_num = model_subseq.getColumnCount();
                for(int i=0; i<column_num; i++){
                    ComboboxTableCellEditor editor = new ComboboxTableCellEditor(towns);
                    subseq_table.getColumnModel().getColumn(i).setCellEditor(editor);
                }
            }


        }
    }

    private void deleteTownFromTable(){
        Object obj = delete_town.getSelectedItem();
        String town_to_delete = (String) obj;

        if(!town_to_delete.equals("")){
            // remove the town from the dropdown menu
            delete_town.removeItem(obj);
            start_town.removeItem(obj);
            end_town.removeItem(obj);

            // remove the town from the table
            // get the model of our table so that we can use our 'own' methods
            DefaultTableModel model = (DefaultTableModel) input_table.getModel();
            // get the current data of the table stored in a vector
            Vector data_vec = model.getDataVector();
            int i = 0;
            for(Object object : data_vec){
                //get next rowdata
                Vector row_data = (Vector) object;

                // get the town contained in the row
                String town = (String) row_data.get(0);

                if(town.equals(town_to_delete)){
                    model.removeRow(i);
                    break;
                }
                i++;
            }
        }
        }


}


class ButtonListenerSubSeq implements ActionListener{
    JTable subseq_table;
    JTable input_table;
    ButtonListenerSubSeq(JTable table1, JTable table2){
        subseq_table = table1;
        input_table = table2;
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Add another subsequence")){
            add_subseq();
        }
    }

    private void add_subseq(){
        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model = (DefaultTableModel) subseq_table.getModel();
        int column_num = model.getColumnCount();
        model.addColumn("Subsequence ".concat(String.valueOf(column_num + 1)));
        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model_input = (DefaultTableModel) input_table.getModel();
        // get the current data of the table stored in a vector
        Vector data_vec = model_input.getDataVector();
        // initialize a list with the towns for the subsequence table which is filled in the loop below
        List<String> towns = new ArrayList<>(data_vec.size());
        if(data_vec.size() != 0){
            for(Object obj: data_vec){
                String town = (String)((Vector)obj).get(0);
                towns.add(town);
            }
            for(int i=0; i < subseq_table.getColumnCount(); i++) {
                ComboboxTableCellEditor editor = new ComboboxTableCellEditor(towns);
                subseq_table.getColumnModel().getColumn(i).setCellEditor(editor);
            }
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
