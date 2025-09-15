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

public class Patient {
	
	// Fields for patient
	private String id;
    private String name; 
    private int age;
    private String sex;
    private String disease;
    private String admitStatus; 
    
    
    // Constructor
	public Patient(String id, String name, int age, String sex, String disease, String admitStatus) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.disease = disease;
		this.admitStatus = admitStatus;
	} 
    
	
	// Getter 
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public String getDisease() {
		return disease;
	}
	public String getAdmitStatus() {
		return admitStatus;
	}
	
	
	// Setter 
	public void setAdmitStatus(String admitStatus) {
		this.admitStatus = admitStatus;
	}

	
	// Show Patient Info
	public static void showPatientInfo(Stage primaryStage, ArrayList<Patient> patientList, Scene menu) {
		// Heading Label
        Label titleLabel = new Label("Patient");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
    	TableView<Patient> table = new TableView<Patient>(); 
    	TableColumn<Patient, String> idCol = new TableColumn<Patient, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id")); // Binding id 
        table.getColumns().add(idCol); 
        TableColumn<Patient, String> nameCol = new TableColumn<Patient, String>("Name"); // Column for name 
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("name")); // Binding name
        table.getColumns().add(nameCol); 
        TableColumn<Patient, Integer> ageCol = new TableColumn<Patient, Integer>("Age"); // Column for age
        ageCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age")); // Binding age 
        table.getColumns().add(ageCol); 
        TableColumn<Patient, String> sexCol = new TableColumn<Patient, String>("Sex"); // Column for sex 
        sexCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sex")); // Binding sex 
        table.getColumns().add(sexCol); 
        TableColumn<Patient, String> diseaseCol = new TableColumn<Patient, String>("Disease"); // Column for disease 
        diseaseCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("disease")); // Binding disease
        table.getColumns().add(diseaseCol); 
        TableColumn<Patient, String> admitCol = new TableColumn<Patient, String>("Admit Status"); // Column for admitStatus 
        admitCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admitStatus")); // Binding admitStatus
        table.getColumns().add(admitCol); 
        
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(patientList); // Insert array list into table
    	
    	// Add, change and delete button 
    	Button addBtn = new Button("Add Patient Record"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newPatient(table, patientList));
        
        Button changeBtn = new Button("Change Admit Status"); 
        changeBtn.setPrefWidth(200);
        changeBtn.setPrefHeight(40);
        changeBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        changeBtn.setStyle("-fx-background-color: #212529; -fx-text-fill: white; -fx-background-radius: 5;"); 
        changeBtn.setOnAction(e -> changeAdmitStatus(table, patientList));
        
    	Button deleteBtn = new Button("Delete Patient Record"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deletePatient(table, patientList));
        
        // Set up a HBox for add Delete button 
        HBox btnPane = new HBox(20); 
        btnPane.setPadding(new Insets(20)); 
        btnPane.setAlignment(Pos.CENTER); 
        btnPane.getChildren().addAll(addBtn, changeBtn, deleteBtn); 
        
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
	
	
	// Add new Patient
    public static void newPatient(TableView<Patient> table, ArrayList<Patient> patientList) {
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Patient Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Patient Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        
        // ID validation 
        boolean isValid = false;
        int idNum = patientList.size();
        String newId = null; // Initialize newId to a safe default
                
        while (!isValid) {
            String checkId = "P" + idNum;
            boolean hadOccur = false;
            for (int i = 0; i < patientList.size(); i++) {
                String existedId = patientList.get(i).getId();
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
        TextField ageField = new TextField();
        TextField diseaseField = new TextField();
        ComboBox<String> sexField = new ComboBox<String>(); 
        sexField.getItems().addAll("Male", "Female"); 
        sexField.setValue("Male"); 
        ComboBox<String> admitField = new ComboBox<String>(); 
        admitField.getItems().addAll("Pending", "Success", "Failure"); 
        admitField.setValue("Pending"); 
        
        
        // Form layout
        addPane.addRow(0, new Label("ID:"), idField);
        addPane.addRow(1, new Label("Name:"), nameField);
        addPane.addRow(2, new Label("Age:"), ageField); 
        addPane.addRow(3, new Label("Sex:"), sexField);
        addPane.addRow(4, new Label("Disease:"), diseaseField); 
        addPane.addRow(5, new Label("Admit Status:"), admitField);
        
        
        
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setPrefWidth(100);
        okBtn.setPrefHeight(20);
        okBtn.setOnAction(e -> {
        	try {
        		Patient newPatient = new Patient(idField.getText(), nameField.getText(), Integer.parseInt(ageField.getText()), sexField.getValue(), diseaseField.getText(), admitField.getValue());
        		patientList.add(newPatient); 
        		table.getItems().clear(); 
        		table.getItems().addAll(patientList);
        		addStage.close(); 
        	}
        	catch(NumberFormatException ex){
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Age should be in integer format");
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
    
    
    // Delete Patient
    public static void deletePatient(TableView<Patient> table, ArrayList<Patient> patientList) {
    	Patient selectedItem = table.getSelectionModel().getSelectedItem(); 
    	if (selectedItem == null) {
    		
    		// No selection alert
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
            	patientList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(patientList);
            }
    	}
    }
    
    
    // Change admit status 
    public static void changeAdmitStatus(TableView<Patient> table, ArrayList<Patient> patientList) {
    	Patient selectedItem = table.getSelectionModel().getSelectedItem(); 
    	if (selectedItem == null) { 
    		
    		// No selection alert
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No record selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select record to change its admit status");
            alert.showAndWait();
            
    	} else {
    		
    		// Create new stage for admit status modify
    		Stage changeStage = new Stage(); 
    		changeStage.setTitle("Change Admit Status"); 
    		
    		// Title
    		Label titleLabel = new Label("Select a new admit status:"); 
    		
    		// Content
    		ComboBox<String> admitField = new ComboBox<String>(); 
    		admitField.setPrefWidth(300);
    	    admitField.setPrefHeight(20);
            admitField.getItems().addAll("Pending", "Success", "Failure"); 
            admitField.setValue("Pending"); 
            
            // Save Button 
            Button saveBtn = new Button("Save"); 
            saveBtn.setPrefWidth(100);
            saveBtn.setPrefHeight(20);
            saveBtn.setOnAction(e -> {
            	patientList.get(patientList.indexOf(selectedItem)).setAdmitStatus(admitField.getValue()); 
            	table.getItems().clear(); 
        		table.getItems().addAll(patientList); 
            	changeStage.close(); 
            });
            HBox btnPane = new HBox(20); 
            btnPane.setPadding(new Insets(20)); 
            btnPane.setAlignment(Pos.CENTER); 
            btnPane.getChildren().addAll(saveBtn); 
            
            // VBox for layout
        	VBox pane = new VBox(10); 
            pane.setPadding(new Insets(10)); 
            pane.setStyle("-fx-background-color: #f0f4f7;"); 
            pane.getChildren().addAll(titleLabel, admitField, btnPane); 
            
            // Set up Stage and Scene
            Scene changeScene = new Scene(pane, 300, 130); 
            changeStage.setScene(changeScene); 
            changeStage.showAndWait();
    		
    	}
    }
}
