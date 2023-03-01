package geometries;

public abstract class RadialGeometry implements Geometry {
    final protected double radius;

    RadialGeometry(double _radius) {
        radius = _radius;
    }

    public double getRadius() {
        return radius;
    }
}
