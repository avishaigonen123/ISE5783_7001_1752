package primitives;

/**
 * class that represents ray. a primitive object.
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * constructor that gets Point and direction Vector (saved as normalized)
     * @param p the point to start
     * @param vec the direction
     */
    public Ray(Point p, Vector vec) {
        p0 = p;
        dir = vec.normalize();
    }

    /**
     * getter for p0
     * @return the field p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for dir
     * @return the field dir
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }
    @Override
    public String toString() { return "point: " + p0.toString() + " vector: " + dir.toString();}
}
