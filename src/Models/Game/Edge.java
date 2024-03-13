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

    public Point getEnd() {
        return end;
    }

    public int getDirection() {
        return direction;
    }

    public void visit() {
        this.visited++;
    }

    public void setNotVisit(){
        this.visited=NOTVISIT;
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
        else {
            return this.visited == VISIT && this.count == SECONDVISIT;
        }
    }

    public void back(){
        if(visited!=NOTVISIT){
            visited--;
        }
    }
}
