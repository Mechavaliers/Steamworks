package ca.team4519.FRC2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
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
    		
    	}else if (blue){
    		
    	}else{
    		System.out.println("Failed to select Alliance... Auton Ending");
    	}
    	
    }
}
