package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tube
 *
 * @author Ariel Zaken && Avishai Gonen
 */
public class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point center = new Point(1, 1, 1);
        Vector dir = new Vector(0, 1, 0);
        Ray ray = new Ray(center, dir);
        double radius = 2;
        Tube tube = new Tube(radius, ray);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for a state where the vector from center to p0 isn't orthogonal to dir
        Point p0 = new Point(1, 2, 3);
        Vector vr = new Vector(0, 0, 1);
        Vector norm = tube.getNormal(p0);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr,norm, "getNormal() result is wrong!");
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // TC11: Test that checks getNormal func for a state where the vector from center to p0 is orthogonal to dir
        p0 = new Point(1, 1, 3);
        vr = new Vector(0, 0, 1);
        norm = tube.getNormal(p0);
        assertEquals(1, norm.length(), 0.000001, "getNormal() result length isn't equals to 1!");
        assertEquals(vr,norm, "getNormal() result is wrong!");
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");
    }

    /**
     * Test method for {@link Tube#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections(){
        // ============ Equivalence Partitions Tests ==============
        // TC01

        // =============== Boundary Values Tests ==================
        // TC11:
    }
}