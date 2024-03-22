package Controllers;

import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;
import Models.Timer.CountdownTimer;
import Views.MainView;

import java.io.*;
import java.util.List;

public class PlayController {
    private MainView views;
    private Graph models;
    private CountdownTimer timer;
    private int curPlay;//challenge đang chơi hiện tại
    private int curChallenge;//số challenge mở được - phải lần lượt theo thứ tụ
    private int curLevel;//thời gian set hiện tại
    private final String PATH = ".\\src\\Resources\\Datas\\Data.txt";

    public PlayController() {
        readData();
        views = new MainView(this);
        models = new Graph(this);
        timer = new CountdownTimer(curLevel, this);
    }

    public void play() {
        views.setVisible(true);
    }

    public void setChallenge() {
        this.models.readData(curPlay);
        List<Point> p = this.models.getPoints();
        List<Edge> e = this.models.getEdges();
        this.views.getPlayViews().setBoardGame();
        this.views.getPlayViews().getMainPlay().newGame(p, e);
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
        FileWriter writer=null;
        BufferedWriter bufferedWriter=null;
        try {
            File file=new File(PATH);
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(String.valueOf(this.curPlay)+"\n");
            bufferedWriter.write(String.valueOf(this.curChallenge)+"\n");
            bufferedWriter.write(String.valueOf(this.curLevel)+"\n");

        } catch (Exception e) {
            System.out.println(e);
        }finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
