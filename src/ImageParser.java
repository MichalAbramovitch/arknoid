
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Michal abramovitch
 * ImageParser class.
 * translates the string into path
 */
public class ImageParser {
    /**
     * pathToImage method.
     * @param path the image path's string
     * @return the image's path
     */
    public static Image pathToImage(String path) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            System.out.print("error");
            return null;
        }
    }
}
