package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

/**
 * class that represents a cylinder, finite cylinder. this is a geometry object.
 */
public class Cylinder extends Tube {
    final private double height;

    /**
     * constructor that initialize the cylinder object
     * @param _radius the radius
     * @param _axisRay the axisRay
     * @param _height the height
     */
    Cylinder(double _radius, Ray _axisRay, double _height) {
        super(_radius, _axisRay);
        height = _height;
    }

    /**
     * getter for height
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = this.axisRay.getP0();
        Vector v = this.axisRay.getDir();
        double t = v.dotProduct(p.subtract(p0));
        if(t == this.height) // if the point is on base B return the dir
            return v;
        else if (t == 0) // if the point is on base A return the opposite of dir
            return v.scale(-1);
        else
            return super.getNormal(p);// if the point is not on the bases then return get normal of tube
    }
}
