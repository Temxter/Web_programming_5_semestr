/**
 * The type Point.
 * @author Andrew
 * @version 1
 */
public class Point {

    /**
     * Define point location.
     */
    private int x, y;

    /**
     * Instantiates a new Point with x=0 and y=0.
     * @see #Point(int, int)
     */
    public Point(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Calculate distance between two points.
     *
     * @param b is another point.
     * @return the double
     */
    public double distance(Point b){
        Point a = this;
        return Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2);
    }


    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     * @see #Point()
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x, that can set by {@link #setX(int)}.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x, that can get by {@link #getX()}
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y, that can set by {@link #setY(int)}
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y, that can get by {@link #getY()}
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x = " + x +
                ", y = " + y +
                '}';
    }
}
