import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HospitalManagementSystem extends Application{ 
	
	private ArrayList<Staff> staffList = new ArrayList<Staff>(); 
	private ArrayList<Patient> patientList = new ArrayList<Patient>(); 
	private ArrayList<Doctor> doctorList = new ArrayList<Doctor>(); 
	private ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
	private ArrayList<Lab> labList=new ArrayList<Lab>();
	private ArrayList<Facility> facilityList = new ArrayList<Facility>();
	private Scene menu; // Set menu scene for every back button
	
	public void start(Stage primaryStage) { 
		
		staffList.add(new Staff("S1", "Khor Jie Shen", "Security Guard", "Male", 1000)); 
		staffList.add(new Staff("S2", "Tan Yu Keat", "Pharmasist", "Male", 1800)); 
		staffList.add(new Staff("S3", "Taylor Swift", "Nurse", "Female", 1500)); 
		
		patientList.add(new Patient("P1", "Khor Jie Shen", 20, "Male", "HIV", "Success")); 
		patientList.add(new Patient("P2", "Tan Yu Keat", 20, "Male", "Influenza B", "Pending")); 
		patientList.add(new Patient("P3", "Taylor Swift", 35, "Female", "Hypertension", "Failure")); 
		
		doctorList.add(new Doctor("415", "Khor Jie Shen", "Cardiology", "08:30", "UTAR Doctor of MBBS", 100)); 
		doctorList.add(new Doctor("796", "Tan Yu Keat", "Dermatology", "08:00", "UTAR Doctor of MBBS", 101)); 
		doctorList.add(new Doctor("D3", "Taylor Swift", "Psychologist", "12:00", "UTAR Doctor of Psychology", 110)); 
		
		medicineList.add(new Medicine("M1","Paracetamol", "PharmaLife","04/10/2028",12,100));
		medicineList.add(new Medicine("M2","Amoxicillin", "MediCare Ltd","24/10/2028",25,50));
		medicineList.add(new Medicine("M3","Vitamin C", "HealthPlus","19/06/2029",25,100));
		
		labList.add(new Lab("L1","Hematology Lab",570));
		labList.add(new Lab("L2","Microbiology Lab",650));
		labList.add(new Lab("L3","Radiology Lab",456));
		
		facilityList.add(new Facility("F1","Emergency Deparment"));
		facilityList.add(new Facility("F2","Intensive Care Unit (ICU)"));
		facilityList.add(new Facility("F3","Radiology Deparment"));
		
		
		// Create a VBox
		VBox mainLayout = new VBox(20); 
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #f0f4f7;"); 
        
        // Welcome message
        Label welcomeLabel = new Label("Welcome to the Hospital Management System");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Current date time 
        Label timeLabel = new Label();
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48)); 
        timeLabel.setStyle("-fx-text-fill: white;");
        Label dateLabel = new Label();
        dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        dateLabel.setStyle("-fx-text-fill: white;");
        
        VBox dateTimeLabel = new VBox(10); // Create a VBox to hold date time
        dateTimeLabel.setAlignment(Pos.CENTER);
        dateTimeLabel.setPadding(new Insets(20));
        dateTimeLabel.setStyle("-fx-background-color: #212529; -fx-background-radius: 5;"); 
        dateTimeLabel.getChildren().addAll(timeLabel, dateLabel); 
        
        // Generate formatter for date and time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"); 
        
        Timeline timeline = new Timeline(
                // Create a KeyFrame that triggers an event every second.
                new KeyFrame(Duration.seconds(1), event -> {
                    // Get the current date and time.
                    LocalDateTime now = LocalDateTime.now();
                    timeLabel.setText(now.format(timeFormat)); 
                    dateLabel.setText(now.format(dateFormat));
                }) 
        );
        timeline.setCycleCount(Animation.INDEFINITE); 
        timeline.play();
        
		
        // Staff Button
        Button staffBtn = new Button("Staff");
        staffBtn.setPrefWidth(200);
        staffBtn.setPrefHeight(40);
        staffBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        staffBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;");
        staffBtn.setOnAction(e -> Staff.showStaffInfo(primaryStage, staffList, menu)); 
        
        // Doctor Button 
        Button doctorBtn = new Button("Doctor");
        doctorBtn.setPrefWidth(200);
        doctorBtn.setPrefHeight(40);
        doctorBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        doctorBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;"); 
        doctorBtn.setOnAction(e -> {
        	Doctor.showDoctorInfo(primaryStage, doctorList, menu); 
        }); 
        
        // Patient Button 
        Button patientBtn = new Button("Patient");
        patientBtn.setPrefWidth(200);
        patientBtn.setPrefHeight(40);
        patientBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        patientBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;"); 
        patientBtn.setOnAction(e -> {
        	Patient.showPatientInfo(primaryStage, patientList, menu); 
        });
        
        // Medicine Button 
        Button medBtn = new Button("Medicine");
        medBtn.setPrefWidth(200);
        medBtn.setPrefHeight(40);
        medBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        medBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;"); 
        medBtn.setOnAction(e -> {
        	Medicine.findMedicine(primaryStage, medicineList, menu); 
        });
        
        // Lab Button 
        Button labBtn = new Button("Lab");
        labBtn.setPrefWidth(200);
        labBtn.setPrefHeight(40);
        labBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        labBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;"); 
        labBtn.setOnAction(e -> {
        	Lab.labList(primaryStage, labList, menu); 
        });
        
        // Facility Button 
        Button faciBtn = new Button("Facility");
        faciBtn.setPrefWidth(200);
        faciBtn.setPrefHeight(40);
        faciBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        faciBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;"); 
        faciBtn.setOnAction(e -> {
        	Facility.showFacility(primaryStage, facilityList, menu); 
        });
        
        // Exit Button 
        Button exitBtn = new Button("Exit"); 
        exitBtn.setPrefWidth(200);
        exitBtn.setPrefHeight(40);
        exitBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        exitBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 5;"); 
        exitBtn.setOnAction(e -> {
        	primaryStage.close(); 
        });
        
        // Set GridPane for menu button
        GridPane menuPane = new GridPane();
        menuPane.setHgap(20);
        menuPane.setVgap(20);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setStyle("-fx-background-color: #e0eaf0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;"); 
        
        menuPane.addRow(0, staffBtn, doctorBtn); 
        menuPane.addRow(1, patientBtn, medBtn); 
        menuPane.addRow(2, labBtn, faciBtn); 
        
        
        // Main Layout and primary scene set up
        mainLayout.getChildren().addAll(welcomeLabel, dateTimeLabel, menuPane, exitBtn); 
        menu = new Scene(mainLayout, 800, 600); 
        primaryStage.setTitle("Hospital Management System"); 
        primaryStage.setScene(menu); 
        primaryStage.show();
        
	}
	
	
	public static void main(String[] args) {
		launch(args); 
	}
	
}
