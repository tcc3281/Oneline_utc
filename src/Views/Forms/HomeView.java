package Views.Forms;

import javax.swing.*;

public class HomeView {

    private JButton btnPlay;
    private JButton btnChallenges;
    private JButton btnExit;
    private JButton btnLevel;
    private JPanel JPanelHome;

    public HomeView() {
    }

    public HomeView(JButton btnPlay, JButton btnChallenges, JButton btnExit, JButton btnLevel, JPanel JPanelHome) {
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
