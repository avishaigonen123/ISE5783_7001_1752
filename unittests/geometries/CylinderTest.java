package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Cylinder
 * @author Ariel Zaken && Avishai Gonen
 */
public class CylinderTest {

    /** Test method for {@link Cylinder#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        Point center = new Point(1, 1, 1);
        Vector dir = new Vector(0, 1, 0);
        Ray ray = new Ray(center, dir);
        double radius = 2;
        double height = 4;
        Cylinder cylinder = new Cylinder(radius, ray, height);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir, and not on the bases.
        Point p0 = new Point(1, 2, 3);
        Vector vr = new Vector(0, 0, 1);
        Vector norm = cylinder.getNormal(p0);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr, norm, "getNormal() result is wrong!");
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");

        // TC02: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir, and on base A (with the center).
        Point p1 = new Point(1, 1, 2);
        vr = new Vector(0, -1, 0);
        norm = cylinder.getNormal(p1);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr,norm, "getNormal() result is wrong!");

        // TC03: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir, and on base B (without the center).
        Point p2 = new Point(1, 5, 0);
        vr = new Vector(0, 1, 0);
        norm = cylinder.getNormal(p2);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr,norm, "getNormal() result is wrong!");

        // =============== Boundary Values Tests ==================
        // TC11: Test that checks getNormal func for a state where the point is in the middle of base A (with the center)
        p1 = new Point(1,1,1);
        vr = new Vector(0, -1, 0);
        norm = cylinder.getNormal(p1);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr,norm, "getNormal() result is wrong!");

        // TC12: Test that checks getNormal func for a state where the point is in the middle of base B (without the center).
        p2 = new Point(1, 5, 1);
        vr = new Vector(0, 1, 0);
        norm = cylinder.getNormal(p2);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr, norm, "getNormal() result is wrong!");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections(){
       /* Cylinder cylinder = new Cylinder(4,new Ray(new Point(-2,0,0),new Vector(1,0,0)),5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the cylinder, and not orthogonal or parallel to the axis ray (0 points)
        assertNull(cylinder.findIntersections(new Ray(new Point(0,7,0), new Vector(1, 0, 1))),
                "Ray's line out of cylinder, TC01");
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
        p1 = new Point(0.5, 0, 0.866025403784439d);
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0),
                new Vector(0,0,1)));
        assertEquals(1, result.size(), "Wrong number of points, TC03");
        assertEquals(List.of(p1), result, "Wrong point, TC03");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2.5, 0, 0), new Vector(0.5,0,0))),
                "Ray's line out of sphere, TC04");

        // =============== Boundary Values Tests ==================
        // TC11:*/
    }
}