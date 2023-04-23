package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
        //assertEquals(1, geometries.findIntersections(new Point(),new Vector()).size(), "Wrong number of points, TC01");
        // =============== Boundary Values Tests ==================
        // TC11: empty collection
        // TC12: non of the shapes are intersected
        // TC13: only one shape is intersected
        // TC11: all of the shapes are intersected


    }
}