package Views.Forms;

import Models.Timer.CountdownTimer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LevelView {
    private JButton btnBackLevel;
    private JRadioButton rdbEasy;
    private JRadioButton rdbMedium;

    public void setRdbEasy(JRadioButton rdbEasy) {
        this.rdbEasy = rdbEasy;
    }

    public void setRdbMedium(JRadioButton rdbMedium) {
        this.rdbMedium = rdbMedium;
    }

    public void setRdbHard(JRadioButton rdbHard) {
        this.rdbHard = rdbHard;
    }

    public void setRdbCustom(JRadioButton rdbCustom) {
        this.rdbCustom = rdbCustom;
    }

    private JRadioButton rdbHard;
    private JRadioButton rdbCustom;
    private JButton saveButton;
    private JPanel JPanelLevel;
    private JSpinner jSPCustom;

    public LevelView() {
    }

    public LevelView(JButton btnBackLevel, JRadioButton rdbEasy, JRadioButton rdbMedium, JRadioButton rdbHard, JRadioButton rdbCustom, JButton saveButton, JPanel JPanelLevel) {
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

    public JSpinner getjSPCustom() {
        return jSPCustom;
    }
    public  int setTime(){
        int time=0;
        if(getRdbEasy().isSelected())
        {
            time = CountdownTimer.EASY;
        }
        else if(getRdbMedium().isSelected())
        {
            time = CountdownTimer.MEDIUM;
        }
        else if(getRdbHard().isSelected())
        {
            time = CountdownTimer.HARD;
        }else {
            Date date = (Date) getjSPCustom().getValue();
            time = date.getMinutes()*60+date.getSeconds();
        }
        return time;
    }

    private void createUIComponents() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Date date=calendar.getTime();
        SpinnerDateModel model = new SpinnerDateModel(date, null, null, Calendar.SECOND);
        jSPCustom=new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSPCustom, "mm:ss");
        jSPCustom.setEditor(editor);
        JFormattedTextField tf=((JSpinner.DefaultEditor) jSPCustom.getEditor()).getTextField();
        tf.setEditable(false);

    }
}
