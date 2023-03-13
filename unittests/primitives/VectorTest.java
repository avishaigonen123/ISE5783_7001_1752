package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Vector
 * @author Avishai Gonen
 */
public class VectorTest {
    /** Test method for {@link primitives.Vector#add(Vector)}. */
    @Test
    void testAdd() {        fail("Not yet implemented");

    }
    /** Test method for {@link primitives.Vector#scale(double)}. */
    @Test
    void testScale() {        fail("Not yet implemented");

    }
    /** Test method for {@link primitives.Vector#dotProduct(Vector)}. */
    @Test
    void testDotProduct() {        fail("Not yet implemented");
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

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
    void testLengthSquared() {        fail("Not yet implemented");

    }

    /** Test method for {@link Vector#length()}. */
    @Test
    void testLength() {        fail("Not yet implemented");

    }

    /** Test method for {@link Vector#normalize()}. */
    @Test
    void testNormalize() {        fail("Not yet implemented");

    }
}