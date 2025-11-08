package HospitalManagementSystem;

import java.util.Scanner;

public class Lab {
	private String lab;
	private int cost;
	
	//constructor
	public Lab() {
		
	}
	
	public Lab(String lab,int cost) {
		this.lab=lab;
		this.cost=cost;
	}
	
	//method to prompt user to enter the information of lab
	public void newLab() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter lab's name: ");;
		lab=keyboard.nextLine();
		
		System.out.print("Please enter cost of lab: ");
		cost=keyboard.nextInt();
		keyboard.close();
	}
	
	//method to show the lab information
	public String labList() {
	    return  "----------------------------------------\n" +
	            "Facility in Lab: " + lab + "\n" +
	            "Cost: RM " + cost + "\n";
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
	
}
