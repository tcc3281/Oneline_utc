package Views.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChallengesView {
    private JPanel JPanelChallenges;
    private JButton btnBackChallenges;
    private JPanel JPItem;
    private ArrayList<JButton> lstItem;


    public ChallengesView(JPanel JPanelChallenges, JButton btnBackChallenges, JPanel JPItem) {
        this.JPanelChallenges = JPanelChallenges;
        this.btnBackChallenges = btnBackChallenges;
        this.JPItem = JPItem;
    }

    public ChallengesView() {
        lstItem = new ArrayList<>();
    }

    public JPanel getJPanelChallenges(int c,int curChallenge) {
        AddItems(c,curChallenge);
        return JPanelChallenges;
    }

    public JPanel getJPItem() {
        return JPItem;
    }

    public JButton getBtnBackChallenges() {
        return btnBackChallenges;
    }

    public void AddItems(int count, int curChallenge) {
        ChallengesItem item;
        int numRows = (int) Math.ceil((double) count / 2);
        JPItem.setLayout(new GridLayout(numRows, 2, 20, 20)); // Đặt GridLayout cho JPItem
        for (int i = 1; i <= count; i++) {
            item = new ChallengesItem(i);
            if (i <= curChallenge) {
                ImageIcon imageIcon = readImageFromLevelFolder("Level" + i +".png");
                item.setBackgroundImage(imageIcon);
            }
            item.getJPanelItem().setPreferredSize(new Dimension(100, 150));
            JPItem.add(item.getJPanelItem());
            lstItem.add(item.getBtnPlayItem());
        }
        JPItem.revalidate(); // Cập nhật layout của JPItem
        JPItem.repaint(); // Vẽ lại JPItem
    }
    public void addListener(ActionListener ac){
        for(JButton item : lstItem){
            item.addActionListener(ac);
        }
    }
    // Phương thức đọc ảnh từ thư mục level
    public ImageIcon readImageFromLevelFolder(String imageName) {
        return new ImageIcon(getClass().getResource("/Resources/Images/Level/" + imageName));
    }
    public ArrayList<JButton> getLstItem() {
        return lstItem;
    }

    public void updateItems(int curChallenge) {
        // Remove all current components from JPItem
        JPItem.removeAll();
        // Add the items again with the updated curChallenge
        AddItems(lstItem.size(), curChallenge);
        // Revalidate and repaint JPItem to reflect the changes
        JPItem.revalidate();
        JPItem.repaint();
    }
}
