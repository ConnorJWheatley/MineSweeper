import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {

    private GUI gui; // game passed through to allow for game manipulation
    private int x;
    private int y;

    public KeyBoardListener(GUI gui, int x, int y) {
        this.gui = gui;
        this.x = x;
        this.y = y;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
// will be used when the user wants to play with the keyboard