package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.FRC2017.subsystems.GearBox.Gearage_State;

public class LeftGear extends AutoMode{
	protected double toPeg = crossBaseline + 40;
	
	@Override
	protected void sequence() throws AutonException {
		gear.closed();
		drive.setDistanceTarget(crossBaseline/2,  halfSpeed/3);
		waitForDriveDistance(crossBaseline/2, true, 2);
		drive.setTurnTarget(-pegAngle, 83.3);
		waitForTurn(-pegAngle, false, 10);
		drive.setDistanceTarget(toPeg/2, halfSpeed/3);
		waitForDriveDistance(toPeg/2, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(crossBaseline/2);
		waitForDriveDistance(crossBaseline/2, false, 1.5);
		gear.closed();
		drive.setTurnTarget(0);
		waitForTurn(0, true, 10);
		
	}

	@Override
	public void init() {
		gear.changeState(Gearage_State.CLOSED);
	}

}
