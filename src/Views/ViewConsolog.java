package Views;

import Models.Bot.Hint;
import Models.Game.Edge;
import Models.Game.Graph;
import Models.Game.Point;

import java.util.LinkedList;
import java.util.Scanner;

public class ViewConsolog {
    private LinkedList<Integer> steps;
    private Graph graph;
    private Hint hint;
    private int level;

    public ViewConsolog() {
        graph = new Graph();
        hint = new Hint();
        steps = new LinkedList<>();
        level = 1;
    }

    public void printPoints() {
        int i = 0;
        for (Point point : this.graph.getPoints()) {
            System.out.print((i++) + " ");
            System.out.println(point);
        }
    }

    public void printEdges() {
        int i = 0;
        for (Edge edge : this.graph.getEdges()) {
            System.out.print((i++) + " ");
            System.out.println(edge);
        }
    }

    public void play() {
        graph.readData(1);
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---------List of points");
            printPoints();
            System.out.println("---------List of edges");
            printEdges();
            System.out.println("---------Visited Point: " + this.graph.getVisitedPoints().size() + " " + steps);
            System.out.println("---------Current Point: " + this.graph.getCur());
            showMenu();
            String input = sc.nextLine();
            choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    System.out.println("Choose the point");
                    int index = Integer.parseInt(sc.nextLine());
                    if (this.graph.connect(this.graph.getPoints().get(index))) {
                        steps.add(index);
                    }
                    break;
                case 2:
                    if (this.graph.back()) {
                        steps.removeLast();
                    }
                    break;
                case 3:
                    this.graph.reset();
                    steps.clear();
                    break;
                case 4:
                    hint.setLevel(1);
                    hint.play();
                    this.graph.reset();
                    steps.clear();
                    break;
                default:
                    break;
            }
        } while (!this.graph.isFinish() && choice != 5);
        System.out.println();
        if (this.graph.isWinner()) {
            System.out.println("Win!");
        } else {
            System.out.println("Lose!");
        }
    }

    public void showMenu() {
        System.out.println("Choose!");
        System.out.println("1. Connect.");
        System.out.println("2. Back.");
        System.out.println("3. Reset.");
        System.out.println("4. Hint.");
        System.out.println("5. Out.");
    }
}
