package HospitalManagementSystem;

import java.util.Scanner;

public abstract class Staff {
	private String id,name,designation,sex;
	private int salary;
	
	//constructor
	public Staff() {
		
	}
	
	public Staff(String id,String name,String designation,String sex,int salary) {
		this.id=id;
		this.name=name;
		this.designation=designation;
		this.sex=sex;
		this.salary=salary;
	}
	
	//method to prompt user to enter those informations
	public void newStaff() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter staff's id: ");
		id=keyboard.nextLine();
		
		System.out.print("Please enter name: ");
		name=keyboard.nextLine();
		
		System.out.print("Please enter designation: ");;
		designation=keyboard.nextLine();
		
		System.out.print("Please enter sex: ");
		sex=keyboard.nextLine();
		
		System.out.print("Please enter salary: ");
		salary=keyboard.nextInt();
		
		keyboard.close();
	}
	
	//method to show staff information
	public String showStaffInfo() {
	    return  "----------------------------------------\n" +
	            "ID: " + id + "\n" +
	            "Name: " + name + "\n" +
	            "Sex: " + sex + "\n" +
	            "Salary: " + salary + "\n" +
	            "Designation: " + designation;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
