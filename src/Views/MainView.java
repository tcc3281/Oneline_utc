package Views;

import Controllers.PlayController;
import Views.Forms.ChallengesView;
import Views.Forms.HomeView;
import Views.Forms.LevelView;
import Views.Forms.PlayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame implements ActionListener {
    private HomeView homeView;
    private LevelView levelView;
    private ChallengesView challengesView;
    private PlayView playView;
    private JPanel JP;
    private CardLayout cardLayout;
    private PlayController controller;

    public MainView(PlayController controller) {
        this.controller = controller;
        setTitle("One Line");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JP = new JPanel();
        cardLayout = new CardLayout();
        JP.setLayout(cardLayout);
        homeView = new HomeView();
        levelView = new LevelView();
        challengesView = new ChallengesView();
        playView = new PlayView();

        homeView.getBtnLevel().addActionListener(this);
        homeView.getBtnChallenges().addActionListener(this);
        homeView.getBtnPlay().addActionListener(this);
        homeView.getBtnExit().addActionListener(this);
        levelView.getBtnBackLevel().addActionListener(this);
        challengesView.getBtnBackChallenges().addActionListener(this);
        playView.getBtnBackPlay().addActionListener(this);

        JP.add(homeView.getJPanelHome(), "Home");
        JP.add(levelView.getJPanelLevel(), "Level");
        JP.add(challengesView.getJPanelChallenges(), "Challenges");
        JP.add(playView.getJPanelPlay(), "Play");

        getContentPane().add(JP, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeView.getBtnLevel()) {
            cardLayout.show(JP, "Level");
        } else if (e.getSource() == homeView.getBtnChallenges()) {
            cardLayout.show(JP, "Challenges");
        } else if (e.getSource() == homeView.getBtnPlay()) {
            this.controller.setChallenge();
            cardLayout.show(JP, "Play");
            this.controller.runTime();
        } else if (e.getSource() == homeView.getBtnExit()) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.controller.saveData();
                System.exit(0);
            }
        } else if (e.getSource() == levelView.getBtnBackLevel()) {
            cardLayout.show(JP, "Home");
        } else if (e.getSource() == challengesView.getBtnBackChallenges()) {
            cardLayout.show(JP, "Home");
        } else if (e.getSource() == playView.getBtnBackPlay()) {
            cardLayout.show(JP, "Home");
            this.controller.pauseTime();
        }

    }

    public PlayView getPlayViews() {
        return playView;
    }
}
