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
    private static final double DISTANCE = 0.2;

    static List<Point> superSampling( Ray ray, double area, int numOfRays) {
        LinkedList<Point> res = new LinkedList<>();
        Point pC = ray.getPoint(DISTANCE); // the center of the targetArea
        Random random = new Random();
        for (int i = 0; i < numOfRays; i++) {
            Ray randomRay = new Ray(pC,ray.getDir()).randomOrthogonalRay();


            double t = random.nextDouble() * area; // random values between 0 to radius
            res.add(randomRay.getPoint(t));
        }
        return res;
    }


}
