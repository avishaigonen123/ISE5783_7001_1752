/**
 * 
 */
package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
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
      ImageWriter imageWriter = new ImageWriter("sphereMultipleLightsBlurry", 500, 500);
      camera1.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene1)) //
              .renderImage() //
              .writeToImage(); //
   }
   @Test
   public void GlossySphereTest() {
      Scene scene3 = new Scene("squars");
      Camera camera = new Camera(new Point(500, 500, 50), new Vector(-1, -1, 0), new Vector(0, 0, 1))
              .setVPSize(250, 250).setVPDistance(1000);
      camera.transformation(new Vector(200,200,0));
      //camera.transformation(new Vector(0,0,100));
      scene3.setBackground(Color.BLACK);
      Material wall = new Material().setKd(0.3).setKs(0.3).setShininess(60);
      Material window = new Material().setKd(0.2).setKs(0.4).setKt(0.1).setKr(0.3).setShininess(60);
      Material tile = new Material().setKd(0.4).setKs(0.2).setKr(0.4).setShininess(60);
      //    Material door = new Material().setKd(0.05).setKs(0.35).setShininess(60);

      Point p0 = new Point(0, 0, 0), p1 = new Point(-50, 0, 0), p2 = new Point(0, -50, 0);
      Point p3 = new Point(0, 0, 50), p4 = new Point(-50, 0, 50), p5 = new Point(0, -50, 50);
      Point p6 = new Point(-50, -50, 100);
      Point p10 = new Point(-30, 1, 40), p11 = new Point(-30, 1, 20), p12 = new Point(-10, 1, 20), p13 = new Point(-10, 1, 40);
      Point p14 = new Point(1, -30, 40), p15 = new Point(1, -30, 20), p16 = new Point(1, -10, 20), p17 = new Point(1, -10, 40);
       Point p18 = new Point(20,-30,0), p19 = new Point(60,-100,0), p20 = new Point(20,-30,70),p21 = new Point(60,-100,70);
      scene3.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
      scene3.geometries.add(
              //            new Plane(new Point(0,0,0), new Vector(1,0,0)).setEmission(Color.GREEN)
              new Polygon(p0, p1, p4, p3).setMaterial(wall).setEmission(Color.GRAY)
              , new Polygon(p0, p2, p5, p3).setMaterial(wall).setEmission(Color.GRAY)
              , new Triangle(p3, p4, p6).setMaterial(wall).setEmission(Color.RED)
              , new Triangle(p3, p5, p6).setMaterial(wall).setEmission(Color.RED)

              , new Polygon(p10, p11, p12, p13).setMaterial(window).setEmission(Color.BLUE.scale(0.4))
              , new Polygon(p14, p15, p16, p17).setMaterial(window).setEmission(Color.BLUE.scale(0.4))
              , new Polygon(new Point(-25, 0, 85), new Point(-25, 0, 55), new Point(-25, -15, 55), new Point(-25, -15, 85)) //
                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.MAROON),
              new Sphere(7d, new Point(-25, 7, 95))
                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.7)).setEmission(Color.GRAY.scale(0.7)),
              new Sphere(5d, new Point(-25, -7, 108))
                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.8)).setEmission(Color.GRAY.scale(0.8)),
              new Sphere(3d, new Point(-25, 5, 115))
                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.9)).setEmission(Color.GRAY.scale(0.9))
              //, new Plane(new Point(400,400,40),new Vector(-1,-1,0)).setMaterial(window.setKb(2).setKt(1))// MIRRRORRRRRR
//                , new Polygon(p18, p19,p20,p21).setEmission(Color.MAROON.scale(0.8))

              , new Polygon(p18,p19,p21,p20).setMaterial(new Material().setKd(0.2).setKs(0.4).setKt(0.92).setKr(0.3).setKb(1).setShininess(60)).setEmission(Color.BLUE.scale(0.4))
          );
      int a = 100;

     for (int i = -1000;i<1000;i+=a)
         for (int j = -1000;j<1000;j+=a)
            scene3.geometries.add(new Polygon(
                    new Point(i,j,0),
                    new Point(i,j+a,0),
                    new Point(i+a,j+a,0),
                    new Point(i+a,j,0)).setEmission((i/a+j/a)%2==0?Color.GRAY:Color.BLACK).setMaterial(tile));
      scene3.lights.add(
              new DirectionalLight(sphereLightColor, new Vector(-1,0,-1))
              );

      ImageWriter imageWriter = new ImageWriter("GlossySphereTest", 1000, 1000);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene3)) //
              .renderImage() //
              .writeToImage(); //
   }
   @Test
   public void GlossySphereTestWithComposite() {
      Scene scene3 = new Scene("squars");
      Camera camera = new Camera(new Point(500, 500, 50), new Vector(-1, -1, 0), new Vector(0, 0, 1))
              .setVPSize(250, 250).setVPDistance(1000);
      camera.transformation(new Vector(200,200,0));
      //camera.transformation(new Vector(0,0,100));
      scene3.setBackground(Color.BLACK);
      Material wall = new Material().setKd(0.3).setKs(0.3).setShininess(60);
      Material window = new Material().setKd(0.2).setKs(0.4).setKt(0.1).setKr(0.3).setShininess(60);
      Material tile = new Material().setKd(0.4).setKs(0.2).setKr(0.4).setShininess(60);
      //    Material door = new Material().setKd(0.05).setKs(0.35).setShininess(60);

      Point p0 = new Point(0, 0, 0), p1 = new Point(-50, 0, 0), p2 = new Point(0, -50, 0);
      Point p3 = new Point(0, 0, 50), p4 = new Point(-50, 0, 50), p5 = new Point(0, -50, 50);
      Point p6 = new Point(-50, -50, 100);
      Point p10 = new Point(-30, 1, 40), p11 = new Point(-30, 1, 20), p12 = new Point(-10, 1, 20), p13 = new Point(-10, 1, 40);
      Point p14 = new Point(1, -30, 40), p15 = new Point(1, -30, 20), p16 = new Point(1, -10, 20), p17 = new Point(1, -10, 40);
      Point p18 = new Point(20,-30,0), p19 = new Point(60,-100,0), p20 = new Point(20,-30,70),p21 = new Point(60,-100,70);
      scene3.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
      scene3.geometries.add(
              //            new Plane(new Point(0,0,0), new Vector(1,0,0)).setEmission(Color.GREEN)
              new Geometries(new Geometries( new Polygon(p0, p1, p4, p3).setMaterial(wall).setEmission(Color.GRAY)
                      , new Polygon(p0, p2, p5, p3).setMaterial(wall).setEmission(Color.GRAY)
                      , new Triangle(p3, p4, p6).setMaterial(wall).setEmission(Color.RED)
                      , new Triangle(p3, p5, p6).setMaterial(wall).setEmission(Color.RED)

                      , new Polygon(p10, p11, p12, p13).setMaterial(window).setEmission(Color.BLUE.scale(0.4))
                      , new Polygon(p14, p15, p16, p17).setMaterial(window).setEmission(Color.BLUE.scale(0.4))
                      , new Polygon(new Point(-25, 0, 85), new Point(-25, 0, 55), new Point(-25, -15, 55), new Point(-25, -15, 85)) //
                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.MAROON)),
                      new Geometries(new Sphere(7d, new Point(-25, 7, 95))
                              .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.7)).setEmission(Color.GRAY.scale(0.7)),
                              new Sphere(5d, new Point(-25, -7, 108))
                                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.8)).setEmission(Color.GRAY.scale(0.8)),
                              new Sphere(3d, new Point(-25, 5, 115))
                                      .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60).setKt(0.9)).setEmission(Color.GRAY.scale(0.9))))
              //, new Plane(new Point(400,400,40),new Vector(-1,-1,0)).setMaterial(window.setKb(2).setKt(1))// MIRRRORRRRRR
//                , new Polygon(p18, p19,p20,p21).setEmission(Color.MAROON.scale(0.8))

              , new Polygon(p18,p19,p21,p20).setMaterial(new Material().setKd(0.2).setKs(0.4).setKt(0.92).setKr(0.3).setKb(1).setShininess(60)).setEmission(Color.BLUE.scale(0.4))
      );
      int a = 100;

      for (int i = -1000;i<1000;i+=a)
         for (int j = -1000;j<1000;j+=a)
            scene3.geometries.add(new Polygon(
                    new Point(i,j,0),
                    new Point(i,j+a,0),
                    new Point(i+a,j+a,0),
                    new Point(i+a,j,0)).setEmission((i/a+j/a)%2==0?Color.GRAY:Color.BLACK).setMaterial(tile));
      scene3.lights.add(
              new DirectionalLight(sphereLightColor, new Vector(-1,0,-1))
      );

      ImageWriter imageWriter = new ImageWriter("GlossySphereTest", 1000, 1000);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene3)) //
              .renderImage() //
              .writeToImage(); //
   }

}
