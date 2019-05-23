package Utilitaire;

import java.awt.*;
import java.util.Random;

public class RandomColor {
    private static final Color[] couleurs = {
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.GREEN,
            Color.LIGHT_GRAY,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED,
            Color.WHITE,
            Color.YELLOW
    };

    public static Color getRandomColor() {
        Random couleurAléatoire = new Random();
        return couleurs[couleurAléatoire.nextInt(couleurs.length)];
    }
}
