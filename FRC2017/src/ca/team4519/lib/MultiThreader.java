package ca.team4519.lib;

import java.util.Vector;

public class MultiThreader implements Thread{

	private Threader threader;
	private Vector<Thread> threads = new Vector<Thread>();
	
	public MultiThreader(String name, double period){
		threader = new Threader(name, this, period);
		
	}
	
	public void controlLoops() {
		int i;
		for(i = 0; i< threads.size(); i++){
			Thread c = threads.elementAt(i);
			if(c != null){
				c.controlLoops();
			}
		}
		
	}
	
	public void start(){
		threader.start();
	}
	
	public void stop(){
		threader.stop();
	}
	
	public void addThread(Thread thread){
		threads.add(thread);
	}
}