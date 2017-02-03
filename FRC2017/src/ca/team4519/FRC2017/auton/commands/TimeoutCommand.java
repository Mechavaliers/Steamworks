package ca.team4519.FRC2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TimeoutCommand extends Command {

	double startTime;
	double waitTime;
	
    public TimeoutCommand(double timeoutTime) {
        waitTime = timeoutTime;
    }

    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() >= startTime + waitTime;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
