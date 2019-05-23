package Panel;

import Interface.Game;
import Interface.Start;
import Enum.InfoCase;
import Objets.Piece;
import Objets.ThreadDeplacement;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    private static final int EPAISSEUR = 10;
    private static final int EPAISSEUR_MULTIPLICATEUR = 31;
    private static final Image image = Toolkit.getDefaultToolkit().getImage(Start.class.getClassLoader().getResource("Images/GameBackground.png"));

    private InfoCase[][] jeux;
    private int largeur;
    private int hauteur;
    private int tailleForme;
    private Game main;
    private Piece[] pieces;
    private Piece pieceActuel;
    private int xPieceActuel;
    private int yPieceActuel;
    private Random random;
    private int ligne;
    private int colonne;
    private AudioInputStream playingMusic = null;
    private AudioInputStream gameOverMusic = null;
    private AudioInputStream tetrisFourLines = null;
    private Clip clipPlay = null;
    private Clip clipGameover = null;
    private Clip clipFourLines = null;
    private ThreadDeplacement deplacerPiece;
    private int level;
    private int rotationEffectuée;
    private int ligneCassée;
    private int score;
    private Piece pieceSuivante;
    private boolean gameOver;

    public GamePanel(int largeur, int hauteur, Game main, int level) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.main = main;
        this.level = level;
        rotationEffectuée = 0;
        ligneCassée = 0;
        score = 0;
        gameOver = false;

        random = new Random();
        tailleForme = (hauteur / 20) - (EPAISSEUR * 6 / 20);
        jeux = new InfoCase[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                jeux[i][j] = InfoCase.VIDE;
            }
        }
        pieces = new Piece[7];
        pieces[0] = (new Piece(Color.ORANGE, Piece.PIECE_L, this));
        pieces[1] = (new Piece(Color.BLUE, Piece.PIECE_J, this));
        pieces[2] = (new Piece(Color.YELLOW, Piece.PIECE_CARRER, this));
        pieces[3] = (new Piece(Color.CYAN, Piece.PIECE_DROITE, this));
        pieces[4] = (new Piece(Color.MAGENTA, Piece.PIECE_T, this));
        pieces[5] = (new Piece(Color.RED, Piece.PIECE_Z, this));
        pieces[6] = (new Piece(Color.GREEN, Piece.PIECE_S, this));
        pieceActuel = pieces[random.nextInt(pieces.length)];
        pieceSuivante = pieces[random.nextInt(pieces.length)];
        xPieceActuel = EPAISSEUR * EPAISSEUR_MULTIPLICATEUR + tailleForme * 3;
        yPieceActuel = EPAISSEUR * 5;
        ligne = 0;
        colonne = 3;
        try {
            playingMusic = AudioSystem.getAudioInputStream(Start.class.getClassLoader().getResource("Musique/tetris.wav"));
            gameOverMusic = AudioSystem.getAudioInputStream(Start.class.getClassLoader().getResource("Musique/gameover.wav"));
            tetrisFourLines = AudioSystem.getAudioInputStream(Start.class.getClassLoader().getResource("Musique/four_lines.wav"));
            clipPlay = AudioSystem.getClip();
            clipGameover = AudioSystem.getClip();
            clipFourLines = AudioSystem.getClip();
            clipPlay.open(playingMusic);
            clipGameover.open(gameOverMusic);
            clipFourLines.open(tetrisFourLines);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        deplacerPiece = new ThreadDeplacement(this);
        deplacerPiece.start();
        if (clipPlay != null) {
            clipPlay.start();
            clipPlay.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(image, 0, 0, largeur, hauteur, this);

        int x = EPAISSEUR * EPAISSEUR_MULTIPLICATEUR;
        int y = EPAISSEUR * 5;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(x, y, tailleForme, tailleForme);
                if (jeux[i][j] == InfoCase.VIDE) {
                    g.setColor(Color.BLACK);
                    //g.setColor(Color.DARK_GRAY);
                    g.fillRect(x, y, tailleForme, tailleForme);
                    //g.setColor(Color.WHITE);
                    //g.drawRect(x, y, tailleForme, tailleForme);
                } else {
                    switch (jeux[i][j]) {
                        case PLEINE_CARRER:
                            g.setColor(Color.YELLOW);
                            break;
                        case PLEINE_DROITE:
                            g.setColor(Color.CYAN);
                            break;
                        case PLEINE_L:
                            g.setColor(Color.ORANGE);
                            break;
                        case PLEINE_J:
                            g.setColor(Color.BLUE);
                            break;
                        case PLEINE_S:
                            g.setColor(Color.GREEN);
                            break;
                        case PLEINE_Z:
                            g.setColor(Color.RED);
                            break;
                        case PLEINE_T:
                            g.setColor(Color.MAGENTA);
                            break;
                    }
                    g.fillRect(x, y, tailleForme, tailleForme);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, tailleForme, tailleForme);
                }
                x += tailleForme;
            }
            y += tailleForme;
            x = EPAISSEUR * EPAISSEUR_MULTIPLICATEUR;
        }

        pieceActuel.dessine(g);

        g.setFont(Start.retroFontText);
        g.setColor(Color.WHITE);
        g.drawString("Score", 60, 100);
        g.drawString(Integer.toString(score), 60, 130);
        g.drawString("Ligne" + (ligneCassée > 1 ? "s" : ""), 60, 200);
        g.drawString(Integer.toString(ligneCassée), 60, 230);
        g.drawString("Rotation" + (rotationEffectuée > 1 ? "s" : ""), 60, 300);
        g.drawString(Integer.toString(rotationEffectuée), 60, 330);
        g.drawString("Level", (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (10 * tailleForme) + 30, 100);
        g.drawString(Integer.toString(level), (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (10 * tailleForme) + 30, 130);
        g.drawString("Suivant", (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (10 * tailleForme) + 30, 200);
        x = (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (10 * tailleForme) + 30;
        y = 210;
        int tailleFormeSuivante = 30;
        for (int iLigne = 0; iLigne < 4; iLigne++) {
            for (int iColonne = 0; iColonne < 4; iColonne++) {
                if (pieceSuivante.getPieces()[0][iLigne][iColonne] == InfoCase.VIDE) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, tailleFormeSuivante, tailleFormeSuivante);
                } else {
                    g.setColor(pieceSuivante.getCouleur());
                    g.fillRect(x, y, tailleFormeSuivante, tailleFormeSuivante);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, tailleFormeSuivante, tailleFormeSuivante);
                }
                x += tailleFormeSuivante;
            }
            y += tailleFormeSuivante;
            x = (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (10 * tailleForme) + 30;
        }

        if (gameOver) {
            g.setFont(Start.retroFontTitle);
            g.setColor(Color.WHITE);
            g.drawString("Game over", (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (2 * tailleForme), hauteur / 2); //largeur / 2 - 125
        } else {
            if (deplacerPiece.isPause()) {
                g.setFont(Start.retroFontTitle);
                g.setColor(Color.WHITE);
                g.drawString("Pause", (EPAISSEUR * EPAISSEUR_MULTIPLICATEUR) + (3 * tailleForme), hauteur / 2); //largeur / 2 - 80
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                if (deplacerPiece.isPause()) {
                    clipPlay.start();
                    deplacerPiece.setPause(false);
                } else {
                    clipPlay.stop();
                    deplacerPiece.setPause(true);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_R) nouvellePartie(level);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                boolean verif = false;
                while (!verif) {
                    verif = pieceActuel.bouge();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (!deplacerPiece.isPause()) {
                    if (colonne + pieceActuel.colonneGaucheContact(pieceActuel.getRotation() + 1) >= 0 && colonne + pieceActuel.colonneDroiteContact(pieceActuel.getRotation() + 1) <= 9) {
                        int rotationSuivante = pieceActuel.getRotation() + 1;
                        if (rotationSuivante == pieceActuel.getPieces().length) rotationSuivante = 0;
                        int colonneGauche = pieceActuel.colonneGaucheContact(rotationSuivante);
                        int colonneDroite = pieceActuel.colonneDroiteContact(rotationSuivante);
                        boolean colisionDetectee = false;

                        for (int iLigne = 0; iLigne < 4; iLigne++) {
                            if (!colisionDetectee) {
                                if (pieceActuel.getPieces()[rotationSuivante][iLigne][colonneGauche] != InfoCase.VIDE) {
                                    colisionDetectee = jeux[ligne + iLigne][colonne + colonneGauche] != InfoCase.VIDE;
                                }
                                if (!colisionDetectee && pieceActuel.getPieces()[rotationSuivante][iLigne][colonneDroite] != InfoCase.VIDE) {
                                    colisionDetectee = jeux[ligne + iLigne][colonne + colonneDroite] != InfoCase.VIDE;
                                }
                            }
                        }

                        if (!colisionDetectee) {
                            pieceActuel.incrémenteRotation();
                            rotationEffectuée++;
                        }
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (!deplacerPiece.isPause()) {
                    score++;
                    pieceActuel.bouge();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!deplacerPiece.isPause()) {
                    if (colonne + pieceActuel.colonneGaucheContact(pieceActuel.getRotation()) != 0) {

                        boolean colisionDetectee = false;
                        for (int iLigne = 0; iLigne < 4; iLigne++) {
                            for (int iColonne = 0; iColonne < 4; iColonne++) {
                                if (!colisionDetectee) {
                                    if (pieceActuel.getPieces()[pieceActuel.getRotation()][iLigne][iColonne] != InfoCase.VIDE) {
                                        colisionDetectee = jeux[ligne + iLigne][colonne + iColonne - 1] != InfoCase.VIDE;
                                    }
                                }
                            }
                        }
                        if (!colisionDetectee) {
                            xPieceActuel -= tailleForme;
                            colonne--;
                        }
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!deplacerPiece.isPause()) {
                    if (colonne + pieceActuel.colonneDroiteContact(pieceActuel.getRotation()) != 9) {

                        boolean colisionDetectee = false;
                        for (int iLigne = 0; iLigne < 4; iLigne++) {
                            for (int iColonne = 3; iColonne >= 0; iColonne--) {
                                if (!colisionDetectee) {
                                    if (pieceActuel.getPieces()[pieceActuel.getRotation()][iLigne][iColonne] != InfoCase.VIDE) {
                                        colisionDetectee = jeux[ligne + iLigne][colonne + iColonne + 1] != InfoCase.VIDE;
                                    }
                                }
                            }
                        }
                        if (!colisionDetectee) {
                            xPieceActuel += tailleForme;
                            colonne++;
                        }
                    }
                }
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int levelDemander;
                do {
                    String levelFormat = JOptionPane.showInputDialog(this, "Quel level souhaitez-vous ? (Entre 0 et 19)", "0");
                    levelDemander = Integer.parseInt(levelFormat);
                } while (levelDemander < 0 || levelDemander > 19);
                nouvellePartie(levelDemander);
            }
        }
        repaint();
    }

    private void nouvellePartie(int levelDemander) {
        jeux = new InfoCase[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                jeux[i][j] = InfoCase.VIDE;
            }
        }
        clipGameover.stop();
        level = levelDemander;
        rotationEffectuée = 0;
        ligneCassée = 0;
        score = 0;
        gameOver = false;
        pieceActuel = pieces[random.nextInt(pieces.length)];
        pieceSuivante = pieces[random.nextInt(pieces.length)];
        xPieceActuel = EPAISSEUR * EPAISSEUR_MULTIPLICATEUR + tailleForme * 3;
        yPieceActuel = EPAISSEUR * 5;
        ligne = 0;
        colonne = 3;
        deplacerPiece.setPause(false);
        clipPlay.setFramePosition(0);
        clipPlay.start();
        clipPlay.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(largeur, hauteur); }

    public Piece getPieceActuel() {
        return pieceActuel;
    }

    public void setyPieceActuel(int yPieceActuel) {
        this.yPieceActuel = yPieceActuel;
    }


    public int getTailleForme() {
        return tailleForme;
    }

    public int getxPieceActuel() {
        return xPieceActuel;
    }

    public int getyPieceActuel() {
        return yPieceActuel;
    }

    public void incrementeLigne() {
        ligne++;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void nouvellePiece() {
        verifLigneComplete();
        pieceActuel = pieceSuivante;
        pieceSuivante = pieces[random.nextInt(pieces.length)];
        pieceActuel.resetRotation();
        xPieceActuel = EPAISSEUR * EPAISSEUR_MULTIPLICATEUR + tailleForme * 3;
        yPieceActuel = EPAISSEUR * 5;
        ligne = 0;
        colonne = 3;
        repaint();
    }

    public void setJeux(int ligne, int cologne, InfoCase type) {
        jeux[ligne][cologne] = type;
    }

    public InfoCase[][] getJeux() {
        return jeux;
    }

    public void verifLigneComplete() {
        int ligneDétruite = 0;
        for (int iLigne = 0; iLigne < 20; iLigne++) {
            int compteurLigne = 0;
            for (int iColonne = 0; iColonne < 10; iColonne++) {
                if (jeux[iLigne][iColonne] != InfoCase.VIDE) {
                    compteurLigne++;
                }
            }
            if (compteurLigne == 10) {
                resetLigne(iLigne);
                ligneDétruite++;
            }
        }
        if (ligneDétruite == 1) score += 40 * (level + 1);
        if (ligneDétruite == 2) score += 100 * (level + 1);
        if (ligneDétruite == 3) score += 300 * (level + 1);
        if (ligneDétruite == 4) {
            score += 1200 * (level + 1);
            clipFourLines.setFramePosition(0);
            clipFourLines.start();
        }
        ligneCassée += ligneDétruite;
        if (ligneCassée >= (level + 1) * 10) {
            level++;
        }
    }

    private void resetLigne(int ligne) {
        for (int iLigne = ligne; iLigne > 0; iLigne--) {
            for (int iColonne = 0; iColonne < 10; iColonne++) {
                jeux[iLigne][iColonne] = jeux[iLigne - 1][iColonne];
            }
        }
        for (int iColonne = 0; iColonne < 10; iColonne++) {
            jeux[0][iColonne] = InfoCase.VIDE;
        }
    }

    public void setGameOver() {
        gameOver = true;
        deplacerPiece.setPause(true);
        clipPlay.stop();
        clipGameover.setFramePosition(0);
        clipGameover.start();
        repaint();
    }

    public int defilementParLevel() {
        switch (level) {
            case 0: return 800;
            case 1: return 710;
            case 2: return 630;
            case 3: return 550;
            case 4: return 460;
            case 5: return 380;
            case 6: return 300;
            case 7: return 200;
            case 8: return 130;
            case 9: return 100;
            case 10:
            case 11:
            case 12: return 83;
            case 13:
            case 14:
            case 15: return 66;
            case 16:
            case 17:
            case 18: return 50;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28: return 34;
            default: return 17;
        }
    }
}
