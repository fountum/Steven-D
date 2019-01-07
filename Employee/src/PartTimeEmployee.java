public class PartTimeEmployee extends HourlyEmployee {
    private boolean hasBenefits;


    public PartTimeEmployee(String name, int hireYear, double hoursPerWeek, double hourlyWage) {
        super(name, hireYear, hoursPerWeek, hourlyWage);
    }

    @Override
    public boolean hasBenefits() {
       return hasBenefits;
    }

    public double monthlySalary() {
        return hoursPerWeek*hourlyWage*4;
    }

    public double annualSalary() {
        return monthlySalary()*12;
    }
}
