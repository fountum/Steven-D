public class SalaryEmployee extends Employee {
    private int annualSalary;
    private boolean hasBenefits;

    public SalaryEmployee(int annualSalary, boolean hasBenefits) {
        super();
        this.annualSalary = annualSalary;
        this.hasBenefits = hasBenefits;
    }

    public SalaryEmployee(String name, int hireYear, int annualSalary, boolean hasBenefits) {
        super(name, hireYear);
        this.annualSalary = annualSalary;
        this.hasBenefits = hasBenefits;
    }

    public double monthlyPay() {
        return annualSalary/12;
    }

    @Override
    public double annualSalary() {
        return annualSalary;
    }

    @Override
    public double monthlySalary() {
        return monthlyPay();
    }
}

