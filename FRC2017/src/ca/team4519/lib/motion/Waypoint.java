package ca.team4519.lib.motion;

import java.util.Optional;

public class Waypoint {

	public final Coords position;
	public final double speed;
	public final Optional<String> posName;
	
	public Waypoint(Coords position, double speed) {
		this.position = position;
		this.speed = speed;
		this.posName = Optional.empty();
	}
	
	public Waypoint(Coords position, double speed, String name) {
		this.position = position;
		this.speed = speed;
		this.posName = Optional.of(name);
	}
	
	public double getAngle() {
		return position.getAngle();
	}
	
	public double getAngle(Coords position){
		return position.getAngle();
	}
	
	public double getDist(Coords position){
		return position.getHypot();
	}
	
}
