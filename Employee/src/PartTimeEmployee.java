public class PartTimeEmployee extends HourlyEmployee {
    private boolean benefits;

    public PartTimeEmployee(double hoursPerWeek, double hourlyWage) {
        super(hoursPerWeek, hourlyWage);
        benefits = hasBenefits();
    }

    public PartTimeEmployee(String name, int hireYear, double hoursPerWeek, double hourlyWage) {
        super(name, hireYear, hoursPerWeek, hourlyWage);
        benefits = hasBenefits();
    }

    @Override
    public double monthlyPay() {
        return monthlySalary();
    }

    public boolean hasBenefits() {
        if (hoursPerWeek > 20) {
            return true;
        } else {
            return false;
        }
    }
}
