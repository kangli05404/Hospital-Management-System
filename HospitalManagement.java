package HospitalManagementSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HospitalManagement extends Application {

	public static final ArrayList<Doctor> doctorList = new ArrayList<>(25);
	public static final ArrayList<Patient> patientList = new ArrayList<>(100);
	public static final ArrayList<Lab> labList = new ArrayList<>(20);
	public static final ArrayList<Facility> facilityList = new ArrayList<>(20);
	public static final ArrayList<Medicine> medicineList = new ArrayList<>(100);
	public static final ArrayList<Staff> staffList = new ArrayList<>(100);

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		Parent root = loader.load();

		// get controller so we can call its loadView / OpenXxxMenu methods
		MainMenuController controller = loader.getController();

		// find the input field + Go button in the left VBox
		TextField input = (TextField) root.lookup("#menuInputField");
		Button goBtn = (Button) root.lookup("#menuGoBtn");

		// handle “Enter” key
		input.setOnAction(e -> routeSelection(input.getText(), controller));
		// handle “Go” button click
		goBtn.setOnAction(e -> routeSelection(input.getText(), controller));

		Scene scene = new Scene(root, 800, 492);
		scene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hospital Management System");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * if/else (or switch) routing moved into HospitalManagement.java as you wanted
	 */
	private void routeSelection(String raw, MainMenuController controller) {
		if (raw == null)
			return;
		String s = raw.trim().toLowerCase();

		// accept number OR word
		if (s.equals("1") || s.equals("doctor")) {
			controller.OpenDoctorMenu(null); // loads Doctor.fxml into the center StackPane
		} else if (s.equals("2") || s.equals("patient")) {
			controller.OpenPatientMenu(null);
		} else if (s.equals("3") || s.equals("medicine")) {
			controller.OpenMedicineMenu(null);
		} else if (s.equals("4") || s.equals("laboratory") || s.equals("lab")) {
			controller.OpenLaboratoryMenu(null);
		} else if (s.equals("5") || s.equals("facility")) {
			controller.OpenFacilityMenu(null);
		} else if (s.equals("6") || s.equals("staff")) {
			controller.OpenStaffMenu(null);
		} else {
			// Unknown selection: show a small alert via controller helper
			try {
				// If you have controller.showAlert(String)
				java.lang.reflect.Method m = controller.getClass().getDeclaredMethod("showAlert", String.class);
				m.setAccessible(true);
				m.invoke(controller,
						"Invalid selection. Use 1-6 or one of: doctor, patient, medicine, lab, facility, staff.");
			} catch (Exception ignore) {
				/* or log */ }
		}
		TextField input = controller.getMenuInputField();
		if (input != null) {
			input.clear(); // This will clear the text field after processing the selection
		}
	}

	/* ------------------- DOCTOR ------------------- */
	public static boolean isDoctorIdExists(String id) {
		return HospitalManagement.doctorList.stream().anyMatch(d -> d.getId().equalsIgnoreCase(id));
	}

	public static boolean isRoomTaken(int room) {
		return doctorList.stream().anyMatch(d -> d.getRoom() == room);
	}

	public static Doctor addDoctor(String id, String name, String specialist, String workTime, String qualification,
			int room) {
		Doctor d = new Doctor(id, name, specialist, workTime, qualification, room);
		doctorList.add(d);
		return d;
	}

	public static String listDoctorsText() {
		if (doctorList.isEmpty())
			return "No doctors added yet.";
		StringBuilder sb = new StringBuilder("List of Doctors:\n");
		for (Doctor d : doctorList)
			sb.append(d.showDoctorInfo()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	/* ------------------- PATIENT ------------------- */
	public static boolean isPatientIdExists(String id) {
		return patientList.stream().anyMatch(p -> p.getId().equalsIgnoreCase(id));
	}

	public static Patient addPatient(String id, String name, String disease, String sex, String admit, int age) {
		Patient p = new Patient(id, name, disease, sex, admit, age);
		patientList.add(p);
		return p;
	}

	public static String listPatientsText() {
		if (patientList.isEmpty())
			return "No patients added yet.";
		StringBuilder sb = new StringBuilder("Patients:\n");
		for (Patient p : patientList)
			sb.append(p.showPatientInfo()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	/* ------------------- MEDICINE ------------------- */
	private static final DateTimeFormatter EXPIRY_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static boolean isDuplicateMedicine(String name, String manufacturer, LocalDate expiryDate) {
		String n = name.trim();
		String mfg = manufacturer.trim();
		String formattedDate = expiryDate.format(EXPIRY_FMT);

		return medicineList.stream()
				.anyMatch(m -> m.getName() != null && m.getName().trim().equalsIgnoreCase(n)
						&& m.getManufacturer() != null && m.getManufacturer().trim().equalsIgnoreCase(mfg)
						&& m.getExpiryDate() != null && m.getExpiryDate().trim().equals(formattedDate));
	}

	public static Medicine addMedicine(String name, String manufacturer, LocalDate expiryDate, int cost, int units) {
		String expiryStr = expiryDate.format(EXPIRY_FMT);
		Medicine m = new Medicine(name, manufacturer, expiryStr, cost, units);
		medicineList.add(m);
		return m;
	}

	public static String listMedicinesText() {
		if (medicineList.isEmpty())
			return "No medicines added yet.";
		StringBuilder sb = new StringBuilder("Medicines:\n");
		for (Medicine m : medicineList)
			sb.append(m.findMedicine()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	/* ------------------- LAB ------------------- */
	public static boolean isLabFacilityExists(String facility) {
		return labList.stream().anyMatch(l -> l.getLab().equalsIgnoreCase(facility));
	}

	public static Lab addLab(String facility, int cost) {
		Lab lab = new Lab(facility, cost);
		labList.add(lab);
		return lab;
	}

	public static String listLabsText() {
		if (labList.isEmpty())
			return "No labs added yet.";
		StringBuilder sb = new StringBuilder("Lab Facilities:\n");
		for (Lab l : labList)
			sb.append(l.labList()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	/* ------------------- FACILITY ------------------- */
	public static boolean isFacilityExists(String name) {
		return facilityList.stream().anyMatch(f -> f.getFacility().equalsIgnoreCase(name));
	}

	public static Facility addFacility(String name) {
		Facility f = new Facility(name);
		facilityList.add(f);
		return f;
	}

	public static String listFacilitiesText() {
		if (facilityList.isEmpty())
			return "No facilities added yet.";
		StringBuilder sb = new StringBuilder("Facilities:\n");
		for (Facility f : facilityList)
			sb.append(f.showFacility()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	/* ------------------- STAFF ------------------- */
	public static boolean isStaffIdExists(String id) {
		return staffList.stream().anyMatch(s -> s.getId().equalsIgnoreCase(id));
	}

	public static Staff addNurse(String id, String name, String type, String sex, int salary, String shift) {
		Staff s = new Nurse(id, name, type, sex, salary, shift);
		staffList.add(s);
		return s;
	}

	public static Staff addCleaner(String id, String name, String type, String sex, int salary, String floor) {
		Staff s = new Cleaner(id, name, type, sex, salary, floor);
		staffList.add(s);
		return s;
	}

	public static Staff addLabTech(String id, String name, String type, String sex, int salary, int experience) {
		Staff s = new LabTechnician(id, name, type, sex, salary, experience);
		staffList.add(s);
		return s;
	}

	public static Staff addSecurity(String id, String name, String type, String sex, int salary, String location) {
		Staff s = new Security(id, name, type, sex, salary, location);
		staffList.add(s);
		return s;
	}

	public static String listStaffText() {
		if (staffList.isEmpty())
			return "No staff added yet.";
		StringBuilder sb = new StringBuilder("Staff:\n");
		for (Staff s : staffList)
			sb.append(s.showStaffInfo()).append("\n");
		sb.append("----------------------------------------");
		return sb.toString();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
