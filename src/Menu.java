

/**
 * Menu interface.
 * @param <T> a generic value that represents any object
 */
public interface Menu<T> extends Animation {
    /**
     * @param key the selection's key
     * @param message the selecion's message to display
     * @param returnVal the selection's return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus method.
     * @return the menu's status
     */
    T getStatus();

    /**

     * @param key the sub menu's key
     * @param message the sub menu's message
     * @param subMenu the sub menu's menu to display
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
