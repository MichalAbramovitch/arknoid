
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Michal abramovitch
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {
    /**
     * fromReader.
     * @param reader the files reader
     * @return the blocks factory
     * @throws IOException when file isn't found
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        BufferedReader bf = new BufferedReader(reader);
        String line;
        Map<String, BlockCreator> blocks = new TreeMap<>();
        String symbol = null;
        String def = "";
        Map<String, Integer> spacers = new TreeMap<>();
        int width;

        try {
            while ((line = bf.readLine()) != null) {
                if ((!line.startsWith("default")) && (!line.startsWith("bdef")) && ((!line.startsWith("sdef")))) {
                    continue;
                }
                if (line.startsWith("default")) {
                    def = line.replace("default ", "");
                    continue;
                }
                if (line.startsWith("bdef")) {
                    String blockInfo = line.replace("bdef ", "");
                    blockInfo = blockInfo.concat(" " + def);

                    String[] parts = blockInfo.split(" ");

                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].startsWith("symbol")) {
                            symbol = parts[i].split(":")[1].trim();
                        }
                    }
                    Map<String, String> bc = new TreeMap<>();
                    for (String s : parts) {
                        if (!bc.containsKey(s)) {
                            if ((!s.contains("fill") && (!s.contains("fill-1")))
                                    || (s.contains("fill") && (!bc.containsKey("fill-1")))) {
                                bc.put(s.split(":")[0], s.split(":")[1]);
                            }
                            if ((!s.contains("fill") && (!s.contains("fill-1")))
                                    || (s.contains("fill-1") && (!bc.containsKey("fill")))) {
                                bc.put(s.split(":")[0], s.split(":")[1]);
                            }

                        }
                    }
                    BlockCreator b = new BlockFactory(bc);

                    blocks.put(symbol, b);
                }
                if (line.startsWith("sdef")) {
                    String[] parts = line.split(" ");
                    if (parts[1].startsWith("symbol")) {
                        symbol = parts[1].split(":")[1].trim();
                        width = Integer.parseInt(parts[2].split(":")[1].trim());
                    } else {
                        symbol = parts[2].split(":")[1].trim();
                        width = Integer.parseInt(parts[1].split(":")[1].trim());
                    }
                    spacers.put(symbol, width);
                }
            }
            bf.close();
        } catch (IOException e) {
            throw new IOException("error");
        }
        return new BlocksFromSymbolsFactory(spacers, blocks);
    }
}
