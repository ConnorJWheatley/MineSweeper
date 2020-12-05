import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public abstract class DrawShape extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setSize(75,67);
    }
}

class Bomb extends DrawShape{

    private JPanel panel;
    Bomb(JPanel panel){
        this.panel = panel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        setOpaque(false);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(18, 13, 40, 40);
        g2d.fillRect(35, 8, 7, 51);
        g2d.fillRect(13, 30, 51, 7);
        g2d.setStroke(new BasicStroke(7));
        g2d.draw(new Line2D.Float(23, 18, 53, 48));
        g2d.draw(new Line2D.Float(54, 18, 24, 49));
        g2d.setColor(Color.GRAY);
        g2d.fillOval(19, 14, 32, 32);
        g2d.setColor(new Color(140,140,140));
        g2d.fillOval(20,15,24,24);
        g2d.setColor(new Color(220,220,220));
        g2d.fillOval(35, 32, 5, 5);
    }
}

class Flag extends DrawShape{

    private JPanel panel;
    Flag(JPanel panel){
        this.panel = panel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        setOpaque(false);
        // 75 height, 67 width
        g2d.fillRect(15, 50, 45,10);
        g2d.fillRect(25, 43, 25, 7);
        g2d.fillRect(35, 30, 5, 13);
        g2d.setColor(Color.red);
        //g2d.fillRect(30, 6, 10, 24);

        // bottom left corner, top corner, right corner
        int[] xValues = {40, 15, 40};
        int[] yValues = {32, 18, 4};
        g2d.fillPolygon(xValues, yValues, 3);
    }
}
/**
 * https://www.guru99.com/java-abstract-class-method.html
 * useful link
 */
