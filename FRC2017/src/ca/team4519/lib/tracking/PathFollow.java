package ca.team4519.lib.tracking;

import java.util.List;

public class PathFollow {

	List<Waypoint> waypoints;
	
	public static class Waypoint {
		
		public final double x;
		public final double y;

		public Waypoint(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	public PathFollow(List<Waypoint> waypoints){
		
	}
	
}
