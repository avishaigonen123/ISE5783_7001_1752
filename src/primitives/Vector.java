package primitives;

import static java.lang.Math.sqrt;

public class Vector extends Point {
    /**
     * public constructor that sets 3 double to xyz
     *
     * @param x cordinat 1
     * @param y cordinat 2
     * @param z cordinat 3
     * @throws IllegalArgumentException if the result is vector 0
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        Double3 _xyz = new Double3(x, y, z);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }

    /**
     * public constructor that sets 3 double to xyz
     *
     * @param _xyz the dubel3 to be set.
     * @throws IllegalArgumentException if the result is vector 0
     */
    Vector(Double3 _xyz) {
        super(_xyz);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }

    public Vector add(Vector vec) {
        Point point = super.add(vec);
        return new Vector(point.xyz);
    }

    public Vector scale(double t) {
        return new Vector((this.xyz.scale(t)));
    }

    public double dotProduct(Vector vec) {
        return vec.xyz.d1 * this.xyz.d1 + vec.xyz.d2 * this.xyz.d2 + vec.xyz.d3 * this.xyz.d3;
    }

    public Vector crossProduct(Vector vec) {
        return new Vector(this.xyz.d2 * vec.xyz.d3 - this.xyz.d3 * vec.xyz.d2, this.xyz.d3 * vec.xyz.d1 - this.xyz.d1 * vec.xyz.d3, this.xyz.d1 * vec.xyz.d2 - this.xyz.d2 * vec.xyz.d1);
        /*
        a = this
        b = Vec

        x = a2*b3-a3*b2
        y = a3*b1-a1*b3
        z = a1*b2-a2*b1
         */
    }


    public double lengthSquared() {
        return this.dotProduct(this);
    }
    /**
     * func that calculates the length of the vector
     * @return returns the length daa
     */
    public double length() {
        return sqrt(lengthSquared());
    }

    public Vector normalize() {
        return scale(1 / length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return super.equals(obj);
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
