
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

import biuoop.KeyboardSensor;


/**
 * @author michal Abramovitch
 */
/**
 * A class that controls the game and make it run.
 *
 * @author Michal abramovitch
 */
public class GameLevel implements  Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keys;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private Counter livesCounter;
    private AnimationRunner runner;
    private boolean running;
    private Paddle p;
    private LevelInformation level;



    /**
     * constructor.
     * @param thisLevel the information about the level
     * @param r the annimation of the level
     * @param k the Keyboard option of the level
     * @param scores the score the player achive
     * @param lives the lives the player has
     *
     */
    public GameLevel(LevelInformation thisLevel, AnimationRunner r, KeyboardSensor k, Counter scores, Counter lives) {
            this.runner = r;
            this.scoreCounter = scores;
            this.livesCounter = lives;
            this.sprites = new SpriteCollection();
            this.environment = new GameEnvironment();
            this.ballCounter = new Counter();
            this.blockCounter = new Counter();
            this.gui = this.runner.getGui();
            this.keys = k;
            this.level = thisLevel;
        }





    /**
     *
     *@param c the collidable object.
     * add the collidable oblect to the games' environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * add the sprite object to the sprites collection.
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * removes the collidable from the game environment.
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removes the sprite from the sprite collection.
     * @param s a sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * getter.
     *
     * @return the block counter.
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }
    /**
     * getter.
     *
     * @return the ball counter.
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    /**
     *
     * Initialize a new game: create the function to the game.
     * and add them to the game.
     */
    public void initialize() {

        this.addSprite(this.level.getBackground());
        /* create 6 lines of blocks */
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(livesCounter);
        livesIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(this.level.levelName());
        levelIndicator.addToGame(this);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        this.createBlocks(scoreTrackingListener, blockRemover);
        //creating ballRemover
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        /* create the borders */

        this.createBorders(ballRemover);
        this.sprites.addSprite(levelIndicator);
        this.sprites.addSprite(scoreIndicator);
        this.sprites.addSprite(livesIndicator);

    }
    /**
     *
     * Run the game- start the animation loop.
     */

    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        p = new Paddle(new Rectangle(new Point(400 - (this.level.paddleWidth() / 2), 580),
                level.paddleWidth(), 15), Color.ORANGE, this.keys, 20,
                780, this.level.paddleSpeed());

        p.addToGame(this);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        this.p.removeFromGame(this);

    }
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        if (this.getBlockCounter().getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;

        }
        if (this.getBallCounter().getValue() == 0) {
            this.livesCounter.decrease(1);
            this.running = false;

        }
        if (this.keys.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keys, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));

        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }
    /**
     * creates the borders of the game.

     * @param ballRemover the block's height
     */
    public void createBorders(BallRemover ballRemover) {
        /* create 4 blocks- for each side of the borders */
        Block blockUpUp = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.white
                , 0);
        blockUpUp.addToGame(this);
        Block blockUp = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY
                , -1);
        blockUp.addToGame(this);
        Block blockLeft = new Block(new Rectangle(new Point(0, 40), 20, 580), Color.GRAY
                , -1);
        blockLeft.addToGame(this);
        Block blockRight = new Block(new Rectangle(new Point(780, 40), 20, 600), Color.GRAY,
                -1);
        blockRight.addToGame(this);
        //creating the death block
        Block deathBlock = new Block(new Rectangle(new Point(0, 600), 800, 20), Color.GRAY,
                -1);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
    }



    /**
     * a method deciding wether the loop should stop or not.
     *
     * @return true if should stop, otherwise false.
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * creates the balls for the game.
     */
    public void createBallsOnTopOfPaddle() {
        for (Velocity v : this.level.initialBallVelocities()) {
            Ball gameBall = new Ball(400, 575, 5, Color.white, this.environment);
            gameBall.setVelocity(v);
            gameBall.addToGame(this);
        }
        this.ballCounter.increase(this.level.numberOfBalls());
    }

    /**
     *
     * creates the game's blocks.
     * @param scoreTrackingListener keeps score of the player.
     * @param blockRemover removes blocks that have been hit.

     */
    public void createBlocks(ScoreTrackingListener scoreTrackingListener, BlockRemover blockRemover) {
        for (Block b : this.level.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            this.blockCounter.increase(1);
        }
    }

}