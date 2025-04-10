import javax.swing.*;

public class KartSpinning extends JFrame {

    public KartSpinning() {
        //Setting Up the main JFrame
        setTitle("Kart Spinning Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        //Creating an instance of KartAnimation
        KartAnimationPanel animationPanel = new KartAnimationPanel();
        //Adding it to JFrame
        add(animationPanel);

        //Centering window on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Start the kart spinning game
        SwingUtilities.invokeLater(KartSpinning::new);
    }
}