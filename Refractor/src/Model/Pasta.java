package Model;

public class Pasta extends Items {
    boolean isDeluxe;

    public Pasta(String name, double price, boolean deluxe){
        super(name, price);
        this.isDeluxe = deluxe;
    }

    public Pasta(Pasta pasta){
        super(pasta.getName(), pasta.getTruePrice());
        this.isDeluxe = pasta.getDeluxe();
    }

    public boolean getDeluxe() {
        return isDeluxe;
    }

    public double getTruePrice(){
        return price;
    }

    @Override
    public double getPrice() {
        if (isDeluxe) {
            return (price + 2.0);
        } else {
            return (price);
        }
    }

    @Override
    public String toString(){
        return "Name: " + getName() + " Price: " + getPrice() + " Deluxe: "+ getDeluxe();
    }

    public boolean compare(Items i){
        if (i instanceof Pasta){
            Pasta pasta = (Pasta)i;
            if (this.getName().equals(pasta.getName()) && this.getTruePrice() == pasta.getTruePrice() && this.getDeluxe() == pasta.getDeluxe()){
                return true;
            }
        }
        return false;
    }

}
