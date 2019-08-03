
import java.awt.Color;

/**
 * ColorsParser class.
 * translates the strings into colors
 */
public class ColorsParser {

    /**
     * colorFromString method.
     * parse color definition and return the specified color
     * @param s the color's string
     * @return the string's color
     */
    public static java.awt.Color colorFromString(String s) {
        String ls = s.toLowerCase();
        if (ls.startsWith("rgb")) {
            ls = ls.substring(s.indexOf("(") + 1, s.indexOf(")"));
            String[] color = ls.split(",");
            int x = Integer.parseInt(color[0].trim());
            int y = Integer.parseInt(color[1].trim());
            int z = Integer.parseInt(color[2].trim());
            return new Color(x, y, z);
        } else {
            switch (ls) {
                case "red":
                    return Color.red;
                case "blue":
                    return Color.blue;
                case "yellow":
                    return Color.yellow;
                case "cyan":
                    return Color.cyan;
                case "gray":
                    return Color.gray;
                case "black":
                    return Color.black;
                case "lightgray":
                    return Color.lightGray;
                case "orange":
                    return Color.orange;
                case "green":
                    return Color.green;
                case "white":
                    return Color.white;
                case "pink":
                    return Color.pink;
                default:
                    return null;
            }
        }
    }
}
