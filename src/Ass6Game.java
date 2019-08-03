
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
/**
 * the class runs the whole game.
 */
public class Ass6Game {


    /**
     * adding the high score animation.
     * @param runner the animation runner throw the game.
     * @param animation of the game
     * @return score table.
     */
    public Task<Void> highScores(final AnimationRunner runner, final Animation animation) {
        Task<Void> scoreTable = new Task<Void>() {
            private AnimationRunner r = runner;
            private Animation a = animation;

            @Override
            public Void run() {
                this.r.run(this.a);
                return null;
            }
        };
        return scoreTable;
    }

    /**
     * adding the quit animation.
     * @param game the final game flow
     * @param levels the list of the levels of the game
     * @return the arnokid game
     */
    public Task<Void> arkanoid(final GameFlow game, final List<LevelInformation> levels) {
        Task<Void> arkanoid = new Task<Void>() {
            private GameFlow g = game;
            private List<LevelInformation> l = levels;

            @Override
            public Void run() {
                this.g.runLevels(this.l);
                return null;
            }
        };
        return arkanoid;
    }
    /**
     * adding the quit animation.
     * @return quit animation
     */
    public Task<Void> quit() {
        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };
        return quit;
    }

    /**
     * the main method to run the game.
     *
     * @param args the list of levels to run.
     *
     */
    public static void main(String[] args) {
        Ass6Game ass6 = new Ass6Game();
        GUI gui = new GUI("arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();
        LevelSpecificationReader lr = new LevelSpecificationReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        List<Selection<String>> lsr = LevelSetsReader.fromReader(new InputStreamReader(is));
        HighScoresTable table = HighScoresTable.loadFromFile(new File("src/HighScores.txt"));

        GameFlow game = new GameFlow(ar, ks, table);

        while (true) {

            Animation scores = new HighScoresAnimation(table);
            Animation scoresK = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, scores);

            MenuAnimation<Task<Void>> menu = new MenuAnimation<>("Menu", ks);
            Menu<Task<Void>> subMenu = new MenuAnimation<>("SubMenu", ks);
            for (Selection<String> s : lsr) {
                InputStream is2 = ClassLoader.getSystemClassLoader().getResourceAsStream(s.getReturnVal());
                List<LevelInformation> levels = lr.fromReader(new InputStreamReader(is2));
                subMenu.addSelection(s.getKey(), s.getMessage(), ass6.arkanoid(game, levels));
            }
            menu.addSubMenu("p", "play game", subMenu);
            menu.addSelection("s", "high scores", ass6.highScores(ar, scoresK));
            menu.addSelection("q", "quit", ass6.quit());

            ar.run(menu);
            Task<Void> task = menu.getStatus();
            //scoreTable/quit
            if (!menu.isSub()) {
                task.run();
                //runLevels
            } else {
                ar.run(menu.getSubMenu());
                task = menu.getSubMenu().getStatus();
                task.run();
            }
        }
    }
}
