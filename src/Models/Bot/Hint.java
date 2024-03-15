package Models.Bot;

import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;

import java.util.ArrayList;
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
        graph.readData(1);
    }

    public void setLevel(int level) {
        graph.readData(level);
    }

    public LinkedList<Point> play() {
        for (Point p : graph.getPoints()) {
            this.graph.connect(p);
            this.steps.add(p);
            if (_try(p)) {
                return steps;
            }
            this.graph.reset();
            this.steps.clear();
        }
        return null;
    }

    public boolean _try(Point p) {
        if (graph.isWinner()) {
            return true;
        }
        for (Edge e : p.getEdges()) {
            if (e.isVisitableFrom(p)) {
                if (graph.connect(e.ortherPoint(p))) {
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
