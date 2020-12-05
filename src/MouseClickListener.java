import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {

    private GUI gui; // game passed through to allow for game manipulation
    private int x;
    private int y;

    public MouseClickListener(GUI gui, int x, int y) {
        this.gui = gui;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Operations operations = new Operations(gui,x,y);
        ImageIcon flag = new ImageIcon(new ImageIcon("M:\\CE203_Assignment2\\ce203_assignment2\\src\\Minesweeper\\flag.png").getImage().getScaledInstance(60, 65, Image.SCALE_FAST));
        // if there is a flag already placed, right clicking again will remove the flag and update the amount of flags placed
        if(SwingUtilities.isRightMouseButton(e) && gui.flagsPlaced.containsKey(gui.mineArray[x][y])){
            gui.mineArray[x][y].removeAll();
            gui.mineArray[x][y].updateUI();
            gui.numberOfFlags += 1;
            gui.updateFlagCount();
            gui.flagsPlaced.remove(gui.mineArray[x][y]);
            return;
        }
        // if no flag has been placed down yet, and it can only be placed on unclicked squares
        /**
         * flag counter does not go to 0, needs fixing
         */
        if(SwingUtilities.isRightMouseButton(e) && !gui.flagsPlaced.containsKey(gui.mineArray[x][y]) && !gui.leftClickSquares.containsKey(gui.mineArray[x][y])){
            gui.mineArray[x][y].removeAll();
            gui.mineArray[x][y].updateUI(); // this line and the line above will clear the JPanel that is clicked so the image does not duplicate on screen
            gui.numberOfFlags -= 1;
            gui.updateFlagCount();
            // stops someone from putting down flags after they have reached 0
            if(gui.numberOfFlags == 0){
                gui.flagsPlaced.put(gui.mineArray[x][y], 1);
                gui.mineArray[x][y].add(new Flag(gui.mineArray[x][y]), BorderLayout.LINE_START);
                gui.mineArray[x][y].revalidate();
                return;
            }

            if(gui.numberOfFlags < 0){
                gui.numberOfFlags = 0; // sets numberOfFlags back to 0 so that when a random square is clicked, the variable is not affected
                return;
            }
            else{
                gui.flagsPlaced.put(gui.mineArray[x][y], 1);
                gui.mineArray[x][y].add(new Flag(gui.mineArray[x][y]), BorderLayout.LINE_START);
                gui.mineArray[x][y].revalidate();
            }
        }

        if(SwingUtilities.isLeftMouseButton(e)){
            gui.mineArray[x][y].setBorder(BorderFactory.createLineBorder(new Color(180,180,180))); // removes the bevel to set the square to be flat
            e.getComponent().setBackground(new Color(230,230,230));
            // could possibly put this into Operations
            if(gui.allSquares.get(gui.mineArray[x][y]) == 1){
                gui.mineArray[x][y].removeAll();
                gui.mineArray[x][y].updateUI();
                gui.mineArray[x][y].add(new Bomb(gui.mineArray[x][y]), BorderLayout.LINE_START);
                e.getComponent().setBackground(new Color(200,0,0));
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - gui.startTime;
                double seconds = elapsedTime / 1000.0;
                WinCondition wc = new WinCondition(gui);
                wc.hitBomb(seconds);
            }
            if(gui.allSquares.get(gui.mineArray[x][y]) == 0){
                int spaceFromBomb = operations.checkForBombs();
                if(!gui.leftClickSquares.containsKey(gui.mineArray[x][y])){
                    gui.leftClickSquares.put(gui.mineArray[x][y], 0);
                }
                else{
                    return;
                }

                if(spaceFromBomb == 0){
                    //operations.checkForBombs();
                }
            }
            int spaceFromBomb = operations.checkForBombs();
            Color numberColour = operations.bombNumberColor(spaceFromBomb);
            JLabel number = new JLabel("" + spaceFromBomb);
            number.setHorizontalAlignment(JLabel.CENTER);
            number.setVerticalAlignment(JLabel.CENTER);
            number.setForeground(numberColour);
            number.setFont(number.getFont().deriveFont(30f));
            if(spaceFromBomb > 0){
                gui.mineArray[x][y].add(number);
                gui.mineArray[x][y].revalidate();
            }
        }
    }

    // this method is used to keep checking whether the win condition for the game has been met when the player clicks something on the screen
    @Override
    public void mouseReleased(MouseEvent e) {
        Operations operations = new Operations(gui,x,y);
        if(100 - gui.leftClickSquares.size() == gui.numberOfBombs && operations.flagCheck(gui.flagsPlaced, gui.allSquares)){
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - gui.startTime;
            double seconds = elapsedTime / 1000.0;
            WinCondition wc = new WinCondition(gui);
            wc.playerHasWon(seconds);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
/*
    Use the left+right click. If a square has the correct number of mines marked around it and you left and right click on it at the same time, all non marked sqares around it will open.
 */