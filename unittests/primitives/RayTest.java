package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Ray
 * @author Ariel Zaken && Avishai Gonen
 */
class RayTest {
    /** Test method for {@link primitives.Ray#getPoint(double)}}. */
    @Test
    void getPoint() {
        Ray ray = new Ray(new Point(1,0,0),new Vector(1,0,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: t>0
        assertEquals(new Point(2,0,0),ray.getPoint(1),"TC01: t>0 doesn't work well");
        // TC02: t<0
        assertEquals(new Point(0,0,0),ray.getPoint(-1),"TC01: t<0 doesn't work well");

        // =============== Boundary Values Tests ==================
        // TC11: t=0
        assertEquals(new Point(1,0,0),ray.getPoint(0),"TC11: t=0 doesn't work well" );
    }
}