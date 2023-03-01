package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    final private Point center;

    Sphere(double _radius, Point _center) {
        super(_radius);
        center = _center;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
