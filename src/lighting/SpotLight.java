package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;


/**
 * class for SpotLight
 */
public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * constructor for PointLight
     *
     * @param _intensity the intensity
     * @param _position  the position
     * @param _direction the direction
     */
    public SpotLight(Color _intensity, Point _position, Vector _direction) {
        super(_intensity, _position);
        direction = _direction.normalize();
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    @Override
    public Color getIntensity(Point p) {
        double angle = direction.dotProduct(getL(p)); // dir*l
        return super.getIntensity(p).scale((angle>0?angle:0)); // [ I0 / (kC + kL*d + kQ*(d^2)) ] * max(0,dir*l)
    }
}
