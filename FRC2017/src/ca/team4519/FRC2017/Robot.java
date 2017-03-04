package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonRunner;
import ca.team4519.FRC2017.auton.modes.*;
import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;
import ca.team4519.lib.MechaRobotBase;
import ca.team4519.lib.MultiThreader;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends MechaRobotBase{
	
	MultiThreader controlLoops = new MultiThreader("100Hz", 1.0/100.0);
	
	AutonRunner autonLoopRunner = new AutonRunner();
	
	SendableChooser<AutoMode> auton = new SendableChooser<AutoMode>();
	Joystick PS4 = new Joystick(0);
	Joystick OP = new Joystick(1);

	public void robotInit() {
       
		controlLoops.addThread(Drivebase.grabInstance());
		
		auton.addObject("Red: Lane 1 Gear", null);
		auton.addObject("Red: Lane 1 Gear + Hopper", null);
		auton.addObject("Red: Lane 1 Gear + Boiler", null);
		auton.addObject("Red: Lane 1 Hopper + Boiler", null);
		auton.addObject("Red: Lane 2 Gear -> Left", null);
		auton.addObject("Red: Lane 2 Gear -> Right", null);
		auton.addObject("Red: Lane 3 Gear", null);
		
		auton.addObject("Blue: Lane 1 Gear", null);
		auton.addObject("Blue: Lane 2 Gear -> Left", null);
		auton.addObject("Blue: Lane 2 Gear -> Right", null);
		auton.addObject("Blue: Lane 3 Gear", null);
		auton.addObject("Blue: Lane 3 Gear + Hopper", null);
		auton.addObject("Blue: Lane 3 Gear + Boiler", null);
		auton.addObject("Blue: Lane 3 Hopper + Boiler", null);

		auton.addDefault("Lane B Gear", new LaneBGear());
		auton.addObject("Lane B Go Right", new LaneBGoRight());
		auton.addObject("Lane B Gear: Left", new LaneBGearGoLeft());
		auton.addObject("Lane B Gear: Right", new LaneBGearGoRight());
    	SmartDashboard.putData("Selector 2.0", auton);
    	
    }
    
    

    public void autonomousInit() {
    	Drivebase.grabInstance().resetSensors();
    	
    	AutoMode mode = auton.getSelected();
    	
    	autonLoopRunner.selectAuto(mode);
    	
    	controlLoops.start();
    	mode.init();
    	autonLoopRunner.start();
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	Drivebase.grabInstance().getRobotPose();
    	Drivebase.grabInstance().resetSensors();
    	//controlLoops.start();
    
    }
    
    public void teleopPeriodic() {

       Drivebase.grabInstance().setDrivePower(Drivebase.grabInstance().arcadeDriveMath(PS4.getRawAxis(1), PS4.getRawAxis(4), PS4.getRawButton(6)));
       Hopper.grabInstance().hopperControl(OP.getRawButton(1), OP.getRawButton(3));
       Climber.grabInstance().climb(OP.getRawButton(4), OP.getRawAxis(1));
       Shooter.grabInstance().test(OP.getRawButton(1));
       GearBox.grabInstance().setDeg(PS4.getRawButton(4), PS4.getRawButton(1));
      

    }

    
    public void disabledInit() {
    	controlLoops.stop();
    	Drivebase.grabInstance().disableSubsystem();
    	Climber.grabInstance().disableSubsystem();
    	GearBox.grabInstance().disableSubsystem();
    	Hopper.grabInstance().disableSubsystem();
    	Shooter.grabInstance().disableSubsystem();
    }
    
    public void allPeriodic() {
    	Drivebase.grabInstance().update();
    	Climber.grabInstance().update();
    	GearBox.grabInstance().update();
    	Hopper.grabInstance().update();
    	Shooter.grabInstance().update();
    }
}
