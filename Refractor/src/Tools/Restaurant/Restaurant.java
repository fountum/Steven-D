package Tools.Restaurant;

import Model.*;
import Tools.Time.Time;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private Time currentTime;
    private ArrayList<Items> inventory = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> newOrders = new ArrayList<>();
    private ArrayList<Order> deleveringOrders = new ArrayList<>();
    private ArrayList<Order> completedOrders = new ArrayList<>();

    public Restaurant(String name, Time currentTime) {
        this.name = name;
        this.currentTime = currentTime;
        System.out.println("Welcome to " + name + "!");
        for (int i = 0; i < 5; i++) {
            makePizza("Cheese Pizza", 7, false);
            makePasta("Rotini Pasta & Tomato Sauce", 8, false);
            makeSandwich("Black Forest Ham Sandwich", Sandwich.Size.MEDIUM);
        }
    }

    //requires: nothing
    //modifies: this, order
    //effects: returns true if inventory has every item required for the order, also changes order status to "Delivering"
    public boolean processOrder(Order order) {
        System.out.println("Processing "+ order.customer.getName()+"'s order...");
        if (!order.checkInventory()) {
            System.out.println("\r");
            System.out.println("Missing items for order. \r");
            return false;
        } else {
            System.out.println("Food prepared, ready to deliver order...");
            order.setStatus("Delivering");
            deleveringOrders.add(new Order(order, inventory));
            newOrders.remove(order);
            return true;
        }
    }

    //requires: nothing
    //modifies: this
    //effects: takes a list of Items and creates clones of the Items
    public void makeItems(ArrayList<Items> items){
        for (Items i:items){
            if (i instanceof Pizza){
                makePizza(i.getName(), ((Pizza) i).getTruePrice(), ((Pizza) i).getDeluxe());
            } else if (i instanceof Pasta){
                makePasta(i.getName(), ((Pasta) i).getTruePrice(),((Pasta) i).getDeluxe());
            } else if (i instanceof Sandwich){
                makeSandwich(i.getName(), ((Sandwich) i).getSize());
            }
        }
        System.out.println(items.size() + " food item(s) created.\r");
    }

    //requires: nothing
    //modifies: nothing
    //effects: prints out all orders that are not being delivered or completed
    public void showNewOrders() {
        if (newOrders.isEmpty()) {
            System.out.println("No orders queued up.");
        } else {
            System.out.println(newOrders.size()+ " order(s):");
            for (Order i:newOrders){
                System.out.println(i);
            }
        }
        System.out.println("\r");
    }

    //requires: nothing
    //modifies: nothing
    //effects: prints out all items in inventory
    public void showInventory(){
        if (inventory.isEmpty()){
            System.out.println("No items in inventory,");
        } else {
            System.out.println(inventory.size() + " item(s) in inventory:");
            for (Items i:inventory){
                System.out.println(i);
            }
        }
        System.out.println("\r");
    }

    //requires: nothing
    //modifies: nothing
    //effects: prints out all missing items from order
    public void showMissingItems(){
        Order order = newOrders.get(0);
        ArrayList<Items> missingItems = order.getMissingItems();
        if (missingItems.isEmpty()) {
            System.out.println("No items missing.");
        } else {
            System.out.println("Missing " + missingItems.size() + " item(s):");
            for (Items i:missingItems){
                System.out.println(i);
            }
        }
        System.out.println("\r");
    }

    //requires: nothing
    //modifies: this, order
    //effects: changes order's status to "Delivered" and moves out of deliveringOrders to completedOrders
    public void deliverOrder() {
        Order order = deleveringOrders.get(0);
        System.out.println("Delivering "+ order.customer.getName()+"'s order...");
        System.out.println("Order delivered!");
        order.setStatus("Delivered");
        completedOrders.add(new Order(order, inventory));
        deleveringOrders.remove(order);
    }

    public void addOrder(Order order) {
        System.out.println("New order added.");
        newOrders.add(order);
    }

    public String getOrderInfo(Order order) {
        return (order.customer.getName() + " Status: " + order.getStatus() + " Total Price: " + order.getTotalPrice() + " Start Time: " + order.getStartTime() + " Est. End Time: " + order.getEndTime());
    }


    //requires: price > 0
    //modifies: this
    //effects: creates a Pizza object with given fields and adds to inventory
    public void makePizza(String name, double price, boolean isDeluxe) {
        inventory.add(new Pizza(name, price, isDeluxe));
    }
    //requires: price > 0
    //modifies: this
    //effects: creates a Pasta object with given fields and adds to inventory
    public void makePasta(String name, double price, boolean isDeluxe) {
        inventory.add(new Pasta(name, price, isDeluxe));
    }
    //requires: price > 0
    //modifies: this
    //effects: creates a Sandwich object with given fields and adds to inventory
    public void makeSandwich(String name, Sandwich.Size size) {
        inventory.add(new Sandwich(name, size));
    }

    //requires: nothing
    //modifies: nothing
    //effects: returns first order in newOrders
    public Order nextOrder() {
        return (newOrders.get(0));
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public int getNumOrders() {
        return newOrders.size();
    }

    public ArrayList<Order> getNewOrders(){
        return newOrders;
    }
}
