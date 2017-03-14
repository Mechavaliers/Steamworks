package ca.team4519.FRC2017.subsystems.controllers;

import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.Path;

import ca.team4519.FRC2017.Gains;
import ca.team4519.FRC2017.subsystems.Drivebase.Controllers;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;

public class DrivePathController implements Controllers{

	//Trajectory trajectory;
	
	private TrajectoryFollowingController leftController;
	private TrajectoryFollowingController rightController;
	
	public DrivePathController(Path path){
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Drive.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Drive.ROBOT_MAX_ACCELERATION;
		configuration.max_vel = Gains.Drive.ROBOT_MAX_VELOCITY;
		leftController = new TrajectoryFollowingController(
				Gains.Drive.Dist_P, 
				Gains.Drive.Dist_I, 
				Gains.Drive.Dist_D, 
				Gains.Drive.Dist_V, 
				Gains.Drive.Dist_A, 
				Gains.Drive.Dist_Tollerance, 
				configuration);
		rightController = new TrajectoryFollowingController(
				Gains.Drive.Dist_P, 
				Gains.Drive.Dist_I, 
				Gains.Drive.Dist_D, 
				Gains.Drive.Dist_V, 
				Gains.Drive.Dist_A, 
				Gains.Drive.Dist_Tollerance, 
				configuration);
		
		//leftController.
		
	}
	
	@Override
	public DrivetrainOutput update(RobotPose pose) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
