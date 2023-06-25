package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

import static java.lang.Math.pow;


/**
 * class for SpotLight
 */
public class SpotLight extends PointLight {
    private final Vector direction;
    private double nRestriction = 1;

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

    /**
     * setter for nRestriction
     * @param nRestriction the nRestriction
     * @return this
     */
    public SpotLight setNarrowBeam(double nRestriction) {
        this.nRestriction = nRestriction;
        return this;
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    @Override
    public Color getIntensity(Point p) {
        double angle = direction.dotProduct(getL(p)); // dir*l
        return super.getIntensity(p).scale(pow((angle>0?angle:0),nRestriction)); // [ I0 / (kC + kL*d + kQ*(d^2)) ] * max(0,dir*l)
        // nRestriction is like nShininess
    }

}
