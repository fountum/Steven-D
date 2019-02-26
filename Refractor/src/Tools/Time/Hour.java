package Tools.Time;

public class Hour {
    private int hours;

    Hour() {
        hours = 0;
    }

    public Hour(int hours) {
        this.hours = hours;
    }

    public Hour(Hour hr) {
        this.hours = hr.hours;
    }

    public int getHour() {
        return hours;
    }
    //requires 0 < hours < 23
    //modifies: this
    //effects: changes hours variable to hours field
    public void setHour(int hours) {
        this.hours = hours;
    }

    public String toString() {
        if (hours < 10) {
            return "0" + hours;
        } else
            return "" + hours;
    }

    //Requires: nothing
    //Modifies: this
    //Effects: increase hours by 1
    public void tick() {
        hours++;
    }
}
