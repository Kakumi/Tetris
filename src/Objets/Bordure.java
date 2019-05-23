package Objets;

import java.awt.*;

public class Bordure {
    private Rectangle rectangle;
    private Color couleur;

    public Bordure(int x, int y, int largeur, int hauteur, Color couleur) {
        this.rectangle = new Rectangle(x, y, largeur, hauteur);
        this.couleur = couleur;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void dessine(Graphics g) {
        g.setColor(couleur);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
