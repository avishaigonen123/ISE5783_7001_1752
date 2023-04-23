package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * class that represents sphere. this is a geometry object.
 */
public class Sphere extends RadialGeometry {
    final private Point center;

    /**
     * a constructor that initialize the cylinder
     *
     * @param _radius the radius
     * @param _center the center point of the sphere
     */
    Sphere(double _radius, Point _center) {
        super(_radius);
        center = _center;
    }

    /**
     * getter for center
     *
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        Vector v = p.subtract(this.center);
        return v.normalize();
    }

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if(center.equals(ray.getP0()))//if center and p0 are the same
            return List.of(center.add(ray.getDir().scale(radius))); // we return the p0+radius*v. BVA.
        Vector u = center.subtract(ray.getP0()); // calculate u
        double tm = Util.alignZero(ray.getDir().dotProduct(u));
        double d = Math.sqrt(u.lengthSquared() - tm * tm); // like the calculates in the slides
        if (Util.isZero(d - radius) || d > radius)
            return null;
        double th = Math.sqrt(radius*radius - d*d);
        double t1 = tm+th;
        double t2 = tm-th;
        if(t1 > 0 && t2 > 0) { // if they are both positive, we will return both points.
           return List.of(ray.getPoint(t1),
                   ray.getPoint(t2));
        }
        if(t1 > 0) { // if only one is positive, we will return only one point
            return List.of(ray.getPoint(t1));
        }
        if(t2 > 0) { // same
            return List.of(ray.getPoint(t2));
        }
        return null; // otherwise - null
    }
}
