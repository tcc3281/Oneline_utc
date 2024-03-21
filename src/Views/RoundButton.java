package Views;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    public static final Color PURPLE = new Color(134, 100, 218);
    public static final Color BLUE = new Color(59, 175, 218);
    public static final Color GREEN = new Color(21, 153, 127);
    public static final Color GRAY = new Color(204, 209, 217);
    private Color buttonColor = PURPLE;


    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    // Thay đổi màu sắc của nút
    public void setButtonColor(Color newColor) {
        buttonColor = newColor;
        repaint();
    }

    // Vẽ nút hình tròn
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(buttonColor);
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }

    // Vẽ viền cho nút (nếu cần)
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(buttonColor);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Định hình kích thước ưa thích cho nút (hình tròn)
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        return size;
    }

    // Định hình viền cho nút (nếu cần)
    @Override
    public Insets getInsets() {
        int radius = getSize().height / 2;
        return new Insets(radius, radius, radius, radius);
    }
}
