package Models.Game;

public class Edge {
    protected Point start;
    protected Point end;
    protected int direction;
    protected int count;// đại diện cho số lần đi 0 < count <=2
    protected int visited;
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
        this.visited=NOTVISIT;
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

    public int isVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
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

    public boolean isVisitable(){
        if(this.visited==NOTVISIT){
            return true;
        }
        else if(this.visited==VISIT && this.count==SECONDVISIT){
            return true;
        }else {
            return false;
        }
    }

    public void back(){
        if(visited!=NOTVISIT){
            visited--;
        }
    }
}
