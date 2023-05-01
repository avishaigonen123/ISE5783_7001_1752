package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to represent a 2D Triangle in 3D
 */
public class Triangle extends Polygon{
    /**
     * constructor for building the triangle with 3 points
     * @param p0 point 1
     * @param p1 point 2
     * @param p2 point 3
     */
    public Triangle(Point p0, Point p1, Point p2){
        super(p0,p1,p2);
    }

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> list = plane.findIntersections(ray); // same as polygon - but only without the loop.
        if(list==null)
            return null;
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        if((ray.getDir().dotProduct(n1)<0 && ray.getDir().dotProduct(n3)<0 && ray.getDir().dotProduct(n2)<0) ||
                (ray.getDir().dotProduct(n1)>0 && ray.getDir().dotProduct(n3)>0 && ray.getDir().dotProduct(n2)>0))
                return list;
        return null;

    }
}
