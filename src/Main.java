import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    private int kart1X = 350, kart1Y = 545;
    private int kart2X = 350, kart2Y = 580;
    private int speed = 5;
    private Image trackImage, kart1, kart2;

    public Main() {
        // Load the images
        trackImage = new ImageIcon("goKart/resources/desertTrack.png").getImage();
        kart1 = new ImageIcon("goKart/resources/Kart 1/4.png").getImage();
        kart2 = new ImageIcon("goKart/resources/Kart 2/4.png").getImage();

        // Set preferred size before adding to the frame
        this.setPreferredSize(new Dimension(850, 650));

        // Create the window
        JFrame frame = new JFrame("Kart Racing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.pack(); // Automatically set size based on panel's preferred size
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the track background
        g.drawImage(trackImage, 0, 0, getWidth(), getHeight(), this);

        drawKarts(g);
    }


    private void drawKarts(Graphics g) {
        g.drawImage(kart1, kart1X, kart1Y, this); // Kart 1
        g.drawImage(kart2, kart2X, kart2Y, this); // Kart 1
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W -> kart1Y -= speed; // Kart 1 Up
            case KeyEvent.VK_S -> kart1Y += speed; // Kart 1 Down
            case KeyEvent.VK_A -> kart1X -= speed; // Kart 1 Left
            case KeyEvent.VK_D -> kart1X += speed; // Kart 1 Right

            case KeyEvent.VK_UP -> kart2Y -= speed; // Kart 2 Up
            case KeyEvent.VK_DOWN -> kart2Y += speed; // Kart 2 Down
            case KeyEvent.VK_LEFT -> kart2X -= speed; // Kart 2 Left
            case KeyEvent.VK_RIGHT -> kart2X += speed; // Kart 2 Right
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new Main();
    }
}