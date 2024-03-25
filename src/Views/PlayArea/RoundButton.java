package Views.PlayArea;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class RoundButton extends JButton {
    private Color buttonColor;
    private Timer timer;
    private final int x;
    private final int y;
    private boolean isBlink;
    private static final int DIAMETER = 17;

    private Color mainColor;

    public RoundButton(int x, int y, Color color) {
        super("");
        this.x = x;
        this.y = y;
        setBounds(x, y, DIAMETER, DIAMETER);
        setContentAreaFilled(false);
        buttonColor = color;
        timer = new Timer();
        mainColor = buttonColor;
    }
    public RoundButton(int x, int y) {
        super("");
        this.x = x;
        this.y = y;
        setBounds(x, y, DIAMETER, DIAMETER);
        setContentAreaFilled(false);
        buttonColor = LinePanel.PURPLE;
        timer = new Timer();
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

    public void setRButtonColor(Color c) {
        buttonColor = c;
        repaint();
    }

    public void blink(boolean isBlink) {
        this.isBlink=isBlink;
        if (isBlink) {
            timer.scheduleAtFixedRate(new RemindTask(), 0, 200);
        } else {
            buttonColor = mainColor;
            setRButtonColor(buttonColor);
            timer.cancel();
            timer=new Timer();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundButton)) return false;
        RoundButton that = (RoundButton) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    class RemindTask extends TimerTask {
        Color color;

        RemindTask() {
            color = buttonColor;
        }

        @Override
        public void run() {
            if (color.equals(mainColor)) {
                color = LinePanel.WHITE;
            } else {
                color = mainColor;
            }
            setRButtonColor(color);
        }
    }

    public boolean isBlink() {
        return isBlink;
    }

    public void setBlink(boolean blink) {
        isBlink = blink;
    }
}
