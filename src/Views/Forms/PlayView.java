package Views.Forms;

import Models.Game.Graph;
import Views.PlayArea.LinePanel;

import javax.swing.*;
import java.awt.*;

public class PlayView {
    private JPanel JPanelPlay;
    private JButton btnBackPlay;
    private JButton btnNext;
    private JLabel txtTimer;
    private JButton btnReturn;
    private JButton btnHint;
    private JButton btnReset;
    private JLabel First;
    private JLabel Second;
    private JLabel Third;
    private JPanel jPanelBoardGame;
    private JPanel jPanelHead;
    private JLabel jLabelTitle;
    private LinePanel mainPlay;
    private static final ImageIcon REDHEART = new ImageIcon(".\\src\\Resources\\Images\\redheart.png");
    private static final ImageIcon BLACKHEART = new ImageIcon(".\\src\\Resources\\Images\\blackheart.png");

    public PlayView() {
    }

    public PlayView(JPanel JPanelPlay, JButton btnBackPlay, JButton btnNext, JLabel txtTimer, JButton btnReturn, JButton btnHint, JButton btnReset, JLabel first, JLabel second, JLabel third) {
        this.JPanelPlay = JPanelPlay;
        this.btnBackPlay = btnBackPlay;
        this.btnNext = btnNext;
        this.txtTimer = txtTimer;
        this.btnReturn = btnReturn;
        this.btnHint = btnHint;
        this.btnReset = btnReset;
        First = first;
        Second = second;
        Third = third;
    }

    public void setTitle(String title) {
        this.jLabelTitle.setText(title);
    }

    public JPanel getJPanelPlay() {
        return JPanelPlay;
    }

    public JButton getBtnBackPlay() {
        return btnBackPlay;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public JLabel getTxtTimer() {
        return txtTimer;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnHint() {
        return btnHint;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public JLabel getFirst() {
        return First;
    }

    public JLabel getSecond() {
        return Second;
    }

    public JLabel getThird() {
        return Third;
    }

    public JPanel getjPanelHead() {
        return jPanelHead;
    }

    public LinePanel getMainPlay() {
        return mainPlay;
    }

    public void setPlayView() {
        jPanelBoardGame.setLayout(new GridLayout(1, 1));
        jPanelBoardGame.add(mainPlay);

    }

    public void setNumberHeart(int num) {
        First.setIcon(BLACKHEART);
        Second.setIcon(BLACKHEART);
        Third.setIcon(BLACKHEART);
        if (num > 0) {
            First.setIcon(REDHEART);
        }
        if (num > 1) {
            Second.setIcon(REDHEART);
        }
        if (num > 2) {
            Third.setIcon(REDHEART);
        }
    }

    public void createMainPlay() {
        if (mainPlay == null) {
            mainPlay = new LinePanel(jPanelBoardGame.getWidth(), jPanelBoardGame.getHeight());
        }
    }

    public void reset() {
        setNumberHeart(Graph.BACKLIMIT);
        this.mainPlay.reset();
    }
}
