package geometries;

import jdk.jshell.spi.ExecutionControl;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
    final protected Ray axisRay;
    Tube(double _radius, Ray _axisRay){
        super(_radius);
        axisRay=_axisRay;
    }
   public Vector getNormal(Point p){
        throw new ExecutionControl.NotImplementedException();
    }
}
