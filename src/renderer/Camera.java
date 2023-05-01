package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static primitives.Util.isZero;

/**
 * Class that presents our camera
 */
public class Camera {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

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
        Point pC = p0.add(vTo.scale(distance));
        double rY = height/nY;
        double rX = width/nX;
        double yI = -(i-(double)(nY-1)/2)*rY;
        double xJ = (j-(double)(nX-1)/2)*rX;
        Point pIJ = pC;
        if(xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if(yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(p0, pIJ.subtract(p0));
    }

}
