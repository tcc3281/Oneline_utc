package Models.Game;

import java.util.ArrayList;

public class Point {
    protected ArrayList<Edge> edges;
    protected int x;
    protected int y;
    public Point() {
        this.edges=new ArrayList<>();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.edges=new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
