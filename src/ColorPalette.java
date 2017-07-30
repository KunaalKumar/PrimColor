import java.awt.*;

public class ColorPalette {
    private RGBColor mainColor;
    private double defLuminance;
    private double[] lumTintList = new double[9];
    private double[] lumShadeList = new double[9];

    public ColorPalette(RGBColor mainColor) {
        this.mainColor = mainColor;
        defLuminance = mainColor.getLuminance();
        compileLumTintList();
        compileLumShadeList();
    }

    private void compileLumTintList() {
         for(int i = 0; i < 9; i++) {
            Color temp = tint(i * 0.1);
            lumTintList[i] = new RGBColor(temp.getRed(), temp.getGreen(), temp.getBlue()).getLuminance();
         }
    }

    private void compileLumShadeList() {
        for(int i = 0; i < 9; i++) {
            Color temp = shade(i * 0.1);
            lumShadeList[i] = new RGBColor(temp.getRed(), temp.getGreen(), temp.getBlue()).getLuminance();
        }
    }

    public Color getColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        RGBColor temp = new RGBColor(r, g, b);
        double lum = temp.getLuminance();
//        if(r == 255 && g == 255 && b == 255 || r == 0 && g == 0 && b ==0) {
//            return color;
//        }
        if(defLuminance == lum) {
            return new Color(mainColor.getRed(), mainColor.getGreen(), mainColor.getBlue());
        }
        else if(defLuminance > lum) {
            double st = getClosestShadeStep(lum);
            return shade(st);
        }
        else {
            double st = getClosestTintStep(lum);
            return tint(st);
        }
    }

    private Color tint(double step) {
        return new Color((int) Math.ceil(mainColor.getRed() + ((255 - mainColor.getRed()) * step)), (int) Math.ceil(mainColor.getGreen() + ((255 - mainColor.getGreen()) * step)), (int) Math.ceil(mainColor.getBlue() + ((255 - mainColor.getBlue()) * step)));
    }

    private Color shade(double step) {
        return new Color((int) Math.ceil(mainColor.getRed() * (1 - step)), (int) Math.ceil(mainColor.getGreen() * (1 - step)), (int) Math.ceil(mainColor.getBlue() * (1 - step)));
    }

    private double getClosestShadeStep(double lum) {
        double smallestDif = Math.abs(lum - lumShadeList[0]);
        double currentDif;
        double step = 0;
        for(int i = 1; i < 9; i++) {
            currentDif = Math.abs(lum - lumShadeList[i]);
            if(currentDif <= smallestDif) {
                step = i * 0.1;
                smallestDif = currentDif;
            }
        }
        return step;
    }

    private double getClosestTintStep(double lum) {
        double smallestDif = Math.abs(lum - lumTintList[0]);
        double currentDif;
        double step = 0;
        for(int i = 1; i < 9; i++) {
            currentDif = Math.abs(lum - lumTintList[i]);
            if(currentDif <= smallestDif) {
                step = i * 0.1;
                smallestDif = currentDif;
            }
        }
        return step;
    }

}
