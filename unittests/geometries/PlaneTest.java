package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane
 *
 * @author Ariel Zaken && Avishai Gonen
 */
public class PlaneTest {

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Plane plane = new Plane(
                new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for every case in plane
        Vector vr = new Vector(1, 0, 0);
        Point p0 = new Point(0, 0, 0);
        Vector norm = plane.getNormal(p0);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
                "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // none
    }

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks the constructor without merging points and no points on the same line
        try {
            new Plane(
                    new Point(0, 0, 0),
                    new Point(0, 1, 0),
                    new Point(0, 0, 1)
            );
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plain in constructor, TC01");
        }
        // =============== Boundary Values Tests ==================
        // TC10: test case that checks the constructor with the same points in the first and the second parameters
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)), //
                "Constructed a Plain with the same two first points, TC10");
        // TC11: test case that checks the constructor with all the points in the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)), //
                "Constructed a Plain with all the points in the same line, TC11");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(0, 1, 0), new Vector(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: test case where the ray must be neither orthogonal nor parallel to the plane, checks where it intersects. (1 point)
        Point p1 = new Point(0, -2, 0);
        List<Point> result = plane.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(-0.5, -1, 0)));
        assertEquals(1, result.size(), "Wrong number of points, TC01");
        assertEquals(List.of(p1), result, "Wrong point, TC01");
        // TC02: test case where the ray must be neither orthogonal nor parallel to the plane, checks where it doesn't intersect. (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0.5, -1, 0))),
                "Ray's line out of plane, TC02");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray is parallel to the plane
        // TC11: test case where the ray included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0.5, 0))),
                "Ray is included in the plane, TC11");
        // TC12: test case where the ray isn't included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0.5, 0))),
                "Ray is out of the plane, TC12");

        // **** Group: Ray is orthogonal to the plane
        // TC13: test case where the ray is before the plane (1 point)
        p1 = new Point(0, 0, 0);
        result = plane.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points, TC13");
        assertEquals(List.of(p1), result, "Wrong point, TC13");
        // TC14: test case where the ray is starts from the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0))),
                "Ray starts from the plane, TC14");
        // TC15: test case where the ray is after the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))),
                "Ray is out of the plane, TC15");

        // **** Group: Some other cases
        // TC16: test case where ray is neither orthogonal nor parallel to and begins at the plane (p0 is in the plane, but not the ray) (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 1))),
                "Ray starts in the plane, TC16");
        // TC17: test case where ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q) (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 1))),
                "Ray starts in the plane, TC17");

    }
}