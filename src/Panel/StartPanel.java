package Panel;

import Interface.Game;
import Interface.Start;
import Objets.Bordure;
import Utilitaire.RandomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartPanel extends JPanel implements KeyListener {
    private static final int EPAISSEUR = 10;
    private static final Image image = Toolkit.getDefaultToolkit().getImage(Start.class.getClassLoader().getResource("Images/MainBackground.jpg"));

    private int largeur;
    private int hauteur;
    private Start main;
    List<Bordure> bordures = Collections.synchronizedList(new ArrayList<>());

    public StartPanel(int largeur, int hauteur, Start main) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.main = main;
    }

    public void setBordure() {
        Color couleur = RandomColor.getRandomColor();
        bordures.add(new Bordure(0, 0, largeur, EPAISSEUR, couleur));
        bordures.add(new Bordure(0, hauteur - EPAISSEUR, largeur, EPAISSEUR, couleur));
        bordures.add(new Bordure(0, 0, EPAISSEUR, hauteur, couleur));
        bordures.add(new Bordure(largeur - EPAISSEUR, 0, EPAISSEUR, hauteur, couleur));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Bordure bordure : bordures) {
            bordure.dessine(g);
        }

        g.setFont(main.getRetroFontTitle());
        g.setColor(Color.WHITE);
        g.drawString("APPUYER SUR ENTRER", largeur / 7, hauteur / 2);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Game newGame = new Game();
            main.setVisible(false);
            newGame.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(largeur, hauteur); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, largeur - EPAISSEUR, hauteur - EPAISSEUR, this);
    }
}
