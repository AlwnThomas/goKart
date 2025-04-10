import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KartAnimationPanel extends JPanel implements ActionListener {

    //pre-defining images, animations etc.
    private Image[] kartImages;         //The images for the rotating kart
    private int currentImageIndex = 0;  //The current image being displayed
    private Timer animationTimer;       //Timer for rotation animation
    private int rotationAngle = 0;      //Kart roation angle

    public KartAnimationPanel() {
        //Loading in the kart images
        kartImages = new Image[16];  //Array of 16 kart images going 180 deg rotation
        for (int i = 0; i < kartImages.length; i++) {
            kartImages[i] = new ImageIcon("goKart/resources/Kart 1/" + i + ".png").getImage();
        }

        animationTimer = new Timer(100, this); //Timer to spin kart 22.5 deg every 100 ms
        animationTimer.start();                      //Animation start
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //The current kart image based on how much its rotated
        Image currentKartImage = kartImages[currentImageIndex];

        //Centering the kart
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        //Applying transformation to rotate the kart
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        //Rotating with a center fixed
        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.drawImage(currentKartImage, -currentKartImage.getWidth(null) / 2, -currentKartImage.getHeight(null) / 2, this);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Updating and repainting the image
        currentImageIndex = (currentImageIndex + 1) % kartImages.length;

        //Updating angle for next rotation uodate, rotating 22.5 deg per frame
        rotationAngle = (rotationAngle + 45/2) % 360;
        repaint();
    }
}
