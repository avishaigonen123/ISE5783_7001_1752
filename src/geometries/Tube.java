package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
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
