package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane
 * @author Ariel Zaken && Avishai Gonen
 */
public class PlaneTest {

    /** Test method for {@link Plane#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        Plane plane = new Plane(
                new Point(0,0,0),
                new Point(0,1,0),
                new Point(0,0,1)
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks getNormal func for every case in plane
        Vector vr = new Vector(1,0,0);
        Point p0 = new Point(0,0,0);
        Vector norm = plane.getNormal(p0);
        assertEquals(1, norm.length(),0.000001,"getNormal() result length isn't equals to 1!" );
        assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
                "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // none
    }
 }