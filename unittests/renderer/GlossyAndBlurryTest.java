/**
 * 
 */
package renderer;

import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/** Tests for glossy and blurry
 *
 *
 * @author yourMOM */
public class GlossyAndBlurryTest {
   private final Scene          scene1                  = new Scene("Test scene");
   private final Scene          scene2                  = new Scene("Test scene")
           .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

   private final Camera         camera1                 = new Camera(new Point(0, 0, 1000),
           new Vector(0, 0, -1), new Vector(0, 1, 0))
           .setVPSize(150, 150).setVPDistance(1000);
   private final Camera         camera2                 = new Camera(new Point(0, 0, 1000),
           new Vector(0, 0, -1), new Vector(0, 1, 0))
           .setVPSize(200, 200).setVPDistance(1000);

   private static final int     SHININESS               = 301;
   private static final double  KD                      = 0.5;
   private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

   private static final double  KS                      = 0.5;
   private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

   private final Material       material                = new Material().setKd(KD3).setKs(KS3).setShininess(SHININESS);
   private final Color          trianglesLightColor     = new Color(800, 500, 250);
   private final Color          sphereLightColor        = new Color(800, 500, 0);
   private final Color          sphereColor             = new Color(BLUE).reduce(2);

   private final Point          sphereCenter            = new Point(0, 0, -50);
   private static final double  SPHERE_RADIUS           = 50d;

   // The triangles' vertices:
   private final Point[]        vertices                =
           {
                   // the shared left-bottom:
                   new Point(-110, -110, -150),
                   // the shared right-top:
                   new Point(95, 100, -150),
                   // the right-bottom
                   new Point(110, -110, -150),
                   // the left-top
                   new Point(-75, 78, 100)
           };
   private final Point          sphereLightPosition     = new Point(-50, -50, 25);
   private final Point          trianglesLightPosition  = new Point(30, 10, -100);
   private final Vector         trianglesLightDirection = new Vector(-2, -2, -2);

   private final Geometry sphere                  = new Sphere(SPHERE_RADIUS,sphereCenter )
           .setEmission(sphereColor).setMaterial(new Material().setKd(KD).setKs(KS).setShininess(SHININESS));
   private final Geometry       triangle1               = new Triangle(vertices[0], vertices[1], vertices[2])
           .setMaterial(material);
   private final Geometry       triangle2               = new Triangle(vertices[0], vertices[1], vertices[3])
           .setMaterial(material);
   private Scene scene = new Scene("Test scene");

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
                           new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                           new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                          .setKl(0.0004).setKq(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(2500, 2500).setVPDistance(10000); //

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
                           new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                 .setKt(new Double3(0.5, 0, 0))),
                           new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setKl(0.00001).setKq(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(200, 200).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                           new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
         .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture with an infinite mirror
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void twoPlanesOneSphere() {
      Camera camera = new Camera(new Point(50,  0,0), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
              .setVPSize(200, 200).setVPDistance(1000);


      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
              new Polygon(new Point(0,25,500),new Point(25,25,500),new Point(25,-25,500),new Point(0,-25,500)).setEmission(new Color(WHITE).scale(0.003)) //
                      .setMaterial(new Material().setKd(0.8).setKs(0.5).setShininess(60).setKt(0.9).setKb(100000)), //
              new Sphere(new Point(-200, 0,0),10d).setEmission(Color.BLUE.scale(0.4)).setMaterial(new Material().setKd(0.5).setKs(0.6))
      );

      scene.lights.add(new SpotLight(new Color(800, 500, 0), new Point(-200, 0, 20), new Vector(0, 0, -1)) //
              .setKl(0.1).setKq(0.01));

      ImageWriter imageWriter = new ImageWriter("twoPlanesOneSphere", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   /** test for multiple light sources with sphere */
   @Test
   public void sphereMultipleLightsBlurry() {
      scene1.geometries.add(sphere,
              new Polygon(new Point(-50,50,500),new Point(50,50,500),new Point(50,-50,500),new Point(-50,-50,500)).setEmission(new Color(WHITE).scale(0.003)) //
                      .setMaterial(new Material().setKd(0.8).setKs(0.5).setShininess(60).setKt(0.7).setKb(0.0001))
      );
      scene1.lights.add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5))
              .setKl(0.001).setKq(0.0001));
      scene1.lights.add(new PointLight(Color.BLUE.scale(100),new Point(0,0,100)).setKq(0.0001).setKl(0.0001).setKc(1));
      scene1.lights.add(new SpotLight(Color.RED.scale(20),new Point(100,0,100),new Vector(0,0,-1)).setKq(0.0001).setKl(0.0001).setKc(1));

      ImageWriter imageWriter = new ImageWriter("sphereMultipleLightsBlurry", 500, 500);
      camera1.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene1)) //
              .renderImage() //
              .writeToImage(); //
   }
}
