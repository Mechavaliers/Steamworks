package ca.team4519.lib;

public class Toggle {

	boolean canSwitch = false;
	boolean state = false;
	
	public Toggle(boolean forceToggle){
		
		if(!forceToggle){
			canSwitch = true;
		}else if(canSwitch){
			state = !state;
			canSwitch = false;
		}
	}
	
	public boolean getState(){
		return state;
	}

}