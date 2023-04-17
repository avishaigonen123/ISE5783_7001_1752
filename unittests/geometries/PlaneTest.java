package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
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

    /** Test method for {@link Plane#Plane(Point,Point,Point)}. */
    @Test
    void testConstructor(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks the constructor without merging points and no points on the same line
        try {
            new Plane(
                    new Point(0, 0, 0),
                    new Point(0, 1, 0),
                    new Point(0, 0, 1)
            );
        }
        catch (IllegalArgumentException e){
            fail("Failed constructing a correct Plain in constructor TC01");
        }
        // =============== Boundary Values Tests ==================
        // TC10: test case that checks the constructor with the same points in the first and the second parameters
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)), //
                "Constructed a Plain with the same two first points");
        // TC11: test case that checks the constructor with all the points in the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)), //
                "Constructed a Plain with all the points in the same line");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections(){
        // ============ Equivalence Partitions Tests ==============
        // TC01

        // =============== Boundary Values Tests ==================
        // TC11:
    }
 }