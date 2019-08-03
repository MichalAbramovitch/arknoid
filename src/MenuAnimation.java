
import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal abramovitch
 * MenuAnimation class.
 * @param <T> a generic value that represents any object
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuName;
    private KeyboardSensor k;
    private List<String> key;
    private List<String> message;
    private List<T> returnVal;
    private List<Boolean> isSubMenu;
    private Menu<T> subMenu;
    private boolean stop;
    private boolean sub;

    /**
     * MenuAnimation constructor.
     * @param menuName the menu's name
     * @param k the keyboard sensor
     */
    public MenuAnimation(String menuName, KeyboardSensor k) {
        this.menuName = menuName;
        this.k = k;
        this.stop = false;
        this.key = new ArrayList<>();
        this.message = new ArrayList<>();
        this.returnVal = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
        this.sub = false;

    }

    /**
     * addSelection method.
     * @param ke the selection's k
     * @param mes the selection's mes
     * @param retV the selection's return value
     */
    @Override
    public void addSelection(String ke, String mes, T retV) {
        this.key.add(ke);
        this.message.add(mes);
        this.returnVal.add(retV);
        this.isSubMenu.add(false);
    }

    /**
     * getStatus method.
     * @return the menu's status
     */
    @Override
    public T getStatus() {
        for (int i = 0; i < this.key.size(); i++) {
            if (this.k.isPressed(this.key.get(i))) {
                if (!this.isSubMenu.get(i)) {
                    return this.returnVal.get(i);
                } else {
                    this.sub = true;
                }
            }
        }
        return null;
    }

    /**
     * @param ke the sub menu's k.
     * @param mes the sub menu's mes
     * @param sMenu the sub menu's menu to run
     */
    @Override
    public void addSubMenu(String ke, String mes, Menu<T> sMenu) {
        this.key.add(ke);
        this.message.add(mes);
        this.subMenu = sMenu;
        this.returnVal.add(null);
        this.isSubMenu.add(true);
    }

    /**
     * @param d the surface to draw on.
     * @param dt the amount of seconds passed since the last call
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xAE417D));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0x48AE51));
        d.fillRectangle(0, 0, 600, 600);
        d.setColor(new Color(0xAE417D));
        d.fillRectangle(0, 0, 400, 600);
        d.setColor(new Color(0x48AE51));
        d.fillRectangle(0, 0, 200, 600);
        d.setColor(Color.black);
        d.drawText(50, 50, menuName, 50);
        d.setColor(Color.PINK);
        d.drawText(50, 53, menuName, 50);
        for (int i = 0; i < this.key.size(); i++) {
            d.setColor(Color.white);
            d.drawText(99, (100 + 50 * i), "for " + this.message.get(i) + " press " + this.key.get(i), 30);
            d.setColor(Color.green);
            d.drawText(100, (100 + 50 * i), "for " + this.message.get(i) + " press " + this.key.get(i), 30);
        }
        for (int i = 0; i < this.key.size(); i++) {
            if (this.k.isPressed(this.key.get(i))) {
                this.stop = true;
            }
        }
    }

    /**
     *
     * @return true if the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     *
     * @return true if the sub menu should be displayed and false if not.
     */
    public boolean isSub() {
        return this.sub;
    }

    /**
     * @return the sub menu.
     */
    public Menu<T> getSubMenu() {
        return this.subMenu;
    }
}
