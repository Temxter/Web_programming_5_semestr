public class Main {

    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        double radius = 15.0;
	    CirclePrint circle = new CirclePrint(x, y, radius);
	    Point pointInCircle = new Point(11, 21);
	    Point pointOutCircle = new Point(0, 0);
        printWhereIsPoint(circle.isPointInCircle(pointInCircle), pointInCircle);
        printWhereIsPoint(circle.isPointInCircle(pointOutCircle), pointOutCircle);
        System.out.println(circle);
        circle.setCenter(1, 1);
        System.out.println(circle);
        circle.setRadius(500);
        System.out.println(circle);

    }

    static void printWhereIsPoint(boolean condition, Point p){
        if (condition)
            System.out.println(String.format("Point %s in circle", p));
        else
            System.out.println(String.format("Point %s out of circle", p));
    }

}
