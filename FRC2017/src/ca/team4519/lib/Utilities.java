package ca.team4519.lib;

public class Utilities {

	public static double clamp(double input, double maxValue){
		return (Math.abs(input) < maxValue) ? input : maxValue * (input < 0 ? -1 : 1);
	}
	
}
