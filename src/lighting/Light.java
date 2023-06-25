package lighting;

import primitives.Color;

/**
 * class for light
 */
abstract class Light {
    private final Color intensity;

    /**
     * constructor for light class
     * @param _intensity the intensity
     */
    protected Light(Color _intensity){
        intensity=_intensity;
    }

    /**
     * getter for intensity
     * @return the intensity
     */
    public Color getIntensity(){
        return intensity;
    }
}
