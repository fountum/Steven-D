package Tools.Time;

public class Second {
    int sec;

    public Second() {
        sec = 0;
    }

    public Second(int sec) {
        this.sec = sec;
    }

    public Second(Second sec) {
        this.sec = sec.sec;
    }

    public int getSec() {
        return sec;
    }

    //requires: 0 < sec < 59
    //modifies: this
    //effects: changes sec variable to sec field
    public void setSec(int sec) {
        this.sec = sec;
    }

    public String toString() {
        if (sec < 10) {
            return "0" + sec;
        } else
            return "" + sec;
    }

    //Requires: nothing
    //Modifies: this
    //Effects: increase sec by 1
    public void tick() {
        sec++;
    }
}
