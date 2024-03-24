package Controllers;

import Models.Bot.Hint;
import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;
import Models.Timer.CountdownTimer;
import Views.MainView;
import Views.PlayArea.LinePanel;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PlayController {
    private MainView views;
    private Graph models;
    private CountdownTimer timer;
    private int curPlay;//challenge đang chơi hiện tại
    private int curChallenge;//số challenge mở được - phải lần lượt theo thứ tụ
    private int curLevel;//thời gian set hiện tại
    private Hint hint;
    private LinePanel playPanel;
    private final String PATH = ".\\src\\Resources\\Datas\\Data.txt";
    public Color playerColor = null;

    public PlayController() {
        readData();
        views = new MainView(this);
        models = new Graph(this);
        hint = new Hint();
        this.playPanel = views.getPlayViews().getMainPlay();
        this.playPanel.setController(this);
    }

    public void ramdomColor() {
        int color = (int) (Math.random() * 4);
        switch (color) {
            case 0:
                playerColor = LinePanel.GREEN;
                break;
            case 1:
                playerColor = LinePanel.BLUE;
                break;
            case 2:
                playerColor = LinePanel.PURPLE;
                break;
            case 3:
                playerColor = LinePanel.YELLOW;
                break;
        }
    }

    public void play() {
        views.setVisible(true);
    }

    public void setChallenge() {
        ramdomColor();
        this.views.getPlayViews().setTitle("Play " + String.valueOf(this.curPlay));
        this.models.readData(curPlay);
        timer = new CountdownTimer(curLevel, this);
        List<Point> p = this.models.getPoints();
        List<Edge> e = this.models.getEdges();
        this.views.getPlayViews().setPlayView();
        if (playPanel == null) {
            playPanel = this.views.getPlayViews().getMainPlay();
            playPanel.setController(this);
        }
        this.playPanel.setGUI(p, e, playerColor);
    }

    public void setTextTime(String time) {
        views.getPlayViews().getTxtTimer().setText(time);
    }

    public void readData() {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(PATH);
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            int currentChallenge = Integer.parseInt(bufferedReader.readLine());
            int challenge = Integer.parseInt(bufferedReader.readLine());
            int time = Integer.parseInt(bufferedReader.readLine());
            this.curPlay = currentChallenge;
            this.curChallenge = challenge;
            this.curLevel = time;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException d) {
            System.out.println(d);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void saveData() {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(PATH);
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(String.valueOf(this.curPlay) + "\n");
            bufferedWriter.write(String.valueOf(this.curChallenge) + "\n");
            bufferedWriter.write(String.valueOf(this.curLevel) + "\n");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
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

    public void callHint() {
        hint.setChallenge(this.curPlay);
        LinkedList<Point> s = hint.solve();
        System.out.println(s);
    }

    public void connect(int position) {
        if (this.models.isWinner()) {
            return;
        }
        Point cur = models.getCur();
        if (position == -1) {
            lose();
            if (cur != null) this.playPanel.blink(cur.getX(), cur.getY(), false);
            return;
        }
        Point next = models.getPoints().get(position);
        if (this.models.connect(position)) {
            this.playPanel.blink(next.getX(), next.getY(), true);
            if (cur != null) {
                this.playPanel.blink(cur.getX(), cur.getY(), false);
                this.playPanel.connect(cur, next);
                Edge tempEdge = cur.getEdge(next);
                if (tempEdge.getMustVisit() == Edge.SECONDVISIT) {
                    this.playPanel
                            .getRoundLabel(tempEdge)
                            .setText(String.valueOf(tempEdge.getLeftVisited()));
                    if (tempEdge.getLeftVisited() == 0) {
                        this.playPanel.getRoundLabel(tempEdge).setVisible(false);
                    }
                }
            }
        }
        if (this.models.isFinish()) {
            if (!this.models.isBack()) {
                this.playPanel.blink(next.getX(), next.getY(), false);
                lose();
            }
            if (this.models.isWinner()) {
                this.playPanel.blink(next.getX(), next.getY(), false);
                winner();
            }
        }
    }

    public void lose() {
        this.timer.cancel();
        System.out.println("Lose");
    }

    public void winner() {
        this.timer.cancel();
        System.out.println("Win");
    }

    public void reset() {
        this.models.reset();
        this.timer.reset();
        this.views.getPlayViews().reset();
    }

    public void back() {
        if (this.models.isWinner()) {
            return;
        }
        Point after = this.models.getCur();
        if (after == null) {
            return;
        }
        if (this.models.back()) {
            Point prev = this.models.getCur();
            this.playPanel.blink(after.getX(), after.getY(), false);
            this.views.getPlayViews().setNumberHeart(this.models.getTimesLeftBack());
            if (prev != null) {
                this.playPanel.back(prev.getEdge(after));
                this.playPanel.blink(prev.getX(), prev.getY(), true);
            } else {
                for (Edge e : after.getEdges()) {
                    e.setColor(LinePanel.GRAY);
                }
                this.playPanel.reset();
            }
        } else {
            System.out.println("Can't back");
        }
    }

    public void setTime() {
        int time = views.getLevelView().Timer();
        curLevel = time;
        this.timer.setTime(time);
    }

    public void setLevel() {
        if (curLevel == CountdownTimer.EASY)
            views.getLevelView().getRdbEasy().setSelected(true);
        if (curLevel == CountdownTimer.MEDIUM)
            views.getLevelView().getRdbMedium().setSelected(true);
        if (curLevel == CountdownTimer.HARD)
            views.getLevelView().getRdbHard().setSelected(true);
    }
}
