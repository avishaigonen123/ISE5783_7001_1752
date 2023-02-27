package primitives;

import static java.lang.Math.sqrt;

public class Vector extends Point{
    Vector(double x,double y,double z){
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

    Vector add(Vector vec){
        Point point=super.add(vec);
        return new Vector(point.xyz);
    }

    Vector scale(double t){
        return new Vector((this.xyz.scale(t)));
    }

    double dotProduct(Vector vec){
        return vec.xyz.d1*this.xyz.d1+vec.xyz.d2*this.xyz.d2+vec.xyz.d3*this.xyz.d3;
    }

    Vector crossProduct(Vector vec){
        return new Vector(this.xyz.d2*vec.xyz.d3-this.xyz.d3*vec.xyz.d2 , this.xyz.d1*vec.xyz.d3-this.xyz.d3*vec.xyz.d1  ,this.xyz.d1*vec.xyz.d2-this.xyz.d2*vec.xyz.d1  );
    }

    double lengthSquared(){
        return this.dotProduct(this);
    }

    double length(){
        return sqrt(lengthSquared());
    }

    Vector normalize(){
        return scale(1/ length() );
    }


}
