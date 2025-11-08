package HospitalManagementSystem;

import java.util.Scanner;

public class Doctor {
	private String id,name,specialist,workTime,qualification;
	private int room;
	
	//constructor
	public Doctor() {
		
	}
	
	public Doctor(String id,String name,String specialist,String workTime,String qualification,int room) {
		this.id=id;
		this.name=name;
		this.specialist=specialist;
		this.workTime=workTime;
		this.qualification=qualification;
		this.room=room;
	}

	//method to prompt user to enter doctor's information
	public void newDoctor() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter doctor's id: ");
		id=keyboard.nextLine();
		
		System.out.print("Please enter doctor's name: ");
		name=keyboard.nextLine();
		
		System.out.print("Please enter doctor's specialization: ");
		specialist=keyboard.nextLine();
		
		System.out.print("Please enter doctor's work time: ");
		workTime=keyboard.nextLine();
		
		System.out.print("Please enter doctor's qualification: ");
		qualification=keyboard.nextLine();
		
		System.out.print("Please enter doctor's room: ");
		room=keyboard.nextInt();
		
		keyboard.close();
	}
	
	//method to show doctor's information
	public String showDoctorInfo() {
	    return  "----------------------------------------\n" +
	            "ID: " + id + "\n" +
	            "Name: " + name + "\n" +
	            "Specialist: " + specialist + "\n" +
	            "Work Time: " + workTime + "\n" +
	            "Qualification: " + qualification + "\n" +
	            "Room No: " + room + "\n";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	
}

