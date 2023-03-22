package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * interface that implements all the geometries that can be intersected
 */
public interface Intersectable {
    /**
     * function that returns list of intersections with the geometry and the given ray
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    List<Point> findIntersections(Ray ray);

}
