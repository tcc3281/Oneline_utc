package Models.Game;

import Controllers.PlayController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private List<Point> points;
    private List<Edge> edges;
    private Stack<Point> visitedPoints;
    private Stack<Edge> visitedEdges;
    private Point cur;
    private PlayController controller;
    private int timesLeftBack;
    private final String PATH = ".\\src\\Resources\\Levels\\level_";
    public static final int TIMESBACK = 3;

    public Graph(PlayController controller) {
        this.controller = controller;
        this.points = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.visitedPoints = new Stack<>();
        this.visitedEdges = new Stack<>();
    }

    public Graph() {
        this.points = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.visitedPoints = new Stack<>();
        this.visitedEdges = new Stack<>();
    }

    public void readData(int level) {
        if (this.points != null) {
            this.points.clear();
        }
        if (this.edges != null) {
            this.edges.clear();
        }
        File file=null;
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            file = new File(PATH + String.format("%02d", level) + ".txt");
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            int num;
            String line;
            line = bufferedReader.readLine();
            num = Integer.parseInt(line);
            for (int i = 0; i < num; i++) {
                String[] nums = bufferedReader.readLine().split(" ");
                Point point = new Point(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                points.add(point);
            }
            line = bufferedReader.readLine();
            num = Integer.parseInt(line);
            for (int i = 0; i < num; i++) {
                String[] nums = bufferedReader.readLine().split(" ");
                Edge edge = new Edge(points.get(Integer.parseInt(nums[0])), points.get(Integer.parseInt(nums[1])), Integer.parseInt(nums[2]), Integer.parseInt(nums[3]));
                edge.getStart().addEdge(edge);
                edge.getEnd().addEdge(edge);
                edges.add(edge);
            }
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
        reset();
    }

    public boolean connect(Point next) {
        if (this.cur == null) {
            setPointStart(next);
            return true;
        }
        if (next == this.cur) {
            return false;
        }
        for (Edge edge : this.cur.getEdges()) {
            if (edge.isVisitableTo(next)) {
                if (!edge.isVisitable()) {
                    return false;
                }
                this.cur = next;
                this.visitedPoints.push(this.cur);
                this.visitedEdges.push(edge);
                edge.visit();
                return true;
            }
        }
        return false;
    }

    public void setPointStart(Point start) {
        this.cur = start;
        visitedPoints.push(cur);
    }

    public boolean isFinish() {
        if (this.cur == null) {
            return false;
        }
        for (Edge edge : this.cur.getEdges()) {
            if (edge.isVisitable()) {
                return false;
            }
        }
        return true;
    }

    public boolean isWinner() {
        for (Edge edge : edges) {
            if (edge.isVisitable()) {
                return false;
            }
        }
        return true;
    }

    public boolean back() {
        if (this.cur == null) {
            return false;
        }
        if (this.visitedEdges.empty()) {
            return false;
        }
        if (this.timesLeftBack == 0) {
            return false;
        }
        if (!visitedEdges.empty()) {
            visitedEdges.pop().back();
        }
        if (visitedEdges.empty()) {
            visitedPoints.clear();
            this.cur = null;
        } else {
            visitedPoints.pop();
            this.cur = visitedPoints.peek();
        }
        this.timesLeftBack--;
        return true;
    }

    public void reset() {
        if (!visitedPoints.empty()) {
            visitedPoints.clear();
        }
        if (!visitedEdges.empty()) {
            visitedEdges.clear();
        }
        this.cur = null;
        this.timesLeftBack = TIMESBACK;
        for (Edge e : this.edges) {
            e.setNotVisit();
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Point getCur() {
        return cur;
    }

    public Stack<Point> getVisitedPoints() {
        return visitedPoints;
    }
}
