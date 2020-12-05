import javax.swing.JLabel;
import java.util.TimerTask;

public class Time extends TimerTask {
    private int second;
    private GUI gui;

    public Time(int second, GUI gui){
        this.second = second;
        this.gui = gui;
    }

    @Override
    public void run() {
        if(gui.time == null){
            gui.time = new JLabel("Time Played: " + second);
        }
        else {
            gui.time.setText("Time Played: " + second);
        }
        second += 1;
    }
}

