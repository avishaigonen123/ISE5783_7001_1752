package renderer;

import geometries.Geometry;
import geometries.Polygon;
import primitives.Point;
import primitives.Util;
import primitives.Vector;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PictureImprover {
    /**
     * that is for the distance from the target plane to the point
     */
    static final double DISTANCE = 100;

    static List<Point> superSampling(Ray ray, double area, int numOfRays) {
        LinkedList<Point> res = new LinkedList<>();
        Point pC = ray.getPoint(DISTANCE); // the center of the targetArea
        if (area == 0)
            return List.of(pC);
        Random random = new Random();
        Ray r = new Ray(pC,ray.getDir());
        for (int i = 0; i < numOfRays; i++) {
            Ray randomRay = r.randomOrthogonalRay();
            if(randomRay == null)
                res.add(pC);
            else {
                double t = random.nextDouble() * area; // random values between 0 to radius
                
                if (!Util.isZero(t))
                    res.add(randomRay.getPoint(t));
                else
                    res.add(pC);
            }
        }
        return res;
    }


}