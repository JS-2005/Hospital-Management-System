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

public class Facility {
	private String id;
	private String facility;
	
	
	public Facility(String id,String facility) {
		this.id=id;
		this.facility=facility;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFacility() {
		return facility;
	}
	
	public void setFacility(String facility) {
		this.facility = facility;
	}




	public static void showFacility(Stage primaryStage, ArrayList<Facility> FacilityList, Scene menu) {
    	// Heading Label
        Label titleLabel = new Label("Facility");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
        TableView<Facility> table = new TableView<Facility>(); 
    	TableColumn<Facility, String> idCol = new TableColumn<Facility, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("id")); // Binding id
        table.getColumns().add(idCol); 
    	
    	TableColumn<Facility, String> FacilityCol = new TableColumn<Facility, String>("Facility"); // Column for Medicine name
        FacilityCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("facility")); // Binding name
        table.getColumns().add(FacilityCol); 
       
         
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(FacilityList); // Insert array list into table
    	
    	// Add and delete button 
    	Button addBtn = new Button("Add FacilityRecord"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newFacility(table, FacilityList));
        
    	Button deleteBtn = new Button("Delete FacilityRecord"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deleteFacility(table, FacilityList));
        
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
	
	// New Facility 
    public static void newFacility(TableView<Facility> table, ArrayList<Facility> FacilityList) { 
    	
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Facility Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Facility Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        // Set up input Area
        TextField idField = new TextField("F"+(FacilityList.size()+1));
        idField.setEditable(false); // Auto generate id with ArrayList.size()+1
        TextField facilityField = new TextField();

        
        // Form layout
        addPane.addRow(0, new Label("ID:"), idField);
        addPane.addRow(1, new Label("Facility Name:"), facilityField);
       
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setOnAction(e -> {
        
        	Facility newFacility = new Facility(idField.getText(), facilityField.getText());
        	FacilityList.add(newFacility); 
        	table.getItems().clear(); 
       		table.getItems().addAll(FacilityList);
       		addStage.close(); 
        	
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
    
 // Delete Facility 
    public static void deleteFacility(TableView<Facility> table, ArrayList<Facility> FacilityList) {
    	Facility selectedItem = table.getSelectionModel().getSelectedItem(); 
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
            	FacilityList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(FacilityList);
            }
    	}
    }

}
