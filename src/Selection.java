

/**
 * @author Michal abramovitch
 * SelectionInfo method.
 * @param <T> a generic value that represents any object
 */
public class Selection<T> {

    private String key;
    private String message;
    private T returnVal;

    /**
     * SelectionInfo constructor.
     *
     * @param key       the selection's key
     * @param message   the selection's message to display
     * @param returnVal the selection's return value
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * getReturnVal method.
     *
     * @return the selection's return value
     */
    public T getReturnVal() {
        return this.returnVal;
    }

    /**
     * getMessage method.
     *
     * @return the selection's message to display
     */
    public String getMessage() {
        return message;
    }

    /**
     * getKey method.
     *
     * @return the selection's key
     */
    public String getKey() {
        return this.key;
    }
    /**
     *
     * turns the task in to a string for example (s)start game.
     *
     * @return the name to be displayed as a string.
     */
    public String toString() {
        return "(" + this.key + ")" + this.message;
    }

}
