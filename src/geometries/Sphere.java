package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class that represents sphere. this is a geometry object.
 */
public class Sphere extends RadialGeometry {
    final private Point center;

    /**
     * a constructor that initialize the cylinder
     * @param _radius the radius
     * @param _center the center point of the sphere
     */
    Sphere(double _radius, Point _center) {
        super(_radius);
        center = _center;
    }

    /**
     * getter for center
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
        return null;
    }
}
