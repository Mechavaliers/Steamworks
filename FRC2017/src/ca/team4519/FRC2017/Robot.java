
package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.modes.*;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.Controller;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
  
	Command autoToExecute;	
	SendableChooser autoMode = new SendableChooser();
	
	
	Drivebase driveBase = new Drivebase();
	Shooter flywheel = new Shooter();
	Climber muscles = new Climber();
	GearBox gearDevice = new GearBox();
	Hopper ballHolder = new Hopper();
	Controller PS4 = new Controller();
	
	

	public void robotInit() {
       
		driveBase.subsystemInit();
    	
    	autoMode.addDefault("Do Nothing", null);
    	autoMode.addObject("Hang Gear Right", new HangGearRight());
    	autoMode.addObject("Hang Gear Center", new HangGearCenter());
    	autoMode.addObject("Hang Gear Left", new HangGearLeft());
    	autoMode.addObject("Red-Alliance: Gear+Shoot", new GearNShootRed());
    	autoMode.addObject("Blue-Alliance: Gear+Shoot", new GearNShootBlue());
    	SmartDashboard.putData("Autonomous Mode Selector", autoMode);
    }
    
    
    public void autonomousInit() {
    	
    	autoToExecute = (Command) autoMode.getSelected();
    	autoToExecute.start();
    }

    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	driveBase.resetSensors();
    }
    
    public void teleopPeriodic() {
        driveBase.arcadeDriveMath(PS4.getLeftStick_Y(), PS4.getRightStick_X());
    }

    
    public void disabledInit() {
    	driveBase.disableSubsystem();
    	flywheel.disableSubsystem();
    	muscles.disableSubsystem();
    	gearDevice.disableSubsystem();
    	ballHolder.disableSubsystem();
    }
    
    public void allPeriodic() {
    	driveBase.update();
    	flywheel.update();
    	muscles.update();
    	gearDevice.update();
    	ballHolder.update();
    }
}
