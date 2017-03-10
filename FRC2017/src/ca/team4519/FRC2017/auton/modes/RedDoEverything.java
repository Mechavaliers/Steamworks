package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.FRC2017.subsystems.GearBox.Gearage_State;
import ca.team4519.FRC2017.subsystems.Shooter.Flywheel_State;

public class RedDoEverything extends AutoMode{
	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, true, 2);
		drive.setTurnTarget(pegAngle, 83.3);
		waitForTurn(pegAngle, true, 2);
		drive.setDistanceTarget(toPeg, halfSpeed);
		waitForDriveDistance(toPeg, true, 2);
		gear.changeState(Gearage_State.EJECT);
		wait(0.5);
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, false, 1.5);
		gear.changeState(Gearage_State.CLOSED);
		drive.setTurnTarget(90);
		waitForTurn(90, true, 1);
		drive.setDistanceTarget(0);
		waitForDriveDistance(0, false, 2);
		hopper.intakeMode();
		wait(2.0);
		drive.setDistanceTarget(10);
		waitForDriveDistance(10,true, 1);
		hopper.off();
		drive.setTurnTarget(180);
		waitForTurn(180, true, 1);
		drive.setDistanceTarget(50);
		waitForDriveDistance(50, true, 1.5);
		shooter.setState(Flywheel_State.ON);
		drive.setTurnTarget(225);
		waitForTurn(220, true, 1);
		drive.setDistanceTarget(20);
		waitForDriveDistance(20, true, 1);
		hopper.shootMode();
		wait(10.0);
		
		
		
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
