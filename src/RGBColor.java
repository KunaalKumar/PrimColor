public class RGBColor {
    private int r;
    private int g;
    private int b;
    private int count;

    public RGBColor(int r , int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        count++;
    }

    public void addCount(){
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public boolean isItSame(RGBColor col) {
        if(Math.abs(col.getRed() - r) <= 20) {
            if(Math.abs(col.getGreen() - g) <= 20) {
                if(Math.abs(col.getBlue() - b) <= 20) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean toAdd() {
        if(r == g && g == b) {
            return false;
        }
        if(r == 255 && g ==  255) {
            return b <= 100;
        }
        if(r == 255 && b == 255) {
            return g <= 100;
        }
        if(g == 255 && b == 255) {
            return r <= 100;
        }
        if(r / 100 == 2 && g / 100 == 2 && b /100 == 2) {
            return false;
        }
        return 130 <= r && r <= 255 && 130 <= g && g <= 255 && 130 <= b && b <= 255;
    }

    public String toString() {
        return "(" + r + ", " + g + ", " + b + ")";
    }

    public double getLuminance() {
        return ((r * 0.299) + (g * 0.587) + (b * 0.114) ) / 3;
    }
}
