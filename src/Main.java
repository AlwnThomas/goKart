import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    private final int WIDTH = 850, HEIGHT = 650;

    private Image trackImage;
    private Image[] kart1Images = new Image[16];
    private Image[] kart2Images = new Image[16];

    private int kart1X = 350, kart1Y = 545, kart1Direction = 0, kart1Speed = 0;
    private int kart2X = 350, kart2Y = 580, kart2Direction = 0, kart2Speed = 0;

    public Main() {
        // Load the images
        trackImage = new ImageIcon("goKart/resources/desertTrack.png").getImage();
        
        for (int i = 0; i < 16; i++) {
            kart1Images[i] = new ImageIcon("goKart/resources/Kart 1/" + i + ".png").getImage();
            kart2Images[i] = new ImageIcon("goKart/resources/Kart 2/" + i + ".png").getImage();
        }

        // Set preferred size before adding to the frame
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Create the window
        JFrame frame = new JFrame("Kart Racing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.pack(); // Automatically set size based on panel's preferred size
        frame.setResizable(false);
        frame.setVisible(true);

        // Start game loop
        Timer timer = new Timer(50, e -> moveKarts());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the track background
        g.drawImage(trackImage, 0, 0, getWidth(), getHeight(), this);

        drawKarts(g);
    }

    private void drawKarts(Graphics g) {
        g.drawImage(kart1Images[kart1Direction], kart1X, kart1Y, this);
        g.drawImage(kart2Images[kart2Direction], kart2X, kart2Y, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            // Kart 1 controls
            case KeyEvent.VK_A -> kart1Direction = (kart1Direction + 15) % 16; // Rotate Left (22.5°)
            case KeyEvent.VK_D -> kart1Direction = (kart1Direction + 1) % 16;  // Rotate Right (22.5°)
            case KeyEvent.VK_W -> kart1Speed = Math.min(kart1Speed + 10, 100); // Accelerate
            case KeyEvent.VK_S -> kart1Speed = Math.max(kart1Speed - 10, 0);   // Brake

            // Kart 2 controls
            case KeyEvent.VK_LEFT -> kart2Direction = (kart2Direction + 15) % 16; // Rotate Left (22.5°)
            case KeyEvent.VK_RIGHT -> kart2Direction = (kart2Direction + 1) % 16;  // Rotate Right (22.5°)
            case KeyEvent.VK_UP -> kart2Speed = Math.min(kart2Speed + 10, 100); // Accelerate
            case KeyEvent.VK_DOWN -> kart2Speed = Math.max(kart2Speed - 10, 0);  // Brake
        }

        repaint();
    }

    public void moveKarts() {
        // Convert 16-direction index to angle in degrees
        double angle1 = Math.toRadians(kart1Direction * 22.5);
        double angle2 = Math.toRadians(kart2Direction * 22.5);
    
        // Move Kart 1
        kart1X += Math.cos(angle1) * kart1Speed / 10;  // Adjust X based on cos
        kart1Y += Math.sin(angle1) * kart1Speed / 10;  // Adjust Y based on sin (invert Y-axis)
    
        // Move Kart 2
        kart2X += Math.cos(angle2) * kart2Speed / 10;  // Adjust X based on cos
        kart2Y += Math.sin(angle2) * kart2Speed / 10;  // Adjust Y based on sin (invert Y-axis)
    
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