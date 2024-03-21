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
        int x1 = list.get(0).getX() + list.get(0).getWidth() / 2;
        int y1 = list.get(0).getY() + list.get(0).getHeight() / 2;
        int x2 = list.get(1).getX() + list.get(1).getWidth() / 2;
        int y2 = list.get(1).getY() + list.get(1).getHeight() / 2;
        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke = g2.getStroke();

        g2.setStroke(new BasicStroke(7));
        g2.setColor(Color.RED); // Đặt màu cho đường thẳng
        g2.drawLine(x1, y1, x2, y2); // Vẽ đường thẳng
        g2.setStroke(stroke);
    }
}
