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

   /** test for multiple light sources with sphere */
   @Test
   public void sphereMultipleLightsBlurry() {
      int a = 200;
      scene1.geometries.add(sphere.setEmission(Color.CYAN),
              new Polygon(new Point(0,50,a),new Point(50,50,a),new Point(50,-50,a),new Point(0,-50,a)).setEmission(new Color(WHITE).scale(0.02)) //
                      .setMaterial(new Material().setKd(0).setKs(0).setShininess(60).setKt(0.98).setKb(1))
      );
      scene1.lights.add(new PointLight(sphereLightColor, new Point(50,50,0))
              .setKl(0.001).setKq(0.0001));
      //scene1.lights.add(new PointLight(Color.BLUE.scale(100),new Point(0,0,100)).setKq(0.0001).setKl(0.0001).setKc(1));
      //scene1.lights.add(new SpotLight(Color.RED.scale(20),new Point(100,0,100),new Vector(0,0,-1)).setKq(0.0001).setKl(0.0001).setKc(1));

      ImageWriter imageWriter = new ImageWriter("sphereMultipleLightsBlurry", 500, 500);
     // camera1.RotationOnZaxis(20);
      camera1.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene1)) //
              .renderImage() //
              .writeToImage(); //
   }
}
