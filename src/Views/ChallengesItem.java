package Views;

import javax.swing.*;

public class ChallengesItem {
    private JPanel JPanelItem;
    private JButton btnPlayItem;
    private JLabel txtNumber;

    public ChallengesItem(int count) {
        txtNumber.setText(String.valueOf(count));

    }

    public ChallengesItem(JPanel JPanelItem, JButton btnPlayItem, JLabel txtNumber) {
        this.JPanelItem = JPanelItem;
        this.btnPlayItem = btnPlayItem;
        this.txtNumber = txtNumber;
    }

    public JPanel getJPanelItem() {
        return JPanelItem;
    }

    public JButton getBtnPlayItem() {
        return btnPlayItem;
    }

    public JLabel getTxtNumber() {
        return txtNumber;
    }
}
