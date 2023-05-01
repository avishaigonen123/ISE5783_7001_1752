import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for all the integretion tests
 */
class IntegrationTest {
    /**
     * function for the test only!!! that counts the numbers of intersetions
     *
     * @param _camera  the camera
     * @param geometry the geometry
     * @param expected
     * @return the number of intersections
     */
    private void countIntersections(Camera _camera, Geometry geometry, int expected) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> list = geometry.findIntersections(_camera.constructRay(3, 3, j, i));
                count += list == null ? 0 : list.size();
            }
        }
        assertEquals(expected, count, "the number of intersections isn't correct");
    }

    @Test
    void cameraSphereIntersectionsTest() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
        // TC01: the camera is out of the sphere (2 points)
        countIntersections(camera, sphere, 2);
        // TC02: the camera is out of the sphere (18 points)
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        sphere = new Sphere(2.5, new Point(0, 0, -2.5));
        countIntersections(camera, sphere, 18);
        // TC03: the camera is out of the sphere (10 points)
        sphere = new Sphere(2, new Point(0, 0, -2));
        countIntersections(camera, sphere, 10);
        // TC04: the camera is in the sphere (9 points)
        sphere = new Sphere(4, new Point(0, 0, -1));
        countIntersections(camera, sphere, 9);
        // TC05: the camera is in front of sphere (0 points)
        sphere = new Sphere(0.5, new Point(0, 0, 1));
        countIntersections(camera, sphere, 0);
    }

    @Test
    void cameraPlaneIntersectionsTest() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Plane plane = new Plane(new Point(0, 0, -3), new Vector(0, 0, -1));

        // TC01: the camera is behind the plane(9 points)
        countIntersections(camera, plane, 9);
        // TC02: the camera is out of the sphere (9 points)
        plane = new Plane(new Point(0, 0, -3), new Vector(0, 0.5, -1));
        countIntersections(camera, plane, 9);
        // TC03: the camera is out of the sphere (6 points)
        plane = new Plane(new Point(0, 0, -5), new Vector(0, 1, -1));
        countIntersections(camera, plane, 6);
    }

    @Test
    void cameraTriangleIntersectionsTest() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        // TC01: the camera is behind the plane(1 point)
        countIntersections(camera, triangle, 1);
        // TC02: the camera is behind the sphere (2 points)
        triangle = new Triangle(new Point(0, 20, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        countIntersections(camera, triangle, 2);
    }

}