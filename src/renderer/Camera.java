package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.MissingResourceException;

import static java.lang.Math.*;
import static primitives.Util.isZero;


/**
 * Class that presents our camera
 */
public class Camera {
    private int numOfThreads = 3;
    private double interval = 0.5;
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    private ImageWriter imageWriter;
    private  RayTracerBase rayTracer;

    /**
     * Constructor for initializing Camera
     * @param _p0 the point p0
     * @param _vTo the vector vTo
     * @param _vUp the vector vUp
     * @throws IllegalArgumentException if the vectors vUp and vTo aren't orthogonal.
     */
    public Camera(Point _p0, Vector _vTo, Vector _vUp){

        if(!isZero(_vTo.dotProduct(_vUp))) // if the dot product isn't zero, it is bad
            throw new IllegalArgumentException("Vectors aren't orthogonal");
        p0 = _p0;
        vTo = _vTo.normalize();
        vUp = _vUp.normalize();
        vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * func that sets the size of the view plane
     * @param _width the width
     * @param _height the height
     * @return the camera
     */
    public Camera setVPSize(double _width, double _height){
        width = _width;
        height = _height;
        return this;
    }
    /**
     * func that sets the distance of the view plane
     * @param _distance the distance
     * @return the camera
     */
    public Camera setVPDistance(double _distance){
        distance = _distance;
        return this;
    }

    /**
     * setter for imageWriter
     * @param _imageWriter the imageWrite
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter _imageWriter){
        imageWriter = _imageWriter;
        return this;
    }

    /**
     * setter for rayTracer
     * @param _rayTracer the rayTracer
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase _rayTracer){
        rayTracer = _rayTracer;
        return this;
    }
    /**
     * setter for numOfThread
     * @param _numOfThread the numOfThread
     * @return the camera
     */
    public Camera setMultithreading(int _numOfThread){
        numOfThreads = _numOfThread;
        return this;
    }
    /**
     * setter for interval
     * @param _interval the interval
     * @return the camera
     */
    public Camera setDebugPrint(double _interval){
        interval = _interval;
        return this;
    }
    /**
     * getter for distance
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter for p0
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for height
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter width
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for vRight
     * @return vRight
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter for vTo
     * @return vTo
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter for vUp
     * @return vUp
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * this func constructs a ray through given pixels.
     * @param nX the length of the lines of the pixels
     * @param nY the length of the columns of the pixels
     * @param j the place in the view plane in the columns
     * @param i the place in the view plane in the rows
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point pC = p0.add(vTo.scale(distance)); // pCenter = p0 + distance*vTo
        double rY = height/nY; // the size of each pixel
        double rX = width/nX; // same
        double yI = -(i-(double)(nY-1)/2)*rY; // we calculate the height to move up
        double xJ = (j-(double)(nX-1)/2)*rX; // we calculate the height to move right
        Point pIJ = pC;
        if(xJ != 0) pIJ = pIJ.add(vRight.scale(xJ)); // we don't want to have zero vector
        if(yI != 0) pIJ = pIJ.add(vUp.scale(yI)); // same
        return new Ray(p0, pIJ.subtract(p0)); // we return the ray between p0 and the pIJ, the pixel coordinate
    }

    /**
     * this func render the image
     * @return this
     */
    public Camera renderImage(){
        checkAreNotEmpty();
        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();
        double threadsCount = numOfThreads;

        Pixel.initialize(nY, nX, interval);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    imageWriter.writePixel(pixel.col,pixel.row,castRay(pixel.col,pixel.row));
            }).start();
        }
        Pixel.waitToFinish();
/*
        for(int i=0;i<nY;i++)
            for(int j=0;j<nX;j++){
                imageWriter.writePixel(j,i,castRay(j,i));
            }
            */
        return this;
    }
    private Color castRay(int j,int i){
        Ray ray = constructRay(imageWriter.getNx(),imageWriter.getNy(),j,i);
        return rayTracer.traceRay(ray);
    }
    /**
     * func that prints grid
     * @param interval where we want to put the line
     * @param color the color
     */
    public void printGrid(int interval, Color color){
        checkAreNotEmpty();
        for(int i=0; i< imageWriter.getNy();i++)
            for (int j=0; j< imageWriter.getNx();j++) {
                if(i%interval == 0 || j%interval == 0)
                    imageWriter.writePixel(j,i, color);
            }
        imageWriter.writeToImage();
    }

    /**
     * this func writes to image
     */
    public void writeToImage(){
        checkAreNotEmpty();
        imageWriter.writeToImage();
    }

    /**
     * private func to help us
     */
    private void checkAreNotEmpty(){
        if(p0 == null){//vUp == null||vTo==null||vRight==null||width == 0.0||height==0.0||distance==0||imageWriter==null||rayTracerBase==null){
            throw new MissingResourceException("the var is empty","Point","p0");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(vUp == null){
            throw new MissingResourceException("the var is empty","Vector","vUp");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(vTo == null){
            throw new MissingResourceException("the var is empty","Vector","vTo");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(vRight == null){
            throw new MissingResourceException("the var is empty","Vector","vRight");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(width == 0.0){
            throw new MissingResourceException("the var is empty","double","width");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(height == 0.0){
            throw new MissingResourceException("the var is empty","double","height");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(distance == 0.0){
            throw new MissingResourceException("the var is empty","double","distance");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(imageWriter == null){
            throw new MissingResourceException("the var is empty","ImageWriter","imageWriter");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }
        else if(rayTracer == null){
            throw new MissingResourceException("the var is empty","RayTracerBase","rayTracer");
            //  throw new UnsupportedOperationException();   UnReachable statement
        }

    }
    // bonus method

    /**
     * function that transformate the camera by moving the point with a vector
     * @param vec the vector we want to move on
     * @return this. we want it to be a builder pattern
     */
    public Camera transformation(Vector vec)
    {
        p0 = p0.add(vec); // we moved the point of the camera

        return this;
    }

    /**
     * function that rotate the camera around the axis X
     * @param aCel the angle (cel)
     * @return this. we want it to be a builder pattern
     */
    public Camera RotationOnXaxis(double aCel)
    {
        double a = toRadians(aCel);
        double x = vTo.getX(), y=  vTo.getY(), z = vTo.getZ();
        vTo = new Vector(x, y*cos(a)-z*sin(a),y*sin(a)+z*cos(a)); // rotation matrix for rotating in the axis
        x = vUp.getX(); y=  vUp.getY(); z = vUp.getZ();
        vUp = new Vector(x, y*cos(a)-z*sin(a),y*sin(a)+z*cos(a)); // same what only with the second vector
        vRight  = vTo.crossProduct(vUp);
        return this;
    }

    /**
     * function that rotate the camera around the axis Y
     * @param aCel the angle (cel)
     * @return this. we want it to be a builder pattern
     */
    public Camera RotationOnYaxis(double aCel)
    {
        double a = toRadians(aCel);
        double x = vTo.getX(), y=  vTo.getY(), z = vTo.getZ();
        vTo = new Vector(x*cos(a)+z*sin(a), y,-x*sin(a)+z*cos(a)); // rotation matrix for rotating in the axis
        x = vUp.getX(); y=  vUp.getY(); z = vUp.getZ();
        vUp = new Vector(x*cos(a)+z*sin(a), y,-x*sin(a)+z*cos(a)); // same what only with the second vector
        vRight  = vTo.crossProduct(vUp);
        return this;
    }

    /**
     * function that rotate the camera around the axis Z
     * @param aCel the angle (cel)
     * @return this. we want it to be a builder pattern
     */
    public Camera RotationOnZaxis(double aCel)
    {
        double a = toRadians(aCel);
        double x = vTo.getX(), y=  vTo.getY(), z = vTo.getZ();
        vTo = new Vector(x*cos(a)-y*sin(a), x*sin(a)+y*cos(a),z); // rotation matrix for rotating in the axis
        x = vUp.getX(); y=  vUp.getY(); z = vUp.getZ();
        vUp = new Vector(x*cos(a)-y*sin(a), x*sin(a)+y*cos(a),z); // same what only with the second vector
        vRight  = vTo.crossProduct(vUp);
        return this;
    }
}
