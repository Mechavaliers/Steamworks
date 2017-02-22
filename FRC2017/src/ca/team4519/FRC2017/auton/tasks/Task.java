package ca.team4519.FRC2017.auton.tasks;

import ca.team4519.FRC2017.subsystems.Drivebase;

public abstract class Task {

	protected Drivebase drive = Drivebase.grabInstance();
	
	
	public abstract boolean isDone();
	
	public abstract void update();
	
	public abstract void done();
	
	public abstract void start();
	
}
