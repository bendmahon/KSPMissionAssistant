package MathAssistant;

public class DeltaVCalculator {
    public static String getDeltaV(double isp, double fuelUsed, double initialMass){
        double massRatio = initialMass / (initialMass - fuelUsed);
        double deltaV = (9.81*isp*Math.log(massRatio));
        return String.format("%.4f", deltaV);
    }
    public static boolean isItEnough(double dV, String origin, String destination){
        return false;
    }
}
