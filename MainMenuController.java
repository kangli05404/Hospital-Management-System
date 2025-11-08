package HospitalManagementSystem;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class MainMenuController implements Initializable {

	// ====== General UI ======
	@FXML
	private TextField menuInputField;
	@FXML
	private Label timeLabel;
	private Timeline clockTimeline;
	@FXML
	private StackPane outputPane;
	@FXML
	private VBox WelcomeImg;
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button exitButton;

	// ====== Doctor UI ======
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private ComboBox<String> specialistComboBox;
	@FXML
	private ComboBox<String> workTimeComboBox;
	@FXML
	private ComboBox<String> qualificationComboBox;
	@FXML
	private TextField roomField;

	// ====== Patient UI ======
	@FXML
	private TextField patientIdField;
	@FXML
	private TextField patientNameField;
	@FXML
	private TextField diseaseField;
	@FXML
	private TextField sexField;
	@FXML
	private ComboBox<String> admitStatusComboBox;
	@FXML
	private TextField ageField;

	// ====== Medicine UI ======
	@FXML
	private TextField medNameField;
	@FXML
	private TextField manufacturerField;
	@FXML
	private DatePicker expiryDatePicker;
	@FXML
	private TextField costField;
	@FXML
	private TextField unitsField;

	// ====== Lab UI ======
	@FXML
	private TextField facilityField;
	@FXML
	private TextField labCostField;

	// ====== Facility UI ======
	@FXML
	private TextField facilityNameField;

	// ====== Staff UI (common) ======
	@FXML
	private TextField staffIdField, staffNameField, staffSexField, salaryField;
	@FXML
	private ComboBox<String> designationComboBox, shiftComboBox;
	@FXML
	private TextField floorField, experienceField, locationField;
	@FXML
	private Label shiftLabel, floorLabel, experienceLabel, locationLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		clockTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			String currentTime = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
			if (timeLabel != null)
				timeLabel.setText("Current Date and Time: " + currentTime);
		}), new KeyFrame(Duration.seconds(1)));
		clockTimeline.setCycleCount(Timeline.INDEFINITE);
		clockTimeline.play();

		if (expiryDatePicker != null) {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			expiryDatePicker.setConverter(new StringConverter<LocalDate>() {
				@Override
				public String toString(LocalDate date) {
					return date == null ? "" : fmt.format(date);
				}

				@Override
				public LocalDate fromString(String s) {
					if (s == null || s.trim().isEmpty())
						return null;
					return LocalDate.parse(s, fmt);
				}
			});
			expiryDatePicker.setDayCellFactory(dp -> new DateCell() {
				@Override
				public void updateItem(LocalDate date, boolean empty) {
					super.updateItem(date, empty);
					setDisable(empty || date.isBefore(LocalDate.now()));
				}
			});
		}

		// Doctor form combos
		if (specialistComboBox != null) {
			specialistComboBox.getItems().setAll("Cardiology", "Pediatrics", "Orthopedics", "Neurology", "Oncology");
			specialistComboBox.setPromptText("Select a specialty");
		}
		if (workTimeComboBox != null) {
			workTimeComboBox.getItems().setAll("8AM-4PM", "9AM-5PM", "10AM-6PM", "12PM-8PM", "2PM-10PM");
			workTimeComboBox.setPromptText("Select work time");
		}
		if (qualificationComboBox != null) {
			qualificationComboBox.getItems().setAll("MBBS", "MD");
			qualificationComboBox.setPromptText("Select a qualification");
		}

		// Patient combo
		if (admitStatusComboBox != null) {
			admitStatusComboBox.getItems().setAll("Admitted", "Discharged");
			admitStatusComboBox.setPromptText("Select status");
		}

		// Staff combos
		if (designationComboBox != null) {
			designationComboBox.getItems().setAll("Nurse", "Cleaner", "Lab Technician", "Security");
			designationComboBox.setPromptText("Select designation");
		}
		if (shiftComboBox != null) {
			shiftComboBox.getItems().setAll("Day", "Night", "Rotational");
			shiftComboBox.setPromptText("Select shift");
		}

		if (shiftLabel != null)
			shiftLabel.managedProperty().bind(shiftLabel.visibleProperty());
		if (shiftComboBox != null)
			shiftComboBox.managedProperty().bind(shiftComboBox.visibleProperty());
		if (floorLabel != null)
			floorLabel.managedProperty().bind(floorLabel.visibleProperty());
		if (floorField != null)
			floorField.managedProperty().bind(floorField.visibleProperty());
		if (experienceLabel != null)
			experienceLabel.managedProperty().bind(experienceLabel.visibleProperty());
		if (experienceField != null)
			experienceField.managedProperty().bind(experienceField.visibleProperty());
		if (locationLabel != null)
			locationLabel.managedProperty().bind(locationLabel.visibleProperty());
		if (locationField != null)
			locationField.managedProperty().bind(locationField.visibleProperty());

		if (designationComboBox != null) {
			designationComboBox.valueProperty().addListener((obs, oldV, newV) -> updateSpecificRows(newV));
			updateSpecificRows(designationComboBox.getValue());
		}

		try {
			if (HospitalManagement.doctorList.isEmpty()) {
				HospitalManagement.addDoctor("323", "Teea Kang Li", "Cardiology", "8AM-4PM", "MBBS", 101);
				HospitalManagement.addDoctor("301", "Loke Yan Zhuo", "Pediatrics", "9AM-5PM", "MD", 102);
				HospitalManagement.addDoctor("D001", "Matthew Lim", "Neurology", "10AM-6PM", "MD", 103);
			}
			if (HospitalManagement.patientList.isEmpty()) {
				HospitalManagement.addPatient("P001", "John", "Flu", "M", "Admitted", 25);
				HospitalManagement.addPatient("P002", "Nora", "Covid", "F", "Discharged", 30);
				HospitalManagement.addPatient("P003", "Kumar", "Fracture", "M", "Admitted", 42);
			}
			if (HospitalManagement.labList.isEmpty()) {
				HospitalManagement.addLab("Blood Test", 120);
				HospitalManagement.addLab("X-Ray", 200);
				HospitalManagement.addLab("MRI", 600);
			}
			if (HospitalManagement.facilityList.isEmpty()) {
				HospitalManagement.addFacility("Cafeteria");
				HospitalManagement.addFacility("Ambulance");
				HospitalManagement.addFacility("Pharmacy");
			}
			if (HospitalManagement.medicineList.isEmpty()) {
				HospitalManagement.addMedicine("Paracetamol", "MediPharm", LocalDate.parse("2026-12-01"), 7, 100);
				HospitalManagement.addMedicine("Amoxicillin", "HealWell", LocalDate.parse("2025-09-30"), 10, 50);
				HospitalManagement.addMedicine("Cough Syrup", "PharmaPlus", LocalDate.parse("2027-03-01"), 9, 30);
			}
			if (HospitalManagement.staffList.isEmpty()) {
				HospitalManagement.addNurse("S001", "Fatihah", "Nurse", "F", 3000, "Night");
				HospitalManagement.addLabTech("S002", "Izzudin", "Lab Technician", "M", 4800, 3);
				HospitalManagement.addCleaner("S003", "Fahmin", "Cleaner", "M", 2500, "3rd Floor");
			}
		} catch (Exception e) {
			System.err.println("Error initializing sample data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public TextField getMenuInputField() {
		return menuInputField;
	}

	// ====== Doctor======

	@FXML
	private void saveDoctor() {
		try {
			String id = idField.getText().trim().toUpperCase();
			String name = nameField.getText().trim();
			String specialist = specialistComboBox.getValue();
			String workTime = workTimeComboBox.getValue();
			String qualification = qualificationComboBox.getValue();
			String roomText = roomField.getText().trim();

			Validator.checkNotEmpty(id, "Doctor ID");
			Validator.checkDoctorId(id, "Doctor ID");
			if (HospitalManagement.isDoctorIdExists(id)) {
				throw new IllegalArgumentException("Doctor ID already exists.");
			}

			Validator.checkNotEmpty(name, "Name");
			Validator.checkAlphabeticName(name, "Name");

			Validator.checkNotEmpty(specialist, "Specialist");
			Validator.checkNotEmpty(workTime, "Work Time");

			Validator.checkNotEmpty(qualification, "Qualification");

			Validator.checkNotEmpty(roomText, "Room");
			Validator.checkPositiveInteger(roomText, "Room");

			int room = Integer.parseInt(roomText);
			if (HospitalManagement.isRoomTaken(room)) {
				Optional<Doctor> takenBy = HospitalManagement.doctorList.stream().filter(d -> d.getRoom() == room)
						.findFirst();

				throw new IllegalArgumentException("Room " + room + " is already assigned to Dr. "
						+ takenBy.map(Doctor::getName).orElse("?") + ".");
			}

			HospitalManagement.addDoctor(id, name, specialist, workTime, qualification, room);

			threeButton("Doctor saved successfully!", "Doctor.fxml");
		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showDoctors() {
		String content = HospitalManagement.listDoctorsText();
		if (content.startsWith("No doctors")) {
			new Alert(Alert.AlertType.INFORMATION, content).showAndWait();
			return;
		}
		showCustomDialog("Doctor List", "Doctors in the System", content, "Doctor.fxml");
	}

	// ====== Patient======
	@FXML
	private void savePatient() {
		try {
			String id = patientIdField.getText().trim().toUpperCase();
			String name = patientNameField.getText().trim();
			String disease = diseaseField.getText().trim();
			String sex = sexField.getText().trim().toUpperCase();
			String admit = admitStatusComboBox.getValue();
			String ageText = ageField.getText().trim();

			Validator.checkNotEmpty(id, "Patient ID");
			Validator.checkPatientId(id, "Patient ID");
			if (HospitalManagement.isPatientIdExists(id)) {
				throw new IllegalArgumentException("Patient ID already exists.");
			}
			Validator.checkNotEmpty(name, "Name");
			Validator.checkAlphabeticName(name, "Name");

			Validator.checkNotEmpty(disease, "Disease");
			Validator.checkAlphaNumericWithLetters(disease, "Disease");

			Validator.checkNotEmpty(sex, "Sex");
			Validator.checkSex(sex);

			Validator.checkNotEmpty(admit, "Admit Status");

			Validator.checkNotEmpty(ageText, "Age");
			Validator.checkPositiveInteger(ageText, "Age");

			int age = Integer.parseInt(ageText);
			HospitalManagement.addPatient(id, name, disease, sex, admit, age);

			threeButton("Patient saved successfully!", "Patient.fxml");
		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showPatient() {
		String content = HospitalManagement.listPatientsText();
		if (content.startsWith("No patients")) {
			showAlert(content);
			return;
		}
		showCustomDialog("Patient List", "Patients in the System", content, "Patient.fxml");
	}

	// ====== Medicine======
	@FXML
	private void saveMedicine() {
		try {
			String name = medNameField.getText().trim();
			String manufacturer = manufacturerField.getText().trim();
			LocalDate expiryDate = expiryDatePicker.getValue();
			String costText = costField.getText().trim();
			String unitText = unitsField.getText().trim();

			Validator.checkNotEmpty(name, "Medicine Name");
			Validator.checkAlphaNumericWithLetters(name, "Name");

			Validator.checkNotEmpty(manufacturer, "Manufacturer");
			Validator.checkAlphaNumericWithLetters(manufacturer, "Manufacturer");

			Validator.check(expiryDate != null, "Expiry Date is required.");
			if (HospitalManagement.isDuplicateMedicine(name, manufacturer, expiryDate)) {
				throw new IllegalArgumentException(
						"This medicine already exists (same name, manufacturer, and expiry date).");
			}

			Validator.checkNotEmpty(costText, "Cost");
			Validator.checkPositiveInteger(costText, "Cost");

			Validator.checkNotEmpty(unitText, "Units");
			Validator.checkPositiveInteger(unitText, "Units");

			int cost = Integer.parseInt(costText);
			int units = Integer.parseInt(unitText);

			HospitalManagement.addMedicine(name, manufacturer, expiryDate, cost, units);

			threeButton("Medicine saved successfully!", "Medicine.fxml");
		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showMedicine() {
		String content = HospitalManagement.listMedicinesText();
		if (content.startsWith("No medicines")) {
			showAlert(content);
			return;
		}
		showCustomDialog("Medicine List", "Medicines in the System:", content, "Medicine.fxml");
	}

	// ====== Lab======
	@FXML
	private void saveLab() {
		try {
			String facility = facilityField.getText().trim();
			String costText = labCostField.getText().trim();

			Validator.checkNotEmpty(facility, "Facility");
			Validator.checkValidFacilityName(facility, "Facility");
			if (HospitalManagement.isLabFacilityExists(facility)) {
				throw new IllegalArgumentException("Facility '" + facility + "' already exists.");
			}

			Validator.checkNotEmpty(costText, "Cost");
			Validator.checkPositiveInteger(costText, "Cost");

			int cost = Integer.parseInt(costText);
			HospitalManagement.addLab(facility, cost);

			threeButton("Facility in lab saved successfully!", "Lab.fxml");
		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showLab() {
		String content = HospitalManagement.listLabsText();
		if (content.startsWith("No labs")) {
			showAlert(content);
			return;
		}
		showCustomDialog("Lab List", "Labs in the System:", content, "Lab.fxml");
	}

	// ====== Facility======
	@FXML
	private void saveFacility() {
		try {
			String name = facilityNameField.getText().trim();

			Validator.checkNotEmpty(name, "Facility Name");
			Validator.checkAlphabeticName(name, "Facility Name");
			if (HospitalManagement.isFacilityExists(name)) {
				throw new IllegalArgumentException("Facility '" + name + "' already exists.");
			}

			HospitalManagement.addFacility(name);

			threeButton("Facility saved successfully!", "Facility.fxml");
		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showFacility() {
		String content = HospitalManagement.listFacilitiesText();
		if (content.startsWith("No facilities")) {
			showAlert(content);
			return;
		}
		showCustomDialog("Facility List", "Facilities in the System:", content, "Facility.fxml");
	}

	// ====== Staff======
	@FXML
	private void onDesignationChanged() {
		updateSpecificRows(designationComboBox.getValue());
	}

	private void updateSpecificRows(String sel) {
		String v = (sel == null ? "" : sel.trim());
		boolean isNurse = v.equalsIgnoreCase("Nurse");
		boolean isCleaner = v.equalsIgnoreCase("Cleaner");
		boolean isLabTechnician = v.equalsIgnoreCase("Lab Technician");
		boolean isSecurity = v.equalsIgnoreCase("Security");

		shiftLabel.setVisible(isNurse);
		shiftComboBox.setVisible(isNurse);

		floorLabel.setVisible(isCleaner);
		floorField.setVisible(isCleaner);

		experienceLabel.setVisible(isLabTechnician);
		experienceField.setVisible(isLabTechnician);

		locationLabel.setVisible(isSecurity);
		locationField.setVisible(isSecurity);
	}

	@FXML
	private void saveStaff() {
		try {
			String id = staffIdField.getText().trim().toUpperCase();
			String name = staffNameField.getText().trim();
			String sex = staffSexField.getText().trim().toUpperCase();
			String salTx = salaryField.getText().trim();
			String type = (designationComboBox.getValue() == null) ? "" : designationComboBox.getValue().trim();

			Validator.checkNotEmpty(id, "Staff ID");
			Validator.checkStaffId(id, "Staff ID");
			if (HospitalManagement.isStaffIdExists(id)) {
				throw new IllegalArgumentException("Staff ID " + id + " already exists.");
			}

			Validator.checkNotEmpty(name, "Name");
			Validator.checkAlphabeticName(name, "Name");

			Validator.checkNotEmpty(sex, "Sex");
			Validator.checkSex(sex);

			Validator.checkNotEmpty(salTx, "Salary");
			Validator.checkPositiveInteger(salTx, "Salary");
			int salary = Integer.parseInt(salTx);

			Validator.checkNotEmpty(type, "Designation");

			switch (type.toLowerCase()) {
			case "nurse": {
				boolean hasSel = shiftComboBox != null && shiftComboBox.getSelectionModel().getSelectedItem() != null;
				Validator.check(hasSel, "Shift (for Nurse) is required.");
				String shift = shiftComboBox.getSelectionModel().getSelectedItem();
				HospitalManagement.addNurse(id, name, type, sex, salary, shift);
				break;
			}
			case "cleaner": {
				String floor = (floorField == null) ? "" : floorField.getText().trim();
				Validator.checkNotEmpty(floor, "Floor (for Cleaner)");
				HospitalManagement.addCleaner(id, name, type, sex, salary, floor);
				break;
			}
			case "lab technician": {
				String expText = (experienceField == null) ? "" : experienceField.getText().trim();
				Validator.checkNotEmpty(expText, "Year of Experience (for Lab Technician)");
				Validator.checkPositiveInteger(expText, "Year of Experience");
				int experience = Integer.parseInt(expText);
				HospitalManagement.addLabTech(id, name, type, sex, salary, experience);
				break;
			}
			case "security": {
				String postLocation = (locationField == null) ? "" : locationField.getText().trim();
				Validator.checkNotEmpty(postLocation, "Post Location (for Security)");
				Validator.checkAlphaNumericWithLetters(postLocation, "Post Location");
				HospitalManagement.addSecurity(id, name, type, sex, salary, postLocation);
				break;
			}
			default:
				throw new IllegalArgumentException("Unsupported designation: " + type);
			}

			threeButton("Staff saved successfully!", "Staff.fxml");

			staffIdField.clear();
			staffNameField.clear();
			staffSexField.clear();
			salaryField.clear();
			designationComboBox.getSelectionModel().clearSelection();
			if (shiftComboBox != null) {
				shiftComboBox.getItems().clear();
				shiftComboBox.setValue(null);
			}
			if (floorField != null)
				floorField.clear();
			if (experienceField != null)
				experienceField.clear();
			if (locationField != null)
				locationField.clear();

		} catch (IllegalArgumentException e) {
			showAlert(e.getMessage());
		} catch (Exception e) {
			showAlert("Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void showStaff() {
		String content = HospitalManagement.listStaffText();
		if (content.startsWith("No staff")) {
			showAlert(content);
			return;
		}
		showCustomDialog("Staff List", "Staffs in the System:", content, "Staff.fxml");
	}

	// ====== Navigation (left menu buttons / routes) ======
	@FXML
	public void OpenDoctorMenu(ActionEvent event) {
		loadView("Doctor.fxml");
	}

	@FXML
	public void OpenPatientMenu(ActionEvent event) {
		loadView("Patient.fxml");
	}

	@FXML
	public void OpenMedicineMenu(ActionEvent event) {
		loadView("Medicine.fxml");
	}

	@FXML
	public void OpenLaboratoryMenu(ActionEvent event) {
		loadView("Lab.fxml");
	}

	@FXML
	public void OpenFacilityMenu(ActionEvent event) {
		loadView("Facility.fxml");
	}

	@FXML
	public void OpenStaffMenu(ActionEvent event) {
		loadView("Staff.fxml");
	}

	private void loadView(String fxmlFile) {
		try {
			String path = "/HospitalManagementSystem/" + fxmlFile;
			URL url = getClass().getResource(path);
			if (url == null) {
				showAlert("Could not find FXML at: " + path);
				return;
			}
			FXMLLoader loader = new FXMLLoader(url);
			loader.setController(this); 
			Node view = loader.load();
			outputPane.getChildren().setAll(view);
		} catch (Exception e) {
			Throwable root = e;
			while (root.getCause() != null)
				root = root.getCause();
			showAlert("Failed to load " + fxmlFile + ":\n" + root.getClass().getSimpleName() + ": "
					+ String.valueOf(root.getMessage()));
		}
	}

	@FXML
	private void showWelcomeMessage() {
		outputPane.getChildren().setAll(WelcomeImg);
	}

	@FXML
	private void goBack(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide(); 
	}

	@FXML
	private void exitApp() {
		System.exit(0);
	}

	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Input Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showCustomDialog(String title, String header, String content, String returnFxml) {
		VBox contentVBox = new VBox(10);
		for (String line : content.split("\n")) {
			contentVBox.getChildren().add(new Label(line));
		}
		ScrollPane scrollPane = new ScrollPane(contentVBox);
		scrollPane.setFitToWidth(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		Alert dialog = new Alert(Alert.AlertType.INFORMATION);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.getDialogPane().setContent(scrollPane);

		ButtonType returnBtn = new ButtonType("Add Another New Entry");
		ButtonType mainMenuBtn = new ButtonType("Return to Main Menu");
		ButtonType exitBtn = new ButtonType("Exit Program");
		dialog.getButtonTypes().setAll(returnBtn, mainMenuBtn, exitBtn);

		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
		pane.setMinSize(700, 800);
		pane.setPrefSize(700, 800);
		pane.setMaxSize(700, 800);

		((Button) pane.lookupButton(returnBtn)).setId("saveButton");
		((Button) pane.lookupButton(mainMenuBtn)).setId("mainMenuButton");
		((Button) pane.lookupButton(exitBtn)).setId("exitButton");

		Optional<ButtonType> result = dialog.showAndWait();
		result.ifPresent(button -> {
			if (button == returnBtn) {
				loadView(returnFxml);
			} else if (button == mainMenuBtn) {
				showWelcomeMessage();
			} else if (button == exitBtn) {
				handleExit();
			}
			scrollPane.getStyleClass().add("scroll-pane");
		});
	}

	private void threeButton(String successMessage, String returnFxml) {
		Alert dialog = new Alert(Alert.AlertType.INFORMATION);
		dialog.setTitle("Success");
		dialog.setHeaderText(successMessage);
		dialog.setContentText("What would you like to do next?");

		ButtonType returnBtn = new ButtonType("Add Another New Entry");
		ButtonType mainMenuBtn = new ButtonType("Return to Main Menu");
		ButtonType exitBtn = new ButtonType("Exit Program");
		dialog.getButtonTypes().setAll(returnBtn, mainMenuBtn, exitBtn);

		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
		pane.setPrefWidth(700);

		((Button) pane.lookupButton(returnBtn)).setId("saveButton");
		((Button) pane.lookupButton(mainMenuBtn)).setId("mainMenuButton");
		((Button) pane.lookupButton(exitBtn)).setId("exitButton");

		Optional<ButtonType> result = dialog.showAndWait();
		result.ifPresent(button -> {
			if (button == returnBtn) {
				loadView(returnFxml);
			} else if (button == mainMenuBtn) {
				showWelcomeMessage();
			} else if (button == exitBtn) {
				handleExit();
			}
			pane.getStyleClass().add("dialog-pane");
		});
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
