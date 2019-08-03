
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * @author Michal abramovitch
 * Class representing animation of the high score table.
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

        /**
         * constructor.
         *
         * @param scores the score we set.
         */
        public HighScoresAnimation(HighScoresTable scores) {
            this.scores = scores;
        }

        @Override
        public void doOneFrame(DrawSurface d, double dt) {
            d.setColor(new Color(0xAE417D));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new Color(0x48AE51));
            d.fillRectangle(0, 0, 800, 450);
            d.setColor(new Color(0xAE417D));
            d.fillRectangle(0, 0, 800, 300);
            d.setColor(new Color(0x48AE51));
            d.fillRectangle(0, 0, 800, 150);
            d.setColor(Color.WHITE);
            d.drawText(250, 80, "Score Table", 40);
            d.setColor(Color.cyan);
            d.drawText(250, 82, "Score Table", 40);
            int lineCount = 0;
            for (ScoreInfo scoreInfo : this.scores.getHighScores()) {
                d.drawText(250, 150 + lineCount, scoreInfo.toString(), 30);
                lineCount += 50;
            }
        }

        @Override
        public boolean shouldStop() {
            return false;
        }

}

