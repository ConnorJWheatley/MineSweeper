import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class WinCondition {

    private GUI gui;

    public WinCondition(GUI gui){
        this.gui = gui;
    }
    /**
     *
     * @param seconds is how long the player took to lose the game
     */
    public void hitBomb(double seconds){
        gui.timer.cancel();
        JOptionPane.showMessageDialog(null,"You hit a bomb, you lost!\nSeconds: " + seconds + "\nThank you for playing!", "LOSS", JOptionPane.INFORMATION_MESSAGE);
        System.exit(1);
    }

    /**
     *
     * @param seconds is how long the player took to win the game
     */
    public void playerHasWon(double seconds){
        gui.timer.cancel();
        List<Double> allScores = new ArrayList<>();
        String file = "C:\\Users\\Connor\\Documents\\AllCode\\Java\\MineSweeper";
        String line;
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            while((line = bReader.readLine()) != null){
                Double time = Double.valueOf(line);
                allScores.add(time); // need to split the string first before adding it so i can take [0] which will be the time which i can then use to compare
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(allScores);
        for(int i = 0; i < allScores.size(); i++){
            if(seconds < allScores.get(i)){
                allScores.add(i, seconds);
                break;
            }
            if(i == allScores.size() - 1){
                allScores.add(i + 1, seconds);
            }
        }

        try{
            FileWriter fWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for(Double str : allScores){
                bWriter.write(str.toString() + System.lineSeparator());
            }
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null,"Congratulations, you won!\nSeconds: " + seconds + "\nCurrent top score is: " + allScores.get(0) +"\nThank you for playing!", "WIN", JOptionPane.INFORMATION_MESSAGE);
        System.exit(1);
    }
    /* this is needed for displaying the top 5 scores
        for(int i = 0; i < 5; i++){
            System.out.println(times.get(i));
        }
    */
}
