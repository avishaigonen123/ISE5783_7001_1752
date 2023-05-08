package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * class to present AmbientLight
 */

public class AmbientLight {
    private Color intensity;
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
        intensity = ia.scale(ka);
    }
    /**
     * constructor for AmbientLight
     * @param ka attenuation coefficient
     */
    public AmbientLight(double ka){
        intensity.scale(ka);
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity(){
        return intensity;
    }
}
