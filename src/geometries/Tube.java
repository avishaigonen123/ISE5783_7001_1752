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

    /**
     * getter for axis ray
     * @return the axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * a constructor for tube
     * @param _radius the radius
     * @param _axisRay the axisRay
     */
    Tube(double _radius, Ray _axisRay) {
        super(_radius);
        axisRay = _axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
    }
