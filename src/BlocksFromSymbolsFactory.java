
import java.util.Map;

/**
 * @author Michal abramovitch
 * Class that holds all the information of the blocks an how each one of them should be implements.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     *
     * @param spacerWidths  list of the spacer
     * @param blockCreators list of the blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s the spacer's symbol.
     * @return if is a valid spacer symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return (this.spacerWidths.containsKey(s));
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s the block's symbol.
     * @return if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated.
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    the block's symbol.
     * @param xpos the location of the 'x' value.
     * @param ypos the location of the 'y' value.
     * @return new Block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the spacer symbol.
     * @return the width of the current symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}