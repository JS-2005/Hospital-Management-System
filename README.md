
# UCCD2044 Object-Oriented Programming (OOP) Assignment: Hospital Management System

## Overview 🏥

This repository contains the solution for the UCCD2044 OOP Programming Assignment, which involves developing a **Hospital Management System (HMS)** using Java. The system is designed to manage various hospital activities and records, including information about patients, doctors, administrative staff, medicine, facilities, and laboratories.

This project was developed in Java using the Eclipse IDE and applies fundamental object-oriented programming (OOP) principles. 

---

## Features ✨

The HMS provides the following key functionalities:

* **Main Menu**: A main menu with six options: Doctors, Patients, Medicine, Laboratories, Facilities, and Staff.
* **Data Management**: The program uses `ArrayLists` to store data for each category (doctors, patients, medicine, laboratories, facilities, and staff).
* **User Interaction**: Users can add new entries or display a list of existing entries for each category[cite: 37].
* **Navigation**: After each operation, the user is prompted to return to the previous selection, return to the main menu, or exit the program.
* **Exception Handling**: The application includes exception handling to manage runtime errors, such as incorrect data types in user input.
* **Graphical User Interface (GUI)**: The application's interface is enhanced with a graphical user interface (GUI) developed using JavaFX technology for improved usability and visual appeal. 

---

## Classes 💻

The program is structured around several classes, each with a specific responsibility:

* `HospitalManagement`: This is the main application class. It contains the `main()` method, controls the program flow, handles user input, and coordinates interactions between other objects. It also declares and initializes arrays for all the other classes with at least three sample objects each.
* `Doctor`: Represents a doctor object with fields for ID, name, specialist, work time, qualification, and room number. Includes methods to create a new doctor (`newDoctor()`) and display their information (`showDoctorInfo()`).
* `Patient`: Represents a patient object with fields for ID, name, disease, sex, admit status, and age. Includes methods to create a new patient (`newPatient()`) and display their information (`showPatientInfo()`).
* `Staff`: Represents a staff member with fields for ID, name, designation, sex, and salary. Includes methods to create a new staff member (`newStaff()`) and display their information (`showStaffInfo()`).
* `Medicine`: Represents a medicine object with fields for name, manufacturer, expiry date, cost, and count. Includes a method to add a new medicine (`newMedicine()`) and a method to display its information (`findMedicine()`).
* `Lab`: Represents a laboratory object with fields for the lab name and cost. Includes methods to add a new lab (`newLab()`) and display the lab list (`labList()`).
* `Facility`: Represents a facility object with a field for the facility name. Includes methods to add a new facility (`newFacility()`) and display the facility information (`showFacility()`). 

---

## How to Run the Program ▶️

1.  **Clone the repository:**
    ```
    git clone https://github.com/JS-2005/Hospital-Management-System.git
    ```
2.  **Open in Eclipse:**
    Import the project into your Eclipse IDE.
3.  **Run the `HospitalManagement.java` file:**
    The program will launch and display a welcome message with the current date and time.
