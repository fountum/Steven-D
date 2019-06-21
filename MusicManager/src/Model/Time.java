package Model;

public class Time {
    public Time() {
    }

    public static String formatTime(int totalTime) {
        int minutes = totalTime/60;
        int hours = minutes/60;
        int seconds = totalTime-minutes*60;

        String sString = "";
        String mString = "";

        if (seconds < 10) {
            sString = "0" + seconds;
        }
        else {
            sString = Integer.toString(seconds);
        }

        if (minutes < 10 && hours > 1) {
            mString = "0" + minutes;
        } else {
            mString = Integer.toString(minutes);
        }

        if (hours > 0) {
             return (hours + ":" + mString + ":" + sString);
        }
        return (mString + ":" + sString);
    }


}
