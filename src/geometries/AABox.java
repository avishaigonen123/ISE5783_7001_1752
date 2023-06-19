package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * an Intersectable that represents an axes aliened box
 */
public class AABox extends Intersectable {
    /**
     * one of the corner points of the Axes Aliened Box
     */
    private Point A;
    /**
     * the opposite Point to Point A of the Axes Aliened Box
     */
    private Point B;

    public AABox(Point a, Point b) {
        A = a;
        B = b;
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double tx1 = (A.getX() - ray.getP0().getX())/ray.getDir().getX();
        double tx2 = (B.getX() - ray.getP0().getX())/ray.getDir().getX();

        double tmin = min(tx1, tx2);
        double tmax = max(tx1, tx2);

        double ty1 = (A.getY() - ray.getP0().getY())/ray.getDir().getY();
        double ty2 = (B.getY() - ray.getP0().getY())/ray.getDir().getY();

        tmin = max(tmin ,min(ty1, ty2));
        tmax = min(tmax,max(ty1, ty2));

        double tz1 = (A.getZ() - ray.getP0().getZ())/ray.getDir().getZ();
        double tz2 = (B.getZ() - ray.getP0().getZ())/ray.getDir().getZ();

        tmin = max(tmin ,min(tz1, tz2));
        tmax = min(tmax,max(tz1, tz2));

        return tmax >= tmin? new LinkedList<>():null;
    }
}
