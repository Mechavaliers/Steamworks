package ca.team4519.FRC2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;

import ca.team4519.FRC2017.subsystems.Drivebase;
/**
 *
 */
public class WaitForTurnCommand extends Command {

	double angle;
	boolean positive = false;
	
    public WaitForTurnCommand(double angle, boolean positive, double seconds) {
    	super(seconds);
    	this.angle = angle;
    	this.positive = positive;
    }

    protected void initialize() {
    }

    protected void execute() {
    }
    
    protected boolean isFinished() {
    	double robotAngle = Drivebase.grabInstance().getRobotPose().getAngle();
        return isTimedOut() || positive ?  robotAngle > angle : robotAngle <= angle;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
