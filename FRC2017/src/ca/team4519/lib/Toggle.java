package ca.team4519.lib;

public class Toggle {

	boolean canSwitch = false;
	boolean state = false;
	
	public boolean flipToggle(boolean forceToggle){
		
		if(!forceToggle){
			canSwitch = true;
		}else if(canSwitch){
			state = !state;
			canSwitch = false;
		}
		return state;
	}
	
	public boolean getState(){
		return state;
	}

}