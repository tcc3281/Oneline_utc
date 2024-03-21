package Views;

import javax.swing.*;

public class PlayViews {
    private JPanel JPanelPlay;
    private JButton btnBackPlay;
    private JButton btnNext;
    private JLabel txtTimer;
    private JButton btnReturn;
    private JButton btnHint;
    private JButton btnReset;
    private JLabel First;
    private JLabel Second;
    private JLabel Three;
    private JPanel jPanelBoardGame;

    public PlayViews() {
    }

    public PlayViews(JPanel JPanelPlay, JButton btnBackPlay, JButton btnNext, JLabel txtTimer, JButton btnReturn, JButton btnHint, JButton btnReset, JLabel first, JLabel second, JLabel three) {
        this.JPanelPlay = JPanelPlay;
        this.btnBackPlay = btnBackPlay;
        this.btnNext = btnNext;
        this.txtTimer = txtTimer;
        this.btnReturn = btnReturn;
        this.btnHint = btnHint;
        this.btnReset = btnReset;
        First = first;
        Second = second;
        Three = three;
    }

    public JPanel getjPanelBoardGame() {
        return jPanelBoardGame;
    }

    public JPanel getJPanelPlay() {
        return JPanelPlay;
    }

    public void setJPanelPlay(JPanel JPanelPlay) {
        this.JPanelPlay = JPanelPlay;
    }

    public JButton getBtnBackPlay() {
        return btnBackPlay;
    }

    public void setBtnBackPlay(JButton btnBackPlay) {
        this.btnBackPlay = btnBackPlay;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(JButton btnNext) {
        this.btnNext = btnNext;
    }

    public JLabel getTxtTimer() {
        return txtTimer;
    }

    public void setTxtTimer(JLabel txtTimer) {
        this.txtTimer = txtTimer;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(JButton btnReturn) {
        this.btnReturn = btnReturn;
    }

    public JButton getBtnHint() {
        return btnHint;
    }

    public void setBtnHint(JButton btnHint) {
        this.btnHint = btnHint;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(JButton btnReset) {
        this.btnReset = btnReset;
    }

    public JLabel getFirst() {
        return First;
    }

    public void setFirst(JLabel first) {
        First = first;
    }

    public JLabel getSecond() {
        return Second;
    }

    public void setSecond(JLabel second) {
        Second = second;
    }

    public JLabel getThree() {
        return Three;
    }

    public void setThree(JLabel three) {
        Three = three;
    }
}
