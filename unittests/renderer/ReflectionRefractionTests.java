/**
 *
 */
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import geometries.Polygon;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
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

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
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
                        .setMaterial(new Material().setKr(1).setKg(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4)).setKg(1)));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
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

    /**
     * Produce a picture with an infinite mirror
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void twoPlanesOneSphere() {
        Camera camera = new Camera(new Point(-17, 30, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)) //
                .setVPSize(200, 200).setVPDistance(1000);
        camera.RotationOnZaxis(82);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Polygon(new Point(-20, -100, -200), new Point(-20, 100, -200), new Point(-20, 100, 200), new Point(-20, -100, 200)).setEmission(new Color(WHITE).scale(0.03)) //
                        .setMaterial(new Material().setKd(0.8).setKs(0.5).setShininess(60).setKr(0.59).setKt(0)), //
                new Polygon(new Point(20, -100, -200), new Point(20, 100, -200), new Point(20, 100, 200), new Point(20, -100, 200)).setEmission(new Color(WHITE).scale(0.03)) //
                        .setMaterial(new Material().setKd(0.8).setKs(0.5).setShininess(60).setKr(0.59).setKt(0)), //
                new Sphere(new Point(0, 0, 0), 7d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.2).setKr(1)));

        scene.lights.add(new SpotLight(new Color(YELLOW).scale(1.1), new Point(0, 0, 1000), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("twoPlanesOneSphere", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void HouseBonusTest() {
        Camera camera = new Camera(new Point(0, -6000, 100), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
                .setVPSize(200, 200).setVPDistance(1000);
        camera.transformation(new Vector(0, 0, 150));

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.setBackground(Color.CYAN);
        scene.geometries.add(
                new Polygon(new Point(-200, 0, -200), new Point(200, 0, -200), new Point(200, 0, 200), new Point(-200, 0, 200)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(Color.BLUE),
                new Triangle(new Point(-200, 0, 200), new Point(200, 0, 200), new Point(0, 0, 400))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(Color.RED),
                new Polygon(new Point(-70, -1, -20), new Point(70, -1, -20), new Point(70, -1, -200), new Point(-70, -1, -200)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.BLUE.scale(0.4)),
                new Polygon(new Point(-130, 1, 230), new Point(-130, 1, 400), new Point(-70, 1, 400), new Point(-70, 1, 230)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.MAROON),
                new Sphere(50d, new Point(-80, 0, 480))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(0.6)),
                new Sphere(40d, new Point(-140, 0, 560))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(0.7)),
                new Sphere(30d, new Point(-90, 0, 630))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(0.8)),
                new Sphere(25d, new Point(-135, 0, 685))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(0.9)),
                new Sphere(20d, new Point(-95, 0, 725))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(1)),
                new Sphere(15d, new Point(-130, 0, 755))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(1.1)),
                new Sphere(10d, new Point(-97, 0, 777))
                        .setMaterial(new Material().setKd(0.05).setKs(0.05).setShininess(60)).setEmission(Color.GRAY.scale(1.15))
        );

        scene.lights.add(new SpotLight(new Color(255, 255, 0), new Point(1000, -1000, 1000), new Vector(-1, 1, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("HouseBonus", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void HouseBonusTest2() {
        Camera camera = new Camera(new Point(500, 500, 50), new Vector(-1, -1, 0), new Vector(0, 0, 1)) //
                .setVPSize(200, 200).setVPDistance(1000);
        scene.setBackground(Color.CYAN);
        Material wall = new Material().setKd(0.05).setKs(0.05).setShininess(60);
        Material window = new Material().setKd(0.2).setKs(0.5).setKt(0.3).setShininess(60);
        //    Material door = new Material().setKd(0.05).setKs(0.35).setShininess(60);

        Point p0 = new Point(0, 0, 0), p1 = new Point(-50, 0, 0), p2 = new Point(0, -50, 0);
        Point p3 = new Point(0, 0, 50), p4 = new Point(-50, 0, 50), p5 = new Point(0, -50, 50);
        Point p6 = new Point(-50, -50, 100);
        Point p10 = new Point(-30, 1, 40), p11 = new Point(-30, 1, 20), p12 = new Point(-10, 1, 20), p13 = new Point(-10, 1, 40);
        Point p14 = new Point(1, -30, 40), p15 = new Point(1, -30, 20), p16 = new Point(1, -10, 20), p17 = new Point(1, -10, 40);
        // Point p18 = new Point(), p19 = new Point(), p20 = new Point(),p21 = new Point();
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.setBackground(Color.CYAN);
        scene.geometries.add(
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
                , new Plane(new Point(400,400,40),new Vector(-1,-1,0)).setMaterial(window.setKb(0.3).setKt(1))// MIRRRORRRRRR
//                , new Polygon(p18, p19,p20,p21).setEmission(Color.MAROON.scale(0.8))
        );

        scene.lights.add(new DirectionalLight(Color.YELLOW.scale(2), new Vector(1, -1, -1)));
        scene.lights.add(new SpotLight(new Color(255, 255, 0).scale(20), new Point(-25, 2, 0), new Vector(0, 0, 1)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new SpotLight(new Color(255, 255, 0).scale(20), new Point(2, -25, 0), new Vector(0, 0, 1)) //
                .setKl(4E-5).setKq(2E-7));
        ImageWriter imageWriter = new ImageWriter("HouseBonus2", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
