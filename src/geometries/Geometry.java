package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * interface that represents all the geometries objects in our project
 */
public abstract class Geometry extends Intersectable {
   protected Color emission = Color.BLACK;
   private Material material=new Material();
   /**
    * a func to be implemented to get the normal in a point
    * assumes that the point is on the surface
    * @param point the point
    * @return the normal to the surface
    */
   public abstract Vector getNormal(Point point);
   /**
    * get the emissen
    * @return the emmishen
    */
   public Color getEmission() {
      return emission;
   }

   /**
    * set the emission
    * @param _emission the emission
    * @return this
    */
   public Geometry setEmission(Color _emission){
      emission = _emission;
      return this;
   }

   /**
    * getter for material
    * @return the material
    */
   public Material getMaterial(){
      return material;
   }

   /**
    * setter for material
    * @param _material the given material
    * @return this
    */
   public Geometry setMaterial(Material _material){
      material = _material;
      return this;
   }
}
