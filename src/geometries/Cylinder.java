package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {

    final private double height;

    Cylinder(double _radius, Ray _axisRay, double _height) {
        super(_radius, _axisRay);
        height = _height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
