package pepse.world;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.AnimationRenderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import static pepse.util.GameConstants.*;

/**
 * Represents the avatar in the game and handles its movement and energy.
 * @see GameObject
 * @author Neriya Ben David
 */
public class Avatar extends GameObject {
    private static final int EAT_FRUIT_ENERGY_INCREASE = 10;


    // Fields
    private final UserInputListener inputListener;
    private final ImageReader imageReader;
    private final Runnable jumpCallback;
    private float avatarEnergy = AVATAR_MAX_ENERGY;
    private AvatarMovementState avatarMovementState = AvatarMovementState.IDLE;
    private final HashMap<AvatarMovementState, AnimationRenderable> animationMap = new HashMap<>();
    private final HashMap<AvatarMovementState, Vector2> dimensionMap = new HashMap<>();

    /**
     * Creates a new Avatar object.
     * @param topLeftCorner the top left corner position of the avatar.
     * @param inputListener the input listener for the avatar.
     * @param imageReader the image reader for the avatar.
     * @param jumpCallback the callback for the avatar jump.
     */
    public Avatar(Vector2 topLeftCorner, UserInputListener inputListener, ImageReader imageReader,
                  Runnable jumpCallback) {
        super(topLeftCorner, AVATAR_IDLE_DIMENSIONS, null);
        this.inputListener = inputListener;
        this.imageReader = imageReader;

        initAnimationMap();
        initDimensionMap();
        setGravityAndPreventIntersections();

        this.jumpCallback = jumpCallback;
        setTag(TAG_AVATAR);
    }

    /**
     * Updates the avatar's movement and energy.
     * @param deltaTime the time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        aimJumpMovement();
        aimRunMovement();
        handleNotMoving();
        changeAnimation();
    }

    /**
     * This method is called when a collision occurs with an avatar game object and updates
     * the avatar's energy accordingly.
     * @param other The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals(TAG_FRUIT)) {
            updateEnergy(EAT_FRUIT_ENERGY_INCREASE);
        }
    }

    /**
     * a getter for the energy of the avatar.
     * @return the energy of the avatar.
     */
    public float getEnergy() {
        return avatarEnergy;
    }

    /*
     * Updates the energy of the avatar by the energy given in the parameter.
     */
    private boolean updateEnergy(float energy) {
        float updatedEnergy = avatarEnergy + energy;
        if (updatedEnergy < AVATAR_MIN_ENERGY) {
            return false;
        }
        avatarEnergy = Math.min(updatedEnergy, AVATAR_MAX_ENERGY);
        return true;
    }

    /*
     * Handles the case when the avatar is not moving.
     */
    private void handleNotMoving() {
        if (getVelocity().equals(Vector2.ZERO)) {
            // update avatar energy

            updateEnergy(IDLE_ENERGY_INCREASE);
            // change avatar movement state
            avatarMovementState = AvatarMovementState.IDLE;
        }
    }

    /*
     * Initializes the dimensions of the avatar based on the movement state.
     */
    private void initDimensionMap() {
        dimensionMap.put(AvatarMovementState.IDLE, AVATAR_IDLE_DIMENSIONS);
        dimensionMap.put(AvatarMovementState.JUMP, AVATAR_JUMP_DIMENSIONS);
        dimensionMap.put(AvatarMovementState.RUN, AVATAR_RUN_DIMENSIONS);
    }

    /*
     * Initializes the animation of the avatar based on the movement state.
     */
    private void initAnimationMap() {
        animationMap.put(AvatarMovementState.IDLE, new AnimationRenderable(AVATAR_IDLE_IMAGES,
                imageReader, true, FRAME_ANIMATION_DURATION));
        animationMap.put(AvatarMovementState.JUMP, new AnimationRenderable(AVATAR_JUMP_IMAGES,
                imageReader, true, FRAME_ANIMATION_DURATION));
        animationMap.put(AvatarMovementState.RUN, new AnimationRenderable(AVATAR_RUN_IMAGES,
                imageReader, true, FRAME_ANIMATION_DURATION));
    }

    /*
     * Sets the gravity and prevents intersections for the avatar.
     */
    private void setGravityAndPreventIntersections() {
        transform().setAccelerationY(GRAVITY);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
    }

    /*
     * Handles the avatar's jump movement.
     */
    private void aimJumpMovement() {
        boolean isJumping = inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0;

        if (isJumping && updateEnergy(JUMP_ENERGY_REDUCTION)) {
            transform().setVelocityY(VELOCITY_Y);

            // change avatar movement state
            avatarMovementState = AvatarMovementState.JUMP;

            // invoke the jump callback
            jumpCallback.run();
        }
    }

    /*
     * Changes the avatar's animation based on the movement state.
     */
    private void changeAnimation() {
        // change the avatar animation if the movement state has changed
        if (animationMap.get(avatarMovementState) != renderer().getRenderable()) {
            renderer().setRenderable(animationMap.get(avatarMovementState));
        }

        // flip the avatar if moving left (moving to the negative x direction)
        if (getVelocity().x() != 0) {
            renderer().setIsFlippedHorizontally(getVelocity().x() < 0);
        }

        // set the dimensions of the avatar based on the movement state
        setDimensions(dimensionMap.get(avatarMovementState));

    }

    /*
     * Handles the avatar's run movement.
     */
    private void aimRunMovement() {
        float xVel = 0;
        float xIncrement = 0;
        boolean isRunning = false;
        boolean isMovingLeft = inputListener.isKeyPressed(KeyEvent.VK_LEFT);
        boolean isMovingRight = inputListener.isKeyPressed(KeyEvent.VK_RIGHT);

        // handle the case when the avatar is moving to the sides

        if (isMovingLeft) {
            xIncrement = -VELOCITY_X;
            isRunning = true;
        }
        if (isMovingRight) {
            xIncrement = VELOCITY_X;
            isRunning = true;
        }
        if (isRunning && updateEnergy(RUN_ENERGY_REDUCTION)) {
            xVel += xIncrement;

            // change avatar movement state
            avatarMovementState = AvatarMovementState.RUN;
        }
        transform().setVelocityX(xVel);
    }
}
