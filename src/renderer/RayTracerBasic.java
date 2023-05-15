package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class RayTracerBasic extends RayTracerBase {
    /**
     * we will move the ray of the shadow in the normal direction inorder to not intersect ourselves
     */
    private static final double DELTA = 0.1;
    /**
     * constructor that activate the father constructor
     *
     * @param scene the scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * the function to decide if the light ray is obstructed
     * @param gp the point to be unshaded
     * @param l the direction from the light
     * @param n the normal for the point(for DELTA)
     * @return true if the point is unshaded
     */
    private boolean unshaded(GeoPoint gp,Vector l,Vector n){
        return true;
    }

    /**
     * func that implements fathers' func
     *
     * @param ray the ray
     * @return the color to return
     */
    public Color traceRay(Ray ray) {
        List<GeoPoint> list = scene.geometries.findGeoIntersections(ray);
        if (list == null)
            return scene.background; // there are no intersections
        GeoPoint point = ray.findClosestGeoPoint(list); // find the closest point that intersects.
        return calcColor(point, ray); // calc the color of this point, and return
    }

    /**
     * private method for calculating the color of the given GeoPoint
     *
     * @param geoPoint the GeoPoint
     * @return the color that we have calculated
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(geoPoint, ray)); // result = ambientLight+emission
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector l;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = Util.alignZero(n.dotProduct(v));
        double nl;
        Color iL;
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            l = lightSource.getL(gp.point).normalize();
            nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0 && unshaded(gp,l,n)) { // sign(nl)==sign(nv)
                iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)))
                        .add(iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        int nShininess = material.nShininess;
        Vector r = (l.subtract(n.scale(2*nl))).normalize(); // r = l - 2 * (l*n) * n
        double vr = (v.normalize().scale(-1)).dotProduct(r);
        return material.kS.scale((vr > 0) ? pow(vr, nShininess) : 0);
    }
}
