package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface that represents all the geometries objects in our project
 */
public interface Geometry {
   public Vector getNormal(Point point);
}
