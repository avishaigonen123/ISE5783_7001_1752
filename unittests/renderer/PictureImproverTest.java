package renderer;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PictureImproverTest {
    @Test
    void superSampling() {
        Point p = new Point(0,0,5);
        Vector v = new Vector(1,5,7);
        Ray ray = new Ray(p,v);
        // ============ Equivalence Partitions Tests ==============
        List<Point> lst = PictureImprover.superSampling(ray,5,200);
        // TC01: t>0
        for (Point point:lst) {
            assertTrue(point.distance(ray.getPoint(5))<=5,"TC01:distance is "+point.distance(p));
        }
        // =============== Boundary Values Tests ==================
        // TC11: t=0
        lst = PictureImprover.superSampling(ray,0,200);
        assertEquals(1,lst.size(),"TC11: size is not 1");
        assertEquals(ray.getPoint(5),lst.get(0),"TC11: its not the p we hoped for");
    }
}