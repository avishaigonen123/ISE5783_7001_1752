package primitives;



import static java.lang.Math.sqrt;

public class Point {
   protected final Double3 xyz;
   public Point(double x, double y, double z){
       xyz=new Double3(x,y,z);
   }
   Point(Double3 _xyz){
       xyz=_xyz;
   }

    public Vector subtract(Point point){
       return new Vector(this.xyz.subtract(point.xyz));
    }

   public Point add(Vector vector){
       return new Point(this.xyz.add(vector.xyz));
   }

   public double distanceSquared(Point point){
       return (point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1)+(point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1)+(point.xyz.d1-this.xyz.d1)*(point.xyz.d1-this.xyz.d1);
   }

   public double distance(Point point){
       return sqrt(distanceSquared(point));
   }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    @Override
    public String toString() { return xyz.toString(); }
}
