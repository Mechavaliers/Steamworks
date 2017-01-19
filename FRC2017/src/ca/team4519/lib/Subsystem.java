package ca.team4519.lib;

public abstract class Subsystem {

	public abstract void subsystemInit();
	
	public abstract void resetSensors();
	
	public abstract void update();
	
	public abstract void disableSubsystem();
	
}
