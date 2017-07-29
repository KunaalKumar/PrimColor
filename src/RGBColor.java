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
        if(Math.abs(col.getRed() - r) <= 10) {
            if(Math.abs(col.getGreen() - g) <= 10) {
                if(Math.abs(col.getBlue() - b) <= 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean clean() {
        if(r == 255) {
            if(g == 255) {
                if(b == 255) {
                    return true;
                }
            }
        }
        else if(r == 0) {
            if(g == 0) {
                if(b == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
