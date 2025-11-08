package HospitalManagementSystem;

public class Nurse extends Staff {
    private String shift;

    public Nurse(String id, String name,String designation, String sex, int salary, String shift) {
        super(id, name,designation, sex, salary);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String showStaffInfo() {
        return super.showStaffInfo() + "\nShift: " + getShift();
    }
}
