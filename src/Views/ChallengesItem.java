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

    public void setJPanelItem(JPanel JPanelItem) {
        this.JPanelItem = JPanelItem;
    }

    public JButton getBtnPlayItem() {
        return btnPlayItem;
    }

    public void setBtnPlayItem(JButton btnPlayItem) {
        this.btnPlayItem = btnPlayItem;
    }

    public JLabel getTxtNumber() {
        return txtNumber;
    }

    public void setTxtNumber(JLabel txtNumber) {
        this.txtNumber = txtNumber;
    }
}
