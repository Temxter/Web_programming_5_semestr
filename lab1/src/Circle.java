/**
 * The type Circle.
 * @author Andrew
 * @version 1
 */
public class Circle {

    /**
     * Define the center of circle.
     */
    private Point center;
    /**
     * Define the radios of circle.
     */
    private double radius;

    /**
     * Instantiates a new Circle.
     *
     * @param center the center
     * @param radius the radius
     * @see #Circle(Point, double)
     */
    public Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    /**
     * Gets center, set center by {@link #setCenter(Point)}
     *
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Sets center, get center by {@link #getCenter()}
     *
     * @param center the center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Gets radius, set radius by {@link #setRadius(double)}
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius, get radius by {@link #getRadius()}
     *
     * @param radius the radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
