import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KartAnimationPanel extends JPanel implements ActionListener {
    private Image[] kartImages;  // Array to hold the images for the rotating kart
    private int currentImageIndex = 0;  // Index of the current image being displayed
    private Timer animationTimer;  // Timer for the animation
    private int rotationAngle = 0;  // Angle for rotating the kart

    public KartAnimationPanel() {
        // Load the sprite images (replace with your images)
        kartImages = new Image[16];  // Assuming 12 images for the full rotation (30 degrees per image)
        for (int i = 0; i < kartImages.length; i++) {
            kartImages[i] = new ImageIcon("goKart/resources/Kart 1/" + i + ".png").getImage();  // Adjust image path
        }

        // Set up the timer to fire every 100 milliseconds (adjust to change the spinning speed)
        animationTimer = new Timer(100, this);
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the current kart image based on the current rotation angle
        Image currentKartImage = kartImages[currentImageIndex];

        // Calculate the center of the panel to rotate the kart around
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Rotate the kart by applying an affine transformation
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Rotate around the center of the panel
        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.drawImage(currentKartImage, -currentKartImage.getWidth(null) / 2, -currentKartImage.getHeight(null) / 2, this);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the current image to simulate the spinning of the kart
        currentImageIndex = (currentImageIndex + 1) % kartImages.length;

        // Update the rotation angle for the next frame
        rotationAngle = (rotationAngle + 45/2) % 360;  // Rotate by 30 degrees per frame
        repaint();
    }
}
