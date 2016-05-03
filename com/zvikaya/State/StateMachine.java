package com.zvikaya.State;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.zvikaya.gameScreen.GameScreen;
import com.zvikaya.menuScreen.MenuScreen;

public class StateMachine  {

	private ArrayList<SuperStateMachine> states = new ArrayList<SuperStateMachine>();
	private Canvas canvas;
	private byte selectState = 0;
	
	public StateMachine(Canvas canvas){
		SuperStateMachine game = new GameScreen(this);
		SuperStateMachine menu = new MenuScreen(this);
		states.add(menu);
		states.add(game);
		
		this.canvas = canvas;
	}
	
	
	public void draw(Graphics2D g){
		states.get(selectState).draw(g);
	}
//	
	public void update(double delta){
		states.get(selectState).update(delta);
	}
//	
	public void setState(byte k){
		for(int i =0; i < canvas.getKeyListeners().length;i++){
			canvas.removeKeyListener(canvas.getKeyListeners()[i]);
		}
		selectState = k;
		states.get(selectState).init(canvas);
	}


	public byte getStates() {
		return selectState;
	}
	
	
}
