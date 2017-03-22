package ca.team4519.lib.motion;

public class distanceAndAngle {
		
	double distance;
	double angle;
		
	public distanceAndAngle() {
		distance = 0;
		angle = 0;
	}
		
	public distanceAndAngle(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}
		
	public distanceAndAngle(distanceAndAngle d_A_A) {
		distance = d_A_A.distance;
		angle = d_A_A.angle;
	}
		
	public double getDistance() {
		return distance;
	}
	
	public double getAngle() {
		return angle;
	}
	
}