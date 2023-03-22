package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        // ============ Equivalence Partitions Tests ==============
        // TC01

        // =============== Boundary Values Tests ==================
        // TC11:
    }
}