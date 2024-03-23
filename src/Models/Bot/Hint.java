package Models.Bot;

import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;

import java.util.LinkedList;

public class Hint {
    private Graph graph;
    LinkedList<Point> steps;

    public Hint() {
        graph = new Graph();
        steps = new LinkedList<>();
    }

    public Hint(int level) {
        graph = new Graph();
        steps = new LinkedList<>();
    }

    public void setChallenge(int level) {
        graph.readData(level);
    }

    public LinkedList<Point> solve() {
        for (int i=0;i<graph.getPoints().size();i++) {
            this.graph.connect(i);
            this.steps.add(graph.getPoints().get(i));
            if (_try(graph.getPoints().get(i))) {
                return steps;
            }
            this.graph.reset();
            this.steps.clear();
        }
        return null;
    }

    private boolean _try(Point p) {
        if (graph.isWinner()) {
            return true;
        }
        for (int i=0;i<p.getEdges().size();i++) {
            Edge e=p.getEdges().get(i);
            if (e.isVisitableFrom(p)) {
                if (graph.connect(this.graph.getPoints().indexOf(e.ortherPoint(p)))) {
                    steps.add(e.ortherPoint(p));
                    if (_try(e.ortherPoint(p))) {
                        return true;
                    }
                    if (graph.back()) {
                        steps.removeLast();
                    }
                }
            }
        }
        return false;
    }
}
