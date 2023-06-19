package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * an Intersectable that represents an axes aliened box
 */
public class AABox extends Intersectable {
    /**
     * one of the corner points of the Axes Aliened Box
     */
    private Point A;
    /**
     * the opposite Point to Point A of the Axes Aliened Box
     */
    private Point B;

    public AABox(Point a, Point b) {
        A = a;
        B = b;
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
