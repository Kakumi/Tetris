package Objets;

import Panel.GamePanel;

public class ThreadDeplacement extends Thread {
    private GamePanel gamePanel;
    private boolean pause;

    public ThreadDeplacement(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        pause = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(gamePanel.defilementParLevel());
                if (!pause) {
                    gamePanel.getPieceActuel().bouge();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPause(boolean bool) {
        pause = bool;
    }

    public boolean isPause() {
        return pause;
    }
}
