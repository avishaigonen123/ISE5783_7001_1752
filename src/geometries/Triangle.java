package geometries;

import primitives.Point;

/**
 * a class to represent a 2D Triangle in 3D
 */
public class Triangle extends Polygon{
    /**
     * constructor for building the triangle with 3 points
     * @param p0 point 1
     * @param p1 point 2
     * @param p2 point 3
     */
    Triangle(Point p0, Point p1, Point p2){
        super(p0,p1,p2);
    }

}
