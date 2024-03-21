package Views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LinePanel extends JPanel {
    private List<RoundButton> list;

    public LinePanel(List<RoundButton> list) {
        this.list = list;
        this.setBackground(Color.WHITE);
        for (RoundButton r : list) {
            this.add(r);
        }
        setLayout(null); // Sử dụng null layout
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ đường thẳng từ trung tâm của list.get(0) đến trung tâm của list.get(1)

        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke = g2.getStroke();

        g2.setStroke(new BasicStroke(7));
        draw(0, 1, g2);
        draw(2, 1, g2);
        draw(2, 3, g2);
        draw(0, 3, g2);
        g2.setStroke(stroke);

    }

    private void draw(int r1, int r2, Graphics2D g2) {
        g2.setColor(RoundButton.GRAY); // Đặt màu cho đường thẳng
        int x1 = list.get(r1).getX() + list.get(r1).getWidth() / 2;
        int y1 = list.get(r1).getY() + list.get(r1).getHeight() / 2;
        int x2 = list.get(r2).getX() + list.get(r2).getWidth() / 2;
        int y2 = list.get(r2).getY() + list.get(r2).getHeight() / 2;
        g2.drawLine(x1, y1, x2, y2); // Vẽ đường thẳng

    }
}
