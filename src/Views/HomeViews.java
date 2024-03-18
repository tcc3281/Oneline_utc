package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeViews{

    private JButton btnPlay;
    private JButton btnChallenges;
    private JButton btnExit;
    private JButton btnLevel;
    private JPanel JPanelHome;

    public HomeViews() {
    }
    public JButton getBtnPlay() {
        return btnPlay;
    }

    public void setBtnPlay(JButton btnPlay) {
        this.btnPlay = btnPlay;
    }

    public JButton getBtnChallenges() {
        return btnChallenges;
    }

    public void setBtnChallenges(JButton btnChallenges) {
        this.btnChallenges = btnChallenges;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public JButton getBtnLevel() {
        return btnLevel;
    }

    public void setBtnLevel(JButton btnLevel) {
        this.btnLevel = btnLevel;
    }

    public JPanel getJPanelHome() {
        return JPanelHome;
    }

    public void setJPanelHome(JPanel JPanelHome) {
        this.JPanelHome = JPanelHome;
    }



    public HomeViews(JButton btnPlay, JButton btnChallenges, JButton btnExit, JButton btnLevel, JPanel JPanelHome) {
        this.btnPlay = btnPlay;
        this.btnChallenges = btnChallenges;
        this.btnExit = btnExit;
        this.btnLevel = btnLevel;
        this.JPanelHome = JPanelHome;
    }
}
