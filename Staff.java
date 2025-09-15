import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Staff { 
	
	// Initialise fields
	private String id;
    private String name;
    private String designation;
    private String sex;
    private int salary; 
    
    // Constructor to initialize a Staff object
    public Staff(String id, String name, String designation, String sex, int salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.sex = sex;
        this.salary = salary;
    }
    
    
    // Getter 
    public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDesignation() {
		return designation;
	}
	public String getSex() {
		return sex;
	}
	public int getSalary() {
		return salary;
	}
    
	
    // Show Staff Info
    public static void showStaffInfo(Stage primaryStage, ArrayList<Staff> staffList, Scene menu) {
    	// Heading Label
        Label titleLabel = new Label("Staff");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
    	TableView<Staff> table = new TableView<Staff>(); 
    	TableColumn<Staff, String> idCol = new TableColumn<Staff, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("id")); // Binding id 
        table.getColumns().add(idCol); 
        TableColumn<Staff, String> nameCol = new TableColumn<Staff, String>("Name"); // Column for name 
        nameCol.setCellValueFactory(new PropertyValueFactory<Staff,String>("name")); // Binding name
        table.getColumns().add(nameCol); 
        TableColumn<Staff, String> designationCol = new TableColumn<Staff, String>("Designation"); // Column for designation 
        designationCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("designation")); // Binding designation
        table.getColumns().add(designationCol); 
        TableColumn<Staff, String> sexCol = new TableColumn<Staff, String>("Sex"); // Column for sex 
        sexCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("sex")); // Binding sex 
        table.getColumns().add(sexCol); 
        TableColumn<Staff, Integer> salaryCol = new TableColumn<Staff, Integer>("Salary (RM)"); // Column for salary
        salaryCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("salary")); // Binding salary 
        table.getColumns().add(salaryCol); 
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(staffList); // Insert array list into table
    	
    	// Add and delete button 
    	Button addBtn = new Button("Add Staff Record"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newStaff(table, staffList));
        
    	Button deleteBtn = new Button("Delete Staff Record"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deleteStaff(table, staffList));
        
        // Set up a HBox for add Delete button 
        HBox btnPane = new HBox(20); 
        btnPane.setPadding(new Insets(20)); 
        btnPane.setAlignment(Pos.CENTER); 
        btnPane.getChildren().addAll(addBtn, deleteBtn); 
        
        // Back Button 
        Button backBtn = new Button("Back to Main Menu"); 
        backBtn.setPrefWidth(420);
        backBtn.setPrefHeight(40);
        backBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        backBtn.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-background-radius: 5;"); 
        backBtn.setOnAction(e -> {
        	primaryStage.setScene(menu); 
        });
    	
    	// Set up a VBox for entire layout
    	VBox pane = new VBox(); 
        pane.setPadding(new Insets(20)); 
        pane.setAlignment(Pos.CENTER); 
        pane.setStyle("-fx-background-color: #f0f4f7;"); 
        pane.getChildren().addAll(titleLabel, table, btnPane, backBtn); 
        
        primaryStage.setScene(new Scene(pane, 800, 600));
    }
    
    // New Staff 
    public static void newStaff(TableView<Staff> table, ArrayList<Staff> staffList) { 
    	
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Staff Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Staff Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        
        // ID validation 
        boolean isValid = false;
        int idNum = staffList.size();
        String newId = null; // Initialize newId to a safe default
                
        while (!isValid) {
            String checkId = "S" + idNum;
            boolean hadOccur = false;
            for (int i = 0; i < staffList.size(); i++) {
                String existedId = staffList.get(i).getId();
                if (checkId.equals(existedId)) {
                    hadOccur = true;
                    break; 
                }
            }
            if (!hadOccur) {
                isValid = true;
                newId = checkId; // Assign the unique ID to newId
            } else {
                idNum++;
            }
        }
                
        // Set up input Area
        TextField idField = new TextField(newId);
        idField.setEditable(false);
        TextField nameField = new TextField();
        TextField designationField = new TextField();
        ComboBox<String> sexField = new ComboBox<String>(); 
        sexField.getItems().addAll("Male", "Female"); 
        sexField.setValue("Male"); 
        TextField salaryField = new TextField();
        
        // Form layout
        addPane.addRow(0, new Label("ID:"), idField);
        addPane.addRow(1, new Label("Name:"), nameField);
        addPane.addRow(2, new Label("Designation:"), designationField);
        addPane.addRow(3, new Label("Sex:"), sexField);
        addPane.addRow(4, new Label("Salary (RM):"), salaryField); 
        
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setPrefWidth(100);
        okBtn.setPrefHeight(20);
        okBtn.setOnAction(e -> {
        	try {
        		Staff newStaff = new Staff(idField.getText(), nameField.getText(), designationField.getText(), sexField.getValue(), Integer.parseInt(salaryField.getText()));
        		staffList.add(newStaff); 
        		table.getItems().clear(); 
        		table.getItems().addAll(staffList);
        		addStage.close(); 
        	}
        	catch(NumberFormatException ex){
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Salary should be in integer format");
                alert.showAndWait();
        	}
        });
        HBox btnPane = new HBox(20); 
        btnPane.setPadding(new Insets(20)); 
        btnPane.setAlignment(Pos.CENTER); 
        btnPane.getChildren().addAll(okBtn); 
        
        // VBox for layout
    	VBox pane = new VBox(); 
        pane.setPadding(new Insets(20)); 
        pane.setAlignment(Pos.CENTER); 
        pane.setStyle("-fx-background-color: #f0f4f7;"); 
        pane.getChildren().addAll(titleLabel, addPane, btnPane); 
        
        // Set up new Stage
        Scene addScene = new Scene(pane, 400, 400); 
        addStage.setScene(addScene); 
        addStage.showAndWait();
        
        
    }
    
    // Delete Staff 
    public static void deleteStaff(TableView<Staff> table, ArrayList<Staff> staffList) {
    	Staff selectedItem = table.getSelectionModel().getSelectedItem(); 
    	if (selectedItem == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No record selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select record to be delete");
            alert.showAndWait();
    	} else {
    		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete the selected item?"); 
            Optional<ButtonType> result = confirmAlert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK) {
            	staffList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(staffList);
            }
    	}
    }
}
