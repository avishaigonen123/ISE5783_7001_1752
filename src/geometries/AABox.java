package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * an Intersectable that represents an axes aliened box
 */
public class AABox extends Intersectable {
    /**
     * one of the corner points of the Axes Aliened Box
     */
    private Point max;
    /**
     * the opposite Point to Point A of the Axes Aliened Box
     */
    private Point min;

    public AABox(Point _max, Point _min) {
        max = (_max.getX() >= _min.getX() && _max.getY() >= _min.getY() && _max.getZ() >= _min.getZ()) ? _max : _min;
        min = max.equals(_max) ? _min : _max;
    }

    public Point getMax() {
        return max;
    }

    public Point getMin() {
        return min;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        double t1 = (min.getX() - ray.getP0().getX()) / ray.getDir().getX();
        double t2 = (max.getX() - ray.getP0().getX()) / ray.getDir().getX();
        double t3 = (min.getY() - ray.getP0().getY()) / ray.getDir().getY();
        double t4 = (max.getY() - ray.getP0().getY()) / ray.getDir().getY();
        double t5 = (min.getZ() - ray.getP0().getZ()) / ray.getDir().getZ();
        double t6 = (max.getZ() - ray.getP0().getZ()) / ray.getDir().getZ();

        double tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
        double tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));


        return tmax >= tmin && tmax >= 0 ? new LinkedList<>() : null;
    }

    /**
     * function that returns the boundry box
     *
     * @return the boundery box
     */
    @Override
    public AABox getBox() {
        return this;
    }
}
