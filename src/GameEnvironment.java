
import java.util.ArrayList;
import java.util.List;
/**
 * Class GameEnvironment.
 * creates the game environment- the collidables object
 * and gets information on the colission
 */
public class GameEnvironment {
    private List<Collidable> collidableList;
    /**
     * GameEnvironment constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }
    /**
     * addCollidable method.
     * add the given collidable to the environment
     * @param c the collidable object that needs to be added
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * getClosestCollision method.
     * gets the closest collision point to the start of the line- if exists
     * @param trajectory the line in which the ball suppose to move
     * @return if there is a collision the information about the closest
     * collision that is going to occur. in not- null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollision = trajectory.end();
        Collidable object = null;
        double shortDistance = trajectory.start().distance(trajectory.end());
        for (Object collide : collidableList) {
            Collidable currentObject = (Collidable) collide;
            Point collision = trajectory.closestIntersectionToStartOfLine(currentObject.getCollisionRectangle());
            if (collision != null) {
                double currentDistance = trajectory.start().distance(collision);
                if (Double.compare(currentDistance, shortDistance) < 0 || Double.compare(currentDistance,
                        shortDistance) == 0) {
                    shortDistance = currentDistance;
                    closestCollision = collision;
                    object = currentObject;
                }
            }
        }
        if (closestCollision.equals(trajectory.end()) && object == null) {
            return null;
        }
        return new CollisionInfo(closestCollision, object);
    }
    /**
     * removeCollidable method.
     * @param c - Collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }


}