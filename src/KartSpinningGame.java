import javax.swing.*;

public class KartSpinningGame extends JFrame {

    public KartSpinningGame() {
        // Set up the main frame
        setTitle("Kart Spinning Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create an instance of the KartAnimationPanel and add it to the frame
        KartAnimationPanel animationPanel = new KartAnimationPanel();
        add(animationPanel);

        // Center the window on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Start the kart spinning game
        SwingUtilities.invokeLater(KartSpinningGame::new);
    }
}
