package Objets;

import java.awt.*;
import Enum.InfoCase;
import Panel.GamePanel;

public class Piece {
    public static int nbEcriture = 0;
    public static final InfoCase[][][] PIECE_J = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.PLEINE_J, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_J, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_L = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.VIDE},
                    {InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.VIDE},
                    {InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_L, InfoCase.PLEINE_L, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_DROITE = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_DROITE, InfoCase.PLEINE_DROITE, InfoCase.PLEINE_DROITE, InfoCase.PLEINE_DROITE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_DROITE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_DROITE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_DROITE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_DROITE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_CARRER = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_CARRER, InfoCase.PLEINE_CARRER, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_CARRER, InfoCase.PLEINE_CARRER, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_S = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_S, InfoCase.PLEINE_S, InfoCase.VIDE},
                    {InfoCase.PLEINE_S, InfoCase.PLEINE_S, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_S, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_S, InfoCase.PLEINE_S, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_S, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_Z = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_Z, InfoCase.PLEINE_Z, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_Z, InfoCase.PLEINE_Z, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.PLEINE_Z, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_Z, InfoCase.PLEINE_Z, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_Z, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };
    public static final InfoCase[][][] PIECE_T = {
            {
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            },
            {
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.PLEINE_T, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.PLEINE_T, InfoCase.VIDE, InfoCase.VIDE},
                    {InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE, InfoCase.VIDE}
            }
    };

    private final GamePanel gamePanel;
    private Color couleur;
    private InfoCase[][][] pieces;
    private int rotation;

    public Piece(Color couleur, InfoCase[][][] pieces, GamePanel gamePanel) {
        this.couleur = couleur;
        this.pieces = pieces;
        this.gamePanel = gamePanel;
    }

    public Color getCouleur() {
        return couleur;
    }

    public InfoCase[][][] getPieces() {
        return pieces;
    }

    public InfoCase getType(int rotation, int ligne, int celulle) {
        return pieces[rotation][ligne][celulle];
    }

    public int getRotation() {
        return rotation;
    }

    public void incrémenteRotation() {
        rotation++;
        if (rotation == pieces.length) rotation = 0;
    }

    public int ligneBasContact(int rotation) {
        if (rotation >= pieces.length) rotation = 0;
        for (int iLigne = 3; iLigne >= 0; iLigne--) {
            for (int iColonne = 0; iColonne < 4; iColonne++) {
                if (pieces[rotation][iLigne][iColonne] != InfoCase.VIDE) {
                    return iLigne;
                }
            }
        }
        return 0;
    }

    public int colonneDroiteContact(int rotation) {
        if (rotation >= pieces.length) rotation = 0;
        for (int iColonne = 3; iColonne >= 0; iColonne--) {
            for (int iLigne = 0; iLigne < 4; iLigne++) {
                if (pieces[rotation][iLigne][iColonne] != InfoCase.VIDE) {
                    return iColonne;
                }
            }
        }
        return 0;
    }

    public int colonneGaucheContact(int rotation) {
        if (rotation >= pieces.length) rotation = 0;
        for (int iColonne = 0; iColonne < 4; iColonne++) {
            for (int iLigne = 0; iLigne < 4; iLigne++) {
                if (pieces[rotation][iLigne][iColonne] != InfoCase.VIDE) {
                    return iColonne;
                }
            }
        }
        return 0;
    }

    public void dessine(Graphics g) {
        int x = gamePanel.getxPieceActuel();
        int y = gamePanel.getyPieceActuel();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (pieces[rotation][i][j] != InfoCase.VIDE) {
                    g.setColor(couleur);
                    g.fillRect(x, y, gamePanel.getTailleForme(), gamePanel.getTailleForme());
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, gamePanel.getTailleForme(), gamePanel.getTailleForme());
                }
                x += gamePanel.getTailleForme();
            }
            y += gamePanel.getTailleForme();
            x = gamePanel.getxPieceActuel();
            //x = gamePanel.getEPAISSEUR() * gamePanel.getEpaisseurMultiplicateur() + gamePanel.getTailleForme() * 3;
        }
    }

    public void resetRotation() {
        rotation = 0;
    }

    public boolean bouge() {
        int x = gamePanel.getColonne();
        int y = gamePanel.getLigne();

        for (int iLigne = 0; iLigne < 4; iLigne++) {
            for (int iColonne = 0; iColonne < 4; iColonne++) {
                if (pieces[rotation][iLigne][iColonne] != InfoCase.VIDE) {
                    if (gamePanel.getJeux()[y + iLigne][x + iColonne] != InfoCase.VIDE) {
                        gamePanel.setGameOver();
                        return true;
                    }
                }
            }
        }
        if (gamePanel.getLigne() + ligneBasContact(rotation) == 19) {
            ecrireForme();
            gamePanel.nouvellePiece();
            return true;
        }

        x = gamePanel.getColonne();
        y = gamePanel.getLigne();

        for (int iLigne = 3; iLigne >= 0; iLigne--) {
            for (int iColonne = 0; iColonne < 4; iColonne++) {
                if (pieces[rotation][iLigne][iColonne] != InfoCase.VIDE) {
                    if (y + iLigne + 1 < 20 && x + iColonne < 10 && gamePanel.getJeux()[y + iLigne + 1][x + iColonne] != InfoCase.VIDE) {
                        ecrireForme();
                        gamePanel.nouvellePiece();
                        return true;
                    }
                }
            }
        }

        gamePanel.setyPieceActuel(gamePanel.getyPieceActuel() + gamePanel.getTailleForme());
        gamePanel.incrementeLigne();
        gamePanel.repaint();
        return false;
    }

    public void ecrireForme() {
        int x = gamePanel.getColonne();
        int y = gamePanel.getLigne();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (pieces[rotation][i][j] != InfoCase.VIDE) {
                    gamePanel.setJeux(y, x, pieces[rotation][i][j]);
                }
                x++;
            }
            y++;
            x = gamePanel.getColonne();
        }
    }
}
