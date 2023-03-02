package geometries;

/**
 * abstract class that help us to present all the geometries that has radius
 */
public abstract class RadialGeometry implements Geometry {
    final protected double radius;

    /**
     * constructor that get radius and initialize it
     * @param _radius the radius we get
     */
    RadialGeometry(double _radius) {
        radius = _radius;
    }

    public double getRadius() {
        return radius;
    }
}
