package Model;

public class Sandwich extends Items {
    private Size size;
    public Sandwich(String name, Size size){
        super(name,size.getPrice());
        this.size = size;
    }

    public Sandwich(Sandwich sandwich) {
        super(sandwich.getName(), sandwich.getSize().getPrice());
        this.size = sandwich.getSize();
    }


    public enum Size{
        SMALL(6.5), MEDIUM(8.0), LARGE(10.5);
        private double price;
        Size(double price) {this.price = price;}
        double getPrice() { return (price);}
    }

    public Size getSize() {
        return (size);
    }

    @Override
    public double getPrice() {
        return (price);
    }

    @Override
    public String toString(){
        return "Name: " + getName() + " Price: " + getPrice() + " Size; " + getSize();
    }

    @Override
    public boolean compare(Items i){
        if (i instanceof Sandwich){
            Sandwich sandwich = (Sandwich)i;
            if (this.getName().equals(sandwich.getName()) && this.getPrice() == sandwich.getPrice() && this.getSize() == sandwich.getSize()){
                return true;
            }
        }
        return false;
    }
}
