
import java.util.Map;

/**
 * @author Michal abramovitch
 * BlockFactory class.
 */
public class BlockFactory implements BlockCreator {
    private Map<String, String> blockInfo;

    /**
     * BlockFactory constructor.
     * @param blockInfo the blocks' info
     */
    public BlockFactory(Map<String, String> blockInfo) {
        this.blockInfo = blockInfo;
    }

    /**
     * creates the block.
     * @param xpos the upper left x coordinate
     * @param ypos the upper left y coordinate
     * @return the created  block
     */
    public Block create(int xpos, int ypos) {
        Point p = new Point(xpos, ypos);
        int width = Integer.parseInt(this.blockInfo.get("width"));
        int height = Integer.parseInt(this.blockInfo.get("height"));
        int hitPoints = Integer.parseInt(this.blockInfo.get("hit_points"));
        Block block = new Block(new Rectangle(p, width, height), hitPoints);

        if (this.blockInfo.containsKey("stroke")) {
            String str = this.blockInfo.get("stroke");
            str = str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"));
            block.setStroke(ColorsParser.colorFromString(str));
        }
        String temp;
        for (int i = hitPoints; i > 0; i--) {
            if (this.blockInfo.containsKey("fill-" + i)) {
                if (this.blockInfo.get("fill-" + i).startsWith("color")) {
                    temp = this.blockInfo.get("fill-" + i);
                    temp = temp.replace("color(", "");
                    temp = temp.substring(0, temp.lastIndexOf(")"));
                    block.setColorArr(ColorsParser.colorFromString(temp), i - 1);
                }  else {
                    temp = this.blockInfo.get("fill-" + i);
                    temp = temp.replace("image(", "");
                    temp = temp.substring(0, temp.lastIndexOf(")"));
                    block.setImageArr(ImageParser.pathToImage(temp), i - 1);
                }
            } else if (this.blockInfo.containsKey("fill")) {
                if (this.blockInfo.get("fill").startsWith("color")) {
                    temp = this.blockInfo.get("fill");
                    temp = temp.replace("color(", "");
                    temp = temp.substring(0, temp.lastIndexOf(")"));
                    block.setColorArr(ColorsParser.colorFromString(temp), 0);
                }  else {
                    temp = this.blockInfo.get("fill");
                    temp = temp.replace("image(", "");
                    temp = temp.substring(0, temp.lastIndexOf(")"));
                    block.setImageArr(ImageParser.pathToImage(temp), 0);
                }
            }
        }
        return block;
    }
}

