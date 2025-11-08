package HospitalManagementSystem;

import java.util.Scanner;

public class Patient {
	private String id,name,disease,sex,admitStatus;
	private int age;
	
	//constructor
	public Patient() {
		
	}
	
	public Patient(String id,String name,String disease,String sex,String admitStatus,int age){
		this.id=id;
		this.name=name;
		this.disease=disease;
		this.sex=sex;
		this.admitStatus=admitStatus;
		this.age=age;
	}
	
	//prompt user to enter patient information
	public void newPatient() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter patient's id: ");
		id=keyboard.nextLine();
		
		System.out.print("Please enter patient's name: ");
		name=keyboard.nextLine();
		
		System.out.print("Please enter patient's disease: ");
		disease=keyboard.nextLine();
		
		System.out.print("Please enter patient's sex: ");
		sex=keyboard.nextLine();
		
		System.out.print("Please enter patient's admit status: ");
		admitStatus=keyboard.nextLine();
		
		System.out.print("Please enter patient's age: ");
		age=keyboard.nextInt();
		
		keyboard.close();
	}
	
	//show patient's information
	public String showPatientInfo() {
	    return  "----------------------------------------\n" +
	            "ID: " + id + "\n" +
	            "Name: " + name + "\n" +
	            "Disease: " + disease + "\n" +
	            "Sex: " + sex + "\n" +
	            "Admit Status: " + admitStatus + "\n" +
	            "Age: " + age + "\n";
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

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(String admitStatus) {
		this.admitStatus = admitStatus;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
		
}
