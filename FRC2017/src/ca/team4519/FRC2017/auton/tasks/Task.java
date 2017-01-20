package ca.team4519.FRC2017.auton.tasks;

public interface Task {

	public abstract boolean isTaskComplete();
	
	public abstract void exitTask();
	
	public abstract void output();
	
	public abstract void runTask();
	
}
