import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

//TODO: Instead of looking for most popular color, look for most distinctive color.
public class Main {
    public static void main(String[] args) throws IOException {

        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg");
            }
        });
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String fileC = dialog.getDirectory() + dialog.getFile();
        System.out.println(fileC + " Chosen");
        File file = new File(fileC);
        BufferedImage image = ImageIO.read(file);
        ArrayList<RGBColor> colorList = new ArrayList<>();
        Color[][] c = new Color[image.getWidth()][image.getHeight()];
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                int  red   = (color & 0x00ff0000) >> 16;
                int  green = (color & 0x0000ff00) >> 8;
                int  blue  =  color & 0x000000ff;
                RGBColor tempColor = new RGBColor(red, green, blue);
                c[i][j] = new Color(image.getRGB(i, j));

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

        // Clean up list for whites, blacks and other dulls.
        for(int i = 0; i < colorList.size(); i++) {
            System.out.println("Checking" + colorList.get(i));
            if(colorList.get(i).toAdd()) {
                System.out.println("Adding " + colorList.get(i));
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

        ColorPalette primCol = new ColorPalette(mostPop);
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, primCol.getColor(c[i][j]).getRGB());
            }
        }

        File output = new File(dialog.getFile() + "-Modded");
        ImageIO.write(image, "jpg", output);

    }
}
