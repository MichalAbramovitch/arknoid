

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal abramovitch
 * LevelSetsReader class.
 */
public class LevelSetsReader {
    /**
     * fromReader method.
     * @param reader the file's reader
     * @return the level's set
     */
    public static List<Selection<String>> fromReader(java.io.Reader reader) {
        List<Selection<String>>  l = new ArrayList<>();
        String line;
        LineNumberReader lr = new LineNumberReader(reader);
        String key = null;
        String name = null;
        String path = null;
        try {
            while ((line = lr.readLine()) != null) {
                if (lr.getLineNumber() % 2 == 1) {
                    key = line.split(":")[0].trim();
                    name = line.split(":")[1].trim();
                    line = lr.readLine();
                    path = line;
                } else {
                    continue;
                }
                l.add(new Selection<>(key, name, path));
            }
            lr.close();
        } catch (IOException e) {
            System.out.print("error in levelSetsReader");
        }
        return l;
    }
}
