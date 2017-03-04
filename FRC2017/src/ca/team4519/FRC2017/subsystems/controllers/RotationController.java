package ca.team4519.FRC2017.subsystems.controllers;

import com.team254.lib.trajectory.TrajectoryFollower;

import ca.team4519.FRC2017.Gains;
import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;

public class RotationController implements Drivebase.Controllers{
	
	private final TrajectoryFollowingController controller;
	
	public RotationController(RobotPose startingPos, double angleGoal, double maxVel){
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Drive.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Drive.ROBOT_MAX_ROTATIONAL_ACCELERATION;
		configuration.max_vel = maxVel;
		controller = new TrajectoryFollowingController(
				Gains.Drive.Turn_P,
				Gains.Drive.Turn_I,
				Gains.Drive.Turn_D,
				Gains.Drive.Turn_V,
				Gains.Drive.Turn_A,
				Gains.Drive.Turn_Tollerance,
				configuration);
		
		TrajectoryFollower.TrajectorySetpoint startingPosition = new TrajectoryFollower.TrajectorySetpoint();
		startingPosition.pos = startingPos.getAngle();
		startingPosition.vel = startingPos.getAngularVelocity();
		controller.setTarget(startingPosition, angleGoal);
	}
	
	@Override
	public DrivetrainOutput update(RobotPose pose) {
		controller.update(pose.getAngle(), pose.getAngularVelocity());
		double turn = controller.get();
		return new DrivetrainOutput(-turn, turn);
	}

}
