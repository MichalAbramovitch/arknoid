

import java.io.File;
import java.io.IOException;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author michal Abramovitch
 */
/**
 * Class Name: GameFlow.
 * makes the game flow
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor k;
    private Counter scores;
    private Counter lives;
    private HighScoresTable highScoresTable;



    /**
     * GameFlow constructor.
     * @param ar - the animation runner
     * @param ks - the keyboard sensor
     * @param t - the high score table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable t) {
        this.ar = ar;
        this.k = ks;
        this.highScoresTable = t;
    }

    /**
     * runLevels method.
     * @param levels the games' levels
     */
    public void runLevels(List<LevelInformation> levels) {

        this.scores = new Counter();
        this.lives = new Counter(7);
        Animation score = new HighScoresAnimation(this.highScoresTable);
        Animation end = new EndScreen(this.scores, this.lives);
        Animation scoresK = new KeyPressStoppableAnimation(this.k, KeyboardSensor.SPACE_KEY, score);
        Animation endK = new KeyPressStoppableAnimation(this.k, KeyboardSensor.SPACE_KEY, end);

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ar, this.k, this.scores, this.lives);
            level.initialize();

            while ((this.lives.getValue() > 0) && (level.getBlockCounter().getValue() > 0)) {
                level.playOneTurn();
            }

            if (this.lives.getValue() == 0) {
                break;
            }
        }

        if (this.highScoresTable.getRank(this.scores.getValue()) != -1) {
            DialogManager dialog = ar.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScoresTable.add(new ScoreInfo(name, this.scores.getValue()));
            try {
                this.highScoresTable.save(new File("src/HighScores.txt"));
            } catch (IOException e) {
                System.out.print("error");
            }
            this.ar.run(scoresK);
        }
        this.ar.run(endK);

    }
}
