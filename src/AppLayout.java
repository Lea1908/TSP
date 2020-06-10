import EntityManager.TspEntityManager;
import main.Result;
import main.TSP;
import main.TSPAlgo;
import tsp.model.CityEntity;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class AppLayout extends JFrame{//inheriting JFrame
    // Initialize Town input field
    static JTextField town_textfield = new JTextField();
    // Initialize x input field
    static JTextField x_textfield = new JTextField();
    // Initialize y input field
    static JTextField y_textfield = new JTextField();
    // Declare TSP
    static TSP tsp = new TSP();

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

        // Initialize Subsequence table
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Subsequence 1"}, 0);
        JTable subseq_table = new JTable(new SubSeqTableModel());
        subseq_table.setShowGrid(true);
        subseq_table.setGridColor(Color.GRAY);
        //subseq_table.setAutoCreateColumnsFromModel(false);
        JScrollPane scroll_subseq_table = new JScrollPane(subseq_table);

        // Initialize add new subsequence
        JButton add_new_subsequence = new JButton("Add another subsequence");
        // Initialize dropdown delete subseq
        JComboBox delete_subseq = new JComboBox();
        delete_subseq.addItem("Subsequence 1");
        // Initialize button delete subseq
        JButton delete_subseq_button = new JButton("Delete selected subsequence");

        // Initialize the start calculation button
        JButton start_clac = new JButton("Start calculation");
        // Initialize the save button
        JButton save = new JButton("Save results");


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
        // Init list for result print out
        JList<String> result_print = new JList<>();
        JScrollPane scroll_result_print = new JScrollPane(result_print);

        // Initialize local statistic header
        JLabel local_statistic = new JLabel("Local statistic");
        local_statistic.setFont(new Font("Serif", Font.BOLD, 18));

        // Initialize global statistic header
        JLabel global_statistic = new JLabel("Global statistics");
        global_statistic.setFont(new Font("Serif", Font.BOLD, 18));


        addComponent(container, grid_bag_layout, clear_tsp, 0, 0, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, load_tsp, 3, 0, 3, 1, 1.0, 0.0);

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
        addComponent(container, grid_bag_layout, scroll_subseq_table,   0, 12, 6, 2, 0.0, 1.0);
        addComponent(container, grid_bag_layout, add_new_subsequence,   0, 14, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_subseq,         1, 14, 1, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, delete_subseq_button,  2, 14, 1, 1, 0.0, 0.0);

        addComponent(container, grid_bag_layout, start_clac,            6, 15, 3, 1, 0.0, 0.0);
        addComponent(container, grid_bag_layout, save,                  9, 15, 3, 1, 1.0, 0.0);

        // Results
        addComponent(container, grid_bag_layout, results,               6, 0, 6, 1, 6., 0.0);
        addComponent(container, grid_bag_layout, solutions,             6, 1, 6, 1, 6., 0.0);
        addComponent(container, grid_bag_layout, scroll_result_print,   6, 2, 6, 4, 6., 0.0);

        addComponent(container, grid_bag_layout, local_statistic,       6, 6, 6, 1, 6., 0.0);
        addComponent(container, grid_bag_layout, global_statistic,      6, 11, 6, 1, 6., 0.0);

        // Add listener Input
        add_to_list.addActionListener(new ButtonListenerInput(input_table, delete_town, start_town, end_town, subseq_table));
        delete_town_button.addActionListener(new ButtonListenerInput(input_table, delete_town, start_town, end_town, subseq_table));

        // Add listener Subsequence
        add_new_subsequence.addActionListener(new ButtonListenerSubSeq(subseq_table, input_table, delete_subseq));
        delete_subseq_button.addActionListener(new ButtonListenerSubSeq(subseq_table, input_table, delete_subseq));

        // Add listener for Load TSP and Save TSP
        load_tsp.addActionListener(new ButtonListenerLoadSave());
        save.addActionListener(new ButtonListenerLoadSave());

        // Add listener for calculate TSP and clear TSP
        start_clac.addActionListener(new ButtonListenerCalculate(input_table, solutions, result_print, subseq_table,
                start_town, end_town));
        clear_tsp.addActionListener(new ButtonListenerClear(input_table, subseq_table, solutions, result_print,
                town_textfield, x_textfield, y_textfield, start_town, end_town, delete_subseq, delete_town));

        // Add a Listener to the input table
        //input_table.getModel().addTableModelListener(new ChangeListener(){
            //@Override
            //public void changed(ObservableValue<? extends String> ov, String t, String t1){
                
        //}
        //});

        // Define basic layout for the main window of the app
        setSize(1200, 800);  // define the size of the window
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
            List<CityEntity> cities = new ArrayList<>(data_vec.size() + 1);

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

                // insert town in city list
                Double x_d = Double.parseDouble(x);
                Double y_d = Double.parseDouble(y);
                cities.add(new CityEntity(town, x_d, y_d));
                
                if (town_cand.equals(town) || (x_cand.equals(x) && y_cand.equals(y))){
                    add_row_to_list = false;
                    //TODO construct a warning-pop-up window to tell the user why (s)he could not add his/her input
                    DialogHelper.showWarning("Town " + town + " could not be added because it already exists in the list!");
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

            // set cities of TSP object
            AppLayout.tsp.setCities(cities);
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

            // initialize a list with the towns for the subsequence table which is filled in the loop below
            List<String> towns = new ArrayList<>(data_vec.size());
            List<CityEntity> cities = new ArrayList<>(data_vec.size());
            towns.add("");

            //for(Object object : data_vec){
            int i = 0;
            int number_of_towns = data_vec.size();
            for(int k=0; k < number_of_towns; k++){
                //get next rowdata
                Vector row_data = (Vector) data_vec.get(i);
                // get the town contained in the row
                String town = (String) row_data.get(0);

                if(town.equals(town_to_delete)){
                    model.removeRow(i);
                    // remove from tsp too
                    AppLayout.tsp.deleteCityFromList(town);
                }
                else{
                    i += 1;
                    towns.add(town);
                }
            }

            // TODO Option 1 -> updates the choices for the towns in the subsequences but if the deleted town is not clicked again it will remain there
            // get the model of our table so that we can use our 'own' methods
            /*DefaultTableModel model_input = (DefaultTableModel) input_table.getModel();
            // get the current data of the table stored in a vector
            // initialize a list with the towns for the subsequence table which is filled in the loop below
            List<String> towns = new ArrayList<>(data_vec.size());
            towns.add("");
            if(data_vec.size() != 0){
                for(Object obj_: data_vec){
                    String town = (String)((Vector)obj_).get(0);
                    towns.add(town);
                }
                for(int p=0; p < subseq_table.getColumnCount(); p++) {
                    ComboboxTableCellEditor editor = new ComboboxTableCellEditor(towns);
                    subseq_table.getColumnModel().getColumn(p).setCellEditor(editor);
                }
            }*/

            // TODO Option 2
            // Clear the defined subsequences as they maybe not valid anymore - easiest solution
            DefaultTableModel model_subseq = (DefaultTableModel) subseq_table.getModel();
            System.out.println(model_subseq.getColumnCount());
            int column_count =  model_subseq.getColumnCount();
            for(int j=0; j < column_count; j++){
                System.out.println(j);
                model_subseq.removeRow(0);
            }
            // Initialize again the first, empty subsequence
            model_subseq.setNumRows(data_vec.size());
            model_subseq.addColumn("Subsequence 1");
            ComboboxTableCellEditor editor = new ComboboxTableCellEditor(towns);
            subseq_table.getColumnModel().getColumn(0).setCellEditor(editor);

        } else {
            // TODO maybe different error message when no towns are available
            JOptionPane.showMessageDialog(null, "No town has been selected!", "Warning" , JOptionPane.INFORMATION_MESSAGE);
        }

    }
}


class ButtonListenerSubSeq implements ActionListener{
    JTable subseq_table;
    JTable input_table;
    JComboBox delete_subseq;
    ButtonListenerSubSeq(JTable table1, JTable table2, JComboBox dropdown){
        subseq_table = table1;
        input_table = table2;
        delete_subseq = dropdown;
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Add another subsequence")){
            add_subseq_action();
        }
        else if(command.equals("Delete selected subsequence")){
            delete_subseq_action();
        }
    }

    private void add_subseq_action(){
        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model = (DefaultTableModel) subseq_table.getModel();
        int column_num = model.getColumnCount();
        String subseq_x = "Subsequence ".concat(String.valueOf(column_num + 1));
        // add a new column for the new Subsequence
        model.addColumn(subseq_x);
        // add the new subsequence to the dropdown for deletion of subsequences
        delete_subseq.addItem(subseq_x);
        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model_input = (DefaultTableModel) input_table.getModel();
        // get the current data of the table stored in a vector
        Vector data_vec = model_input.getDataVector();
        // initialize a list with the towns for the subsequence table which is filled in the loop below
        List<String> towns = new ArrayList<>(data_vec.size());
        towns.add("");
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

    private void delete_subseq_action(){
        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model = (DefaultTableModel) subseq_table.getModel();
        int column_to_delete = delete_subseq.getSelectedIndex();
        model.removeRow(column_to_delete);
        Vector<Object> col_names = new Vector<>();
        delete_subseq.removeAllItems();
        for(int i=0; i < subseq_table.getColumnCount(); i++){
            String seq_name = "Subsequence ".concat(String.valueOf(i + 1));
            col_names.add(seq_name);
            delete_subseq.addItem(seq_name);
        }
        model.setColumnIdentifiers(col_names);

        // get the model of our table so that we can use our 'own' methods
        DefaultTableModel model_input = (DefaultTableModel) input_table.getModel();
        // get the current data of the table stored in a vector
        Vector data_vec = model_input.getDataVector();
        // initialize a list with the towns for the subsequence table which is filled in the loop below
        List<String> towns = new ArrayList<>(data_vec.size());
        towns.add("");
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

class ButtonListenerLoadSave implements ActionListener{
    ButtonListenerLoadSave(){

    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Load TSP")){
            load_tsp();
        }
        else if(command.equals("Save results")){
            save_tsp();
        }
    }

    private void load_tsp(){
        // Create JFrame for load dialog
        JFrame loadFrame = DialogHelper.createFrame(600, 600, "Load TSP");

        // maybe create tspEntityManager when AppLayout is created to reduce waiting time
        TspEntityManager tspEntityManager = new TspEntityManager();
                var tspEntities = tspEntityManager.listAllTSPs();
        Vector<String> tsps = tspEntityManager.listAllTSPNames(tspEntities);
        if (tsps.isEmpty()) {
            DialogHelper.showWarning("No TSPs found.");
            return;
        }
        // create dropdown menu
        final JComboBox<String> cb = new JComboBox<String>(tsps);
        cb.setVisible(true);
        // create label for dropdown
        JLabel label = new JLabel();
        label.setText("Load TSP:");

        // create ok button
        JButton button = new JButton("Ok");
        button.setBounds(100,100,140, 40);
        // ok button onclick listener
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadFrame.setVisible(false);
                Integer index = cb.getSelectedIndex();
                var selectedTSP = tspEntities.get(index);
                var loadedTsp = tspEntityManager.loadTSP(selectedTSP);
                AppLayout.tsp = loadedTsp;

                // todo refresh view
                loadFrame.dispose();
            }
        });

        loadFrame.add(label);
        loadFrame.add(cb);
        loadFrame.add(button);
        DialogHelper.displayFrameInCenter(loadFrame);
    }

    private Boolean ready_for_saving() {
        if (AppLayout.tsp.getTsp_result()  == null) {
            return false;
        }
        return true;
    }
    private void save_tsp(){
        if (!ready_for_saving()) {
            DialogHelper.showWarning("Please enter at least 3 towns and start the calculation.");
            return;
        }
        // Create JFrame for save dialog
        JFrame saveFrame = DialogHelper.createFrame(300, 300, "Save TSP");

        // create name input
        JTextField nameField = new JTextField("", 10);
        nameField.setBounds(110, 50, 130, 30);

        // create label for name input
        JLabel label = new JLabel();
        label.setText("TSP Name :");

        // create save button
        JButton button = new JButton("Save");
        button.setBounds(100,100,140, 40);

        // add elements to JFrame
        saveFrame.getContentPane().add(label);
        saveFrame.getContentPane().add(nameField);
        saveFrame.getContentPane().add(button);

        // display in center
        DialogHelper.displayFrameInCenter(saveFrame);

        // Submit button onclick listener
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveFrame.setVisible(false);
                String name = nameField.getText();
                if (name.equals("")) {
                    DialogHelper.showWarning("Please enter a name for your TSP!");
                } else {
                    //AppLayout.tsp_name = name;
                    saveFrame.dispose();
                    AppLayout.tsp.setName(name);
                    Integer tspId = AppLayout.tsp.CreateTSP();
                    // TODO warning if tsp with given name already exists in db
                }
            }
        });

    }
}

class ButtonListenerCalculate implements ActionListener{
    JTable input_table;
    JComboBox solutions;
    JList result_print;
    JTable subseq_table;
    JComboBox start_city;
    JComboBox end_city;
    int start_city_subseq_index;
    int end_city_subseq_index;

    ButtonListenerCalculate(JTable table, JComboBox dropdown, JList list, JTable table2, JComboBox dropdown2, JComboBox dropdown3){
            input_table = table;
            solutions = dropdown;
            result_print = list;
            subseq_table = table2;
            start_city = dropdown2;
            end_city = dropdown3;
            start_city_subseq_index = -1;
            end_city_subseq_index = -1;
        }
        private Boolean ready_for_calculation() {
            if (AppLayout.tsp == null || AppLayout.tsp.getCities() == null || AppLayout.tsp.getCities().size() < 2) {
                return false;
            }
            return true;
        }
        public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Start calculation")){
            if(!ready_for_calculation()) {
                DialogHelper.showWarning("Please enter at least 3 cities to start the calculation!");
                return;
            }
            calculate();
        }
        }

        private Map<String, CityEntity> get_city_dict(Vector data_vec){
            Map<String, CityEntity> city_dict = new HashMap<String, CityEntity>();
            CityEntity[] cities = new CityEntity[data_vec.size()];
            for(Object obj : data_vec){
                // Get the data to fill the dictionary.
                Vector vec = (Vector) obj;
                String town = (String)vec.get(0);
                // TODO The user could enter x or y that is not a number... -> Error
                Double x = Double.parseDouble((String)vec.get(1));
                Double y = Double.parseDouble((String)(vec.get(2)));

                city_dict.put(town, new CityEntity(x, y, town));
            }

            return city_dict;
        }

        private CityEntity[][] get_array_of_subseq(int number_of_subseq, int number_of_cities, DefaultTableModel model_sub,
                                             Map<String, CityEntity> city_dict, CityEntity start, CityEntity end){
            CityEntity[][] subseq_city_arrays = new CityEntity[number_of_subseq][];
            for(int column=0; column < number_of_subseq; column++){
                // We need to use this help vector here bevause we don't know how many cities are contained in the current subseq..
                Vector help = new Vector();
                int row = 0;
                while (model_sub.getValueAt(row, column) != null && model_sub.getValueAt(row, column) != ""
                        && row < number_of_cities)
                {
                    // Get the corresponding City object and add it to help vector.
                    CityEntity city = city_dict.get(model_sub.getValueAt(row, column));
                    if (city != null){
                        help.add(city);
                        city_dict.remove(city.getName(), city);
                    }
                    else{
                        //TODO implement warning & break/return
                        System.out.println("a city as been chosen in two different subseq.");
                    }

                    row++;
                }

                if(help.size() != 0 && help.get(0) == start){
                    start_city_subseq_index = column;
                }
                if(help.size() != 0 && help.get(help.size() - 1) == end){
                    end_city_subseq_index = column;
                }

                // TODO we need to check if start and end city and the subseq are working together

                // Restore the help vctor as City array object.
                CityEntity [] subseq = new CityEntity[help.size()];
                for(int r=0; r < help.size(); r++){
                    subseq[r] = (CityEntity) help.get(r);
                }

                // Store the subseq array for later.
                subseq_city_arrays[column] = subseq;
            }
            return subseq_city_arrays;
        }

        private CityEntity[][] get_city_input(Boolean is_start_city_chosen, Boolean is_end_city_chosen){
            DefaultTableModel model = (DefaultTableModel) input_table.getModel();
            Vector data_vec = model.getDataVector();
            int number_of_cities = data_vec.size();

            // Get the subseq table model
            DefaultTableModel model_sub = (DefaultTableModel) subseq_table.getModel();
            // Count how many subseq the user has defined
            int number_of_subseq = model_sub.getColumnCount();

            // Create a dictionary where the keys are the city names and the values are the corresponding City objects.
            Map<String, CityEntity> city_dict = get_city_dict(data_vec);

            CityEntity start = new CityEntity(-1., -1., " ");
            CityEntity end = new CityEntity(-1., -1., " ");
            if(is_start_city_chosen) {
                start = city_dict.get((String) start_city.getSelectedItem());
                // add start city to TSP
                AppLayout.tsp.setStart_city(start);
            }
            if(is_end_city_chosen) {
                end = city_dict.get((String) end_city.getSelectedItem());
                // add start/EndCity to TSP
                AppLayout.tsp.setStart_city(end);
            }


            // Create a CityEntity array that contains City arrays where each contains one of the defined subsequences.
            CityEntity[][] array_of_subseq = get_array_of_subseq(number_of_subseq, number_of_cities, model_sub, city_dict, start, end);

            // Construct the input for the TSP algorithm which is combination of the subsequences defined above and
            // the cities that are not contained in any subseq..
            CityEntity[][] cities_input = new CityEntity[number_of_subseq + city_dict.size()][];
            int index = 0;
            // Classical TSP input with subseq..
            if(!is_start_city_chosen && !is_end_city_chosen){
                // Add all single cities.
                for(CityEntity city : city_dict.values()){
                    CityEntity[] subseq = new CityEntity[1];
                    subseq[0] = city;
                    cities_input[index] = subseq;
                    index++;
                }
                // Add all city-subseq..
                for(int i_th=0; i_th < array_of_subseq.length; i_th++){
                    CityEntity[] i_th_subseq = array_of_subseq[i_th];
                    cities_input[index] = i_th_subseq;
                    index++;
                }
            }
            // TSP input with defined start and end city..
            else {
                if(is_start_city_chosen) {
                    index = 1;
                    // Add the start city.
                    if (start_city_subseq_index == -1) {
                        CityEntity[] start_subseq = new CityEntity[1];
                        start_subseq[0] = start;
                        cities_input[0] = start_subseq;
                    } else {
                        cities_input[0] = array_of_subseq[start_city_subseq_index];
                    }
                }

                if(is_end_city_chosen) {
                    // Add the end city.
                    if (end_city_subseq_index == -1) {
                        CityEntity[] end_subseq = new CityEntity[1];
                        end_subseq[0] = end;
                        cities_input[number_of_subseq + city_dict.size() - 1] = end_subseq;
                    } else {
                        cities_input[number_of_subseq + city_dict.size() - 1] = array_of_subseq[end_city_subseq_index];
                    }
                }

                // Add all single cities.
                for(CityEntity city : city_dict.values()){
                    if(city != start && city != end){
                        CityEntity[] subseq = new CityEntity[1];
                        subseq[0] = city;
                        cities_input[index] = subseq;
                        index++;
                    }
                }

                // Add all city-subseq..
                for(int i_th=0; i_th < array_of_subseq.length; i_th++){
                    if(i_th != start_city_subseq_index && i_th != end_city_subseq_index) {
                        CityEntity[] i_th_subseq = array_of_subseq[i_th];
                        cities_input[index] = i_th_subseq;
                        index++;
                    }
                }

            }

            return cities_input;
        }

        private void calculate(){
            Boolean is_start_city_chosen = false;
            Boolean is_end_city_chosen = false;

            if(start_city.getSelectedItem() != start_city.getItemAt(0)){
                is_start_city_chosen = true;
            }
            if(end_city.getSelectedItem() != end_city.getItemAt(0)){
                is_end_city_chosen = true;
            }

            CityEntity[][] cities_input = get_city_input(is_start_city_chosen, is_end_city_chosen);

            System.out.println("Start" + start_city.getSelectedItem());
            System.out.println("End" + end_city.getSelectedItem());


            // Call the algo..
            Result result = TSPAlgo.call_tsp(cities_input, is_start_city_chosen, is_end_city_chosen);

            // Prepare the solution for the user...
            solutions.addItem("Solution 1");
            Vector solution_print = new Vector();
            solution_print.add("The optimal length of your tour is: ".concat(String.valueOf(result.getOpt_tour_len())));
            solution_print.add("Visit the towns in the following order: ");
            for(Object obj : result.getBest_tour()){
                CityEntity city = (CityEntity) obj;
                solution_print.add(city.getName());
            }
            result_print.setListData(solution_print);

            // set result in tsp object
            AppLayout.tsp.setTsp_result(result);
        }
}

class ButtonListenerClear implements ActionListener{
    JTable input_table;
    JTable subseq_table;
    JComboBox solutions;
    JList result_print;
    JTextField town;
    JTextField x;
    JTextField y;
    JComboBox start_town;
    JComboBox end_town;
    JComboBox delete_subseq;
    JComboBox delete_town;
    ButtonListenerClear(JTable table, JTable table1, JComboBox dropdown, JList list, JTextField textfield1,
                        JTextField textfield2, JTextField textField3, JComboBox dropdown2, JComboBox dropdown3,
                        JComboBox dropdown4, JComboBox dropdown5){
        input_table = table;
        solutions = dropdown;
        result_print = list;
        subseq_table = table1;
        town = textfield1;
        x = textfield2;
        y = textField3;
        start_town = dropdown2;
        end_town = dropdown3;
        delete_subseq = dropdown4;
        delete_town = dropdown5;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Clear TSP")) {
            clear_tsp();
        }
    }

    private void clear_tsp(){
        input_table.setModel(new InputTableModel());
        subseq_table.setModel(new SubSeqTableModel());
        solutions.removeAllItems();
        result_print.setModel(new DefaultListModel());
        town.setText("");
        x.setText("");
        y.setText("");
        start_town.removeAllItems();
        start_town.addItem("");
        end_town.removeAllItems();
        end_town.addItem("");
        delete_subseq.removeAllItems();
        delete_subseq.addItem("");
        delete_subseq.addItem("Subsequence 1");
        delete_town.removeAllItems();
        delete_town.addItem("");
    }

}

class InputTableListener implements TableModelListener{
    JTable input_table;

    InputTableListener(){

    }

    @Override
    public void tableChanged(TableModelEvent e){
        //if input_table.
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

