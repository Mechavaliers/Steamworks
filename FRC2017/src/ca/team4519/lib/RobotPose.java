package ca.team4519.lib;

public class RobotPose {

	public double leftDistance;
	public double rightDistance;
	public double leftVelocity;
	public double rightVelocity;
	public double angularPosition;
	public double angularVelocity;
	
	
	public RobotPose(double leftDist, double rightDist, double leftVel,
					 double rightVel, double angularPos, double angularVel){
		this.leftDistance = leftDist;
		this.rightDistance = rightDist;
		this.leftVelocity = leftVel;
		this.rightVelocity = rightVel;
		this.angularPosition = angularPos;
		this.angularVelocity = angularVel;
	}
	
	public void reset(double leftDist, double rightDist, double leftVel,
			 double rightVel, double angularPos, double angularVel){
		this.leftDistance = leftDist;
		this.rightDistance = rightDist;
		this.leftVelocity = leftVel;
		this.rightVelocity = rightVel;
		this.angularPosition = angularPos;
		this.angularVelocity = angularVel;
	}
	
	public double getAverageDistance() {
		return (getLeftDistance() + getRightDistance()) / 2;
	}
	
	public double getAverageVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
	
	public double getLeftDistance(){
		return leftDistance;
	}
	
	public double getRightDistance(){
		return rightDistance;
	}
	
	public double getLeftVelocity(){
		return leftVelocity;
	}
	
	public double getRightVelocity(){
		return rightVelocity;
	}
	
	public double getAngle(){
		return angularPosition;
	}
	
	public double getAngularVelocity(){
		return angularVelocity;
	}
}
