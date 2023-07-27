package HMS_Classes;

public abstract class Staff extends Person {
    private String staffId;
    private double salary;
    public Staff(){

    }
    public Staff(String name, int age, String phone,String gender,String password, String staffId,double salary) {
        super(name, age, phone,gender,password);
        this.staffId = staffId;
        this.salary=salary;
    }

    public String getStaffId() {

        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", salary=" + salary +
                '}';
    }
}
