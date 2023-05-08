package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    /**
     * constructor that activate the father constructor
     * @param scene the scene
     */
    public RayTracerBasic(Scene scene){
        super(scene);
    }

    /**
     * func that implements fathers' func
     * @param ray the ray
     * @return the color to return
     */
    public Color traceRay(Ray ray){
        List<Point> list = scene.geometries.findIntersections(ray);
        if(list == null)
            return scene.background; // there are no intersections
        Point point = ray.findClosestPoint(list); // find the closest point that intersects.
        return calcColor(point); // calc the color of this point, and return
    }

    /**
     * private method for calculating the color of the given point
     * @param point the point
     * @return the color that we have calculated
     */
    private Color calcColor(Point point){
        return scene.ambientLight.getIntensity(); // temporary return.
    }

}
