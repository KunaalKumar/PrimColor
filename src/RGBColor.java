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

    public boolean clean() {
//        return r <= 100 && g <= 100 && b <= 100 || r >= 200 && g >= 200 && b >= 200;
        return 200 <= r && r <= 255 && 200 <= g && g <= 255 && 200 <= b && b <= 255 || r <= 100 && g <= 100 && b <= 100 ;
    }

    public String toString() {
        return "(" + r + ", " + g + ", " + b + ")";
    }
}
