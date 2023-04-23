package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList;

    public Geometries() {
        geometriesList = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        geometriesList = new LinkedList<Intersectable>();
        geometriesList.addAll(Arrays.asList(geometries));
    }

    public void add(Intersectable... geometries) {
        geometriesList.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
