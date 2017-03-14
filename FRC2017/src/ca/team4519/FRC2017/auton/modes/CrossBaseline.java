package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.FRC2017.subsystems.GearBox.Gearage_State;

public class CrossBaseline extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2)+20, halfSpeed);
		waitForDriveDistance((crossBaseline/2) +20, true, 5);
		//wait(0.5);
		//gear.open();
		//drive.setDistanceTarget(25);
		//waitForDriveDistance(50, true, 2);
		//gear.currentState(Gearage_State.OPEN);
		//gear.changeState(Gearage_State.OPEN);
	//	gear.open();
		
	}

	@Override
	public void init() {
		gear.closed();
		//gear.changeState(Gearage_State.CLOSED);
	}

}