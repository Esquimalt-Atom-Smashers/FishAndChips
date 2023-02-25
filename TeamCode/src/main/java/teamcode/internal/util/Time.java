package teamcode.internal.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Time {
    public void waitFor(double milliseconds) {
        ElapsedTime time = new ElapsedTime();
        while(time.milliseconds() < milliseconds);
    }
}
