import java.util.ArrayList;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class Lab {
	private String id;
	private String lab;
	private int cost;
	
	public Lab(String id, String lab, int cost) {
		this.id=id;
		this.lab=lab;
		this.cost=cost;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Lab(String lab, int cost) {
		this.lab=lab;
		this.cost=cost;
	}
	
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	// Show Medicine Info
    public static void labList(Stage primaryStage, ArrayList<Lab> LabList, Scene menu) {
    	// Heading Label
        Label titleLabel = new Label("Lab");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
    	TableView<Lab> table = new TableView<Lab>(); 
    	TableColumn<Lab, String> idCol = new TableColumn<Lab, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Lab, String>("id")); // Binding id
        table.getColumns().add(idCol); 
    	TableColumn<Lab, String> labCol = new TableColumn<Lab, String>("Lab"); // Column for Medicine name
        labCol.setCellValueFactory(new PropertyValueFactory<Lab, String>("lab")); // Binding name
        table.getColumns().add(labCol); 
        TableColumn<Lab, String> costCol = new TableColumn<Lab, String>("Cost (RM)"); // Column for Medicine manufacturer
        costCol.setCellValueFactory(new PropertyValueFactory<Lab,String>("cost")); // Binding manufacturer
        table.getColumns().add(costCol); 
         
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(LabList); // Insert array list into table
    	
    	// Add and delete button 
    	Button addBtn = new Button("Add Lab Record"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newLab(table, LabList));
        
    	Button deleteBtn = new Button("Delete Lab Record"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deleteLab(table, LabList));
        
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
        backBtn.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white; -fx-background-radius: 5;"); 
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
    
    public static void newLab(TableView<Lab> table, ArrayList<Lab> LabList) { 
    	
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Lab Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Lab Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        // Set up input Area
        TextField idField = new TextField("L"+(LabList.size()+1));
        idField.setEditable(false); // Auto generate id with ArrayList.size()+1
        TextField labField = new TextField();
        TextField costField = new TextField();
        
        
        // Form layout
        addPane.addRow(0, new Label("ID:"), idField);
        addPane.addRow(1, new Label("Lab:"), labField);
        addPane.addRow(2, new Label("Cost (RM):"), costField);
        
        
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setOnAction(e -> {
        	try {
        		Lab newLab = new Lab(idField.getText(),labField.getText(), Integer.parseInt(costField.getText()));
        		LabList.add(newLab); 
        		table.getItems().clear(); 
        		table.getItems().addAll(LabList);
        		addStage.close(); 
        	}
        	catch(NumberFormatException ex){
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Cost should be in integer format");
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
    
    // Delete Lab 
    public static void deleteLab(TableView<Lab> table, ArrayList<Lab> LabList) {
    	Lab selectedItem = table.getSelectionModel().getSelectedItem(); 
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
            	LabList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(LabList);
            }
    	}
    }
    
		
	
}
