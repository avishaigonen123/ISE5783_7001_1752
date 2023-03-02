package primitives;

import static java.lang.Math.sqrt;

/**
 * class that represent vector. a primitive object.
 */
public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        Double3 _xyz = new Double3(x, y, z);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }

    /**
     * constructor that gets double3 and initalize it
     * @param _xyz the double3 we get
     */
    Vector(Double3 _xyz) {
        super(_xyz);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }

    /**
     * this func add a vector to vector
     *
     * @param vec the vector we get
     * @return the result of the sum
     */
    public Vector add(Vector vec) {
        return new Vector((super.add(vec)).xyz);
    }

    /**
     * func that scale vector by a scalar
     * @param t the scalar we want to scale by
     * @return the scale of the vector and the scalar
     */
    public Vector scale(double t) {
        return new Vector((this.xyz.scale(t)));
    }

    /**
     * func that returns the dotProduct of two vectors
     * @param vec the other vec we get
     * @return a scalar that is the result of the dotProduct
     */
    public double dotProduct(Vector vec) {
        return vec.xyz.d1 * this.xyz.d1 + vec.xyz.d2 * this.xyz.d2 + vec.xyz.d3 * this.xyz.d3;
        // a1*b1 + a2*b2 + a3*b3
    }

    /**
     * func that calculates the crossProduct between two vectors
      * @param vec the other vector
     * @return a vector that is the crossProduct between the vectors
     */
    public Vector crossProduct(Vector vec) {
        return new Vector(this.xyz.d2 * vec.xyz.d3 - this.xyz.d3 * vec.xyz.d2, this.xyz.d3 * vec.xyz.d1 - this.xyz.d1 * vec.xyz.d3, this.xyz.d1 * vec.xyz.d2 - this.xyz.d2 * vec.xyz.d1);
    }

    /**
     * func that returns the lengthSquared of our vector
     * @return our vector lengthSquard
     */
    public double lengthSquared() {
        return this.dotProduct(this);
        // V*V == |V|^2
    }

    public double length() {
        return sqrt(lengthSquared());
        // sqrt( |V|^2 ) == |V|
    }

    /**
     * func that normalize the vector
     * @return our vector after we normalized it.
     */
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
