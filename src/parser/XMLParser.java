package parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import scene.Scene;

import java.io.File;

public class XMLParser {
    private Scene scene;
    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/XMLFiles";


    public Scene InitializeXMLScene(String fileName) {
        try {

            // Create a document builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Use the factory to create a document builder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parse the XML file and create a DOM tree
            scene = new Scene(fileName); // we create the scene
            scene.name = fileName;
            String filePath = FOLDER_PATH + '/' + fileName; // calculate the file path
            Document doc = builder.parse(new File(filePath));
            // Get the root element of the document
            Element root = doc.getDocumentElement();
            // Print out the root element's tag name
            String backgroundText = root.getAttribute("background-color"); // get the text of the attribute background
            scene.background = ConvertToColor(backgroundText); // convert it into color, and move it into the scene

            Node ambientLightNode = root.getElementsByTagName("ambient-light").item(0); // get the first node, which is ambient light
            Element ambientLightElement = (Element) ambientLightNode; // convert it to element, in order to see its attributes.
            String ambientLightText = ambientLightElement.getAttribute("color"); // get his attribute, color
            scene.ambientLight = new AmbientLight(ConvertToColor(ambientLightText), new Double3(1, 1, 1)); // create new ambient light, ka = (1,1,1).

            Node geometriesNode = root.getElementsByTagName("geometries").item(0); // get the node of the geometries
            NodeList list = geometriesNode.getChildNodes(); // get the list of the geometries

            for (int i = 1; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String tagName = element.getTagName();
                    if (tagName == "sphere") { // if the geometry is sphere, so we would do like that
                        String centerText = element.getAttribute("center"); // take the attributes
                        String radiusText = element.getAttribute("radius");
                        scene.geometries.add(ConvertToSphere(centerText, radiusText)); // create the sphere
                    } else if (tagName == "triangle") {
                        String p0Text = element.getAttribute("p0"); // take the attributes
                        String p1Text = element.getAttribute("p1");
                        String p2Text = element.getAttribute("p2");
                        scene.geometries.add(ConvertToTriangle(p0Text, p1Text, p2Text)); // create the triangle
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scene; // return the scene
    }

    /**
     * convert string to sphere
     *
     * @param center the text that presents center
     * @param radius same for radius
     * @return the sphere
     */
    private Sphere ConvertToSphere(String center, String radius) {
        String[] str1 = center.split(" ");
        return new Sphere(Double.parseDouble(radius), new Point(Double.parseDouble(str1[0]), Double.parseDouble(str1[1]), Double.parseDouble(str1[2])));
    }

    /**
     * convert string to triangle
     *
     * @param p1 the text of point 1
     * @param p2 same 2
     * @param p3 same 3
     * @return the triangle
     */
    private Triangle ConvertToTriangle(String p1, String p2, String p3) {
        String[] str1 = p1.split(" ");
        String[] str2 = p2.split(" ");
        String[] str3 = p3.split(" ");
        return new Triangle(
                new Point(Double.parseDouble(str1[0]), Double.parseDouble(str1[1]), Double.parseDouble(str1[2])),
                new Point(Double.parseDouble(str2[0]), Double.parseDouble(str2[1]), Double.parseDouble(str2[2])),
                new Point(Double.parseDouble(str3[0]), Double.parseDouble(str3[1]), Double.parseDouble(str3[2]))
        );
    }

    /**
     * help function for convert from string to Double3
     *
     * @param text the string
     * @return the double3
     */
    private Double3 ConvertToDouble3(String text) {
        String[] str = text.split(" ");
        return new Double3(Double.parseDouble(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2]));
    }

    /**
     * help function for convert from string to color
     *
     * @param double3 the string
     * @return the color
     */
    private Color ConvertToColor(String double3) {
        String[] str1 = double3.split(" ");

        return new Color(Double.parseDouble(str1[0]), Double.parseDouble(str1[1]), Double.parseDouble(str1[2]));
    }
}
