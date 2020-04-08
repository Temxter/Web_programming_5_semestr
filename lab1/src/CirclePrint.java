/**
 * Class for create circle and to prove what point in it and what out of it.
 * @author Andrew
 * @version 1
 * The type Circle print.
 */
public class CirclePrint {
    /**
     * Object circle.
     */
    private Circle circle;

    /**
     * Instantiates a new Circle print.
     *
     * @param circle the circle
     * @see #CirclePrint(Point, double)
     * @see #CirclePrint(int, int, double)
     */
    public CirclePrint(Circle circle) {
        this.circle = circle;
    }

    /**
     * Instantiates a new Circle print.
     *
     * @param center the center
     * @param radius the radius
     * @see #CirclePrint(Circle)
     * @see #CirclePrint(int, int, double)
     */
    public CirclePrint(Point center, double radius){
        circle = new Circle(center, radius);
    }

    /**
     * Instantiates a new Circle print.
     *
     * @param x      the x
     * @param y      the y
     * @param radius the radius
     * @see #CirclePrint(Circle)
     * @see #CirclePrint(Point, double)
     */
    public CirclePrint(int x, int y, double radius){
        circle = new Circle(new Point(x, y), radius);
    }

    /**
     * Sets center.
     *
     * @param x the x
     * @param y the y
     */
    public void setCenter(int x, int y){
        circle.setCenter(new Point(x, y));
    }

    /**
     * Set radius.
     *
     * @param radius the radius
     */
    public void setRadius(double radius){
        circle.setRadius(radius);
    }

    /**
     * Is point in circle boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isPointInCircle(Point point){
        return point.distance(circle.getCenter()) < circle.getRadius();
    }

    /**
     * Define is point in circle or not.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isPointInCircle(int x, int y){
        return isPointInCircle(new Point(x, y));
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center: x = " + circle.getCenter().getX() +
                ", y = " + circle.getCenter().getY() +
                "; radius = " + circle.getRadius() +
                '}';
    }
}
