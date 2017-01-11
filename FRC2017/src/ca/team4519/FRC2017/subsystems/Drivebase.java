package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;

public class Drivebase {

	VictorSP leftDriveA;
	VictorSP leftDriveB;
	VictorSP rightDriveA;
	VictorSP rightDriveB;

	Encoder leftDriveEncodder;
	Encoder rightDriveEncoder;
	
	public void drivebaseInit() {
		leftDriveA = new VictorSP(Constants.leftDriveA);
		leftDriveB = new VictorSP(Constants.leftDriveB);
		rightDriveA = new VictorSP(Constants.rightDriveA);
		rightDriveB = new VictorSP(Constants.rightDriveB);
		
		leftDriveEncodder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, false);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, false);
	}
	
	public void resetState() {
		
	}
	
	
}
