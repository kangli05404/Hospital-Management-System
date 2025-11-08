package HospitalManagementSystem;

import java.util.Scanner;

public class Facility {
	private String facility;
	
	//constructor
	public Facility() {
		
	}
	
	public Facility(String facility) {
		this.facility=facility;
	}
	
	//method to prompt user to enter facility information
	public void newFacility() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter the facility: ");
		facility=keyboard.nextLine();
		keyboard.close();
	}
	
	//method to show the information of facility
	public String showFacility() {
	    return  "----------------------------------------\n" +
	            "Facility: " + facility + "\n";
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	
}
