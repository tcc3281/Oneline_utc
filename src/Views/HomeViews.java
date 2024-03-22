package Views;

import javax.swing.*;

public class HomeViews {

    private JButton btnPlay;
    private JButton btnChallenges;
    private JButton btnExit;
    private JButton btnLevel;
    private JPanel JPanelHome;

    public HomeViews() {
    }

    public HomeViews(JButton btnPlay, JButton btnChallenges, JButton btnExit, JButton btnLevel, JPanel JPanelHome) {
        this.btnPlay = btnPlay;
        this.btnChallenges = btnChallenges;
        this.btnExit = btnExit;
        this.btnLevel = btnLevel;
        this.JPanelHome = JPanelHome;
    }

    public JButton getBtnPlay() {
        return btnPlay;
    }

    public JButton getBtnChallenges() {
        return btnChallenges;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getBtnLevel() {
        return btnLevel;
    }

    public JPanel getJPanelHome() {
        return JPanelHome;
    }
}
