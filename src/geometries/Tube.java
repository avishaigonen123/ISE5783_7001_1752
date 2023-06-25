package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * a class to represent an infinite Tube from a certain point
 */
public class Tube extends RadialGeometry {
    /**
     * a ray for the axes
     */
    final protected Ray axisRay;

    /**
     * getter for axis ray
     *
     * @return the axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * a constructor for tube
     *
     * @param _radius  the radius
     * @param _axisRay the axisRay
     */
    Tube(double _radius, Ray _axisRay) {
        super(_radius);
        axisRay = _axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = this.axisRay.getP0();
        Vector v = this.axisRay.getDir();
        double t = v.dotProduct(p.subtract(p0));
        Point o = p0;
        if(!Util.isZero(t))
            o = p0.add(v.scale(t));
        v = p.subtract(o);
        return v.normalize();
    }

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    /**
     * function that returns the boundry box
     *
     * @return the boundery box
     */
    @Override
    public AABox getBoxHelper() { return null;}
}
