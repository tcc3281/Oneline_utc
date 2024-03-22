package Models.Timer;

import Controllers.PlayController;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private Timer timer;
    protected int second;
    protected int minute;
    private PlayController controller;

    public static final int EASY = 180;
    public static final int MEDIUM = 60;
    public static final int HARD = 15;
    private boolean isPause;

    public CountdownTimer(int second, PlayController controller) {
        this.controller = controller;
        this.second = second % 60;
        this.minute = second / 60;
        timer = new Timer();
        isPause = false;
    }

    public void startTime() {
        timer.scheduleAtFixedRate(new RemindTask(), 0, 1000);
    }

    public String getTime() {
        return String.format("%02d", this.minute) + ":" + String.format("%02d", this.second);
    }

    protected boolean runTime() {
        if (isPause) return true;

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
                controller.setTextTime(getTime());
            } else {
                timer.cancel();
            }
        }
    }

    public void continueTime() {
        isPause = false;
    }

    public void pauseTime() {
        isPause = true;
    }

    public boolean isPause() {
        return isPause;
    }
}
