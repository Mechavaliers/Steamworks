package ca.team4519.lib.tracking;

import ca.team4519.lib.pid.PID;


public class PathFollower {
	
	Path pathToFollow;
	PID leftController, rightController;
	double currentAngle;
	double leftGoal, rightGoal, angleGoal;
	
	boolean isEnabled = false;
	

	public PathFollower(Path path, PID left, PID right, double gyroAngle){
		this.pathToFollow = path;
		this.leftController = left;
		this.rightController = right;
		this.currentAngle = gyroAngle;
	}
	
	
	public void calculate(){
		
		
		
		
		if(isEnabled){
			leftController.setDistance(leftGoal);
			rightController.setDistance(rightGoal);
			
		}
	}
	
}
