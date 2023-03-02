package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * a class to represent an infinite Tube from a certain point
 */
public class Tube extends RadialGeometry {
    /**
     * a ray for the axes
     */
    final protected Ray axisRay;

    public Ray getAxisRay() {
        return axisRay;
    }

    Tube(double _radius, Ray _axisRay) {
        super(_radius);
        axisRay = _axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
    }
