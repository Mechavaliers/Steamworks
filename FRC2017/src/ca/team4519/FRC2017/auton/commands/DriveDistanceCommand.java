package ca.team4519.FRC2017.auton.commands;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.lib.RobotPose;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCommand extends Command {

	double distance;
	
    public DriveDistanceCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivebase.grabInstance().setDistanceTarget(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	RobotPose position = Drivebase.grabInstance().getRobotPose();
    	double averagePos = (position.getLeftDistance() + position.getRightDistance()) / 2.0;
        return (isTimedOut())||(averagePos >= distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
