package HospitalManagementSystem;

public class Cleaner extends Staff {
    private String floor;

    public Cleaner(String id, String name,String designation, String sex, int salary, String floor) {
        super(id, name,designation, sex, salary);
        this.floor = floor;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public String showStaffInfo() {
        return super.showStaffInfo() + "\nFloor: " + getFloor();
    }
}

