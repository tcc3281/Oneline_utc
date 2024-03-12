package Models;

import java.io.*;
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
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try{
            File file = new File(".\\src\\Resources\\input.txt");
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            int num;
            String line;
            line = bufferedReader.readLine();
            num = Integer.parseInt(line);
            for(int i=0;i<num;i++){
                String[] nums = bufferedReader.readLine().split(" ");
                Point point = new Point(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
                points.add(point);
            }
            line = bufferedReader.readLine();
            num = Integer.parseInt(line);
            for(int i=0;i<num;i++){
                String[] nums = bufferedReader.readLine().split(" ");
                Edge edge = new Edge(points.get(Integer.parseInt(nums[0])),points.get(Integer.parseInt(nums[1])));
                edges.add(edge);
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException d){
            System.out.println(d);
        }
        finally {
            try{
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
    public boolean connect(Point next){
        if(next==this.cur){
            return false;
        }
        for(Edge edge:this.cur.getEdges()){
            if(edge.getStart()==next && (edge.getDirection()==Edge.ENDTOSTART || edge.getDirection()==Edge.NODIRECTION)){
                if(edge.isVisited()==VISIT){
                    return false;
                }
                this.cur=next;
                this.visits.push(this.cur);
                return true;
            }
            if(edge.getEnd()==next && (edge.getDirection()==Edge.STARTTOEND || edge.getDirection()==Edge.NODIRECTION)){
                if(edge.isVisited()==VISIT){
                    return false;
                }
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
        for(Edge edge:this.cur.getEdges()){
            if(!edge.isVisited()){
                return true;
            }
        }
        return false;
    }
    public boolean isWinner(){
        for(Edge edge:edges){
            if(edge.isVisited()){
                return false;
            }
        }
        return true;
    }
    public boolean back(){
        if(this.timesOfBack==0){
            return false;
        }
        this.cur=visits.pop();
        this.timesOfBack--;
        return true;
    }
    public void reset(){
        while (!visits.empty()){
            this.cur=this.visits.pop();
        }
        this.cur=null;
        this.timesOfBack=3;
    }
}
