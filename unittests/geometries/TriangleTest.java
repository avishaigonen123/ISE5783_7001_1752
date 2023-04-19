package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Triangle
 * @author Ariel Zaken && Avishai Gonen
 */
public class TriangleTest {


    /** Test method for {@link Triangle#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        Triangle triangle = new Triangle(
                new Point(0,0,0),
                new Point(0,1,0),
                new Point(0,0,1)
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for every case in triangle
        Vector vr = new Vector(1,0,0);
        Point p0 = new Point(0,0,0);
        Vector norm = triangle.getNormal(p0);
        assertEquals(1, norm.length(),0.000001,"getNormal() result length isn't equals to 1!" );
        assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
                "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // none
    }

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections(){
        Triangle triangle = new Triangle(new Point(0,-1,0), new Point(0,0,1), new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: test case where the point is inside the triangle (1 point)
        Point p1 = new Point(0, 0, 0.5);
        List<Point> result = triangle.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(-1, 0, 0.5)));
        assertEquals(1, result.size(), "Wrong number of points, TC01");
        assertEquals(List.of(p1), result, "Wrong point, TC01");
        // TC02: test case where the point is outside against edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1,1,0.5))),
                "Ray's line out of triangle, TC02");
        // TC02: test case where the point is outside against vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1,0,2))),
                "Ray's line out of triangle, TC03");

        // =============== Boundary Values Tests ==================
        // TC11: test case where the ray begins before the plane and on edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1,1,0))),
                "Ray's line out of triangle, TC11");
        // TC12: test case where the ray begins before the plane and in vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1,0,5))),
                "Ray's line out of triangle, TC12");
        // TC13: test case where the ray begins before the plane and on edge's continuation (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1,2,0))),
                "Ray's line out of triangle, TC13");
    }
}