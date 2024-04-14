package Controllers;

import Models.Bot.Hint;
import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;
import Models.Timer.CountdownTimer;
import Views.MainView;
import Views.PlayArea.LinePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class PlayController {
    private MainView views;
    private Graph models;
    private CountdownTimer timer;
    private int curPlay;//challenge đang chơi hiện tại
    private int curChallenge;//số challenge mở được - phải lần lượt theo thứ tụ
    private int totalChallenge;
    private int curLevel;//thời gian set hiện tại
    private Hint hint;
    private LinePanel playPanel;
    private LinkedList<Point> hintValue;
    private final String PATH = ".\\src\\Resources\\Datas\\data.txt";
    public Color playerColor = null;
    private boolean connectability = true;

    public PlayController() {
        readData();
        views = new MainView(this);
        models = new Graph(this);
        hint = new Hint();
        timer = new CountdownTimer(curLevel, this);
        timer.startTime();
        hintValue = null;
        this.playPanel = views.getPlayViews().getMainPlay();
        this.playPanel.setController(this);
    }

    public int getTotalChallenge() {
        return totalChallenge;
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
        timer.setTime(curLevel);
        List<Point> p = this.models.getPoints();
        List<Edge> e = this.models.getEdges();
        this.views.getPlayViews().setPlayView();
        if (playPanel == null) {
            playPanel = this.views.getPlayViews().getMainPlay();
            playPanel.setController(this);
        }
        this.playPanel.setGUI(p, e, playerColor);
        reset(true);
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
            int totalChallenge = Integer.parseInt(bufferedReader.readLine());
            int time = Integer.parseInt(bufferedReader.readLine());
            this.curPlay = currentChallenge;
            this.curChallenge = challenge;
            this.totalChallenge = totalChallenge;
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
            bufferedWriter.write(String.valueOf(this.totalChallenge) + "\n");
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
//        if (this.timer.isPause()) {
//            this.timer.continueTime();
//        } else {
//            this.timer.pauseTime();
//        }
    }

    public void pauseTime() {
        this.timer.pauseTime();
    }

    public void callHint() {
        if (this.hintValue == null) {
            this.reset(false);
            setEnableBtn(false);
            this.playPanel.setHint(true);
            hint.setChallenge(this.curPlay);
            this.hintValue = hint.solve();
        }
        if (this.hintValue.isEmpty()) {
            this.playPanel.setHint(false);
            return;
        }
        Point p = this.hintValue.removeFirst();
        this.playPanel.blink(p.getX(), p.getY(), true);
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
            if (!this.playPanel.isHint()) {
                this.playPanel.blink(next.getX(), next.getY(), true);
            }
            if (cur != null) {
                if (!this.playPanel.isHint()) {
                    this.playPanel.blink(cur.getX(), cur.getY(), false);
                }
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
                setEnableBtn(false);
                winner();
            }
        }
    }

    public void lose() {
        timer.cancel();
        int option = JOptionPane.showConfirmDialog(null, "You are lose!\nDo you want to play again?", "Notification", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            reset(true);
        }
        this.connectability = false;
    }

    public void winner() {
        if (curChallenge < totalChallenge) {
            curChallenge++;
            // Cập nhật hình ảnh khi curChallenge thay đổi
            views.getChallengesView().updateItems(curChallenge);
        }
        timer.cancel();
        this.connectability = false;
        int option = JOptionPane.showConfirmDialog(null, "You are win!\nDo you want to go to next challenge?", "Notification", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            nextChallenges();
        }
    }

    public void reset(boolean isTime) {
        this.models.reset();
        if(isTime) this.timer.reset();
        this.views.getPlayViews().reset();
        this.playPanel.setHint(false);
        this.connectability = true;
        setEnableBtn(true);
        if (this.hintValue != null) {
            this.hintValue.clear();
            this.hintValue = null;
        }
    }

    public void setEnableBtn(boolean enableBtn) {
        this.views.getPlayViews().getBtnReturn().setEnabled(enableBtn);
        this.views.getPlayViews().getBtnHint().setEnabled(enableBtn);
    }

    public void back() {
        if (this.models.isWinner()) {
            return;
        }
        Point after = this.models.getCur();
        if (after == null) {
            return;
        }
        if (this.models.back(false)) {
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
            JOptionPane.showMessageDialog(null, "You only back 3 times", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setTime() {
        int time = views.getLevelView().setTime();
        curLevel = time;
        timer.cancel();
    }

    public void setLevel() {
        if (curLevel == CountdownTimer.EASY)
            views.getLevelView().getRdbEasy().setSelected(true);
        else if (curLevel == CountdownTimer.MEDIUM)
            views.getLevelView().getRdbMedium().setSelected(true);
        else if (curLevel == CountdownTimer.HARD)
            views.getLevelView().getRdbHard().setSelected(true);
        else {
            Date date = new Date();
            date.setTime(curLevel * 1000);
            views.getLevelView().getRdbCustom().setSelected(true);
            views.getLevelView().getjSPCustom().setValue(date);
        }
    }

    public void nextChallenges() {
        if (curPlay >= curChallenge) {
            return;
        }
        if (curPlay == totalChallenge) {
            curPlay = 0;
        }
        curPlay++;
        setChallenge();
        this.timer.startTime();
        reset(true);
    }

    public void setCurPlay(int curPlay) {
        this.curPlay = curPlay;
    }

    public int getCurChallenge() {
        return curChallenge;
    }

    public boolean isConnectability() {
        return connectability;
    }
}
