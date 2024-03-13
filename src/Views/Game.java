package Views;

import Models.Game.Graph;
import Models.Game.Edge;
import Models.Game.Point;

import java.util.ArrayList;

public class Game {
    private Graph graph;
    public Game(){
        graph=new Graph();
    }
    public void printPoints(){
        int i=0;
        for (Point point: this.graph.getPoints()) {
            System.out.print((i++)+" ");
            System.out.println(point);
        }
    }
    public void printEdges(){
        int i=0;
        for (Edge edge:this.graph.getEdges()) {
            System.out.print((i++)+" ");
            System.out.println(edge);
        }
    }
    public void play(){
        boolean isWin=false;
        int i=0;
        ArrayList<Integer> ends=new ArrayList<>();
        ends.add(1);
        ends.add(2);
        ends.add(2);
        ends.add(3);
        ends.add(0);
        ends.add(0);
        ends.add(2);
        this.graph.setPointStart(this.graph.getPoints().get(0));
        while(this.graph.isFinish()){
            System.out.println("Step "+(i)+":");
            System.out.println(this.graph.getCur());
            System.out.println(this.graph.getPoints().get(ends.get(i)));
            System.out.println(this.graph.connect(this.graph.getPoints().get(ends.get(i))));
            i++;
        }
        System.out.println(this.graph.isWinner());
    }
}
