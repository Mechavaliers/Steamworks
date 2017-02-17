package ca.team4519.FRC2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import ca.team4519.FRC2017.auton.commands.TimeoutCommand;
import ca.team4519.FRC2017.auton.commands.WaitForDriveSetpointCommand;
import ca.team4519.FRC2017.auton.commands.WaitForTurnCommand;
import ca.team4519.FRC2017.subsystems.Drivebase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;


/**
 *
 */
public class LaneAGear extends CommandGroup {

	DriverStation ds;
	Alliance alliance = ds.getAlliance();
	boolean red = false;
	boolean blue = false;
	
	public void getAlliance(){
		switch(alliance){
		case Blue:
			blue = true;
		case Red:
			red = true;
		default:
			break;
    	
    	}
	}
	
    public LaneAGear() {
    	getAlliance();
    	
    	if(red){
    		System.out.println("Red Alliance Selected... Auton Beginning");
    		//Drive towards baseline
    		Drivebase.grabInstance().setDistanceTarget(24);
    		new WaitForDriveSetpointCommand(25, true, 5);
    		
    		//turn towards peg
    		Drivebase.grabInstance().setTurnTarget(25);
    		new WaitForTurnCommand(24, true, 3);
    		
    		//hit peg
    		Drivebase.grabInstance().resetEncoders();
    		Drivebase.grabInstance().setDistanceTarget(10);
    		new WaitForDriveSetpointCommand(10, true, 5);
    		new TimeoutCommand(2);
    		
    		//back away
    		Drivebase.grabInstance().setReverseDistanceTarget(10);
    		new WaitForDriveSetpointCommand(10, true, 5);
    		
    	}else if (blue){
    		System.out.println("Blue Alliance Selected... Auton Beginning");
    		//Drive to baseline
    		Drivebase.grabInstance().setDistanceTarget(24);
    		new WaitForDriveSetpointCommand(25, true, 5);
    		
    		//face the peg
    		Drivebase.grabInstance().setTurnTarget(25);
    		new WaitForTurnCommand(24, true, 3);
    		
    		//hit the peg
    		Drivebase.grabInstance().resetEncoders();
    		Drivebase.grabInstance().setDistanceTarget(10);
    		new WaitForDriveSetpointCommand(10, true, 5);
    		new TimeoutCommand(2);
    		
    		//back to baseline
    		Drivebase.grabInstance().setReverseDistanceTarget(10);
    		new WaitForDriveSetpointCommand(10, true, 5);
    		
    		//turn to hopper
    		Drivebase.grabInstance().setTurnTarget(90);
    		new WaitForTurnCommand(90, true, 2);
    		
    		//Flyy to the hopper
    		Drivebase.grabInstance().resetEncoders();
    		Drivebase.grabInstance().setReverseDistanceTarget(50);
    		new WaitForDriveSetpointCommand(50, true, 3);
    		
    	}else{
    		System.out.println("Failed to select Alliance... Auton Ending");
    	}
    	
    }
}
