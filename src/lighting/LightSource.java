package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for lightSources
 */
public interface LightSource {
    /**
     * func that returns intensity in a point
     * @param p the point
     * @return the intensity
     */
    Color getIntensity(Point p);

    /**
     * func that returns the vector of the direction of the light
      * @param p the point
     * @return the vector
     */
    Vector getL(Point p);
    /**
     * the distance from the light source to the point
     * @param point the point
     * @return the distance
     */
    double getDistance(Point point);
}
