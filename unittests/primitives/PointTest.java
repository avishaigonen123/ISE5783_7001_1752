package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Point
 * @author Ariel Zaken && Avishai Gonen
 */
public class PointTest {

     /** Test method for {@link primitives.Point#subtract(Point)}. */
    @Test
    void testSubtract() {
        Point p1 = new Point(0, 0, 5);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks subtraction between points that are not the same
        Point p2 = new Point(0,0,0);
        Vector vr = new Vector(0,0,5);
        assertEquals(vr,p1.subtract(p2), "subtract() wrong result in points that are not the same");

        // =============== Boundary Values Tests ==================
        // TC11: Test that checks subtraction between points that are the same
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "subtract() points that are the same does not throw an exception");
    }


    /** Test method for {@link primitives.Point#add(Vector)}}. */
    @Test
    void testAdd() {
        Point p1 = new Point(0, 0, 5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks add with anything
        Vector v1 = new Vector(1,0,0);
        Point pr = new Point(1,0,5);
        assertEquals(pr,p1.add(v1), "add() wrong result!");

        // =============== Boundary Values Tests ==================
        // none
    }


    /** Test method for {@link primitives.Point#distanceSquared(Point)}}. */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(0, 0, 5);
        Point p2 = new Point(0,0,0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks distanceSquared with anything
        double d = 25;
        assertEquals(d,p1.distanceSquared(p2),0.000001, "distanceSquared() wrong result!");

        // =============== Boundary Values Tests ==================
        // none
    }

    /** Test method for {@link primitives.Point#distance(Point)}}. */
    @Test
    void testDistance() {
        Point p1 = new Point(0, 0, 5);
        Point p2 = new Point(0,0,0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks distance with anything
        double d = 5;
        assertEquals(d,p1.distance(p2),0.000001, "distance() wrong result!");

        // =============== Boundary Values Tests ==================
        // none
    }
}