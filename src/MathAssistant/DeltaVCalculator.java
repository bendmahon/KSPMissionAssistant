package MathAssistant;

public class DeltaVCalculator {
    public static String getDeltaV(double isp, double fuelUsed, double initialWeight){
        double massRatio = initialWeight / (initialWeight - (fuelUsed/90));
        double deltaV = (9.81*isp*Math.log(massRatio));
        return String.format("%.2f", deltaV);
    }
}
