import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*; // need to optimise
import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.Random;

class GUI extends JFrame {

    // add a button to press to start the time, this will give time for someone to click another button to view the top 5 scores
    private JPanel minePanel; // panel used to display grid panels
    private JPanel infoPanel; // used to hold the panels that hold the time and the amount of flags the player has
    private JLabel flagCount;
    Timer timer;
    JLabel time;
    long startTime;
    private int second = 0;
    int numberOfBombs; // this value is not changed
    int numberOfFlags; // this value will change when a player places or removes a flag
    JPanel[][] mineArray; // array used to store panels in grid
    Map<JPanel, Integer> allSquares = new HashMap<>();
    Map<JPanel, Integer> flagsPlaced = new HashMap<>();
    Map<JPanel, Integer> leftClickSquares = new HashMap<>();

    /**
     * Calls methods to initialise all JPanels and to schedule a timer to call the timeDisplay method which updates the time
     */
    public GUI() {
        startTime = System.currentTimeMillis();
        initialiseGrid();
        flagDisplay();
        timeDisplay();
        timer = new Timer();
        timer.schedule(new Time(second, this), 0, 1000);
        setTitle("1803270 - Registration Number");
        setResizable(false);
        setSize(new Dimension(800, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method is what generates the grid for the game, along with producing bombs and giving each JPanel a mouseListener to respond to the users clicks
     */
    private void initialiseGrid() {
        // if grid already drawn (reinitialise rather than initialise) remove grid from frame and delete it
        if(minePanel != null) {
            this.remove(minePanel);
            minePanel = null;
        }
        infoPanel = new JPanel();
        infoPanel.setBackground(Color.darkGray);
        add(infoPanel, BorderLayout.NORTH);
        minePanel = new JPanel(new GridLayout(10, 10));
        mineArray = new JPanel[10][10];
        // for loop to create grid
        for(int x = 0; x < mineArray.length; x++) {
            for(int y = 0; y < mineArray[x].length; y++) {
                mineArray[x][y] = new JPanel(new BorderLayout()); // allows elements to be centered in the JPanel easily
                Random rand = new Random();
                int randInt = rand.nextInt(100);
                if(numberOfBombs == 10){ // sets a max limit of 10 bombs
                    allSquares.put(mineArray[x][y], 0);
                }
                //If a bomb is generated, the value of that square is set to 1 in the HashMap to show this square contains a bomb, otherwise set to 0
                if(randInt > 95) {
                    allSquares.put(mineArray[x][y], 1);
                    numberOfFlags++;
                    numberOfBombs++;
                }
                else {
                    allSquares.put(mineArray[x][y], 0);
                }
                // sets the colours of the JPanels
                if((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
                    mineArray[x][y].setBackground(new Color(200,200,200));
                    mineArray[x][y].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                } else {
                    mineArray[x][y].setBackground(new Color(180,180,180));
                    mineArray[x][y].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
                mineArray[x][y].addMouseListener(new MouseClickListener(this, x, y));
                minePanel.add(mineArray[x][y]);
            }
        }
        // if the whole board has been generated but 10 bombs were not made
        if(numberOfBombs < 10){
            bombCheck();
        }
        this.add(minePanel);
    }

    /**
     * This method is to ensure that 10 bombs are always generated for the board
     */
    private void bombCheck(){
        while(numberOfBombs < 10){
            for (Map.Entry<JPanel, Integer> entry : allSquares.entrySet()) {
                if(numberOfBombs == 10){
                    return;
                }
                Random rand = new Random();
                int randInt = rand.nextInt(100);
                if(entry.getValue() == 1){ // if the square already has a bomb
                    continue;
                }
                if(entry.getValue() == 0){ // if the square currently has no bomb
                    if(randInt > 95) {
                        allSquares.replace(entry.getKey(), 1);
                        numberOfFlags++;
                        numberOfBombs++;
                    }
                }
            }
        }
    }

    /**
     * This method creates a JPanel at the the top of the window that displays to the user how many flags they have left
     */
    private void flagDisplay(){
        JPanel scorePanel = new JPanel(new BorderLayout());
        flagCount = new JLabel("Number of Flags: " + numberOfFlags);
        Font f = new Font("Monospaced",Font.BOLD,25);
        flagCount.setFont(f);
        flagCount.setForeground(Color.red);
        flagCount.setOpaque(true);
        flagCount.setBackground(Color.black);
        Border bombDisplayBorder = new LineBorder(new Color(120,0,0), 5, true);
        flagCount.setBorder(bombDisplayBorder);
        scorePanel.add(flagCount, BorderLayout.LINE_START);
        infoPanel.add(scorePanel, BorderLayout.LINE_START);
    }

    /**
     * This method is used to update the timer that sits next to the flag counter to tell the user how long are taking to complete the game
     */
    private void timeDisplay(){
        JPanel timePanel = new JPanel(new BorderLayout());
        Font f = new Font("Monospaced",Font.BOLD,25);
        time = new JLabel("" + second);
        time.setFont(f);
        time.setForeground(Color.red);
        time.setOpaque(true);
        time.setBackground(Color.black);
        Border bombDisplayBorder = new LineBorder(new Color(120,0,0), 5, true);
        time.setBorder(bombDisplayBorder);
        timePanel.add(time, BorderLayout.LINE_START);
        infoPanel.add(timePanel, BorderLayout.LINE_START);
    }

    // the methods below could possibly go into a separate class
    void updateFlagCount(){
        // this is so that once the counter reaches zero, it cannot go below zero
        if(numberOfFlags > 0){
            flagCount.setText("Number of Flags: " + numberOfFlags);
        }
    }
}