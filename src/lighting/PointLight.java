package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for PointLight
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC=1, kL=0, kQ=0;

    /**
     * constructor for Pointigt
     *
     * @param _position  the position
     * @param _intensity the intensity
     */
    public PointLight(Color _intensity, Point _position) {
        super(_intensity);
        position = _position;
    }

    /**
     * setter for kC
     *
     * @param _kC the kC we get
     * @return the kC we return
     */
    public PointLight setKc(double _kC) {
        kC = _kC;
        return this;
    }


    /**
     * setter for kL
     *
     * @param _kL the kL we get
     * @return the kL we return
     */
    public PointLight setKl(double _kL) {
        kL = _kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param _kQ the kQ we get
     * @return the kQ we return
     */
    public PointLight setKq(double _kQ) {
        kQ = _kQ;
        return this;
    }
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double d =  p.distance(position); // double d = distance
        return getIntensity().scale(1/(kC+kL*d+kQ*d*d)); // I0 / (kC + kL*d + kQ*(d^2))
    }
}
