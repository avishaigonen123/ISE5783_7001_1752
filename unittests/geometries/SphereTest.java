package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Sphere
 * @author Ariel Zaken && Avishai Gonen
 */
public class SphereTest {


    /** Test method for {@link Sphere#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        Point center= new Point(1,1,1);
        double radius = 2;
        Sphere sphere = new Sphere(radius, center);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for every case in sphere
        Point p0 = new Point(1,1,3);
        Vector vr = new Vector(0,0,1);
        Vector norm = sphere.getNormal(p0);
        assertEquals(1, norm.length(),0.000001,"getNormal() result length isn't equals to 1!" );
        assertEquals(vr,norm,"getNormal() result is wrong!" );
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // none
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point (1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere, TC01");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points, TC02");
        if (result.get(0).getX() > result.get(1).getX())
             result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere, TC02");
        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point(0.5, 0, 0.87);
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0),
                new Vector(0,0,1)));
        assertEquals(1, result.size(), "Wrong number of points, TC03");
        assertEquals(List.of(p1), result, "Wrong point, TC03");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2.5, 0, 0), new Vector(0.5,0,0))),
                "Ray's line out of sphere, TC04");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        p1 = new Point(0.4,0,0.8);
        result = sphere.findIntersections(new Ray(new Point(2,0,0),
                new Vector(-1,0,0.5)));
        assertEquals(1, result.size(), "Wrong number of points, TC11");
        assertEquals(List.of(p1), result, "Wrong point, TC11");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1,0,0.5))),
                "Ray's line out of sphere, TC12");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(0, 0, 0);
        p2 = new Point(2, 0, 0);
        result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(2,0,0)));
        assertEquals(2, result.size(), "Wrong number of points, TC13");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere, TC13");
        // TC14: Ray starts at sphere and goes inside (1 point)
        p1 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(new Point(0,0,0),
                new Vector(2,0,0)));
        assertEquals(1, result.size(), "Wrong number of points, TC14");
        assertEquals(List.of(p1), result, "Wrong point, TC14");
        // TC15: Ray starts inside (1 point)
        p1 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(new Point(0.5,0,0),
                new Vector(2,0,0)));
        assertEquals(1, result.size(), "Wrong number of points, TC15");
        assertEquals(List.of(p1), result, "Wrong point, TC15");
        // TC16: Ray starts at the center (1 point)
        p1 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(new Point(1,0,0),
                new Vector(2,0,0)));
        assertEquals(1, result.size(), "Wrong number of points, TC16");
        assertEquals(List.of(p1), result, "Wrong point, TC16");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(2,0,0))),
                "Ray's line out of sphere, TC17");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(2,0,0))),
                "Ray's line out of sphere, TC18");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2,1,0), new Vector(0,-1,0))),
                "Ray's line out of sphere, TC19");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2,0,0), new Vector(0,-1,0))),
                "Ray's line out of sphere, TC20");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2,-1,0), new Vector(0,-1,0))),
                "Ray's line out of sphere, TC21");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3,0,0), new Vector(0,-1,0))),
                "Ray's line out of sphere, TC22");
    }
}