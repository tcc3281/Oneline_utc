package Models;

public class Edge {
    protected Point start;
    protected Point end;
    protected int direction;
    protected int count;// đại diện cho số lần đi 0 < count <=2
    protected boolean visited;
    public static final int STARTTOEND = 1;
    public static final int ENDTOSTART = -1;
    public static final int NODIRECTION = 0;

    public Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.direction = NODIRECTION;
        this.count = 1;
        this.visited = false;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
