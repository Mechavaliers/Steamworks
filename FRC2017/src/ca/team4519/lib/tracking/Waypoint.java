package ca.team4519.lib.tracking;

public class Waypoint {
		
	public final double x;
	public final double y;
	public final double angle;

	public Waypoint(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
		
	public Waypoint(double x, double y, double angle, String name){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	
}
