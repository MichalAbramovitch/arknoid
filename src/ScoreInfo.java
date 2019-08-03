
import java.io.Serializable;

/**
 * @author Michal abramovitch
 * ScoreInfo method.
 */
public class ScoreInfo implements Serializable {

    private String name;
    private int score;

    /**
     * @param name the user's name.
     * @param score the user's score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore method.
     * @return the user's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     *@return the name of the user and his score.
     */
    @Override
    public String toString() {
        return "name = '" + name + '\'' + ", score = " + score;
    }
}