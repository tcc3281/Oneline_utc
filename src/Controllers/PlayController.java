package Controllers;

import Models.Game.Graph;
import Models.Game.Point;
import Models.Timer.CountdownTimer;
import Views.MainViews;

import java.util.List;

public class PlayController {
    private MainViews views;
    private Graph models;
    private CountdownTimer timer;

    public PlayController() {
        views = new MainViews(this);
        models = new Graph(this);
        timer = new CountdownTimer(CountdownTimer.EASY, this);
    }

    public void play() {
        views.setVisible(true);
    }

    public void setChallenge() {
        int challenge = 2;
        this.models.readData(challenge);
        List<Point> p = this.models.getPoints();
        this.views.getPlayViews().setBoardGame();
        this.views.getPlayViews().getMainPlay().newGame(p);
    }

    public void setTextTime(String time) {
        views.getPlayViews().getTxtTimer().setText(time);
    }

    public void runTime() {
        if (this.timer.isPause()) {
            this.timer.continueTime();
        } else {
            this.timer.startTime();
        }
    }

    public void pauseTime() {
        this.timer.pauseTime();
    }
}
