package Models;

import java.util.ArrayList;
import java.util.Stack;

public class Graph {
    protected ArrayList<Point> points;
    protected ArrayList<Edge> edges;
    protected Stack<Point> visits;
    protected Point cur;
    protected int timesOfBack;
    public static final boolean VISIT = true;
    public static final boolean NOTVISIT = false;
    public Graph() {
        this.points = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.visits = new Stack<>();
        readData(1);
        reset();
    }
    public void readData(int level){
        /* Dữ liệu đầu vào
         - Số điểm
         - Danh sách điểm
         - Số cạnh
         - Danh sách cạnh : mặc định là vô hướng
         Extend: danh sách cạnh có thể thêm thông số về sô lần phải đi qua và hướng đi
         */
    }
    public boolean connect(Point next){
        /*
         - Nhận giá trị 2 đỉnh đầu vào của cạnh
         - Nếu cạnh đã được đi qua chưa
          + Nếu đã đi qua thì trả vè false
          + Nếu chưa đi qua thì nối đến điểm đó, gán điểm cur bằng next
         */
        if(next==this.cur){
            return false;
        }
        for(Edge edge:this.cur.getEdges()){
            if(edge.getStart()==next && (edge.getDirection()==Edge.ENDTOSTART || edge.getDirection()==Edge.NODIRECTION)){
                this.cur=next;
                this.visits.push(this.cur);
                return true;
            }
            if(edge.getEnd()==next && (edge.getDirection()==Edge.STARTTOEND || edge.getDirection()==Edge.NODIRECTION)){
                this.cur=next;
                this.visits.push(this.cur);
                return true;
            }
        }
        return false;
    }
    public void setPointStart(Point start){
        this.cur = start;
        visits.push(cur);
    }
    public boolean isFinish(){
        /*
         kiểm tra xem còn có thể đi được không
         - đi được trả về true
         - không đi được trả về false
         */
        for(Edge edge:this.cur.getEdges()){
            if(!edge.isVisited()){
                return true;
            }
        }
        return false;
    }
    public boolean isWinner(){
        /*
        kiểm tra xem người chơi đã thắng chưa
         */
        for(Edge edge:edges){
            if(edge.isVisited()){
                return false;
            }
        }
        return true;
    }
    public boolean back(){
        /*
        - giới hạn số lần back là 3
        - mỗi lần back là pop khỏi stack
         */
        if(this.timesOfBack==0){
            return false;
        }
        this.cur=visits.pop();
        this.timesOfBack--;
        return true;
    }
    public void reset(){
        this.timesOfBack=3;
        while (!visits.empty()){
            this.cur=this.visits.pop();
        }
    }
}
