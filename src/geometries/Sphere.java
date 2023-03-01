package geometries;

import jdk.jshell.spi.ExecutionControl;
import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
    final private Point center;
    Sphere(double _radius, Point _center){
        super(_radius);
        center=_center;
    }
   public Vector getNormal(Point p){
        return new ExecutionControl.NotImplementedException();
    }
}
