package geometries;

import primitives.Point;
import primitives.Vector;

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
}
