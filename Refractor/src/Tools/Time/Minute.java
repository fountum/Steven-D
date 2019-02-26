package Tools.Time;

public class Minute{
    int min;
    public Minute(){
        min = 0;
    }

    public Minute(int min) {
        this.min = min;
    }

    public Minute(Minute minute){
        this.min = minute.min;
    }
    public int getMinutes() {
        return min;
    }

    //Requires  0 < minutes < 60
    //Modifies: this
    //Effects: changes min to minutes
    public void setMinutes(int minutes) {
        this.min = minutes;
    }
    public String toString(){
        if(min < 10){
            return "0"+min;
        }
        else
            return ""+min;
    }

    //Requires: nothing
    //Modifies: this
    //Effects: increase min by 1
    public void tick(){
        min++;
    }
}
