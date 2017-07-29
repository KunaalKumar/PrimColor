import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("Image/flowerY.jpg");
        BufferedImage image = ImageIO.read(file);
        ArrayList<RGBColor> colorList = new ArrayList<RGBColor>();

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                int  red   = (color & 0x00ff0000) >> 16;
                int  green = (color & 0x0000ff00) >> 8;
                int  blue  =  color & 0x000000ff;
                RGBColor tempColor = new RGBColor(red, green, blue);
                if(colorList.size() == 0) {
                    colorList.add(tempColor);
                }
                else {
                    boolean foundSame = false;
                    for(int k = 0; k < colorList.size(); k++) {
                        if(colorList.get(k).isItSame(tempColor) == true) {
                            colorList.get(k).addCount();
                            foundSame = true;
                            break;
                        }
                    }
                    if(!foundSame) {
                        colorList.add(tempColor);
                    }
                }
            }
        }

        RGBColor mostPop = null;
        for(int i = 0; i < colorList.size(); i++) {
            if(mostPop == null) {
                mostPop = colorList.get(i);
            }
            else {
                if(colorList.get(i).getCount() > mostPop.getCount()) {
                    mostPop = colorList.get(i);
                }
            }
        }

        System.out.println("(" + mostPop.getRed() + ", " + mostPop.getGreen() + ", " + mostPop.getBlue() + ")");

    }
}
