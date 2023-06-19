package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AABoxTest {

    @Test
    void findGeoIntersectionsHelper() {
        AABox box1 = new AABox(new Point(1, 1, 1),new Point(-1, -1, -1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray is intersecting (1 points)
        assertEquals(box1.findIntersections(new Ray(new Point(-1.5, 0, 0), new Vector(1, 1, 0))),new LinkedList<>(),
                "Ray is intersecting, TC01");
        // TC02: Ray is not intersecting (1 points)
        assertNull(box1.findIntersections(new Ray(new Point(-1.5, 0, 0), new Vector(-1, 0, 0))),
                "Ray is not intersecting, TC02");

        box1 = new AABox(new Point(-1, -1, -1),new Point(1, 1, 1));
        // TC11: Ray is intersecting (1 points), points are reversed
        assertEquals(box1.findIntersections(new Ray(new Point(-1.5, 0, 0), new Vector(1, 1, 0))),new LinkedList<>(),
                "Ray is intersecting, TC01");
        // TC12: Ray is not intersecting (1 points), points are reversed
        assertNull(box1.findIntersections(new Ray(new Point(-1.5, 0, 0), new Vector(-1, 0, 0))),
                "Ray is not intersecting, TC02");
    }
}