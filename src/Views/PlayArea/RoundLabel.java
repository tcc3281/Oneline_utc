package Views.PlayArea;

import Models.Game.Edge;

import javax.swing.*;
import java.awt.*;

public class RoundLabel extends JLabel {
    private final int DIAMETER = 20;

    public RoundLabel(int x, int y) {
        super();
        this.setText(String.valueOf(Edge.SECONDVISIT));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        setBounds(x, y, DIAMETER, DIAMETER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(LinePanel.WHITE);
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(LinePanel.GRAY);
        g.drawOval(0, 0, getWidth() , getHeight() );
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        return size;
    }

    @Override
    public Insets getInsets() {
        int radius = getSize().height / 2;
        return new Insets(radius, radius, radius, radius);
    }
}
