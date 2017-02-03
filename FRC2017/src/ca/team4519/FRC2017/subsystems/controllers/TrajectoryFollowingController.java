package ca.team4519.FRC2017.subsystems.controllers;

import com.team254.lib.trajectory.TrajectoryFollower;

import ca.team4519.lib.Controller;

public class TrajectoryFollowingController extends Controller{

	TrajectoryFollower tController;
	double goal;
	double error;
	double tollerance;
	double result = 0;
	
	public TrajectoryFollowingController(double kP, double kI, double kD, double kV, double kA, double tollerance, TrajectoryFollower.TrajectoryConfig config){
	
		tController = new TrajectoryFollower();
		tController.configure(kP, kI, kD, kV, kA, config);
		this.tollerance = tollerance;

	}
	
	public void setTarget(TrajectoryFollower.TrajectorySetpoint robotState, double goal){
		this.goal = goal;
		tController.setGoal(robotState, goal);
	}
	
	public void configureController(TrajectoryFollower.TrajectoryConfig configuration){
		tController.setConfig(configuration);
	}

	public void update(double position, double velocity){
		error = goal - position;
		result = tController.calculate(position, velocity);
	}
	
	public double get(){
		return result;
	}
	
	@Override
	public void reset(){
		result = 0;
		error = 0;
		tController.setGoal(tController.getCurrentSetpoint(), goal);
		
	}

	@Override
	public boolean onTarget(){
	
		return tController.isFinishedTrajectory() 
				&& Math.abs(error) < tollerance;
		
		}
}
