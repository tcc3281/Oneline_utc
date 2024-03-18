package Models.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private Timer timer;
    protected int second;
    protected int minute;


    public static final int EASY = 180;
    public static final int MEDIUM = 60;
    public static final int HARD = 15;

    public CountdownTimer(int second) {
        this.second = second % 60;
        this.minute = second / 60;
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, 100);
    }

    public String getTime() {
        return String.format("%02d", this.minute) + ":" + String.format("%02d", this.second);
    }

    protected boolean runTime() {
        if (second == 0 && minute == 0) {
            return false;
        }
        if (second == 0) {
            minute--;
        }
        second--;
        if (second == -1) {
            second = 59;
        }
        return true;
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            if (runTime()) {
                System.out.println(getTime());
            } else {
                System.out.println("Time out!");
                timer.cancel();
            }
        }
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }
}
