package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * class the implements the composite design pattern
 */
public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList;

    /**
     * default public constructor for Geomertries
     */
    public Geometries() {
        geometriesList = new LinkedList<Intersectable>();
    }

    /**
     * public constructor that gets intersectables.
     * @param geometries the intersectables to be initialized with
     */
    public Geometries(Intersectable... geometries) {
        geometriesList = new LinkedList<Intersectable>();
        geometriesList.addAll(Arrays.asList(geometries));
    }

    /**
     * public void func that add intersectables to the list
     * @param geometries the intersectables to add
     */
    public void add(Intersectable... geometries) {
        geometriesList.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> list = null; // we don't initialize it until we see there are points
        boolean hasBeenCreated = false; // flag to help us
        for (Intersectable geometry: geometriesList) {
            List<Point> temp = geometry.findIntersections(ray); // the points from the intersections. "draw()"
            if(!hasBeenCreated && (temp!=null)) { // if we hadn't initialized list yet, we will initialize it now
                list = new ArrayList<Point>();
                hasBeenCreated = true;
            }
            if(hasBeenCreated && (temp!=null)) // if the list isn't empty, we will add the points
                list.addAll(temp);
        }
        return list; // if the list is empty, it will remain being null.
    }
}
