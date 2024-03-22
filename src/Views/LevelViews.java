package Views;

import javax.swing.*;

public class LevelViews {
    private JButton btnBackLevel;
    private JRadioButton rdbEasy;
    private JRadioButton rdbMedium;

    private JRadioButton rdbHard;
    private JRadioButton rdbCustom;
    private JButton saveButton;
    private JPanel JPanelLevel;
    private JComboBox comboBox1;

    public LevelViews() {
    }

    public LevelViews(JButton btnBackLevel, JRadioButton rdbEasy, JRadioButton rdbMedium, JRadioButton rdbHard, JRadioButton rdbCustom, JButton saveButton, JPanel JPanelLevel) {
        this.btnBackLevel = btnBackLevel;
        this.rdbEasy = rdbEasy;
        this.rdbMedium = rdbMedium;
        this.rdbHard = rdbHard;
        this.rdbCustom = rdbCustom;
        this.saveButton = saveButton;
        this.JPanelLevel = JPanelLevel;
    }

    public JButton getBtnBackLevel() {
        return btnBackLevel;
    }

    public JRadioButton getRdbEasy() {
        return rdbEasy;
    }

    public JRadioButton getRdbMedium() {
        return rdbMedium;
    }

    public JRadioButton getRdbHard() {
        return rdbHard;
    }

    public JRadioButton getRdbCustom() {
        return rdbCustom;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JPanel getJPanelLevel() {
        return JPanelLevel;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }
}
