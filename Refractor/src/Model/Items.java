package Model;

public abstract class Items {
    protected String name;
    protected double price;

    public Items(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    public abstract boolean compare(Items i);


}
