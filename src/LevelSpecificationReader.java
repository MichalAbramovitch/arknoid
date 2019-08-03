
import biuoop.DrawSurface;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal abramovitch
 * LevelSpecificationReader class.
 * reads the levels' details from the file
 */
public class LevelSpecificationReader {

    private List<LevelInformation> li;

    /**
     * The method reads all the levels informatics from a file.
     *
     * @param reader the file contains all the information about the game's levels.
     * @return list with all the game's level.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        this.li = new ArrayList<>();
        try {
            final List<List<String>> lls = this.getLevelStrings(reader);
            for (int i = 0; i < lls.size(); i++) {
                final int index = i;
                LevelInformation level = new LevelInformation() {
                    @Override
                    public int numberOfBalls() {
                        return LevelSpecificationReader.readBallVelocities(lls.get(index)).size();
                    }

                    @Override
                    public List<Velocity> initialBallVelocities() {
                        return LevelSpecificationReader.readBallVelocities(lls.get(index));
                    }

                    @Override
                    public int paddleSpeed() {
                        return LevelSpecificationReader.readPaddleSpeed(lls.get(index));
                    }

                    @Override
                    public int paddleWidth() {
                        return LevelSpecificationReader.readPaddleWidth(lls.get(index));
                    }

                    @Override
                    public String levelName() {
                        try {
                            return LevelSpecificationReader.readLevelName(lls.get(index));
                        } catch (Exception e) {
                            System.out.print("error");
                            return null;
                        }
                    }

                    @Override
                    public Sprite getBackground() {
                        try {
                            return new Background(LevelSpecificationReader.readPathBG(lls.get(index)));
                        } catch (Exception e) {
                            System.out.print("error");
                        }
                        return null;
                    }

                    @Override
                    public List<Block> blocks() {
                        try {
                            String s = LevelSpecificationReader.readBlockDefinition(lls.get(index));
                            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
                            BlocksFromSymbolsFactory bf = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
                            List<String> pat = LevelSpecificationReader.blocksPattern(lls.get(index));
                            int rowH = LevelSpecificationReader.readBlockHeight(lls.get(index));
                            Point p = LevelSpecificationReader.readStartPoint(lls.get(index));
                            return LevelSpecificationReader.blocksList(bf, pat, rowH, (int) p.getX(), (int) p.getY());
                        } catch (IOException e) {
                            System.out.print("error");
                            return null;
                        }
                    }

                    @Override
                    public int numberOfBlocksToRemove() {
                        return LevelSpecificationReader.readNumOfBlocks(lls.get(index));
                    }
                };
                this.li.add(level);
            }
        } catch (IOException e1) {
            System.out.println("error");
        }
        return this.li;
    }

    /**
     * getLevelStrings method.
     * @param reader the file's reader
     * @return the levels' list
     * @throws IOException if the level wasn't found
     */
    public List<List<String>> getLevelStrings(java.io.Reader reader) throws IOException {
        BufferedReader bf = new BufferedReader(reader);
        String line;
        List<List<String>> levels = new ArrayList<>();
        try {
            while ((line = bf.readLine()) != null) {
                List<String> l = new ArrayList<>();
                if ((line.startsWith("START_LEVEL")) || (line.equals(""))) {
                    continue;
                }
                while (!line.startsWith("END_LEVEL")) {
                    l.add(line);
                    line = bf.readLine();
                }
                levels.add(l);
            }
            bf.close();
        } catch (IOException e1) {
            throw new IOException("error");
        }
        return levels;
    }

    /**
     * readPaddleWidth method.
     * @param ls the string's list to read from
     * @return the paddle's width
     */
    public static int readPaddleWidth(List<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("paddle_width")) {
                String s = ls.get(i).split(":")[1];
                s.replaceAll(" ", "");
                return Integer.parseInt(s);
            }
        }
        return -1;
    }

    /**
     * readPaddleSpeed method.
     * @param ls the string's list to read from
     * @return the paddle's speed
     */
    public static int readPaddleSpeed(List<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("paddle_speed")) {
                String s = ls.get(i).split(":")[1];
                s.replaceAll(" ", "");
                return Integer.parseInt(s);
            }
        }
        return -1;
    }

    /**
     * readLevelName method.
     * @param ls the string's list to read from
     * @return the levels' name
     * @throws Exception if the level's name wasn't found
     */
    public static String readLevelName(List<String> ls) throws Exception {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("level_name")) {
                String s = ls.get(i).split(":")[1].trim();
                return s;
            }
        }
        throw new Exception("not found");
    }

    /**
     * readNumOfBlocks method.
     * @param ls the string's list to read from
     * @return the amount of blocks
     */
    public static int readNumOfBlocks(List<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("num_blocks")) {
                String s = ls.get(i).split(":")[1];
                s.replaceAll(" ", "");
                return Integer.parseInt(s);
            }
        }
        return -1;
    }

    /**
     * readPathBG method.
     * @param ls the string's list to read from
     * @return the background's path
     * @throws Exception if the path read failed
     */
    public static String readPathBG(List<String> ls) throws Exception {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("background")) {
                String s = ls.get(i).split(":")[1].trim();
                return s;
            }
        }
        throw new Exception("not found");
    }

    /**
     * readBlockHeight method.
     * @param ls the string's list to read from
     * @return block's height
     */
    public static int readBlockHeight(List<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("row_height")) {
                String s = ls.get(i).split(":")[1];
                s.replaceAll(" ", "");
                return Integer.parseInt(s);
            }
        }
        return -1;
    }

    /**
     * readStartPoint method.
     * @param ls the string's list to read from
     * @return the block's upper left start point
     */
    public static Point readStartPoint(List<String> ls) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("blocks_start_x")) {
                String s = ls.get(i).split(":")[1];
                x = Integer.parseInt(s);
            }
            if (ls.get(i).startsWith("blocks_start_y")) {
                String s = ls.get(i).split(":")[1];
                y = Integer.parseInt(s);
            }
        }
        return new Point(x, y);
    }

    /**
     * readBlockDefinition method.
     * @param ls the string's list to read from
     * @return the string with the ball definition
     */
    public static String readBlockDefinition(List<String> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("block_definitions")) {
                String s = ls.get(i).split(":")[1].trim();
                return s;
            }
        }
        return null;
    }
    /**
     * readBallVelocities method.
     * @param ls the string's list to read from
     * @return the balls velocity lost
     */
    public static List<Velocity> readBallVelocities(List<String> ls) {
        List<Velocity> lv = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).startsWith("ball_velocities")) {
                String s = ls.get(i).split(":")[1];
                String[] strings =  s.split(" ");
                for (int j = 0; j < strings.length; j++) {
                    String[] angleSpeed = strings[j].split(",");
                    int angle = Integer.parseInt(angleSpeed[0].replaceAll(" ", ""));
                    int speed = Integer.parseInt(angleSpeed[1].replaceAll(" ", ""));
                    lv.add(Velocity.fromAngleAndSpeed(angle, speed));
                }
            }
        }
        return lv;
    }

    /**
     * blocksPattern method.
     * @param ls the string's list to read from
     * @return the block's strings list
     */
    public static List<String> blocksPattern(List<String> ls) {
        List<String> pat = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            String s = ls.get(i);
            if (!s.startsWith("START_BLOCKS")) {
                continue;
            }
            i++;
            while (!ls.get(i).startsWith("END_BLOCKS")) {
                pat.add(ls.get(i));
                i++;
            }
            break;
        }
        return pat;
    }

    /**
     * blocksList method.
     * @param blocksFactory the blocks factory
     * @param blockDef the blocks' definition
     * @param rowHeight the row's height
     * @param blockStartX the upper left point of the start blocks' x coordinate
     * @param blockStartY the upper left point of the start blocks' x coordinate
     * @return the blocks' list
     */
    public static List<Block> blocksList(BlocksFromSymbolsFactory blocksFactory, List<String> blockDef, int rowHeight,
                                         int blockStartX, int blockStartY) {
        List<Block> blocks = new ArrayList<>();
        int widthRow;
        int rowTotalHeight = blockStartY;

        for (String rawBlockDef : blockDef) {
            if (blocksFactory.isSpaceSymbol(rawBlockDef)) {
                rowTotalHeight += rowHeight;
            } else {
                widthRow = 0;
                for (int i = 0; i < rawBlockDef.length(); i++) {
                    if (blocksFactory.isSpaceSymbol(rawBlockDef.substring(i, i + 1))) {
                        widthRow = widthRow + blocksFactory.getSpaceWidth(rawBlockDef.substring(i, i + 1));
                    } else if (blocksFactory.isBlockSymbol(rawBlockDef.substring(i, i + 1))) {
                        blocks.add(blocksFactory.getBlock(rawBlockDef.substring(i, i + 1), blockStartX + widthRow,
                                rowTotalHeight));
                        widthRow = widthRow + blocks.get(blocks.size() - 1).getWidth();
                    }
                }
                rowTotalHeight += rowHeight;
            }
        }
        return blocks;
    }

    /**
     * Background class.
     * anonymous class that creates the background
     */
    private class Background implements Sprite {
        private String path;
        private Image image;
        /**
         * Background method.
         * @param path the background's path
         */
        public Background(String path) {
            this.path = path;
            if (path.startsWith("image")) {
                String str = path.substring(path.indexOf("(") + 1, path.lastIndexOf(")"));
                this.image = ImageParser.pathToImage(str);
            }
        }

        /**
         * drawOn method.
         * draw the sprite to the screen
         * @param d - the surface on which we want t draw the sprite object
         */
        public void drawOn(DrawSurface d) {
            if (this.path.startsWith("color")) {
                String str = this.path;
                d.setColor(ColorsParser.colorFromString(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"))));
                d.fillRectangle(0, 0, 800, 600);
            } else {
                d.drawImage(0, 0, this.image);
            }
        }
        /**
         * timePassed method.
         * notify the sprite that time has passed
         * @param dt the amount of seconds passed since the last call
         */
        public void timePassed(double dt) {
        }
    }
}