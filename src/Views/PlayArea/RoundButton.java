package Views.PlayArea;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    private Color buttonColor;
    private static final int DIAMETER = 17;

    public RoundButton(int x, int y ,Color color) {
        super("");
        setBounds(x, y, DIAMETER, DIAMETER);
        setContentAreaFilled(false);
        buttonColor = color;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(buttonColor);
        g.fillOval(0, 0, getSize().width, getSize().height);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(LinePanel.GRAY);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
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
