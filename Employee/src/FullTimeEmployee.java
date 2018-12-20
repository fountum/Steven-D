public class FullTimeEmployee extends HourlyEmployee {
    private final boolean HASBENEFITS = true;

    public FullTimeEmployee(double hoursPerWeek, double hourlyWage) {
        super(hoursPerWeek, hourlyWage);
    }

    public FullTimeEmployee(String name, int hireYear, double hoursPerWeek, double hourlyWage) {
        super(name, hireYear, hoursPerWeek, hourlyWage);
    }

    @Override
    public double monthlyPay() {
        return monthlySalary();
    }


}
