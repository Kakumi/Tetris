package Interface;

import Panel.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class Start extends JFrame {
    private static final int LARGEUR_FENETRE = 800;
    private static final int HAUTEUR_FENETRE = 400;

    public static Font retroFontTitle;
    public static Font retroFontText;
    public static ImageIcon logo;
    private StartPanel startPanel;
    private Container conteneur;

    public Start() {
        super("Tetris - By Kakumi");
        setBounds(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        logo = new ImageIcon(Objects.requireNonNull(Start.class.getClassLoader().getResource("Images/logo.png")));
        setIconImage(logo.getImage());

        try {
            Font retroFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Start.class.getClassLoader().getResourceAsStream("Font/tetris.ttf")));
            retroFontTitle = retroFont.deriveFont(40f);
            retroFontText = retroFont.deriveFont(30f);
        } catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }


        startPanel = new StartPanel(LARGEUR_FENETRE, HAUTEUR_FENETRE, this);
        startPanel.setBordure();
        startPanel.addKeyListener(startPanel);
        startPanel.setFocusable(true);

        conteneur = this.getContentPane();
        conteneur.add(startPanel);
        pack();

        setVisible(true);
    }

    public Font getRetroFontTitle() {
        return retroFontTitle;
    }

    public Font getRetroFontText() {
        return retroFontText;
    }
}
