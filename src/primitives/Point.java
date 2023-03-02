package primitives;


import static java.lang.Math.sqrt;

public class Point {
    final Double3 xyz;

    /**
     * public constructor that sets 3 double to xyz
     * @param x cordinat 1
     * @param y cordinat 2
     * @param z cordinat 3
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    Point(Double3 _xyz) {
        xyz = _xyz;
    }

    /**
     * subtract two points
     * @param point the other point
     * @return Vector from this point to that point.
     */
    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * creats a new point that is this point moved by a vector
     * @param vector the vector to move by
     * @return new point by the vector
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    public double distanceSquared(Point point) {
        return (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1) + (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1) + (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1);
    }

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
