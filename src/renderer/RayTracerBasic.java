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
     * for the break in the recursion, i don't want it to be so deep
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * that is another break for the recursion, for case where the light is not strong enough
     */
    private static final double MIN_CALC_COLOR_K = 0.001;
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
     *
     * @param gp the point to be unshaded
     * @param l  the direction from the light
     * @param n  the normal for the point(for DELTA)
     * @return true if the point is unshaded
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray shadowRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
        if (intersections == null)
            return true;
        double dis = lightSource.getDistance(gp.point);
        return findClosestIntersection(shadowRay).point.distance((gp.point)) > dis;
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
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = scene.ambientLight.getIntensity()
                .add(calcLocalEffects(geoPoint, ray)); // result = ambientLight+emission
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(MIN_CALC_COLOR_K));
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR, kkr = k.product(kr);
        if(!kkr.lowerThan(MIN_CALC_COLOR_K)){
            Ray reflectedRay = constructRefletionRay(ray.getDir(), gp.geometry.getNormal(gp.point),gp);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            color = color.add(calcColor(reflectedPoint,reflectedRay,level-1,kkr).scale(kr));
        }
        Double3 kt = mat.kT, kkt = k.product(kt);
        if(!kkt.lowerThan(MIN_CALC_COLOR_K)){
            Ray refractedRay = constructRefractedRay(ray.getDir(), gp.geometry.getNormal(gp.point),gp);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            color = color.add(calcColor(refractedPoint,refractedRay,level-1,kkt).scale(kt));
        }

        return color;
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
            if (nl * nv > 0) {// sign(nl)==sign(nv)
                if (unshaded(gp, lightSource, l, n)) {
                    iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)))
                            .add(iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        int nShininess = material.nShininess;
        Vector r = (l.subtract(n.scale(2 * nl))).normalize(); // r = l - 2 * (l*n) * n
        double vr = (v.normalize().scale(-1)).dotProduct(r);
        return material.kS.scale((vr > 0) ? pow(vr, nShininess) : 0);
    }

    /**
     * function the construct the reflection ray
     *
     * @param v  the dir of the camera
     * @param n  the normal
     * @param gp the point
     * @return the reflection ray
     */
    private Ray constructRefletionRay(Vector v, Vector n, GeoPoint gp) {
        Vector delta = n.scale(n.dotProduct(v) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r);
    }

    /**
     * function the construct the refracted ray
     *
     * @param v  the dir of the camera
     * @param n  the normal
     * @param gp the point
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Vector v, Vector n, GeoPoint gp) {
        Vector delta = n.scale(n.dotProduct(v) > 0 ? -DELTA : DELTA);
        Point point = gp.point.add(v);
        return new Ray(point, v);
    }

    /**
     * this func return the closest intersection
     *
     * @param ray the ray
     * @return the closest intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }
}
