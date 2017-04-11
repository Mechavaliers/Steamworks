package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.lib.DrivetrainOutput;

public class TurnTest extends AutoMode{


	
	@Override
	protected void sequence() throws AutonException {
		wait(1.0);
		drive.setTurnTarget(45, 50);
		waitForTurn(45, true, 2.0);
		drive.setDrivePower(new DrivetrainOutput(0.0,0.0));
		drive.resetEncoders();
		
		wait(0.5);
		
		drive.setDistanceTarget(10, 25);
		waitForDriveDistance(10,true,2);
		
		wait(0.5);
		
		drive.setTurnTarget(90, 50);
		waitForTurn(90, true, 2.0);
		drive.setDrivePower(new DrivetrainOutput(0.0,0.0));
		drive.resetEncoders();
		
		wait(0.5);
		
		drive.setDistanceTarget(10, 25);
		waitForDriveDistance(10,true,2);
		drive.resetEncoders();
		
		wait(0.5);
		
		drive.setDistanceTarget(-10, 25);
		waitForDriveDistance(-10,false,2);
		
		
		
		wait(0.5);
		
		drive.setTurnTarget(45, 50);
		waitForTurn(45, false, 2.0);
		drive.setDrivePower(new DrivetrainOutput(0.0,0.0));
		drive.resetEncoders();
		
		wait(0.5);
		
		drive.setTurnTarget(0, 50);
		waitForTurn(0, false, 2.0);
		drive.setDrivePower(new DrivetrainOutput(0.0,0.0));
		drive.resetEncoders();
		
		wait(0.5);
		
		drive.setDistanceTarget(-10, 25);
		waitForDriveDistance(-10,false,2);
	}

	@Override
	public void init() {
	}

}