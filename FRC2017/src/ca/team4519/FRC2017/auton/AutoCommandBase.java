package ca.team4519.FRC2017.auton;

import ca.team4519.FRC2017.auton.tasks.Task;

public abstract class AutoCommandBase {

	protected boolean isRunning = false;
	protected double updateRate = 1.0 / 50.0;
	
	protected abstract void autoSequence() throws AutonFailure;	
	
	public void run() {
		isRunning = true;
		
		try {
			autoSequence();
		}catch (AutonFailure f) {
			System.out.println("Auto Failure...Exiting");
			return;
		}
		
		complete();
	}
	
	public void complete() {
		
	}
	
	public void quit() {
		isRunning = false;
	}
	
	public boolean runningWithException() throws AutonFailure {
		if(!isRunning){
			throw new AutonFailure();
		}
		return isRunning;
	}
	
	public void executetask(Task task) throws AutonFailure {
		task.runTask();
		while (runningWithException() && !task.isTaskComplete()) {
			task.output();
			long timeToWait = (long) (updateRate*1000);
			try {
				Thread.sleep(timeToWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		task.exitTask();
	}
	
	
	
}
