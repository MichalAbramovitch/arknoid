
/**
 * @author michal Abramovitch
 */
/**
 * class which hold the information of collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * Constructor.
     * @param collisionPoint Point
     * @param collisionObject Collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}