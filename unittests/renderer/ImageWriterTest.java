package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class that test image writer
 */
class ImageWriterTest {
    /**
     * func that test write to image {@link ImageWriter#writeToImage()}
     */
    @Test
    void writeToImage() {
        // imageTest simple grid
        ImageWriter imageWriter = new ImageWriter("simpleGrid",800, 500);
        for(int i=0; i<500;i++)
            for (int j=0; j<800;j++) {
                if(i%50 == 0 || j%50 == 0)
                    imageWriter.writePixel(j,i, Color.BLUE);
                else
                    imageWriter.writePixel(j,i, Color.YELLOW);
            }
        imageWriter.writeToImage();
    }
}