package ca.team4519.lib;

public abstract class Controller {
	protected boolean isEnabled = false;
	
	public abstract void reset();
	
	public abstract boolean onTarget();

}
