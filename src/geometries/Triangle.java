package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

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

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
