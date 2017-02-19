package ca.team4519.FRC2017.subsystems;

import ca.team4519.lib.Subsystem;

import edu.wpi.first.wpilibj.Joystick;

public class DualShock4 extends Subsystem {
	
	
	Joystick DS4 = new Joystick(5);
	

	
	
	
	public double getLeftStick_X(){
		return DS4.getRawAxis(0);
	}
	
	public double getLeftStick_Y(){
		return DS4.getRawAxis(1);
	}
	
	public boolean getLeftStick_Click(){
		return DS4.getRawButton(8);
	}
	
	public double getLeftTrigger(){
		return DS4.getRawAxis(2);
	}
	
	public boolean getLeftBumper(){
		return DS4.getRawButton(4);
	}
	
	public double getRightStick_X(){
		return DS4.getRawAxis(4);
	}
	
	public double getRightStick_Y(){
		return DS4.getRawAxis(5);
	}
	
	public boolean getRightStick_Click(){
		return DS4.getRawButton(9);
	}
	
	public double getRightTrigger(){
		return DS4.getRawAxis(3);
	}
	
	public boolean getRightBumper(){
		return DS4.getRawButton(5);
	}
	
	public boolean getTriangle(){
		return DS4.getRawButton(3);
		
	}
	
	public boolean getSquare(){
		return DS4.getRawButton(2);
	}
	
	public boolean getCircle(){
		return DS4.getRawButton(1);
	}
	
	public boolean getCross(){
		return DS4.getRawButton(0);
	}
	
	public boolean getShare(){
		return DS4.getRawButton(6);
	}
	
	public boolean getOptions(){
		return DS4.getRawButton(7);
	}
	
	public void rumbleLeft(double intensity){
		DS4.setRumble(Joystick.RumbleType.kLeftRumble, (float) intensity);
	}
	
	public void rumbleRight(double intensity){
		DS4.setRumble(Joystick.RumbleType.kRightRumble, (float) intensity);
	}
	
	public void rumbleOff(){
		DS4.setRumble(Joystick.RumbleType.kLeftRumble, 0.0f);
		DS4.setRumble(Joystick.RumbleType.kRightRumble, 0.0f);
	}
	
	@Override
  	public void resetSensors() {
	}

	@Override
	public void update() {
	}

	@Override
	public void disableSubsystem() {
		rumbleOff();
		
	}

}
