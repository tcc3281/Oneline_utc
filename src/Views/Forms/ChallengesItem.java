package Views.Forms;

import javax.swing.*;
import java.awt.*;

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
    // Trong lớp ChallengesItem
    public void setBackgroundImage(ImageIcon imageIcon) {
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanelItem.add(backgroundLabel, gbc);
        JPanelItem.setComponentZOrder(backgroundLabel, JPanelItem.getComponentCount() - 1); // Đặt backgroundLabel ở dưới cùng

    }
}
