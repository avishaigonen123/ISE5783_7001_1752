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
    private static final double DISTANCE = 5;

    static List<Point> superSampling( Ray ray, double area, int numOfRays) {
        LinkedList<Point> res = new LinkedList<Point>();
        Point pC = ray.getPoint(DISTANCE); // the center of the targetArea
        Random random = new Random();
        for (int i = 0; i < numOfRays; i++) {
            Ray randomRay = randomOrthogonalRay(ray.getDir(), pC);

            double t = random.nextDouble() * area; // random values between 0 to radius
            res.add(randomRay.getPoint(t));
        }
        return res;
    }

    static private Ray randomOrthogonalRay(Vector vector, Point point) {
        double a = vector.getX(), b = vector.getY(), c = vector.getZ();
        double x1 = point.getX(), x2 = point.getY(), x3 = point.getZ();
        double d = a * x1 + b * x2 + c * x3; // x1*a + x2*b + x3*c = d
        Random random = new Random();
        double x = random.nextDouble()*1000;
        double y = random.nextDouble()*1000;
        double z = (d - (x * a + y * b)) / c; // x*a + y*b + z*c = d
        Vector v = new Vector(x, y, z);

  //      if(!Util.isZero(v.dotProduct(vector)))
  //      { x = v.dotProduct(vector);
  //          a = 3;}
        return new Ray(point, v);
    }
}
