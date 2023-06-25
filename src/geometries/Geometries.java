package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * class the implements the composite design pattern
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometriesList;
    /**
     * default public constructor for Geomertries
     */
    public Geometries() {
        geometriesList = new LinkedList<Intersectable>();
    }

    /**
     * public constructor that gets intersectables.
     *
     * @param geometries the intersectables to be initialized with
     */
    public Geometries(Intersectable... geometries) {
        geometriesList = new LinkedList<>();
        geometriesList.addAll(Arrays.asList(geometries));
    }

    /**
     * public void func that add intersectables to the list
     *
     * @param geometries the intersectables to add
     */
    public void add(Intersectable... geometries) {
        geometriesList.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> list = null; // we don't initialize it until we see there are points
        for (Intersectable geometry : geometriesList) {
            List<GeoPoint> temp = geometry.findGeoIntersections(ray); // the points from the intersections. "draw()"
            if (temp != null) {
                if (list == null) list = new LinkedList<>();
                list.addAll(temp);
            }
        }
        return list; // if the list is empty, it will remain being null.
    }

    /**
     * function that returns the boundary box for geometries this enables BVH
     *
     * @return the boundary box
     */
    @Override
    public AABox getBoxHelper() {
        double x_min = Double.POSITIVE_INFINITY;
        double y_min = Double.POSITIVE_INFINITY;
        double z_min = Double.POSITIVE_INFINITY;
        double x_max = Double.NEGATIVE_INFINITY;
        double y_max = Double.NEGATIVE_INFINITY;
        double z_max = Double.NEGATIVE_INFINITY;
        Point min_tmp, max_tmp;
        AABox box;
        for (Intersectable geometry : geometriesList) {
            box = geometry.getBox();
            min_tmp = box.getMin();
            max_tmp = box.getMax();

            x_max = max(max_tmp.getX(), x_max);
            y_max = max(max_tmp.getY(), y_max);
            z_max = max(max_tmp.getZ(), z_max);
            x_min = min(min_tmp.getX(), x_min);
            y_min = min(min_tmp.getY(), y_min);
            z_min = min(min_tmp.getZ(), z_min);
        }
        return new AABox(new Point(x_max, y_max, z_max), new Point(x_min, y_min, z_min));
    }


}
