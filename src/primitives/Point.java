package primitives;


import static java.lang.Math.sqrt;

/**
 * class to represent a point. primitive object.
 */
public class Point {
    /**
     * field that holds the coordinates of the point. we use Double3 in the project in order to reduce the DRY.
     */
    final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * public contructor that gets double3 o object and copies it to our field
     * @param _xyz the double3 we get
     */
    Point(Double3 _xyz) {
        xyz = _xyz;
    }

    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * return the distanceSquared from our point to the point we get
     * @param point the point we want to see the distance from
     * @return the distance between the points
     */
    public double distanceSquared(Point point) {
        return (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1) + (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1) + (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1);
    }

    /**
     * func that returns the distance between points
     * @param point the point we want to check the distance
     * @return the distance
     */
    public double distance(Point point) {
        return sqrt(distanceSquared(point));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
}
