package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainViews extends JFrame  implements ActionListener {
    private HomeViews homeViews;
    private LevelViews levelViews;
    private ChallengesViews challengesViews;
    private  PlayViews playViews;
    private JPanel JP;
    private CardLayout cardLayout;

    public MainViews(){
        setTitle("One Line");
        setSize(400,600);
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

        JP.add(homeViews.getJPanelHome(),"Home");
        JP.add(levelViews.getJPanelLevel(),"Level");
        JP.add(challengesViews.getJPanelChallenges(),"Challenges");
        JP.add(playViews.getJPanelPlay(),"Play");

        getContentPane().add(JP,BorderLayout.CENTER);

        test();
    }
    public void test(){
        playViews.getjPanelBoardGame();
        playViews.getjPanelBoardGame().setLayout(new GridLayout(1,1));

        RoundButton roundButton1 = new RoundButton("");
        RoundButton roundButton2 = new RoundButton("");
//
        roundButton1.setBounds(50, 50, 17, 17);
        roundButton2.setBounds(100, 100, 17, 17);
//
        List<RoundButton> list=new ArrayList<>();
        list.add(roundButton1);
        list.add(roundButton2);
//// Tạo LinePanel với kích thước đủ lớn để chứa cả hai nút và đường thẳng
        LinePanel line = new LinePanel(list);
//        line.setBounds(0, 0, playViews.getjPanelBoardGame().getWidth(), playViews.getjPanelBoardGame().getHeight());
//
//// Thêm LinePanel vào JPanel trước
        playViews.getjPanelBoardGame().add(line);
//
//// Sau đó thêm hai RoundButton để chúng nằm trên cùng
//
//// Cuối cùng, gọi repaint để cập nhật giao diện
//        playViews.getjPanelBoardGame().repaint();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeViews.getBtnLevel()){
            cardLayout.show(JP,"Level");
        }
        else if(e.getSource() == homeViews.getBtnChallenges()){
            cardLayout.show(JP,"Challenges");
        }
        else if(e.getSource() == homeViews.getBtnPlay())
        {
            cardLayout.show(JP,"Play");
        }
        else if(e.getSource() == homeViews.getBtnExit()) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        else if(e.getSource() == levelViews.getBtnBackLevel()||e.getSource() == challengesViews.getBtnBackChallenges()
                || e.getSource() == playViews.getBtnBackPlay()){
            cardLayout.show(JP,"Home");
        }

    }
}
