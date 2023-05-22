package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * class to present AmbientLight
 */

public class AmbientLight extends Light{
    /**
     * background for the sean
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    /**
     * constructor for AmbientLight
     * @param ia intensity
     * @param ka attenuation coefficient
     */
    public AmbientLight(Color ia, Double3 ka){
        super(ia.scale(ka));
    }
    /**
     * constructor for AmbientLight
     * @param ia intensity
     * @param ka attenuation coefficient
     */
    public AmbientLight(Color ia, double ka){
        super(ia.scale(ka));
    }

}
