package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static renderer.PictureImprover.superSampling;

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
     * that is for the numbers of rays
     */
    private static final int NUM_OF_RAYS = 10;

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
        Ray shadowRay = new Ray(lightDirection, n, gp);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
        if (intersections == null)
            return true;
        double dis = lightSource.getDistance(gp.point);
        GeoPoint geoPoint = findClosestIntersection(shadowRay);
        return geoPoint.geometry.getMaterial().kT != Double3.ZERO || geoPoint.point.distance((gp.point)) > dis;
    }

    /**
     * the function to decide if the light ray is obstructed
     *
     * @param gp the point to be unshaded
     * @param l  the direction from the light
     * @param n  the normal for the point(for DELTA)
     * @return the precentage of shadow
     */
    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Ray shadowRay = new Ray(lightDirection, n, gp);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
        double dis = lightSource.getDistance(gp.point);
        Double3 ktr = Double3.ONE;
        if (intersections == null)
            return ktr;
        for (GeoPoint intersection : intersections) // for each intersection it checks if its' distance is smaller than the dis of the ls.
            // if it does, he will mul it by the total ktr.
            if (intersection.point.distance(gp.point) < dis) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
            }
        return ktr;
    }

    /**
     * func that implements fathers' func
     *
     * @param ray the ray
     * @return the color to return
     */
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background
                : calcColor(closestPoint, ray);
    }

    /**
     * private method for calculating the color of the given GeoPoint
     *
     * @param geoPoint the GeoPoint
     * @return the color that we have calculated
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k); // result = ambientLight+emission
        return 1 == level ? color
                : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }
    ///

    private Color calcColorSampling(Ray ogRay,double k,GeoPoint gp,int level,Double3 kkX,Double3 kX){
        Color color = Color.BLACK;
        if(k==0){
            GeoPoint point = findClosestIntersection(ogRay);
            color = point == null ? color
                    : color.add(calcColor(point, ogRay, level - 1, kkX)).scale(kX);
            return color;
        }
        Ray refractedRay = null;
        GeoPoint refractedPoint = null;
        List<Point> pointList = superSampling(ogRay,k,NUM_OF_RAYS);
        for (Point point:pointList) {

            refractedRay = new Ray(gp.point, point.subtract(gp.point));
            refractedPoint = findClosestIntersection(refractedRay);

            color = refractedPoint == null ? color
                    : color.add(calcColor(refractedPoint, refractedRay, level - 1, kkX)).scale(kX);
        }
        return color.scale(1/(double)NUM_OF_RAYS);
    }

    ///
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(1)).add(scene.ambientLight.getIntensity());
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color colorR = Color.BLACK;
        Color colorT = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR, kkr = k.product(kr);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray ogReflectedRay = constructReflectionRay(ray.getDir(), gp.geometry.getNormal(gp.point), gp);
            colorR =colorR.add(calcColorSampling(ogReflectedRay,mat.kG,gp,level,kkr,kr));
        }
        Double3 kt = mat.kT, kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray ogRefractedRay = constructRefractedRay(ray.getDir(), gp.geometry.getNormal(gp.point), gp);
            colorT = colorT.add(calcColorSampling(ogRefractedRay,mat.kB,gp,level,kkt,kt));
        }
        return colorR.add(colorT);
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(gp, lightSource, l, n, nv);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    iL = lightSource.getIntensity(gp.point).scale(ktr);
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
    private Ray constructReflectionRay(Vector v, Vector n, GeoPoint gp) {
        Vector normalInDir = n.scale(n.dotProduct(v) < 0 ? 1 : -1);
        Vector r = v.subtract(normalInDir.scale(2 * v.dotProduct(normalInDir)));
        return new Ray(r, normalInDir, gp);
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
        return new Ray(v, n, gp);
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