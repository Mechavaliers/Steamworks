package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.FRC2017.subsystems.GearBox.Gearage_State;

public class CenterGear extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, true, 2);
		drive.setDistanceTarget(toPeg, halfSpeed);
		waitForDriveDistance(toPeg, true, 2);
		gear.changeState(Gearage_State.EJECT);
		wait(0.5);
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, false, 1.5);
		gear.changeState(Gearage_State.CLOSED);
		drive.setTurnTarget(0);
		waitForTurn(0, false, 1);
		drive.setDistanceTarget(200);
		
		
		
		
	}

	@Override
	public void init() {
		gear.changeState(Gearage_State.CLOSED);
	}

}
