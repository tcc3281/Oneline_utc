package Views.Forms;

import javax.swing.*;
import java.awt.*;

public class ChallengesView {
    private JPanel JPanelChallenges;
    private JButton btnBackChallenges;
    private JPanel JPItem;


    public ChallengesView(JPanel JPanelChallenges, JButton btnBackChallenges, JPanel JPItem) {
        this.JPanelChallenges = JPanelChallenges;
        this.btnBackChallenges = btnBackChallenges;
        this.JPItem = JPItem;
    }

    public ChallengesView() {
    }

    public JPanel getJPanelChallenges() {
        AddItems(15);
        return JPanelChallenges;
    }

    public JPanel getJPItem() {
        return JPItem;
    }

    public JButton getBtnBackChallenges() {
        return btnBackChallenges;
    }

    public void AddItems(int count) {
        ChallengesItem item;
        int numRows = (int) Math.ceil((double) count / 2);
        JPItem.setLayout(new GridLayout(numRows, 2, 20, 20)); // Đặt GridLayout cho JPItem
        for (int i = 1; i <= count; i++) {
            item = new ChallengesItem(i);
            item.getJPanelItem().setPreferredSize(new Dimension(100, 150));
            JPItem.add(item.getJPanelItem());
        }
        JPItem.revalidate(); // Cập nhật layout của JPItem
        JPItem.repaint(); // Vẽ lại JPItem
    }
}
