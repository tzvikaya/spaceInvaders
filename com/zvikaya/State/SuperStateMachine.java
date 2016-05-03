package com.zvikaya.State;

import java.awt.Canvas;
import java.awt.Graphics2D;

import com.zvikaya.gameScreen.GameScreen;

public abstract class SuperStateMachine {

	
	private StateMachine stateMachine;
	
	public SuperStateMachine(StateMachine stateMachine){
	//	SuperStateMachine game = new GameScreen(this);
		this.stateMachine = stateMachine;
	}
	
	abstract public void update(double delta );
	abstract public void draw(Graphics2D g);
	abstract public void init(Canvas canvas);
	
	public StateMachine getStateMachine() {
		return stateMachine;
	}
	
}
