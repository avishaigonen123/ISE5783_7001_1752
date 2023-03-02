package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
   /**
    * a func to be implemented to get the normal in a point
    * assumes that the point is on the surface
    * @param point the point
    * @return the normal to the surface
    */
   public Vector getNormal(Point point);
}
