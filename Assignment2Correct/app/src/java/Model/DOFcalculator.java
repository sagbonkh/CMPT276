package Model;

import static java.lang.Math.pow;

public class DOFcalculator {
    private double  CircleOFConfusion =  0.029;
    private double hyperfocaldistance;
    private double nearfocalpoint;
    private double farfocalpoint;
    private double depthOfField;
    private double distance;
    private double aperature;
    private Lens len;

    public DOFcalculator(double distance, double aperature, Lens len) {
        this.distance = distance;
        this.aperature = aperature;
        this.len = len;
    }

    public double getHyperfocaldistance() {
        hyperfocaldistance = (pow(len.getFocalLength(),2)/(aperature*CircleOFConfusion));
        return hyperfocaldistance;
    }

    public double getNearfocalpoint() {
        nearfocalpoint = (getHyperfocaldistance() * distance) /
                (getHyperfocaldistance() + (distance - len.getFocalLength()));
        return nearfocalpoint;
    }

    public double getFarfocalpoint() {
            farfocalpoint = (getHyperfocaldistance() * distance) /
                    (hyperfocaldistance - (distance - len.getFocalLength()));

        if(distance > hyperfocaldistance) {
            farfocalpoint = Double.POSITIVE_INFINITY ;
        }

        return farfocalpoint;
    }

    public double getDepthOfField() {
        depthOfField = getFarfocalpoint() - getNearfocalpoint();
        return depthOfField;
    }
}
