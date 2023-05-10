package primitives;

/**
 * class to present material
 */
public class Material {
    public Double3 kD=Double3.ZERO, kS=Double3.ZERO;
    public int nShininess=0;

    /**
     * setter for kD
     * @param _kD the kD that is given
     * @return this
     */
    public Material setKd(Double3 _kD){
        kD = _kD;
        return this;
    }
    /**
     * setter for kD
     * @param _kD the kD that is given
     * @return this
     */
    public Material setKd(double _kD){
        kD = new Double3(_kD);
        return this;
    }
    /**
     * setter for kS
     * @param _kS the kD that is given
     * @return this
     */
    public Material setKs(Double3 _kS){
        kS = _kS;
        return this;
    }
    /**
     * setter for kS
     * @param _kS the kD that is given
     * @return this
     */
    public Material setKs(double _kS){
        kS = new Double3(_kS);
        return this;
    }

    /**
     * setter for nShininess
     * @param _nShininess the given nShininess
     * @return this
     */
    public Material setShininess(int _nShininess){
        nShininess = nShininess;
        return this;
    }

}
