package primitives;

/**
 * class to present material
 */
public class Material {
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
    public int nShininess = 0;
    /**
     * kT is for the tranperancy, and kR is for the reflection
     */
    public Double3 kT = Double3.ZERO, kR = Double3.ZERO;

    /**
     * setter for kD
     * @param _kD the kD that is given
     * @return this
     */
    public Material setKd(Double3 _kD) {
        kD = _kD;
        return this;
    }

    /**
     * setter for kD
     * @param _kD the kD that is given
     * @return this
     */
    public Material setKd(double _kD) {
        kD = new Double3(_kD);
        return this;
    }

    /**
     * setter for kS
     * @param _kS the kD that is given
     * @return this
     */
    public Material setKs(Double3 _kS) {
        kS = _kS;
        return this;
    }

    /**
     * setter for kS
     * @param _kS the kD that is given
     * @return this
     */
    public Material setKs(double _kS) {
        kS = new Double3(_kS);
        return this;
    }

    /**
     * setter for kT
     * @param _kT the kT
     * @return this
     */
    public Material setKt(Double3 _kT) {
        kT = _kT;
        return this;
    }

    /**
     * setter for kR
     * @param _kR the kR
     * @return this
     */
    public Material setKr(Double3 _kR) {
        kR = _kR;
        return this;
    }

    /**
     * setter for kT
     * @param _kT the kT
     * @return this
     */
    public Material setKt(double _kT) {
        kT = new Double3(_kT, _kT, _kT);
        return this;
    }

    /**
     * setter for kR
     * @param _kR the kR
     * @return this
     */
    public Material setKr(double _kR) {
        kR = new Double3(_kR, _kR, _kR);
        return this;
    }

    /**
     * setter for nShininess
     * @param _nShininess the given nShininess
     * @return this
     */
    public Material setShininess(int _nShininess) {
        nShininess = _nShininess;
        return this;
    }

}
