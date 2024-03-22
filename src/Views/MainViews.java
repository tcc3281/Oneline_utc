package Views;

import Controllers.PlayController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViews extends JFrame implements ActionListener {
    private HomeViews homeViews;
    private LevelViews levelViews;
    private ChallengesViews challengesViews;
    private PlayViews playViews;
    private JPanel JP;
    private CardLayout cardLayout;
    private PlayController controller;

    public MainViews(PlayController controller) {
        this.controller = controller;
        setTitle("One Line");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JP = new JPanel();
        cardLayout = new CardLayout();
        JP.setLayout(cardLayout);
        homeViews = new HomeViews();
        levelViews = new LevelViews();
        challengesViews = new ChallengesViews();
        playViews = new PlayViews();

        homeViews.getBtnLevel().addActionListener(this);
        homeViews.getBtnChallenges().addActionListener(this);
        homeViews.getBtnPlay().addActionListener(this);
        homeViews.getBtnExit().addActionListener(this);
        levelViews.getBtnBackLevel().addActionListener(this);
        challengesViews.getBtnBackChallenges().addActionListener(this);
        playViews.getBtnBackPlay().addActionListener(this);

        JP.add(homeViews.getJPanelHome(), "Home");
        JP.add(levelViews.getJPanelLevel(), "Level");
        JP.add(challengesViews.getJPanelChallenges(), "Challenges");
        JP.add(playViews.getJPanelPlay(), "Play");

        getContentPane().add(JP, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeViews.getBtnLevel()) {
            cardLayout.show(JP, "Level");
        } else if (e.getSource() == homeViews.getBtnChallenges()) {
            cardLayout.show(JP, "Challenges");
        } else if (e.getSource() == homeViews.getBtnPlay()) {
            this.controller.setChallenge();
            cardLayout.show(JP, "Play");
            this.controller.runTime();
        } else if (e.getSource() == homeViews.getBtnExit()) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == levelViews.getBtnBackLevel()) {
            cardLayout.show(JP, "Home");
        } else if (e.getSource() == challengesViews.getBtnBackChallenges()) {
            cardLayout.show(JP, "Home");
        } else if (e.getSource() == playViews.getBtnBackPlay()) {
            cardLayout.show(JP, "Home");
            this.controller.pauseTime();
        }

    }

    public PlayViews getPlayViews() {
        return playViews;
    }
}
