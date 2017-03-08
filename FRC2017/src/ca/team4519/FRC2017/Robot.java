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
	
	MultiThreader autonLoop = new MultiThreader("100Hz", 1.0/100.0);
	MultiThreader fastLoop = new MultiThreader("200Hz" , 1.0/200.0);
	
	AutonRunner autonLoopRunner = new AutonRunner();
	
	SendableChooser<AutoMode> auton = new SendableChooser<AutoMode>();
	
	Joystick PS4 = new Joystick(0);
	Joystick OP = new Joystick(1);

	public void robotInit() {
       
		autonLoop.addThread(Drivebase.grabInstance());
		fastLoop.addThread(Shooter.grabInstance());
		
		auton.addObject("Red: Lane 1 Gear", new R1Gear());
		auton.addObject("Red: Lane 1 Gear + Hopper", new R1GearAndHopper());
		auton.addObject("Red: Lane 1 Gear + Boiler", new R1GearAndBoiler());
		auton.addObject("Red: Lane 1 Hopper + Boiler", new R1HopperAndBoiler());
		auton.addObject("Red: Lane 2 Gear -> Left", new R2GearGoLeft());
		auton.addObject("Red: Lane 2 Gear -> Right", new R2GearGoRight());
		auton.addObject("Red: Lane 3 Gear", new R3Gear());
		
		auton.addObject("Blue: Lane 1 Gear", new B1Gear());
		auton.addObject("Blue: Lane 2 Gear -> Left", new B2GearGoLeft());
		auton.addObject("Blue: Lane 2 Gear -> Right", new B2GearGoRight());
		auton.addObject("Blue: Lane 3 Gear", new B3Gear());
		auton.addObject("Blue: Lane 3 Gear + Hopper", new B3GearAndHopper());
		auton.addObject("Blue: Lane 3 Gear + Boiler", new B3GearAndBoiler());
		auton.addObject("Blue: Lane 3 Hopper + Boiler", new B3HopperAndBoiler());

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
    	
    	autonLoop.start();
    	mode.init();
    	autonLoopRunner.start();
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	Drivebase.grabInstance().getRobotPose();
    	Drivebase.grabInstance().resetSensors();
    	fastLoop.start();
    
    }
    
    public void teleopPeriodic() {

       Drivebase.grabInstance().setDrivePower(Drivebase.grabInstance().arcadeDriveMath(PS4.getRawAxis(1), PS4.getRawAxis(4), PS4.getRawButton(6)));
       Hopper.grabInstance().hopperControl(OP.getRawButton(1), OP.getRawButton(3));
       Climber.grabInstance().climb(OP.getRawButton(4), OP.getRawAxis(1));
       Shooter.grabInstance().toggleState(OP.getRawButton(1));
       GearBox.grabInstance().setDeg(PS4.getRawButton(4), PS4.getRawButton(1));
      

    }

    
    public void disabledInit() {
    	Drivebase.grabInstance().disableSubsystem();
    	Climber.grabInstance().disableSubsystem();
    	GearBox.grabInstance().disableSubsystem();
    	Hopper.grabInstance().disableSubsystem();
    	Shooter.grabInstance().disableSubsystem();
    	
    	autonLoop.stop();
    	fastLoop.stop();
    }
    
    public void allPeriodic() {
    	Drivebase.grabInstance().update();
    	Climber.grabInstance().update();
    	GearBox.grabInstance().update();
    	Hopper.grabInstance().update();
    	Shooter.grabInstance().update();
    }
}
