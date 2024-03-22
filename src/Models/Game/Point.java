package Models.Game;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private List<Edge> edges;
    private int x;
    private int y;

    public List<Edge> getEdges() {
        return edges;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
}
