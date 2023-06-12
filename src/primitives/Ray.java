package primitives;

import java.util.List;
import java.util.Random;

import geometries.Intersectable.GeoPoint;

/**
 * class that represents ray. a primitive object.
 */
public class Ray {
    /**
     * we will move the ray of the shadow in the normal direction inorder to not intersect ourselves
     */
    private static final double DELTA = 0.1;
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
     * constructor for refactor, for create a ray
     * @param v the vector to be moved
     * @param n the normal vector by
     * @param gp the point in which we moved
     */
    public Ray(Vector v, Vector n, GeoPoint gp){
        Vector normalInDirMoved = n.scale(n.dotProduct(v) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(normalInDirMoved);
        p0=point;
        dir=v;
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

    /**
     * refactoring func that calc the point. p = p0+v*t.
     * @param t
     * @return the calculated point.
     */
    public Point getPoint(double t){
        if(Util.isZero(t))
            return p0;
        return (p0.add(dir.scale(t)));
    }

    /**
     * the func returns the closest point from the list to the ray
     * @param points the list
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * the func returns the closest point from the list to the ray
     * @param list the list
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> list){
        if(list==null||list.isEmpty()) // if the list is empty / null
            return null;
        int minIndex = 0; // the min index
        double minDistance = list.get(0).point.distance(p0);
        double newDistance = Double.MAX_VALUE; // the newDistance is inf
        int size = list.size();
        for(int i=1;i<size; i++) {
            newDistance =list.get(i).point.distance(p0); // we try to make it more efficient.
            if (newDistance < minDistance) {
                minDistance = newDistance;
                minIndex = i;
            }
        }
        return list.get(minIndex); // the min index
    }

    /**
     * func that gets ray and returns an orthogonal ray, that starts at the point it gets
     * @return the orthogonal ray
     */
    public Ray randomOrthogonalRay() {
        Vector vector = this.getDir();
        Point point = this.getP0();

        // Generate random values for the orthogonal vector components
        Random random = new Random();
        double x = random.nextDouble();
        double y = random.nextDouble();
        double z = random.nextDouble();
        if(Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
            return null;
        Vector vector1 = new Vector(x,y,z);

        // Calculate the dot product between the original vector and the orthogonal vector
        double dotProduct = vector1.dotProduct(vector);

        // Subtract the projection of the original vector from the orthogonal vector
        x -= dotProduct * vector.getX();
        y -= dotProduct * vector.getY();
        z -= dotProduct * vector.getZ();
        Vector v = new Vector(x,y,z);

        return new Ray(point, v);
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
