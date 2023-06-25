package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * interface that implements all the geometries that can be intersected
 */
public abstract class Intersectable {
    private AABox AABB = null;
    /**
     * helper class to tell to which geometry the point belongs to
     */
    public static class GeoPoint {
        /**
         * the geometry
         */
        public Geometry geometry;
        /**
         * the point
         */
        public Point point;

        /**
         * constructor for geopoint class
         *
         * @param _geo the geometry
         * @param _p   the point
         */
        public GeoPoint(Geometry _geo, Point _p) {
            geometry = _geo;
            point = _p;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof GeoPoint other)
                return this.point.equals(other.point) && this.geometry.equals(other.geometry);
            return false;
        }

        @Override
        public String toString() {
            return "geometry: " + geometry.toString() + " point: " + point.toString();
        }
    }

    /**
     * function that returns list of intersections with the geometry and the given ray
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * function that returns list of intersections with the geometry and the given ray in geopoint
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections in geopoint
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //if (this.getBox() == null || this.getBox().findGeoIntersectionsHelper(ray) != null)
            return findGeoIntersectionsHelper(ray);
        //return null;
    }

    /**
     * a kind of NVI pattern for getting the box and not calculating it twice
     * @return the axes aligned bounding box of the Intersectable
     */
    public AABox getBox(){
        if(AABB == null)
            AABB = getBoxHelper();
        return AABB;
    }

    /**
     * Helper func to be implemented by son
     *
     * @param ray the ray we want to intersect with
     * @return the list of the intersections in geopoint
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Helper func to be implemented by son for building the AABB
     * @return the Box
     */
    protected abstract AABox getBoxHelper();


}
