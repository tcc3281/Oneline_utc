package Models.Game;

import java.util.Objects;

public class Edge {
    private Point start;
    private Point end;
    private int direction;
    private int count;// đại diện cho số lần đi 0 < count <=2
    private int visited;
    public static final int STARTTOEND = 1;
    public static final int ENDTOSTART = -1;
    public static final int NODIRECTION = 0;
    public static final int NOTVISIT = 0;
    public static final int VISIT = 1;
    public static final int SECONDVISIT = 2;

    public Edge(Point start, Point end, int direction, int count) {
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.count = count;
        this.visited = NOTVISIT;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getCount() {
        return count;
    }

    public int getDirection() {
        return direction;
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
                ", count=" + count +
                ", visited=" + visited +
                '}';
    }

    protected boolean isVisitable() {
        if (this.visited == NOTVISIT) {
            return true;
        } else {
            return this.visited == VISIT && this.count == SECONDVISIT;
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
        return direction == edge.direction && count == edge.count && start.equals(edge.start) && end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, direction, count);
    }
}
