package ca.team4519.FRC2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import ca.team4519.FRC2017.auton.commands.WaitForDriveSetpointCommand;
import ca.team4519.FRC2017.auton.commands.WaitForTurnCommand;
import ca.team4519.FRC2017.subsystems.Drivebase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;



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
    		Drivebase.grabInstance().setDistanceTarget(50);
    		new WaitForDriveSetpointCommand(50, true, 5);
    		Drivebase.grabInstance().setTurnTarget(45);
    		new WaitForTurnCommand(45, true, 3);
    		Drivebase.grabInstance().setDistanceTarget(25);
    		new WaitForDriveSetpointCommand(25, true, 5);
    		Drivebase.grabInstance().setReverseDistanceTarget(5);
    		new WaitForDriveSetpointCommand(5, false, 1);
    		Drivebase.grabInstance().setTurnTarget(10);
    	}else if (blue){
    		
    	}else{
    		System.out.println("Failed to select Alliance... Auton Ending");
    	}
    	
    }
}
