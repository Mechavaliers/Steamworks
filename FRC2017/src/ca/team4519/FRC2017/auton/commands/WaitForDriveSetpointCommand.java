package ca.team4519.FRC2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.lib.RobotPose;

/**
 *
 */
public class WaitForDriveSetpointCommand extends Command {

	double setpoint;
	boolean forwards;
	
    public WaitForDriveSetpointCommand(double setpoint, boolean forwards, double seconds) {
    	super(seconds);
    	this.setpoint = setpoint;
    	this.forwards = forwards;
    }

    protected void initialize() {

    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	RobotPose position = Drivebase.grabInstance().getRobotPose();
    	double averagePos = (position.getLeftDistance() + position.getRightDistance()) / 2.0;
        return (isTimedOut())||(forwards ? averagePos >= setpoint : averagePos <= setpoint);
    	
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
