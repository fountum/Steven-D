import java.util.ArrayList;

public class main {
    public static ArrayList<SalaryEmployee> getSalaryEmployees(ArrayList<Employee> employees){
        ArrayList<SalaryEmployee> temp = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof SalaryEmployee){
                temp.add((SalaryEmployee) e);
            }
        }
        return temp;
    }

    public static ArrayList<HourlyEmployee> getHourlyEmployees(ArrayList<Employee> employees){
        ArrayList<HourlyEmployee> temp = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof HourlyEmployee){
                temp.add((HourlyEmployee) e);
            }
        }
        return temp;
    }

    public static ArrayList<FullTimeEmployee> getFullTimeEmployees(ArrayList<Employee> employees){
        ArrayList<FullTimeEmployee> temp = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof FullTimeEmployee){
                temp.add((FullTimeEmployee) e);
            }
        }
        return temp;
    }

    public static ArrayList<PartTimeEmployee> getPartTimeEmployees(ArrayList<Employee> employees){
        ArrayList<PartTimeEmployee> temp = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof PartTimeEmployee){
                temp.add((PartTimeEmployee) e);
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new SalaryEmployee(50000, true));
        employees.add(new FullTimeEmployee("Fred Smith", 2010, 40, 20));
        employees.add(new PartTimeEmployee("Christia Lee", 1995, 25, 16));
        employees.add(new PartTimeEmployee("Sam", 2018, 15, 13));

        ArrayList<SalaryEmployee> sE = getSalaryEmployees(employees);
        System.out.println("Salary Employees");
        for (Employee e : sE) {
            System.out.println(e);
        }
        System.out.println("");
        System.out.println("Hourly Employees");
        ArrayList<HourlyEmployee> hE = getHourlyEmployees(employees);
        for (Employee e : hE) {
            System.out.println(e);
        }
        System.out.println("");
        System.out.println("Full Time Employees");
        ArrayList<FullTimeEmployee> fE = getFullTimeEmployees(employees);
        for (Employee e : fE) {
            System.out.println(e);
        }
        System.out.println("");

        System.out.println("Part Time Employees");
        ArrayList<PartTimeEmployee> ptE = getPartTimeEmployees(employees);
        for (Employee e : ptE) {
            System.out.println(e);
        }
        System.out.println("");

    }
}
