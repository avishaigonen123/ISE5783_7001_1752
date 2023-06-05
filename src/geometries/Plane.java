package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * a class that represent a 3d plane
 */
public class Plane extends Geometry {
    final private Point p0;
    final private Vector normal;

    /**
     * constructor that gets a point in the plane and a normal to the plain
     * @param _p0 the point
     * @param _normal the vector normal
     */
    public Plane(Point _p0, Vector _normal) {
        p0 = _p0;
        normal = _normal.normalize();
    }

    /**
     * constructor that gets 3 points in a plane
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
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
        return normal;
    }

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if(Util.isZero(ray.getDir().dotProduct(normal)) || p0.equals(ray.getP0())) // if the ray is parallel or inside the plane, return null
            return null;
        double t = (normal.dotProduct(p0.subtract(ray.getP0())))/(normal.dotProduct(ray.getDir())); // calculate t
        return (t<0||Util.isZero(t))?null:List.of(new GeoPoint(this,ray.getPoint(t))); // if t<=0. return null. other ways, use the func getPoint(t) and return the desire point
    }

}
