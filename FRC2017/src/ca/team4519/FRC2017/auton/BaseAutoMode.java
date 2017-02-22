package ca.team4519.FRC2017.auton;

import ca.team4519.FRC2017.auton.tasks.Task;

public abstract class BaseAutoMode {

	protected double loopRate = 1.0 / 100.0;
	protected boolean isRunning = false;
	
	protected abstract void sequence() throws AutonException;
	
	public abstract void init();
	
	public void run() {
		isRunning = true;
		try {
			sequence();
		}catch (AutonException e) {
			System.out.println("Auton has crashed, exiting");
			return;
		}
		System.out.println("Auton Sucessful");
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	public boolean isActiveWithGarbageCollection() throws AutonException {
		if(!isRunning()) {
			throw new AutonException();
		}
		return isRunning();
	}
	
	public void runTask(Task task) throws AutonException {
		isActiveWithGarbageCollection();
		task.start();
		while(isActiveWithGarbageCollection() && !task.isDone()) {
			task.update();
			try {
				Thread.sleep((long) (loopRate * 1000));
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		task.done();
	}
}
