package ca.team4519.lib.tracking;

public class Point {
		
	public final double x;
	public final double y;
	public final double angle;

	public Point(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
		
	public Point(double x, double y, double angle, String name){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	
}
