package Model;

public class Lens {
    private String make;
    private double maxAperture;
    private int focalLength;    // in mm

    public Lens(String make, double aperature, int focalLength) {
        this.make = make;
        this.maxAperture = aperature;
        this.focalLength = focalLength;
    }

    public String getMake() {
        return make;
    }

    public double getAperature() {
        return maxAperture;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setAperature(double aperature) {
        this.maxAperture = aperature;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public void show() {
        System.out.println(make + ", " + maxAperture + ", " + focalLength);
    }


    @Override
    public String toString() {
        return
                make +" "+
                 focalLength +" F"+
                        maxAperture
                ;
    }
}
// This is my lens class
//
