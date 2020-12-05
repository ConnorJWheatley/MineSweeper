import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Operations {

    private GUI gui;
    private int x;
    private int y;

    public Operations(GUI mine, int x, int y) {
        this.x = x;
        this.y = y;
        this.gui = mine;
    }

    // not priority
    public void clearEmptySquares(){

    }

    /**
     * Description - This method will check every adjacent square to see if there is a bomb
     * This needs to take into account if the user clicks on the outer squares as the checking criteria needs to change
     * @return the amount of allSquares that surround the square the player has selected
     */
    public int checkForBombs() {
        int bombCount = 0;
        if (gui.allSquares.get(gui.mineArray[x][y]) == 0) {
            // top left
            if (x == 0 && y == 0) {
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y + 1]) == 1) { bombCount += 1; }
            }
            // bottom left
            if (x == 9 && y == 0) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
            }
            // top right
            if (x == 0 && y == 9) {
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
            }
            // bottom right
            if (x == 9 && y == 9) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; } }
            // left column
            if (x >= 1 && x <= 8 && y == 0) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y + 1]) == 1) { bombCount += 1; }
            }
            // top row
            if (x == 0 && y >= 1 && y <= 8) {
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y + 1]) == 1) { bombCount += 1; }
            }
            // bottom row
            if (x == 9 && y >= 1 && y <= 8) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
            }
            // right column
            if (x >= 1 && x <= 8 && y == 9) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
            }
            // all other squares
            if (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
                if (gui.allSquares.get(gui.mineArray[x - 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x - 1][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x][y + 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y - 1]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y]) == 1) { bombCount += 1; }
                if (gui.allSquares.get(gui.mineArray[x + 1][y + 1]) == 1) { bombCount += 1; }
            }
        }
        return bombCount;
    }

    public Color bombNumberColor(int number){
        if(number == 1){
            return new Color(0,0,220);
        }
        if(number == 2){
            return new Color(76, 127, 46);
        }
        if(number == 3){
            return Color.RED;
        }
        if(number == 4){
            return new Color(0,0,120);
        }
        if(number == 5){
            return new Color(110,0,0);
        }
        if(number == 6){
            return new Color(37, 117, 112);
        }
        if(number == 7){
            return Color.BLACK;
        }
        if(number == 8){
            return new Color(122, 122, 122);
        }
        else{
            return null;
        }
    }

    /**
     * Description - This method will compare a hashmap of all squares and a hashmap of the squares that have a flag
     * It merges the two into a hashmap and removes the keyset of the first hashmap.
     * It then uses a loop to check through the new hashmap and if the user placed all flags correctly, all keys should be 0
     * @param first a hashmap of the places the user has selected to place flags on and will be checked against the second parameter
     * @param second second a hashmap of all of the squares that are on screen
     * @return a boolean value to tell the program whether the user correctly placed the flags on screen
     */
    public boolean flagCheck(Map<JPanel, Integer> first, Map<JPanel, Integer> second) {
        int count = 0;
        Map<JPanel, Integer> unionMap = new HashMap<>();
        unionMap.putAll(first);
        unionMap.putAll(second);
        unionMap.keySet().removeAll(first.keySet()); // should give the difference between the two hashmaps
                                                     // the difference should have all 0's if the player won
        for(Map.Entry<JPanel, Integer> entry : unionMap.entrySet()){
            if(entry.getValue() == 0){
                count += 1;
            }
            if(count ==  unionMap.size()){
                return true;
            }

        }
        return false;
    }
}