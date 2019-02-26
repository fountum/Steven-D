package Tools.Restaurant;

import Model.*;
import Tools.Time.Time;

import java.util.ArrayList;

public class Order {
    private ArrayList<Items> order;
    private ArrayList<Items> unconfirmedItems = new ArrayList<>();
    private ArrayList<Items> confirmedItems = new ArrayList<>();
    private ArrayList<Items> inventory;
    public Customer customer;
    private Time startTime;
    private Time endTime;
    private String status;


    public Order(Customer customer, ArrayList<Items> items, Time startTime, ArrayList<Items> inventory) {
        //if not in inventory add 10 minutes to order
        this.customer = customer;
        this.order = items;
        this.unconfirmedItems = items;
        this.startTime = startTime;
        this.endTime = new Time(startTime);
        this.inventory=inventory;
        endTime.increaseMin(15);
        this.status = "Preparing";
        if (getMissingItems().size()>0){
                endTime.increaseMin(10);
        }
    }




    protected Order(Order order, ArrayList<Items> inventory) {
        this.customer = order.customer;
        this.order = order.getOrder();
        this.confirmedItems = order.confirmedItems;
        this.startTime = order.getStartTime();
        this.inventory=inventory;
        this.endTime = order.getEndTime();
        this.setStatus(order.getStatus());
    }

    public Order(ArrayList<Items> items, Time startTime, ArrayList<Items> inventory) {
        this.customer = new Customer();
        this.order = items;
        this.confirmedItems = new ArrayList<>();
        this.unconfirmedItems = items;
        this.startTime = startTime;
        this.endTime = new Time(startTime);
        this.inventory=inventory;
        endTime.increaseMin(15);
        this.status = "Preparing";
        if (getMissingItems().size()>0){
            endTime.increaseMin(10);
        }
    }

    //requires: nothing
    //modifies: nothing
    //effects: creates a clone of i
    public Items clone(Items i) {
        if (i instanceof Pizza) {
            return new Pizza((Pizza) i);
        } else if (i instanceof Pasta) {
            return new Pasta((Pasta) i);
        } else if (i instanceof Sandwich) {
            return new Sandwich((Sandwich) i);
        }
        return null;
    }

    //requires: nothing
    //modifies: this, inventory
    //effects: returns true if inventory contains every item in unconfirmedItems
    protected boolean checkInventory() {
        ArrayList<Items> temp = new ArrayList<>();
        ArrayList<Items> temp2 = new ArrayList<>();
        boolean timeAdded = false;
        for (Items i : unconfirmedItems) {
            int misMatches = 0;
            for (Items ii : inventory) {
                if (i.compare(ii) && !temp.contains(ii)) {
                    temp.add(ii);
                    confirmedItems.add(clone(i));
                    temp2.add(i);
                    break;

                } else {
                    misMatches++;
                }
            }
            if (misMatches == inventory.size()) {
                for (Items iii : temp) {
                    inventory.remove(iii);
                }
                for (Items iiii : temp2) {
                    unconfirmedItems.remove(iiii);
                }
                return false;
            }

        }
        for (Items iii : temp) {
            inventory.remove(iii);
        }
        for (Items iiii : temp2) {
            unconfirmedItems.remove(iiii);
        }
        return true;
    }

    //requires: nothing
    //modifies: nothing
    //effects: returns an ArrayList containing every item in unconfirmedItems not in inventory
    public ArrayList<Items> getMissingItems() {
        ArrayList<Items> missingItems = new ArrayList<>();
        for (Items i : unconfirmedItems) {
            int misMatches = 0;
            for (Items ii : inventory) {
                if (!i.compare(ii)) {
                    misMatches++;
                }
            }
            if (misMatches == inventory.size()) {
                missingItems.add(clone(i));
            }
        }
        return missingItems;
    }

    public String toString() {
        return (customer.getName() + " Start Time: " + startTime + " End Time: " + endTime + " # of items: " + order.size());
    }

    public ArrayList<Items> getOrder() {
        return order;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        int price = 0;
        if (order.isEmpty()) {
            for (Items i : confirmedItems) {
                price += i.getPrice();
            }
            return price;
        } else {
            for (Items i : order) {
                price += i.getPrice();
            }
            return price;
        }
    }


}

