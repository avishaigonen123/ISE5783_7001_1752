package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * a class that represent a 3d plane
 */
public class Plane implements Geometry {
    final private Point p0;
    final private Vector normal;

    /**
     * constructor that gets a point in the plane and a normal to the plain
     * @param _p0 the point
     * @param _normal the vector normal
     */
    Plane(Point _p0, Vector _normal) {
        p0 = _p0;
        normal = _normal.normalize();
    }

    /**
     * constructor that gets 3 points in a plane
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    Plane(Point p1, Point p2, Point p3) {
        normal = null;
        p0 = p1;
    }

    /**
     * get the normal to the plane
     * @return the normal to the plane
     */
    public Vector getNormal() {
        return getNormal(p0);
    }

    /**
     * get the point in the plane
     * @return the point in the plane
     */
    public Point getP0() {
        return p0;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}