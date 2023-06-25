package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class for representing the scene(PDS)
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();
    /**
     * constructor for Scene
     * @param _name the name of the scene
     */
    public Scene(String _name){
        geometries = new Geometries();
    }
    /**
     * setter for name
     * @param _name the name
     * @return this
     */
    public Scene setName(String _name){
        name = _name;
        return this;
    }
    /**
     * setter for background
     * @param _background the background
     * @return this
     */
    public Scene setBackground(Color _background){
        background = _background;
        return this;
    }
    /**
     * setter for ambientLight
     * @param _ambientLight the ambientLight
     * @return this
     */
    public Scene setAmbientLight(AmbientLight _ambientLight){
        ambientLight = _ambientLight;
        return this;
    }
    /**
     * setter for geometries
     * @param _geometries the geometries
     * @return this
     */
    public Scene setGeometries(Geometries _geometries){
        geometries = _geometries;
        return this;
    }

    /**
     * setter for lights
     * @param _lights the lights that are given
     * @return this
     */
    public Scene setLights(List<LightSource> _lights){
        lights=_lights;
        return this;
    }

}
