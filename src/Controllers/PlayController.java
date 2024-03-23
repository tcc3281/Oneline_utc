package Controllers;

import Models.Bot.Hint;
import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;
import Models.Timer.CountdownTimer;
import Views.MainView;
import Views.PlayArea.LinePanel;

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

    public PlayController() {
        readData();
        views = new MainView(this);
        models = new Graph(this);
        timer = new CountdownTimer(curLevel, this);
        hint = new Hint();
        this.playPanel = views.getPlayViews().getMainPlay();
        this.playPanel.setController(this);
    }

    public void play() {
        views.setVisible(true);
    }

    public void setChallenge() {
        this.models.readData(curPlay);
        List<Point> p = this.models.getPoints();
        List<Edge> e = this.models.getEdges();
        this.views.getPlayViews().setBoardGame();
        if (playPanel == null) {
            playPanel = this.views.getPlayViews().getMainPlay();
            playPanel.setController(this);
        }
        this.playPanel.setGUI(p, e);
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
        Point cur = this.models.getCur();
        Point next = models.getPoints().get(position);
        if (this.models.connect(position)) {
            if (cur != null) {
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
        if (this.models.isWinner()) {
            System.out.println("win");
        }
    }

    public void reset() {
        this.models.reset();
        this.timer.reset();
        this.playPanel.reset();
    }
}
