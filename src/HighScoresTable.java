
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michal abramovitch
 * Class that represent score table of the game.
 */
public class
HighScoresTable implements Serializable {
    private int size;
    private ArrayList<ScoreInfo> scores;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size of the table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<>(this.size);
    }

    /**
     * Add a high-score.
     *
     * @param score the score we want to add.
     */
    public void add(ScoreInfo score) {
        if (this.scores.size() < this.size || this.getRank(score.getScore()) <= this.size) {
            if (this.scores.size() == 0) {
                this.scores.add(score);
            } else {
                for (ScoreInfo scoreInfo : this.scores) {
                    if (this.getRank(score.getScore()) <= this.getRank(scoreInfo.getScore())) {
                        this.scores.add(this.getRank(score.getScore()) - 1, score);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Return table size.
     *
     * @return the size of the table.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest scores come first.
     *
     * @return list of all the highest scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * return the rank of the current score.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score the score we want to check its rank.
     * @return the rank of the current score
     */
    public int getRank(int score) {
        if ((this.scores.size() >= this.size)
                && (score < this.scores.get(this.size - 1).getScore())) {
            return -1;
        } else {
            for (int i = 0; i < this.scores.size(); i++) {
                if (score > this.scores.get(i).getScore()) {
                    return (i++);
                }
            }
            return this.scores.size();
        }
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename the file contains the scores info.
     * @throws IOException in case the file doesn't exist.
     */
    public void load(File filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.scores = (ArrayList<ScoreInfo>) ois.readObject();
            this.size = ois.read();
            ois.close();
        } catch (ClassNotFoundException e1) {
            System.out.print("Class Not Found");
        }
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename the name of the file that inside it we save the table.
     * @throws IOException and IO exception that might been thrown.
     */
    public void save(File filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.scores);
            oos.write(this.size);
            oos.close();
        } catch (IOException e1) {
            throw new IOException("error");
        }
    }


    /**
     * Read a table from file and return it. If the file does not exist, or there is a problem with reading it,
     * an empty table is returned.
     *
     * @param filename the file we try to load from.
     * @return a new table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable t = new HighScoresTable(5);
        try {
            t.load(filename);
            return t;
        } catch (IOException e1) {
            return t;
        }
    }
}