package UI;

import Model.*;
import Tools.Restaurant.*;
import Tools.Time.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //restaurant demo
        Restaurant main = new Restaurant("Hizza Put", new Time(new Hour(7), new Minute(30), new Second(0)));
        main.showInventory();
        main.showNewOrders();

        //first order
        ArrayList<Items> temp = new ArrayList<>();
        temp.add(new Pizza("Cheese Pizza", 7, false));
        temp.add(new Pizza("Cheese Pizza", 7, false));
        temp.add(new Sandwich("Black Forest Ham Sandwich", Sandwich.Size.MEDIUM));
        main.addOrder(new Order(temp, new Time(), main.getInventory()));

        //second order
        ArrayList<Items> temp2 = new ArrayList<>();
        temp2.add(new Pizza("Cheese Pizza", 7, false));
        temp2.add(new Pasta("Spaghetti and Meatballs", 14, true));
        main.addOrder(new Order(new Customer("Rotin I.", "Rose Street", "E9G V5T", 624), temp2, new Time(), main.getInventory()));

        //third order
        ArrayList<Items> temp3 = new ArrayList<>();

        temp3.add(new Pizza("Cheese Pizza", 7, false));
        temp3.add(new Pizza("Cheese Pizza", 7, false));
        temp3.add(new Pizza("Cheese Pizza", 7, true));
        temp3.add(new Pizza("Cheese Pizza", 7, true));
        main.addOrder(new Order(new Customer("Paul B.", "Street st.", "V7B P9R", 437), temp3, main.getCurrentTime(), main.getInventory()));

        //fourth order
        ArrayList<Items> temp4 = new ArrayList<>();
        temp4.add(new Sandwich("Just Bread", Sandwich.Size.SMALL));
        temp4.add(new Pasta("Kraft Dinner", 2.99, false));
        temp4.add(new Pizza("Raw Dough with Processed Cheese", 29.99, true));
        main.addOrder(new Order(new Customer("Smith W.", "Edgefield  st.", "E5E C1K", 8), temp4, main.getCurrentTime(), main.getInventory()));


        main.showNewOrders();
        int orders = main.getNumOrders();
        //processing all orders
        for (int i = 0; i < orders; i++) {
            if (!main.processOrder(main.nextOrder())) {
                main.showMissingItems();
                main.makeItems(main.nextOrder().getMissingItems());
                main.processOrder(main.nextOrder());
            }
        }

        main.showNewOrders();


        //delivering all orders
        for (int i = 0; i < orders; i++) {
            main.deliverOrder();
        }


    }
}
