package com.zvikaya.Timer;

public class TicksTimer {

	
	private float tick, tickTarget;
	
	public TicksTimer(float tickTarget){
		this.tickTarget = tickTarget;
		this.tick = 0;
	}
	
	public void tick(double delta){
		if(tick <= tickTarget){
			tick+= 1*delta;
			
		}
	}
	
	
	public boolean isEventReady(){
		if(tick >= tickTarget){
			return true;
		}
		return false;
	}
	private void resetTimer(){
		tick=0;
	}
	
	
	
	
	
	
	
}
