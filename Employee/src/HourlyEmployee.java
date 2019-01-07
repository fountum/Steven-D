public abstract class HourlyEmployee extends Employee {
    protected double hoursPerWeek;
    protected double hourlyWage;
    protected boolean hasBenefits;


    public HourlyEmployee(String name, int hireYear, double hoursPerWeek, double hourlyWage) {
        super(name, hireYear);
        this.hoursPerWeek = hoursPerWeek;
        this.hourlyWage = hourlyWage;
    }

    public abstract boolean hasBenefits();
}

