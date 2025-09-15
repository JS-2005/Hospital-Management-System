import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

public class Doctor {
	
	// Fields for doctor
	private String id; 
	private String name; 
	private String specialist; 
	private String workTime; 
	private String qualification; 
	private int roomNo;
	
	
	// Constructor
	public Doctor(String id, String name, String specialist, String workTime, String qualification, int roomNo) {
		this.id = id;
		this.name = name;
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.roomNo = roomNo;
	} 
	
	
	// Getter
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSpecialist() {
		return specialist;
	}
	public String getWorkTime() {
		return workTime;
	}
	public String getQualification() {
		return qualification;
	}
	public int getRoomNo() {
		return roomNo;
	}
	
	
	// Show Doctor Info
    public static void showDoctorInfo(Stage primaryStage, ArrayList<Doctor> doctorList, Scene menu) {
    	// Heading Label
        Label titleLabel = new Label("Doctor");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
    	TableView<Doctor> table = new TableView<Doctor>(); 
    	TableColumn<Doctor, String> idCol = new TableColumn<Doctor, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id")); // Binding id 
        table.getColumns().add(idCol); 
        TableColumn<Doctor, String> nameCol = new TableColumn<Doctor, String>("Name"); // Column for name 
        nameCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("name")); // Binding name
        table.getColumns().add(nameCol); 
        TableColumn<Doctor, String> specialistCol = new TableColumn<Doctor, String>("Specialist"); // Column for specialist 
        specialistCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialist")); // Binding specialist
        table.getColumns().add(specialistCol); 
        TableColumn<Doctor, Time> workTimeCol = new TableColumn<Doctor, Time>("Work Time"); // Column for workTime 
        workTimeCol.setCellValueFactory(new PropertyValueFactory<Doctor, Time>("workTime")); // Binding workTime 
        table.getColumns().add(workTimeCol); 
        TableColumn<Doctor, String> qualificationCol = new TableColumn<Doctor, String>("Qualification"); // Column for qualification 
        qualificationCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("qualification")); // Binding qualification
        table.getColumns().add(qualificationCol); 
        TableColumn<Doctor, Integer> roomNoCol = new TableColumn<Doctor, Integer>("Room No"); // Column for room no
        roomNoCol.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("roomNo")); // Binding room no 
        table.getColumns().add(roomNoCol); 
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(doctorList); // Insert array list into table
    	
    	// Add and delete button 
    	Button addBtn = new Button("Add Doctor Record"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newDoctor(table, doctorList));
        
    	Button deleteBtn = new Button("Delete Doctor Record"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deleteDoctor(table, doctorList));
        
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
    
    
    // New Doctor 
    public static void newDoctor(TableView<Doctor> table, ArrayList<Doctor> doctorList) { 
    	
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Doctor Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Doctor Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        
        // ID validation 
        boolean isValid = false;
        int idNum = doctorList.size();
        String newId = null; // Initialize newId to a safe default
                
        while (!isValid) {
            String checkId = "D" + idNum;
            boolean hadOccur = false;
            for (int i = 0; i < doctorList.size(); i++) {
                String existedId = doctorList.get(i).getId();
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
        TextField specialistField = new TextField(); 
        
        // Time picker 
        Spinner<Integer> hourField = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12)); 
        Spinner<Integer> minField = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30)); 
        HBox workTimeField = new HBox(); 
        workTimeField.getChildren().addAll(hourField, new Label(" : "), minField); 
        
        TextField qualificationField = new TextField();
        TextField roomNoField = new TextField();
        
        // Form layout
        addPane.addRow(0, new Label("ID:"), idField);
        addPane.addRow(1, new Label("Name:"), nameField);
        addPane.addRow(2, new Label("Specialist:"), specialistField);
        addPane.addRow(3, new Label("Work Time:"), workTimeField);
        addPane.addRow(4, new Label("Qualification:"), qualificationField); 
        addPane.addRow(5, new Label("Room No:"), roomNoField); 
        
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setPrefWidth(100);
        okBtn.setPrefHeight(20);
        okBtn.setOnAction(e -> {
        	String hour = Integer.toString(hourField.getValue()); 
        	String min = Integer.toString(minField.getValue()); 
        	String workTime = null; 
        	try {
        		if(hourField.getValue() < 10) {
        			hour = "0" + hour; 
        		}
        		if(minField.getValue() < 10) {
        			min = "0" + min;
        		}
        		workTime = hour + ":" + min; 
        		Doctor newDoctor = new Doctor(idField.getText(), nameField.getText(), specialistField.getText(), workTime, qualificationField.getText(), Integer.parseInt(roomNoField.getText()));
        		doctorList.add(newDoctor); 
        		table.getItems().clear(); 
        		table.getItems().addAll(doctorList);
        		addStage.close(); 
        	}
        	catch(NumberFormatException ex){
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Room No should be in integer format");
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
        Scene addScene = new Scene(pane, 500, 400); 
        addStage.setScene(addScene); 
        addStage.showAndWait();
        
        
    }
    
    
    // Delete Doctor 
    public static void deleteDoctor(TableView<Doctor> table, ArrayList<Doctor> doctorList) {
    	Doctor selectedItem = table.getSelectionModel().getSelectedItem(); 
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
            	doctorList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(doctorList);
            }
    	}
    }
	
}
