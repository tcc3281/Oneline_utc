package Models.Timer;

import Controllers.PlayController;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private Timer timer;
    protected int second;
    protected int minute;
    private int totalSecond;
    private PlayController controller;

    public static final int EASY = 180;
    public static final int MEDIUM = 60;
    public static final int HARD = 30;
    private boolean isPause;

    public CountdownTimer(int second, PlayController controller) {
        this.totalSecond = second + 1;
        this.controller = controller;
        this.second = totalSecond % 60;
        this.minute = totalSecond / 60;
        timer = new Timer();
        isPause = false;
    }

    public void startTime() {
        timer.scheduleAtFixedRate(new RemindTask(), 0, 1000);
    }

    public String getTime() {
        return String.format("%02d", this.minute) + ":" + String.format("%02d", this.second);
    }

    public void setTime(int time) {
        totalSecond = time + 1;
        minute = totalSecond / 60;
        second = totalSecond % 60;
    }

    public void reset() {
        minute = totalSecond / 60;
        second = totalSecond % 60;
    }
    public void cancel(){
        timer.cancel();
        timer=new Timer();
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
                controller.connect(-1);
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
