package HospitalManagementSystem;

public class LabTechnician extends Staff {
    private int experience;

    public LabTechnician(String id, String name,String designation, String sex, int salary, int experience) {
        super(id, name,designation, sex, salary);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String showStaffInfo() {
        return super.showStaffInfo() + "\nYear of Experience: " + getExperience();
    }
}
