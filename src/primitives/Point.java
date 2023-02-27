package primitives;


import javax.swing.*;

import static java.lang.Math.sqrt;

public class Point {
   final Double3 xyz;
   public Point(double x, double y, double z){
       xyz=new Double3(x,y,z);
   }
   Point(Double3 _xyz){
       xyz=_xyz;
   }

    public Vector subtract(Point point){
       return new Vector(this.xyz.subtract(point.xyz));
    }

    Point add(Vector vector){
       return new Point(this.xyz.add(vector.xyz));
   }

   double distanceSquared(Point point){
       return (point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1)+(point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1)+(point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1);
   }

   double distance(Point point){
       return sqrt(distanceSquared(point));
   }
}
