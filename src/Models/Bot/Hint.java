package Models.Bot;

import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;

import java.util.Collection;
import java.util.LinkedList;

public class Hint {
    private Graph graph;

    public Hint() {
        graph = new Graph();
    }

    public Hint(int level) {
        graph = new Graph();
    }

    public void setChallenge(int level) {
        graph.readData(level);
    }

    public LinkedList<Point> solve() {
        LinkedList<Point> set_point=new LinkedList<>(graph.getPoints());
        for (int i=0;i<set_point.size();i++){
            int check=0;
            for(Edge edge:set_point.get(i).getEdges()){
                check+=edge.getMustVisit();
            }
            if(check%2==1){
                Point p=set_point.get(i);
                this.graph.connect(this.graph.getPoints().indexOf(p));
                if(_try(p)){
                    return new LinkedList<>(graph.getVisitedPoints());
                }
                this.graph.reset();
                set_point.remove(i);
                i=-1;
            }
        }

        for (int i = 0; i < set_point.size(); i++) {
            Point p=set_point.get(i);
            this.graph.connect(this.graph.getPoints().indexOf(p));
            if (_try(p)) {
                return new LinkedList<>(graph.getVisitedPoints());
            }
            this.graph.reset();
        }
        return null;
    }

    private boolean _try(Point p) {
        if (graph.isFinish()){
            if(graph.isWinner()){
                return true;
            }else {
                return false;
            }
        }
        for(int i=0;i<p.getEdges().size();i++){
            Edge e=p.getEdges().get(i);
            Point or_p=e.ortherPoint(p);
            if(graph.connect(this.graph.getPoints().indexOf(or_p))){
                if(_try(or_p)){
                    return true;
                }else {
                    this.graph.back(true);
                }
            }
        }
        return false;
    }
}
