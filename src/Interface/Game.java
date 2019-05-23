package Interface;

import Panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends JFrame {
    private static final int LARGEUR_JEUX = 1000;
    private static final int HAUTEUR_JEUX = Toolkit.getDefaultToolkit().getScreenSize().height - 100; //800

    private Container conteneur;
    private GamePanel gamePanel;

    public Game() {
        super("Tetris - By Kakumi");

        setBounds(0, 0, LARGEUR_JEUX, HAUTEUR_JEUX);
        setIconImage(Start.logo.getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        int level;
        do {
            String levelFormat = JOptionPane.showInputDialog(this, "Quel level souhaitez-vous ? (Entre 0 et 19)", "0");
            level = Integer.parseInt(levelFormat);
        } while (level < 0 || level > 19);
        gamePanel = new GamePanel(LARGEUR_JEUX, HAUTEUR_JEUX, this, level);
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        conteneur = this.getContentPane();
        conteneur.add(gamePanel);
        pack();
    }
}
