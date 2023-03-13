package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        assertEquals(norm.length(), 1,0.000001,"getNormal() result length isn't equals to 1!" );
        assertEquals(norm, vr,"getNormal() result is wrong!" );
        // assertThrows(IllegalArgumentException.class, () -> norm.crossProduct(vr),
        //        "crossProduct() vectors that are not on the same line does not throw an exception");

        // =============== Boundary Values Tests ==================
        // none
    }
}