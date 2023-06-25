package geometries;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   private final int           size;

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the 
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }


   /**
    * function that returns list of intersections with the geometry and the given ray
    *
    * @param ray the ray we want to intersect with
    * @return the list of the intersections
    */

   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
      List<GeoPoint> list = plane.findGeoIntersections(ray); // check if there is intersection point
      if(list==null)
         return null;
      Vector v1 = vertices.get(0).subtract(ray.getP0()); // get the first vector
      boolean pos = true,neg = true; // flags that indicates whether the sign is the same on all the dotProducts or not
      for (int i = 0;i<vertices.size();i++) {
         Vector vi = vertices.get((i+1)%vertices.size()).subtract(ray.getP0()); // (i+1)%size because we want that in the end we will take v1 and vn.
         double nv = ray.getDir().dotProduct((v1.crossProduct(vi)).normalize()); // nv indicates the sign of the dotProduct.
         v1 = vi;
         if (i == 0) { // if this is the first loop, we need to turn on the flags
            neg = nv<0;
            pos = !neg;
         }
         if(pos && nv<0 || neg && nv>0 || Util.isZero(nv)) // if the sign isn't the same, we can't jump out of the func
            return null;
      }
      list.get(0).geometry = this;
      return list; // after all, we will return the list
   }
   /**
    * function that returns the boundry box
    *
    * @return the boundery box
    */
   @Override
   public AABox getBoxHelper() {
      double x_max = Double.NEGATIVE_INFINITY, y_max = Double.NEGATIVE_INFINITY, z_max = Double.NEGATIVE_INFINITY, x_min = Double.POSITIVE_INFINITY, y_min = Double.POSITIVE_INFINITY, z_min = Double.POSITIVE_INFINITY;
      for (Point point : vertices) {
         x_max = max(point.getX(),x_max);
         y_max = max(point.getY(),y_max);
         z_max = max(point.getZ(),z_max);
         x_min = min(point.getX(),x_min);
         y_min = min(point.getY(),y_min);
         z_min = min(point.getZ(),z_min);
      }
      return new AABox(new Point(x_max, y_max, z_max), new Point(x_min, y_min, z_min));
   }
}
