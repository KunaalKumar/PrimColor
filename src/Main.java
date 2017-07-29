import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

//TODO: Add logic to ignore extreme darks and whites.
//TODO: Make a color palette based on primary color.
//TODO: Recreate image using colors from new color palette.
public class Main {
    public static void main(String[] args) throws IOException {

        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String fileC = dialog.getDirectory() + dialog.getFile();
        System.out.println(fileC + " Chosen");
        File file = new File(fileC);
        BufferedImage image = ImageIO.read(file);
        ArrayList<RGBColor> colorList = new ArrayList<>();

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
                        if(colorList.get(k).isItSame(tempColor)) {
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

        ArrayList<RGBColor> newUpList = new ArrayList<>();
        // Clean up list for whites and blacks
        for(int i = 0; i < colorList.size(); i++) {
            System.out.println("Checking" + colorList.get(i));
            if(!colorList.get(i).clean()) {
                newUpList.add(colorList.get(i));
            }
        }

        for(int i = 0; i < newUpList.size(); i++) {
            if(mostPop == null) {
                mostPop = newUpList.get(i);
            }
            if(newUpList.get(i).getCount() > mostPop.getCount()) {
                mostPop = newUpList.get(i);
            }
        }

        System.out.println(mostPop.toString() + " wins with a count of " +mostPop.getCount());

    }
}
