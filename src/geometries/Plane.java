package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final private Point p0;
    final private Vector normal;

    Plane(Point _p0, Vector _normal) {
        p0 = _p0;
        normal = _normal.normalize();
    }

    Plane(Point p1, Point p2, Point p3) {
        normal = null;
        p0 = p1;
    }

    public Vector getNormal() {
        return getNormal(p0);
    }

    public Point getP0() {
        return p0;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
