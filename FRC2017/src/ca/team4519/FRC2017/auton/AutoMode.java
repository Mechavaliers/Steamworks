package ca.team4519.FRC2017.auton;

import ca.team4519.FRC2017.auton.tasks.TimeoutTask;
import ca.team4519.FRC2017.auton.tasks.WaitForDistanceTask;
import ca.team4519.FRC2017.auton.tasks.WaitForTurnTask;
import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;
import ca.team4519.FRC2017.subsystems.Shooter;

public abstract class AutoMode extends BaseAutoMode {

	protected Drivebase drive = Drivebase.grabInstance();
	protected Shooter shooter  = Shooter.grabInstance();
	protected Hopper hopper = Hopper.grabInstance();
	protected GearBox gear = GearBox.grabInstance();
	
	public void wait(double seconds) throws AutonException {
		runTask(new TimeoutTask(seconds));
	}
	
	public void waitForDriveDistance(double distance, boolean direction, double seconds) throws AutonException {
		runTask(new WaitForDistanceTask(distance, direction, seconds));
	}

	public void waitForTurn(double angle, boolean direction, double seconds) throws AutonException {
		runTask(new WaitForTurnTask(angle, direction, seconds));
	}
}
