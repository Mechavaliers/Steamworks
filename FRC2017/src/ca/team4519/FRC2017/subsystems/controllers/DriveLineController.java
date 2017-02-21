package ca.team4519.FRC2017.subsystems.controllers;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Drivebase.Controllers;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.pid.TurningPID;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.trajectory.TrajectoryFollower.TrajectorySetpoint;

import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;

public class DriveLineController implements Controllers{

	private TrajectoryFollowingController controller;
	private TurningPID turningPIDLoop;
	
	public DriveLineController(RobotPose startingPos, double goalPos, double maxVel){
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Drive.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Drive.ROBOT_MAX_ACCELERATION;
		configuration.max_vel = maxVel;
		
		controller = new TrajectoryFollowingController(
				Gains.Drive.Dist_P, 
				Gains.Drive.Dist_I, 
				Gains.Drive.Dist_D, 
				Gains.Drive.Dist_V, 
				Gains.Drive.Dist_A, 
				Gains.Drive.Dist_Tollerance, 
				configuration);
		
		TrajectorySetpoint startingPosition = new TrajectorySetpoint();
		startingPosition.pos = encoderDistance(startingPos);
		startingPosition.vel = encoderVelocity(startingPos);
		controller.setTarget(startingPosition, goalPos);
		
		turningPIDLoop = new TurningPID(
				Gains.Drive.DistTurn_P,
				Gains.Drive.DistTurn_I,
				Gains.Drive.DistTurn_D);
		turningPIDLoop.setTarget(startingPos.getAngle());
		
	}
	
	
	public static double encoderDistance(RobotPose pose){
		return (pose.getLeftDistance() + pose.getRightDistance()) / 2.0;
	}

	public static double encoderVelocity(RobotPose pose){
		return (pose.getLeftVelocity() + pose.getRightVelocity()) / 2.0;
	}


	@Override
	public DrivetrainOutput update(RobotPose pose) {
		controller.update(
				(pose.getLeftDistance() + pose.getRightDistance()) / 2.0,
				(pose.getLeftVelocity() + pose.getRightVelocity()) / 2.0);
		double power = controller.get();
		double turn = turningPIDLoop.calculate(pose.getAngle());
		
		Drivebase.grabInstance().setDrivePower(Drivebase.grabInstance().arcadeDriveMath(-power,turn));
		
		return new DrivetrainOutput(power+turn, power-turn);
	}
	
}
