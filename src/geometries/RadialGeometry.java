package geometries;

public abstract class RadialGeometry implements Geometry {
    /**
     * the radius of the geometry
     */
    final protected double radius;

    RadialGeometry(double _radius) {
        radius = _radius;
    }

    /**
     * getter for radius
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }
}
