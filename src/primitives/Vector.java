package primitives;

import static java.lang.Math.sqrt;

public class Vector extends Point{
   public Vector(double x,double y,double z){
        super(x,y,z);
        Double3 _xyz=new Double3(x,y,z);
        if(_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }
   Vector(Double3 _xyz){
        super(_xyz);
        if(_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector is a zero vector!");
    }

   public Vector add(Vector vec){
        Point point=super.add(vec);
        return new Vector(point.xyz);
    }

   public Vector scale(double t){
        return new Vector((this.xyz.scale(t)));
    }

   public double dotProduct(Vector vec){
        return vec.xyz.d1*this.xyz.d1+vec.xyz.d2*this.xyz.d2+vec.xyz.d3*this.xyz.d3;
    }

  public  Vector crossProduct(Vector vec){
        return new Vector(this.xyz.d2*vec.xyz.d3-this.xyz.d3*vec.xyz.d2 , this.xyz.d3*vec.xyz.d1-this.xyz.d1*vec.xyz.d3  ,this.xyz.d1*vec.xyz.d2-this.xyz.d2*vec.xyz.d1  );
    }

   public double lengthSquared(){
        return this.dotProduct(this);
    }

   public  double length(){
        return sqrt(lengthSquared());
    }

    public Vector normalize(){
        return scale(1/ length() );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return super.equals(obj);
        return false;
    }
    @Override
    public String toString() { return super.toString(); }
}
