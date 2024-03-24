package Views.PlayArea;

import Controllers.PlayController;
import Models.Game.Edge;
import Models.Game.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LinePanel extends JPanel implements ActionListener {
    public static final Color PURPLE = new Color(134, 100, 218);
    public static final Color BLUE = new Color(59, 175, 218);
    public static final Color GREEN = new Color(21, 153, 127);
    public static final Color GRAY = new Color(204, 209, 217);
    public static final Color RED = new Color(255, 83, 100);
    public static final Color YELLOW = new Color(242, 171, 34);
    public static final Color WHITE = new Color(255, 255, 255);
    private List<RoundButton> roundButtons;
    private HashMap<Edge, RoundLabel> roundLabels;
    private List<Point> points;
    private PlayController controller;
    private Color edgeColor = GRAY;


    public LinePanel(int w, int h) {
        super();
        this.roundButtons = new ArrayList<>();
        this.roundLabels = new HashMap<>();
        this.setBackground(LinePanel.WHITE);
        this.setLayout(null);
        setPreferredSize(new Dimension(w, h));
    }

    public RoundLabel getRoundLabel(Edge key) {
        return roundLabels.get(key);
    }

    public void setGUI(List<Point> points, List<Edge> edges, Color color) {
        this.points = points;
        this.edgeColor = color;
        if (this.points == null) return;
        if (this.roundButtons != null){
            this.removeAll();
            this.roundButtons.clear();
        }
        for (Point p : this.points) {
            RoundButton r = new RoundButton(p.getX(), p.getY(), this.edgeColor);
            this.roundButtons.add(r);
            this.add(r);
            r.addActionListener(this);
        }

        for (Edge e : edges) {
            if (e.getMustVisit() == Edge.SECONDVISIT) {
                int x = (3 * e.getStart().getX() + 4 * e.getEnd().getX()) / 7;
                int y = (3 * e.getStart().getY() + 4 * e.getEnd().getY()) / 7;
                RoundLabel rl = new RoundLabel(x, y);
                this.roundLabels.put(e, rl);
                this.add(rl);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        HashSet<Edge> drawed = new HashSet<>();
        for (Point p : this.points) {
            for (Edge e : p.getEdges()) {
                if (drawed.contains(e)) continue;
                drawLine(e, g);
                drawed.add(e);
            }
        }
    }

    private void drawLine(Edge edge, Graphics g) {
        Point start = edge.getStart();
        Point end = edge.getEnd();
        int direction = edge.getDirection();

        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke = g2.getStroke();
        g2.setStroke(new BasicStroke(7));
        g2.setColor(edge.getColor());

        int x1 = start.getX() + 8;
        int y1 = start.getY() + 8;
        int x2 = end.getX() + 8;
        int y2 = end.getY() + 8;
        g2.drawLine(x1, y1, x2, y2);

        if (direction == Edge.STARTTOEND) {
            int x_mid, y_mid;
            x_mid = (4 * x1 + 3 * x2) / 7;
            y_mid = (4 * y1 + 3 * y2) / 7;
            int x_side1, y_side1, x_side2, y_side2;
            x_side1 = x_mid - 8;
            y_side1 = y_mid + 8;
            x_side2 = x_mid + 8;
            y_side2 = y_mid + 8;
            g2.setColor(LinePanel.RED);
            g2.setStroke(new BasicStroke(2.0f));
            int u_x = 0;
            int u_y = 1;
            int v_x = end.getX() - start.getX();
            int v_y = -end.getY() + start.getY();
            double angle = this.calTheta(u_x, u_y, v_x, v_y);
            AffineTransform oldTransform = g2.getTransform();
            g2.rotate(angle, x_mid, y_mid);
            g2.drawLine(x_mid, y_mid, x_side1, y_side1);
            g2.drawLine(x_mid, y_mid, x_side2, y_side2);
            g2.setTransform(oldTransform);
        }
        if (direction == Edge.ENDTOSTART) {
            int x_mid, y_mid;
            x_mid = (4 * x1 + 3 * x2) / 7;
            y_mid = (4 * y1 + 3 * y2) / 7;
            int x_side1, y_side1, x_side2, y_side2;
            x_side1 = x_mid - 10;
            y_side1 = y_mid + 10;
            x_side2 = x_mid + 10;
            y_side2 = y_mid + 10;
            g2.setColor(LinePanel.RED);
            g2.setStroke(new BasicStroke(2.0f));
            int u_x = 0;
            int u_y = 1;
            int v_x = start.getX() - end.getX();
            int v_y = -start.getY() + end.getY();
            double angle = this.calTheta(u_x, u_y, v_x, v_y);
            AffineTransform oldTransform = g2.getTransform();
            g2.rotate(angle, x_mid, y_mid);
            g2.drawLine(x_mid, y_mid, x_side1, y_side1);
            g2.drawLine(x_mid, y_mid, x_side2, y_side2);
            g2.setTransform(oldTransform);
        }
        g2.setStroke(stroke);
    }

    private double calTheta(int ax, int ay, int bx, int by) {
        double dotProduct = ax * bx + ay * by;
        double normA = Math.sqrt(ax * ax + ay * ay);
        double normB = Math.sqrt(bx * bx + by * by);

        double cosTheta = dotProduct / (normA * normB);
        double theta = Math.acos(cosTheta);
        return bx < 0 ? -theta : theta;
    }

    public void setController(PlayController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RoundButton r = (RoundButton) e.getSource();
        int x = r.getX();
        int y = r.getY();
        Point p = new Point(x, y);
        int position = points.indexOf(p);
        if (position != -1) {
            this.controller.connect(position);
        }
    }

    public void reset() {
        for (RoundLabel rb : roundLabels.values()) {
            rb.setVisible(true);
            rb.setText(String.valueOf(Edge.SECONDVISIT));
        }
        repaint();
    }

    public void back(Edge edge) {
        if (this.roundLabels.keySet().contains(edge)) {
            RoundLabel rl = this.roundLabels.get(edge);
            rl.setVisible(true);
            rl.setText(String.valueOf(edge.getLeftVisited()));
        }
        if (edge.getMustVisit() - edge.getLeftVisited() == 0) edge.setColor(LinePanel.GRAY);
        repaint();
    }

    public void connect(Point prev, Point next) {
        for (Edge e : prev.getEdges()) {
            if ((prev == e.getStart() && next == e.getEnd()) || (next == e.getStart() && prev == e.getEnd())) {
                e.setColor(edgeColor);
            }
        }
        repaint();
    }
}
