import Tools.Time.Hour;
import Tools.Time.Minute;
import Tools.Time.Time;
import Tools.Time.Second;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TimeTests {
    Time time;
    Time time2;
    Time time3;
    Hour hr;
    Minute min;
    Second sec;
    Hour hr2;
    Minute min2;
    Second sec2;
    @Before
    public void setup(){
        time = new Time();

        hr = new Hour(10);
        sec = new Second(59);
        min = new Minute(59);
        hr2 = new Hour(23);
        min2 = new Minute(59);
        sec2= new Second((59));
    }

    @Test
    public void testTick(){
        time.tick();
        assertEquals(1,time.getSec().getSec());
        Time time2 = new Time(hr,min,sec);
        time2.tick();
        assertEquals(11,time2.getHr().getHour());
        Time time3 = new Time(hr2, min2, sec2);
        time3.tick();
        assertEquals(0, time3.getHr().getHour());
    }

    @Test
    public void testIncreaseMin(){
        Time time4 = new Time();
        time4.increaseMin(15);
        assertEquals(15, time4.getMin().getMinutes());
        time4.increaseMin(45);
        assertEquals(1, time4.getHr().getHour());
        assertEquals(0, time4.getMin().getMinutes());
        time4.getHr().setHour(23);
        time4.increaseMin(60);
        assertEquals(0, time4.getHr().getHour());
        assertEquals(0, time4.getMin().getMinutes());
    }
}
