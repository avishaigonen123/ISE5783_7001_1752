package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * class the implements the composite design pattern
 */
public class Geometries extends Intersectable {
    private List<Intersectable> geometriesList;

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
        geometriesList = new LinkedList<Intersectable>();
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
         //   if (geometry.getBox().findGeoIntersectionsHelper(ray) != null) {
                List<GeoPoint> temp = geometry.findGeoIntersections(ray); // the points from the intersections. "draw()"
                if (temp != null) {
                    if (list == null) list = new LinkedList<>();
                    list.addAll(temp);
                }
         //   }
        }
        return list; // if the list is empty, it will remain being null.
    }
}
