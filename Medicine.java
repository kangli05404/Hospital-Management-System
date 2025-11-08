package HospitalManagementSystem;

import java.util.Scanner;

public class Medicine {
	private String name,manufacturer,expiryDate;
	private int cost,count;
	
	//constructor
	public Medicine() {
		
	}
	
	public Medicine(String name,String manufacturer,String expiryDate,int cost,int count) {
		this.name=name;
		this.manufacturer=manufacturer;
		this.expiryDate=expiryDate;
		this.cost=cost;
		this.count=count;
	}
	
	//method prompt user to enter medicine information
	public void newMedicine() {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Please enter medicine's name: ");
		name=keyboard.nextLine();
		
		System.out.print("Please enter medicine's manufatcurer: ");
		manufacturer=keyboard.nextLine();
		
		System.out.print("Please enter medicine's expiry date: ");;
		expiryDate=keyboard.nextLine();
		
		System.out.print("Please enter medicine's cost: ");
		cost=keyboard.nextInt();
		
		System.out.print("Please enter medicine's number of unit: ");
		count=keyboard.nextInt();
		keyboard.close();
	}
	
	//method to show the information of medicine
	public String findMedicine() {
	    return  "----------------------------------------\n" +
	            "Name: " + name + "\n" +
	            "Manufacturer: " + manufacturer + "\n" +
	            "Expiry Date: " + expiryDate + "\n" +
	            "Cost: " + cost + "\n";
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
	
}
