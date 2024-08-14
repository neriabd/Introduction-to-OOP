package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;

/**
 * This class represents the graphic life that is displaying on the screen in the game.
 * @see GameObject
 * @see Heart
 * @author Neriya Ben David
 */
public class GraphicLife extends GameObject {
    // Fields
    private final Heart[] graphicLivesArray;
    private final Renderable heartRenderer;
    private final GameObjectCollection gameObjects;
    private final Counter lifeCounter;
    private final int windowsDimensionsY;
    private int numberOfHeartsOnScreen;

    /**
     * The constructor of the GraphicLifeCounter object.
     * @param heartRenderer the renderer for the heart object.
     * @param windowDimensions the dimensions of the window displaying the game.
     * @param lifeCounter the counter of the current number of life the user has.
     * @param gameObject the object which holds all game objects of the game running.
     */
    public GraphicLife(Renderable heartRenderer, Vector2 windowDimensions,
                       Counter lifeCounter, GameObjectCollection gameObject) {
        super(Vector2.ZERO, Vector2.ZERO, null);
        this.heartRenderer = heartRenderer;
        this.lifeCounter = lifeCounter;
        this.gameObjects = gameObject;
        this.windowsDimensionsY = (int) windowDimensions.y();
        this.graphicLivesArray = new Heart[MAX_LIVES];
        this.numberOfHeartsOnScreen = 0;
        updateGraphicLives();
    }

    /**
     * This method updates the graphic lives on the screen based on the number of lives the player
     * has.
     * @param deltaTime the time passed since the last frame.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateGraphicLives();
    }

    /**
     * This method updates the graphic lives on the screen based on the number of lives the player has.
     */
    private void updateGraphicLives() {
        if (numberOfHeartsOnScreen < lifeCounter.value()) {

            // add new hearts to the screen if the player has more lives
            int heartYPosition = windowsDimensionsY - HEART_BOTTOM_MARGIN;

            for (int i = numberOfHeartsOnScreen; i < lifeCounter.value(); i++) {

                // set the position of the heart on the game screen
                float heartXPosition = i * (HEART_WIDTH + HEART_PADDING) + HEART_LEFT_MARGIN;

                // create a new heart object
                Vector2 dimensions = new Vector2(HEART_WIDTH, HEART_HEIGHT);
                graphicLivesArray[i] = new Heart(new Vector2(heartXPosition,
                        heartYPosition),
                        dimensions, heartRenderer);

                this.gameObjects.addGameObject(graphicLivesArray[i], Layer.UI);
            }

        } else {

            // remove hearts from the screen if the player has fewer lives
            for (int i = lifeCounter.value(); i < numberOfHeartsOnScreen; i++) {
                this.gameObjects.removeGameObject(this.graphicLivesArray[i], Layer.UI);
            }
        }

        // update the number of hearts on the screen
        numberOfHeartsOnScreen = lifeCounter.value();
    }
}
