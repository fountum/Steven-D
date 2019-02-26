package Model;

public class Pizza extends Items {
    boolean isDeluxe;
    public Pizza(String name, double price, boolean deluxe){
        super(name, price);
        this.isDeluxe = deluxe;
    }
    public Pizza(Pizza pizza){
        super(pizza.getName(), pizza.getTruePrice());
        this.isDeluxe = pizza.getDeluxe();
    }
    public boolean getDeluxe() {
        return isDeluxe;
    }

    public double getTruePrice(){
        return price;
    }

    @Override
    public double getPrice() {
        if (isDeluxe == true) {
            return (price + 3.0);
        } else {
            return (price);
        }
    }

    @Override
    public String toString(){
        return "Name: " + getName() + " Price: " + getPrice() + " Deluxe: "+ getDeluxe();
    }


    public boolean compare(Items i){
        if (i instanceof Pizza){
            Pizza pizza = (Pizza)i;
            if (this.getName().equals(pizza.getName()) && this.getPrice() == pizza.getPrice() && this.getDeluxe() == pizza.getDeluxe()){
                return true;
            }
        }
        return false;
    }

}
