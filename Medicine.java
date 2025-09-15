import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
import javafx.util.StringConverter;

public class Medicine {
	private String id;
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int count;
	
	public Medicine(String id, String name, String manufacturer, String expiryDate, int cost, int count) {
		this.id=id;
		this.name=name;
		this.manufacturer=manufacturer;
		this.expiryDate=expiryDate;
		this.cost=cost;
		this.count=count;
		
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

    // Show Medicine Info
    public static void findMedicine(Stage primaryStage, ArrayList<Medicine> MedicineList, Scene menu) {
    	// Heading Label
        Label titleLabel = new Label("Medicine");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
    	// Set up a TableView
    	TableView<Medicine> table = new TableView<Medicine>(); 
    	TableColumn<Medicine, String> idCol = new TableColumn<Medicine, String>("ID"); // Column for id
        idCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("id")); // Binding id
        table.getColumns().add(idCol); 
    	TableColumn<Medicine, String> nameCol = new TableColumn<Medicine, String>("Name"); // Column for Medicine name
        nameCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name")); // Binding name
        table.getColumns().add(nameCol); 
        TableColumn<Medicine, String> manufaCol = new TableColumn<Medicine, String>("Manufacturer"); // Column for Medicine manufacturer
        manufaCol.setCellValueFactory(new PropertyValueFactory<Medicine,String>("manufacturer")); // Binding manufacturer
        table.getColumns().add(manufaCol); 
        TableColumn<Medicine, String> expCol = new TableColumn<Medicine, String>("Expiry Date"); // Column for expiry date
        expCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiryDate")); // Binding designation
        table.getColumns().add(expCol); 
        TableColumn<Medicine,Integer> costCol = new TableColumn<Medicine, Integer>("Cost (RM)"); // Column for cost
        costCol.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("cost")); // Binding cost
        table.getColumns().add(costCol); 
        TableColumn<Medicine, Integer> countCol = new TableColumn<Medicine, Integer>("Count"); // Column for count
        countCol.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("count")); // Binding count
        table.getColumns().add(countCol); 
        
        // Create an observable list from ArrayList
    	table.getItems().addAll(MedicineList); // Insert array list into table
    	
    	// Add and delete button 
    	Button addBtn = new Button("Add Medicine Record"); 
    	addBtn.setPrefWidth(200);
        addBtn.setPrefHeight(40);
        addBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        addBtn.setStyle("-fx-background-color: #198754; -fx-text-fill: white; -fx-background-radius: 5;"); 
        addBtn.setOnAction(e -> newMedicine(table, MedicineList));
        
    	Button deleteBtn = new Button("Delete Medicine Record"); 
    	deleteBtn.setPrefWidth(200);
        deleteBtn.setPrefHeight(40);
        deleteBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        deleteBtn.setOnAction(e -> deleteMedicine(table, MedicineList));
        
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
    
    
 // New Medicine 
    public static void newMedicine(TableView<Medicine> table, ArrayList<Medicine> MedicineList) { 
    	
    	// Set up new Stage
    	Stage addStage = new Stage(); 
        addStage.setTitle("Add Medicine Record"); 
    	
    	// Heading Label
        Label titleLabel = new Label("Add Medicine Record");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    	
    	// Set up input form with GridPane
        GridPane addPane = new GridPane();
        addPane.setHgap(10);
        addPane.setVgap(10);
        addPane.setPadding(new Insets(10));
        addPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");
        
        // Set up input Area
        TextField idField = new TextField("M"+(MedicineList.size()+1));
        idField.setEditable(false); // Auto generate id with ArrayList.size()+1
        TextField nameField = new TextField();
        TextField manuField = new TextField();
        
        DatePicker expDate = new DatePicker();
        expDate.setPromptText("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        expDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, formatter)
                        : null;
            }
        });


        TextField costField = new TextField();
        TextField countField = new TextField();
        
        
        // Form layout
        addPane.addRow(0, new Label("Id::"), idField);
        addPane.addRow(1, new Label("Medicine name:"), nameField);
        addPane.addRow(2, new Label("Manufacturer:"), manuField);
        addPane.addRow(3, new Label("Expiry Date:"), expDate);
        addPane.addRow(4, new Label("Cost (RM):"), costField);
        addPane.addRow(5, new Label("Count:"), countField); 
        
        // OK button 
        Button okBtn = new Button("Add Record"); 
        okBtn.setOnAction(e -> {
        	try {
        		Medicine newMedicine = new Medicine(idField.getText(),nameField.getText(), manuField.getText(), expDate.getEditor().getText(), Integer.parseInt(costField.getText()), Integer.parseInt(countField.getText()));
        		MedicineList.add(newMedicine); 
        		table.getItems().clear(); 
        		table.getItems().addAll(MedicineList);
        		addStage.close(); 
        	}
        	catch(NumberFormatException ex){
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Cost and Count should be in integer format. ");
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
    
 // Delete Medicine
    public static void deleteMedicine(TableView<Medicine> table, ArrayList<Medicine> medicineList) {
    	Medicine selectedItem = table.getSelectionModel().getSelectedItem(); 
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
            	medicineList.remove(selectedItem); 
            	table.getItems().clear(); 
        		table.getItems().addAll(medicineList);
            }
    	}
    }
	


}
