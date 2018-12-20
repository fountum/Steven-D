public abstract class Employee {
    protected String name;
    protected int hireYear;

    Employee() {
        this.name = "Jason Dilla";
        this.hireYear = 2003;
    }
    public Employee(String name, int hireYear) {
        this.name = name;
        this.hireYear = hireYear;
    }

    public String getName() {
        return name;
    }

    public int getHireYear() {
        return hireYear;
    }

    public String toString() {
        return "Name: " + name + " Started: " + hireYear;
    }

    public abstract double annualSalary();

    public abstract double monthlySalary();
}
