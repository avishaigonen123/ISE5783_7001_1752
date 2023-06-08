package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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
    /** Test method for {@link primitives.Ray#findClosestPoint(List)}. */
    @Test
    void findClosestPoint() {
        List<Point> list = new LinkedList<Point>();
        list.add(new Point(2,0,0));
        list.add(new Point(1,0,0));
        list.add(new Point(3,0,0));

        Ray ray = new Ray(
                new Point(0,0,0),
                new Vector(1,0,0)
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: the closest point is in the middle of the list
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"TC01: the closest point is in the middle of the list ");
        // =============== Boundary Values Tests ==================
        // TC11: the list is empty
        list = new LinkedList<Point>();
        assertNull(ray.findClosestPoint(list),"TC11: empty list not returning null" );
        // TC12: the closest point is in the begging
        list = new LinkedList<Point>();
        list.add(new Point(1,0,0));
        list.add(new Point(2,0,0));
        list.add(new Point(3,0,0));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"TC12: the closest point is in the begging of the list ");
        // TC13: the closest point is in the end
        list = new LinkedList<Point>();
        list.add(new Point(3,0,0));
        list.add(new Point(2,0,0));
        list.add(new Point(1,0,0));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"TC12: the closest point is in the end of the list ");
    }

    @Test
    void randomOrthogonalRay(){
        Ray ray = new Ray(new Point(10,0,10),new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: two orthogonal vector
        Ray res = ray.randomOrthogonalRay();
        assertEquals(0,Util.alignZero(res.getDir().dotProduct(ray.getDir())),"OH NO, they are not orthogonals");
    }
}