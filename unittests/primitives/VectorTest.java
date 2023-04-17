package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Vector
 * @author Ariel Zaken && Avishai Gonen
 */
public class VectorTest {
    /** Test method for {@link primitives.Vector#add(Vector)}. */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that checks add func with angle that is smaller then 90
        Vector v2 = new Vector(0, 3, 0);
        Vector vr = new Vector(1,5,3);
        assertEquals( vr,v1.add(v2), "add() wrong result in less then 90 degrees");

        // TC02: Test that checks add func with angle that is smaller then 90
        v2 = new Vector(0,3,-7);
        vr = new Vector(1,5,-4);
        assertEquals( vr,v1.add(v2), "add() wrong result in more then 90 degrees");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from add on contrary vectors
        Vector v3 = new Vector(-2, -4, -6);
        Vector v4 = new Vector(2,4,6);
        assertThrows(IllegalArgumentException.class, () -> v3.add(v4),
            "add() for same length and 180 degrees does not throw an exception");

    }
    /** Test method for {@link primitives.Vector#subtract(Point)}. */
    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that checks subtract func with angle that is smaller then 90
        Vector v2 = new Vector(0, 3, 0);
        Vector vr = new Vector(1,-1,3);
        assertEquals( vr,v1.subtract(v2), "subtract() wrong result in less then 90 degrees");

        // TC02: Test that checks subtract func with angle that is smaller then 90
        v2 = new Vector(0,3,-7);
        vr = new Vector(1,-1,10);
        assertEquals( vr,v1.subtract(v2), "subtract() wrong result in more then 90 degrees");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from subtract on the same vectors
        Vector v3 = new Vector(2, 4, 6);
        Vector v4 = new Vector(2,4,6);
        assertThrows(IllegalArgumentException.class, () -> v3.subtract(v4),
                "subtract() for same length and 180 degrees does not throw an exception");

    }
    /** Test method for {@link primitives.Vector#scale(double)}. */
    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 3);
        double t = 2;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks scale with anything that isn't 0
        Vector vr = new Vector(2, 4, 6);
        assertEquals(vr, v1.scale(t), "scale() wrong result in no 0");

        // =============== Boundary Values Tests ==================
        // TC11: test that checks scale with 0
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
                "scale() for scale with 0 does not throw an exception");
    }
    /** Test method for {@link primitives.Vector#dotProduct(Vector)}. */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that checks add func with angle that is smaller then 90
        Vector v2 = new Vector(0, 3, 0);
        double rs = 6;
        assertEquals(rs, v1.dotProduct(v2),0.00001, "dotProduct() wrong result in less then 90 degrees");

        // TC02: Test that checks add func with angle that is smaller then 90
        v2 = new Vector(0,3,-7);
        rs = -15;
        assertEquals(rs, v1.dotProduct(v2),0.00001, "dotProduct() wrong result in more then 90 degrees");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from dot-product of orthogonal vectors
        v2 = new Vector(0, 3, -2);
        rs = 0;
        assertEquals( rs, v1.dotProduct(v2),0.00001, "dotProduct() wrong result in orthogonal vectors");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /** Test method for {@link Vector#lengthSquared()}. */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(0, 0, 5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks lengthSquared with anything
        double d = 25;
        assertEquals(d, v1.lengthSquared(),0.000001, "lengthSquared() wrong result!");

        // =============== Boundary Values Tests ==================
        // none
    }

    /** Test method for {@link Vector#length()}. */
    @Test
    void testLength() {
        Vector v1 = new Vector(0, 0, 5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks length with anything
        double d = 5;
        assertEquals(d, v1.length(),0.000001, "length() wrong result!");

        // =============== Boundary Values Tests ==================
        // none
    }

    /** Test method for {@link Vector#normalize()}. */
    @Test
    void testNormalize() {
        Vector v1 = new Vector(0, 0, 5);
        Vector vr = new Vector(0,0,1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that checks normalized for everyone!
        assertEquals(vr, v1.normalize(), "normalize() wrong result!");

        // =============== Boundary Values Tests ==================
        // none

    }
}