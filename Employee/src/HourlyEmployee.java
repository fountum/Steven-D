public abstract class HourlyEmployee extends Employee {
    protected double hoursPerWeek;
    protected double hourlyWage;

    public HourlyEmployee(double hoursPerWeek, double hourlyWage) {
        this.hoursPerWeek = hoursPerWeek;
        this.hourlyWage = hourlyWage;
    }

    public HourlyEmployee(String name, int hireYear, double hoursPerWeek, double hourlyWage) {
        super(name, hireYear);
        this.hoursPerWeek = hoursPerWeek;
        this.hourlyWage = hourlyWage;
    }

    public abstract double monthlyPay();

    @Override
    public double annualSalary() {
        return monthlySalary()*12;
    }

    @Override
    public double monthlySalary() {
        return hoursPerWeek*hourlyWage;
    }
}
