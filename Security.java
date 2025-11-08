package HospitalManagementSystem;

public class Security extends Staff {
    private String location;

    public Security(String id, String name,String designation, String sex, int salary, String location) {
        super(id, name,designation, sex, salary);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String showStaffInfo() {
        return super.showStaffInfo() + "\nPost Location: " + getLocation();
    }
}
