package Views.PlayArea;

import Models.Game.Edge;
import Models.Game.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LinePanel extends JPanel {
    private List<RoundButton> roundButtons;
    private List<Point> points;

    public LinePanel(int w, int h) {
        super();
        this.roundButtons = new ArrayList<>();
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        setPreferredSize(new Dimension(w, h));
    }

    public void newGame(List<Point> points) {
        this.points = points;
        setGUI();
    }

    private void setGUI() {
        if (this.points == null) return;
        if (this.roundButtons != null) this.roundButtons.clear();

        for (Point p : this.points) {
            RoundButton r = new RoundButton("", p.getX(), p.getY());
            r.setBounds(p.getX(), p.getY(), RoundButton.DIAMETER, RoundButton.DIAMETER);
            this.roundButtons.add(r);
            this.add(r);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        HashSet<Edge> drawed = new HashSet<>();
        for (Point p : this.points) {
            for (Edge e : p.getEdges()) {
                if (drawed.contains(e)) continue;
                drawLine(e.getStart(), e.getEnd(), g, e.getDirection());
                drawed.add(e);
            }
        }

    }

    private void drawLine(Point start, Point end, Graphics g, int direction) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke = g2.getStroke();

        g2.setStroke(new BasicStroke(7));

        g2.setColor(RoundButton.GRAY);
        int x1 = start.getX() + 8;
        int y1 = start.getY() + 8;
        int x2 = end.getX() + 8;
        int y2 = end.getY() + 8;
        g2.drawLine(x1, y1, x2, y2);

        if (direction == Edge.STARTTOEND) {
            int x_mid, y_mid;
            x_mid = (x1 + x2) / 2;
            y_mid = (y1 + y2) / 2;
            int x_side1, y_side1, x_side2, y_side2;
            x_side1 = x_mid - 10;
            y_side1 = y_mid + 10;
            x_side2 = x_mid + 10;
            y_side2 = y_mid + 10;
            g2.setColor(RoundButton.RED);
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
            x_mid = (x1 + x2) / 2;
            y_mid = (y1 + y2) / 2;
            int x_side1, y_side1, x_side2, y_side2;
            x_side1 = x_mid - 10;
            y_side1 = y_mid + 10;
            x_side2 = x_mid + 10;
            y_side2 = y_mid + 10;
            g2.setColor(RoundButton.RED);
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

        double alpha = bx < 0 ? -theta : theta;

        return alpha;
    }
}
