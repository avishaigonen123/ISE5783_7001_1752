package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for directionalLight
 */
public class DirectionalLight extends Light implements LightSource{
    private final Vector direction;

    /**
     * constructor for DirectionalLight
     * @param _intensity the intensity
     * @param _direction the direction
     */
    public DirectionalLight(Color _intensity, Vector _direction){
        super(_intensity);
        direction=_direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
    @Override
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
}

