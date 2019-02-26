package Tools.Time;

public class Time {
    private Hour hr;
    private Minute min;
    private Second sec;

    public Time() {
        hr = new Hour();
        min = new Minute();
        sec = new Second();
    }

    public Time(Hour hr, Minute min, Second sec) {
        this.hr = new Hour(hr);
        this.min = new Minute(min);
        this.sec = new Second(sec);
    }

    public Time(Time time) {
        this.hr = new Hour(time.hr);
        this.min = new Minute(time.min);
        this.sec = new Second(time.sec);
    }

    public Hour getHr() {
        return hr;
    }

    public Minute getMin() {
        return min;
    }

    public Second getSec() {
        return sec;
    }

    //requires: nothing
    //modifies: min, hr
    //effects: increases min by num and adjusts min and hr accordingly
    public void increaseMin(int num) {
        min.setMinutes(min.getMinutes() + num);
        if (min.getMinutes() >= 60) {
            hr.setHour(hr.getHour() + 1);
            min.setMinutes(min.getMinutes() - 60);
            if (hr.getHour() >= 24) {
                hr.setHour(hr.getHour() - 24);
            }
        }
    }

    //requires: nothing
    //modifies: sec, min, hr
    //effects: increases sec by one and adjusts sec, min and hr accordingly
    public void tick() {
        sec.tick();
        if (sec.getSec() == 60) {
            sec.setSec(0);
            min.tick();
            if (min.getMinutes() == 60) {
                min.setMinutes(0);
                hr.tick();
                if (hr.getHour() == 24) {
                    hr.setHour(0);
                }
            }
        }
    }

    public String toString() {
        return hr + ":" + min + ":" + sec;
    }
}
