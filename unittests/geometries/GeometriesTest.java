package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        Geometries geometries = new Geometries(
                new Triangle(new Point(1,1,1),new Point(1,0,0),new Point(1,1,0)),
                new Triangle(new Point(0,0,0),new Point(4,4,0),new Point(4,4,4)),
                new Sphere(3,new Point(1,0,0))
        );
        // ============ Equivalence Partitions Tests =============
        // TC01: some of the shapes are intersecting but not all of them
        assertEquals(3, geometries.findIntersections(new Ray(new Point(2,-5,0), new Vector(0,1,0.1))).size(), "Wrong number of points, TC01");

        // =============== Boundary Values Tests ==================
        // TC11: empty collection
        Geometries geometries1 = new Geometries();
        assertNull( geometries1.findIntersections(new Ray(new Point(0,0,0), new Vector(1,0,0))), "Wrong number of points in empty collection, TC11");
        // TC12: none of the shapes are intersected
        assertNull( geometries.findIntersections(new Ray(new Point(5,-5,0), new Vector(0,1,0.1))), "Wrong number of points, TC12");
        // TC13: only one shape is intersected
        assertEquals(2, geometries.findIntersections(new Ray(new Point(-1,5,0), new Vector(0,-5,0))).size(), "Wrong number of points, TC13");
        // TC11: all the shapes are intersected
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0,0.5,0), new Vector(1,0,0.3))).size(), "Wrong number of points, TC14");

    }
}