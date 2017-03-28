package ca.team4519.FRC2017.auton.tasks;

public class WaitForDistanceTask extends TimeoutTask {

	double distance;
	boolean forwards;
	
	public WaitForDistanceTask(double dist, boolean direction, double timeout) {
		super(timeout);
		distance = dist;
		forwards = direction;
	}

	@Override
	public boolean isDone() { 
		return ((forwards ? drive.averageDistance() >= (distance-5) : drive.averageDistance() <= distance )|| super.isDone());
	}
}
