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
            return List.of(center.add(ray.getDir().scale(radius)));
        Vector u = center.subtract(ray.getP0());
        double tm = Util.alignZero(ray.getDir().dotProduct(u));
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (Util.isZero(d - radius) || d > radius)
            return null;
        double th = Math.sqrt(radius*radius - d*d);
        double t1 = tm+th;
        double t2 = tm-th;
        if(t1 > 0 && t2 > 0) {
           return List.of(ray.getP0().add(ray.getDir().scale(t1)),
                   ray.getP0().add(ray.getDir().scale(t2)));
        }
        if(t1 > 0) {
            return List.of(ray.getP0().add(ray.getDir().scale(t1)));
        }
        if(t2 > 0) {
            return List.of(ray.getP0().add(ray.getDir().scale(t2)));
        }
        return null;
    }
}
