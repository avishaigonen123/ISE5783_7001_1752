package primitives;

public class Ray {
    private final Point p0;
    private final Vector dir;

    Ray(Point p, Vector vec){
        p0=p;
        dir = vec.normalize();
    }


}
