package primitives;

public class Ray {
    private final Point p0;
    private final Vector dir;

    Ray(Point p, Vector vec){
        p0=p;
        dir = vec.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }
}
