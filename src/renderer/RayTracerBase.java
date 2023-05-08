package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for RayTracing
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * constructor for RayTracerBase
     * @param _scene the scene
     */
    public RayTracerBase(Scene _scene){
        scene=_scene;
    }

    /**
     * abstract func that returns the color which the ray gets from intersects the scene
     * @param ray the ray
     * @return the color that we got
     */
    public abstract Color traceRay(Ray ray);
}
