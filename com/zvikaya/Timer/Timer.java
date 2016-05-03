package com.zvikaya.Timer;

public class Timer {

	
	
	private long prevTime;
	
	

	public Timer(){
//		setCurrentTime(System.currentTimeMillis());
		setPrevTime(System.currentTimeMillis());
	}
//	public long getPrevTime() {
//		return prevTime;
//	}
//	public void setPrevTime(long currentTime) {
//		this.prevTime = currentTime;
//	}
	public boolean timeEvent(int timer){
		if(System.currentTimeMillis() - getPrevTime()>timer){
		//if(System.currentTimeMillis() - getCurrentTime()>timer){
			resetTimer();
			return true;
		}
		return false;
	}
//	
	public void resetTimer(){
		this.prevTime = System.currentTimeMillis();
//		prevTime =  System.currentTimeMillis();
	}
//	
//	
	public boolean isTimerReady(int timer){
		
		if(System.currentTimeMillis() - getPrevTime()>timer)
			return true;
		
		return false;
	}


	public long getPrevTime() {
		return prevTime;
	}


	public void setPrevTime(long currentTime) {
		this.prevTime = currentTime;
	}
}
