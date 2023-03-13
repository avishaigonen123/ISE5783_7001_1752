package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        assertEquals(norm.length(), 1, 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(norm, vr, "getNormal() result is wrong!");
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");

        // TC02: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir, and on base A (with the center).
        Point p1 = new Point(1, 1, 2);
        vr = new Vector(0, -1, 0);
        norm = cylinder.getNormal(p1);
        assertEquals(norm.length(), 1, 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(norm, vr, "getNormal() result is wrong!");

        // TC03: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir, and on base B (without the center).
        Point p2 = new Point(1, 5, 0);
        vr = new Vector(0, 1, 0);
        norm = cylinder.getNormal(p2);
        assertEquals(norm.length(), 1, 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(norm, vr, "getNormal() result is wrong!");

        // =============== Boundary Values Tests ==================
        // TC11: Test that checks getNormal func for a state where the point is in the middle of base A (with the center)
        p1 = new Point(1,1,1);
        vr = new Vector(0, -1, 0);
        norm = cylinder.getNormal(p1);
        assertEquals(norm.length(), 1, 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(norm, vr, "getNormal() result is wrong!");

        // TC12: Test that checks getNormal func for a state where the point is in the middle of base B (without the center).
        p2 = new Point(1, 5, 1);
        vr = new Vector(0, 1, 0);
        norm = cylinder.getNormal(p2);
        assertEquals(norm.length(), 1, 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(norm, vr, "getNormal() result is wrong!");
    }
}