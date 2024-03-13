import Views.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game g=new Game();
        g.printPoints();
        g.printEdges();
        g.play();
    }
}