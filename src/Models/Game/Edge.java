package Models.Game;

import Views.PlayArea.LinePanel;

import java.awt.*;
import java.util.Objects;

public class Edge {
    private Point start;
    private Point end;
    private int direction;
    private int mustVisit;// đại diện cho số lần đi 0 < mustVisit <=2
    private int visited;
    private Color color;
    public static final int STARTTOEND = 1;
    public static final int ENDTOSTART = -1;
    public static final int NODIRECTION = 0;
    public static final int NOTVISIT = 0;
    public static final int VISIT = 1;
    public static final int SECONDVISIT = 2;

    public Edge(Point start, Point end, int direction, int mustVisit) {
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.mustVisit = mustVisit;
        this.visited = NOTVISIT;
        this.color = LinePanel.GRAY;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getMustVisit() {
        return mustVisit;
    }

    public int getDirection() {
        return direction;
    }

    public int getLeftVisited() {
        return mustVisit -visited;
    }

    protected void visit() {
        this.visited++;
    }

    protected void setNotVisit() {
        this.visited = NOTVISIT;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", direction=" + direction +
                ", count=" + mustVisit +
                ", visited=" + visited +
                '}';
    }

    protected boolean isVisitable() {
        if (this.visited == NOTVISIT) {
            return true;
        } else {
            return this.visited == VISIT && this.mustVisit == SECONDVISIT;
        }
    }

    protected void back() {
        if (visited != NOTVISIT) {
            visited--;
        }
    }

    public boolean isVisitableFrom(Point point) {
        if (this.direction == NODIRECTION && (point.equals(this.start) || point.equals(this.end))) {
            return true;
        } else if (this.direction == Edge.STARTTOEND && point.equals(this.start)) {
            return true;
        } else if (this.direction == Edge.ENDTOSTART && point.equals(this.end)) {
            return true;
        } else {
            return false;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisitableTo(Point point) {
        if (this.direction == NODIRECTION && (point.equals(this.start) || point.equals(this.end))) {
            return true;
        } else if (this.direction == Edge.STARTTOEND && point.equals(this.end)) {
            return true;
        } else if (this.direction == Edge.ENDTOSTART && point.equals(this.start)) {
            return true;
        } else {
            return false;
        }
    }

    public Point ortherPoint(Point p) {
        if (p.equals(this.start)) return this.end;
        else if (p.equals(this.end)) return this.start;
        else return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return direction == edge.direction && mustVisit == edge.mustVisit && start.equals(edge.start) && end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, direction, mustVisit);
    }
}
