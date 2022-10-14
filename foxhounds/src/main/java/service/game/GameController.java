package service.game;

import model.GameState;
import service.util.Map;

public class GameController {

    private GameState gameState;
    private Map maputil;
    private GameStepPerformer gameStepPerformer;

    public GameController(GameState gameState, Map maputil, GameStepPerformer gameStepPerformer) {
        this.gameState = gameState;
        this.maputil = maputil;
        this.gameStepPerformer = gameStepPerformer;
    }

    public void gameLoop() {
        while (istheGameinProgress()) {
            gameStepPerformer.performgamestep();
        }
    }

    private boolean istheGameinProgress() {
        return !maputil.isMapCompleted(gameState.getMapVO()) && !gameState.isUserExit();
    }
}
